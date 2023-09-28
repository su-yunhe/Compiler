package frontend.parser.parser.declarationParser.constantParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.struct.declaration.BType;
import frontend.parser.struct.declaration.constant.ConstDecl;
import frontend.parser.struct.declaration.constant.ConstDef;

import java.util.ArrayList;

public class ConstDeclParser {
    private TokenListIterator iterator;
    /* ConstDecl Attributes */
    private Token constTk = null; // 'const'
    private BType btype = null;
    private ConstDef first = null;
    private ArrayList<Token> commas = new ArrayList<>();
    private ArrayList<ConstDef> constDefs = new ArrayList<>();
    private Token semicn = null; // ';'

    public ConstDeclParser(TokenListIterator iterator) {
        this.iterator =  iterator;
    }

    public ConstDecl parseConstDecl() {
        this.commas = new ArrayList<>();
        this.constDefs = new ArrayList<>();
        Token token = this.iterator.readNextToken(); // SHOULD be CONST
        /* MAY need handle error */
        if (token.getType().equals(LexType.CONSTTK)) {
            constTk = token;
        } else {
            System.out.println("ERROR : EXPECT CONSTTK");
        }
        BTypeParser btypeParser = new BTypeParser(this.iterator);
        btype = btypeParser.parseBtype();
        ConstDefParser constDefParser = new ConstDefParser(this.iterator);
        first = constDefParser.parseConstDef();
        token = this.iterator.readNextToken();
        while (token.getType().equals(LexType.COMMA)) {
            /* is ',' */
            this.commas.add(token);
            this.constDefs.add(constDefParser.parseConstDef());
            token = this.iterator.readNextToken();
        }
        /* token SHOULD be ';' */
        this.semicn = token;
        ConstDecl constDecl = new ConstDecl(this.constTk, this.btype,
                this.first, this.commas, this.constDefs, this.semicn);
        return constDecl;
    }
}
