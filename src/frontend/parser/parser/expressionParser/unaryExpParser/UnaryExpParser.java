package frontend.parser.parser.expressionParser.unaryExpParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.parser.expressionParser.primaryExpParser.PrimaryExpParser;
import frontend.parser.struct.expression.unaryExp.UnaryExp;
import frontend.parser.struct.expression.unaryExp.UnaryExpEle;

public class UnaryExpParser {
    private TokenListIterator iterator;
    /* UnaryExp Attributes */
    private UnaryExpEle unaryExpEle = null;

    public UnaryExpParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    /**
     * UnaryExp → UnaryExpEle = PrimaryExp | Ident '(' [FuncRParams] ')' | UnaryOp UnaryExp
     * @return {@link UnaryExp}
     */
    public UnaryExp parseUnaryExp() {
        Token first = this.iterator.readNextToken();
        Token second = this.iterator.readNextToken();
        if (isIdentFirst(first, second)) {
            /* Ident '(' [FuncRParams] ')' */
            this.iterator.unReadToken(2);
            UnaryExpFuncParser unaryExpFuncParser = new UnaryExpFuncParser(this.iterator);
            this.unaryExpEle = unaryExpFuncParser.parseUnaryFuncExp();
        } else if (isPrimaryExpFirst(first)) {
            /* PrimaryExp */
            this.iterator.unReadToken(2);
            PrimaryExpParser primaryExpParser = new PrimaryExpParser(this.iterator);
            this.unaryExpEle = primaryExpParser.parsePrimaryExp();
        } else if (isUnaryFirst(first)) {
            /* UnaryOp UnaryExp */
            this.iterator.unReadToken(2);
            UnaryExpOpParser unaryExpOpParser = new UnaryExpOpParser(this.iterator);
            this.unaryExpEle = unaryExpOpParser.parseUnaryExpOp();
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
