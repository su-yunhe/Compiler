package compiler.parser.expressionParser.unaryExpParser;

import enums.LexType;
import compiler.parser.expressionParser.primaryExpParser.PrimaryExpParser;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.expression.unaryExp.UnaryExp;
import struct.syntaxTree.expression.unaryExp.UnaryExpEle;

public class UnaryExpParser {
    /* UnaryExp Attributes */
    private UnaryExpEle unaryExpEle = null;
    /**
     * UnaryExp → UnaryExpEle = PrimaryExp | Ident '(' [FuncRParams] ')' | UnaryOp UnaryExp
     * @return {@link UnaryExp}
     */
    public UnaryExp parseUnaryExp() {
        Token first = TLIterator.readNext();
        Token second = TLIterator.readNext();
        TLIterator.unRead(2);
        if (isUnaryExpFuncFirst(first, second)) {
            /* Ident '(' [FuncRParams] ')' */
            unaryExpEle = new UnaryExpFuncParser().parseUnaryFuncExp();
        } else if (isPrimaryExpFirst(first)) {
            /* PrimaryExp */
            unaryExpEle = new PrimaryExpParser().parsePrimaryExp();
        } else if (isUnaryExpOpFirst(first)) {
            /* UnaryOp UnaryExp */
            unaryExpEle = new UnaryExpOpParser().parseUnaryExpOp();
        } else {
            System.out.println("ERROR: unexpected token in UnaryExpParser!");
        }
        return new UnaryExp(unaryExpEle);
    }

    /**
     * FIRST = {'(', Ident, '['}
     * @param first FIRST
     * @return boolean
     */
    private boolean isPrimaryExpFirst(Token first) {
        return first.getType().equals(LexType.LPARENT) ||
                first.getType().equals(LexType.IDENFR) ||
                first.getType().equals(LexType.INTCON);
    }

    /**
     * FIRST = {Ident '('}
     * @param first FIRST
     * @param second FIRST
     * @return boolean
     */
    private boolean isUnaryExpFuncFirst(Token first, Token second) {
        return first.getType().equals(LexType.IDENFR) &&
                second.getType().equals(LexType.LPARENT);
    }

    /**
     * FIRST = {'+', '-', '!'}
     * @param first FIRST
     * @return boolean
     */
    private boolean isUnaryExpOpFirst(Token first) {
        return first.getType().equals(LexType.PLUS) ||
                first.getType().equals(LexType.MINU) ||
                first.getType().equals(LexType.NOT);
    }
}
