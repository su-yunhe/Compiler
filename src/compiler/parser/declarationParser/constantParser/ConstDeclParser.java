package compiler.parser.declarationParser.constantParser;

import eumes.LexType;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.declaration.BType;
import struct.syntaxTree.declaration.constant.ConstDecl;
import struct.syntaxTree.declaration.constant.ConstDef;
import utils.ErrorUtils;

import java.util.ArrayList;

public class ConstDeclParser {
    /* ConstDecl Attributes */
    private Token constTk = null; // 'const'
    private BType btype = null;
    private ConstDef first = null;
    private ArrayList<Token> commas = new ArrayList<>();
    private ArrayList<ConstDef> constDefs = new ArrayList<>();
    private Token semicn = null; // ';'
    
    public ConstDecl parseConstDecl() {
        commas = new ArrayList<>();
        constDefs = new ArrayList<>();
        Token token = TLIterator.readNext(); // const
        if (token.getType().equals(LexType.CONSTTK)) {
            constTk = token;
        } else {
            System.out.println("ERROR: expect CONSTTK but not found in ConstDeclParser!");
        }
        btype = new BTypeParser().parseBtype();
        first = new ConstDefParser().parseConstDef();
        token = TLIterator.readNext();
        while (token.getType().equals(LexType.COMMA)) {
            /* is ',' */
            commas.add(token);
            constDefs.add(new ConstDefParser().parseConstDef());
            token = TLIterator.readNext();
        }
        semicn = token;
        // TODO: 处理i类错误：缺失`;`
        handleIError();
        return new ConstDecl(constTk, btype, first, commas, constDefs, semicn);
    }

    /**
     * 处理i类错误：缺失`;`
     */
    private void handleIError() {
        if (!semicn.getType().equals(LexType.SEMICN)) {
            TLIterator.unRead(2);
            Token prev = TLIterator.readNext();
            ErrorUtils.handleI(prev);
        }
    }
}
