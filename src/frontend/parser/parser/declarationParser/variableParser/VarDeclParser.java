package frontend.parser.parser.declarationParser.variableParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.parser.declarationParser.constantParser.BTypeParser;
import frontend.parser.struct.declaration.BType;
import frontend.parser.struct.declaration.variable.VarDecl;
import frontend.parser.struct.declaration.variable.varDef.VarDef;

import java.util.ArrayList;

public class VarDeclParser {
    private TokenListIterator iterator;
    /* VarDecl Attributes */
    private BType btype = null;
    private VarDef first = null;
    private ArrayList<Token> commas = new ArrayList<>(); // ','
    private ArrayList<VarDef> varDefs = new ArrayList<>();
    private Token semicn; // ';'

    public VarDeclParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    public VarDecl parseVarDecl() {
        this.commas = new ArrayList<>();
        this.varDefs = new ArrayList<>();
        BTypeParser btypeparser = new BTypeParser(this.iterator);
        this.btype = btypeparser.parseBtype();
        VarDefParser varDefParser = new VarDefParser(this.iterator);
        this.first = varDefParser.parseVarDef();
        Token token = this.iterator.readNextToken();
        while (token.getType().equals(LexType.COMMA)) { // ','
            this.commas.add(token);
            this.varDefs.add(varDefParser.parseVarDef());
            token = this.iterator.readNextToken();
        }
        this.semicn = token;
        return new VarDecl(this.btype, this.first,
                this.commas, this.varDefs, this.semicn);
    }
}
