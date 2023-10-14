package compiler.parser.declarationParser;

import eumes.LexType;
import compiler.parser.declarationParser.constantParser.ConstDeclParser;
import compiler.parser.declarationParser.variableParser.VarDeclParser;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.declaration.Decl;
import struct.syntaxTree.declaration.DeclEle;

public class DeclParser {
    
    /**
     * CONSTTK INTTK || INTTK
     * @return {@link Decl}
     */
    public Decl parseDecl() {
        Token first = TLIterator.readNext();
        TLIterator.unRead(1);
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
