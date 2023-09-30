package frontend.parser.parser.declarationParser.variableParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.parser.declarationParser.constantParser.BTypeParser;
import frontend.parser.struct.declaration.BType;
import frontend.parser.struct.declaration.variable.VarDecl;
import frontend.parser.struct.declaration.variable.varDef.VarDef;

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
        Token token = TLIterator.readNextToken();
        while (token.getType().equals(LexType.COMMA)) { // ','
            commas.add(token);
            varDefs.add(new VarDefParser().parseVarDef());
            token = TLIterator.readNextToken();
        }
        semicn = token;
        return new VarDecl(btype, first, commas, varDefs, semicn);
    }
}
