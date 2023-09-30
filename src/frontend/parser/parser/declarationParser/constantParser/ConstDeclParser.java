package frontend.parser.parser.declarationParser.constantParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.struct.declaration.BType;
import frontend.parser.struct.declaration.constant.ConstDecl;
import frontend.parser.struct.declaration.constant.ConstDef;

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
        Token token = TLIterator.readNextToken(); // const
        if (token.getType().equals(LexType.CONSTTK)) {
            constTk = token;
        } else {
            System.out.println("ERROR: expect CONSTTK but not found in ConstDeclParser!");
        }
        btype = new BTypeParser().parseBtype();
        first = new ConstDefParser().parseConstDef();
        token = TLIterator.readNextToken();
        while (token.getType().equals(LexType.COMMA)) {
            /* is ',' */
            commas.add(token);
            constDefs.add(new ConstDefParser().parseConstDef());
            token = TLIterator.readNextToken();
        }
        /* token SHOULD be ';' */
        semicn = token;
        return new ConstDecl(constTk, btype, first, commas, constDefs, semicn);
    }
}
