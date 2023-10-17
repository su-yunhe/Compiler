package compiler.parser.declarationParser.variableParser;

import enums.LexType;
import struct.token.Token;
import utils.TLIterator;
import compiler.parser.declarationParser.constantParser.BTypeParser;
import struct.syntaxTree.declaration.BType;
import struct.syntaxTree.declaration.variable.VarDecl;
import struct.syntaxTree.declaration.variable.varDef.VarDef;
import utils.ErrorUtils;

import java.util.ArrayList;

public class VarDeclParser {
    /* VarDecl Attributes */
    private BType btype = null;
    private VarDef first = null;
    private ArrayList<Token> commas = new ArrayList<>(); // ','
    private ArrayList<VarDef> varDefs = new ArrayList<>();
    private Token semicn; // ';'

    /**
     * VarDecl → BType VarDef { ',' VarDef } ';'
     * @return {@link VarDecl}
     */
    public VarDecl parseVarDecl() {
        commas = new ArrayList<>();
        varDefs = new ArrayList<>();

        btype = new BTypeParser().parseBtype();
        first = new VarDefParser().parseVarDef();
        Token token = TLIterator.readNext();
        while (token.getType().equals(LexType.COMMA)) { // ','
            commas.add(token);
            varDefs.add(new VarDefParser().parseVarDef());
            token = TLIterator.readNext();
        }
        semicn = token;
        // TODO: 处理i类错误：缺失`;`
        handleIError();
        return new VarDecl(btype, first, commas, varDefs, semicn);
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
