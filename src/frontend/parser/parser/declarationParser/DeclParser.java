package frontend.parser.parser.declarationParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.parser.declarationParser.constantParser.ConstDeclParser;
import frontend.parser.parser.declarationParser.variableParser.VarDeclParser;
import frontend.parser.struct.declaration.Decl;
import frontend.parser.struct.declaration.DeclEle;

public class DeclParser {
    private TokenListIterator iterator;

    public DeclParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    /**
     * CONSTTK INTTK || INTTK
     * @return {@link Decl}
     */
    public Decl parseDecl() {
        Token first = this.iterator.readNextToken();
        this.iterator.unReadToken(1);
        DeclEle declEle = null;
        if (first.getType().equals(LexType.CONSTTK)) {
            /* CONSTTK INTTK -> */
            ConstDeclParser constDeclParser = new ConstDeclParser(this.iterator);
            declEle = constDeclParser.parseConstDecl();
        } else if (first.getType().equals(LexType.INTTK)) {
            /* INTTK -> */
            VarDeclParser varDeclParser = new VarDeclParser(this.iterator);
            declEle = varDeclParser.parseVarDecl();
        } else {
            /* ERROR */
            System.out.println("READ UNEXPECTED TOKEN ");
        }
        return new Decl(declEle);
    }
}
