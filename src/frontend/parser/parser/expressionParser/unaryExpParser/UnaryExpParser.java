package frontend.parser.parser.expressionParser.unaryExpParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.parser.expressionParser.primaryExpParser.PrimaryExpParser;
import frontend.parser.struct.expression.unaryExp.UnaryExp;
import frontend.parser.struct.expression.unaryExp.UnaryExpEle;

public class UnaryExpParser {
    /* UnaryExp Attributes */
    private UnaryExpEle unaryExpEle = null;
    /**
     * UnaryExp → UnaryExpEle = PrimaryExp | Ident '(' [FuncRParams] ')' | UnaryOp UnaryExp
     * @return {@link UnaryExp}
     */
    public UnaryExp parseUnaryExp() {
        Token first = TLIterator.readNextToken();
        Token second = TLIterator.readNextToken();
        TLIterator.unReadToken(2);
        if (isIdentFirst(first, second)) {
            /* Ident '(' [FuncRParams] ')' */
            unaryExpEle = new UnaryExpFuncParser().parseUnaryFuncExp();
        } else if (isPrimaryExpFirst(first)) {
            /* PrimaryExp */
            unaryExpEle = new PrimaryExpParser().parsePrimaryExp();
        } else if (isUnaryFirst(first)) {
            /* UnaryOp UnaryExp */
            unaryExpEle = new UnaryExpOpParser().parseUnaryExpOp();
        } else {
            System.out.println("ERROR: unexpected token in UnaryExpParser!");
        }
        return new UnaryExp(this.unaryExpEle);
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
    private boolean isIdentFirst(Token first, Token second) {
        return first.getType().equals(LexType.IDENFR) &&
                second.getType().equals(LexType.LPARENT);
    }

    /**
     * FIRST = {'+', '-', '!'}
     * @param first FIRST
     * @return boolean
     */
    private boolean isUnaryFirst(Token first) {
        return first.getType().equals(LexType.PLUS) ||
                first.getType().equals(LexType.MINU) ||
                first.getType().equals(LexType.NOT);
    }
}
