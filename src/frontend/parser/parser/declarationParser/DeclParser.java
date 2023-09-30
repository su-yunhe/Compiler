package frontend.parser.parser.declarationParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.parser.declarationParser.constantParser.ConstDeclParser;
import frontend.parser.parser.declarationParser.variableParser.VarDeclParser;
import frontend.parser.struct.declaration.Decl;
import frontend.parser.struct.declaration.DeclEle;

public class DeclParser {
    
    /**
     * CONSTTK INTTK || INTTK
     * @return {@link Decl}
     */
    public Decl parseDecl() {
        Token first = TLIterator.readNextToken();
        TLIterator.unReadToken(1);
        DeclEle declEle = null;
        if (first.getType().equals(LexType.CONSTTK)) {
            /* const INTTK -> */
            declEle = new ConstDeclParser().parseConstDecl();
        } else if (first.getType().equals(LexType.INTTK)) {
            /* INTTK -> */
            declEle = new VarDeclParser().parseVarDecl();
        } else {
            /* ERROR */
            System.out.println("read unexpected token in DeclParser!");
        }
        return new Decl(declEle);
    }
}
