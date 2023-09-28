package frontend.parser.parser.expressionParser.unaryExpParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.parser.expressionParser.FuncRParamsParser;
import frontend.parser.parser.terminalParser.IdentParser;
import frontend.parser.struct.expression.FuncRParams;
import frontend.parser.struct.expression.unaryExp.UnaryExpFunc;
import frontend.parser.struct.terminal.Ident;

public class UnaryExpFuncParser {
    private TokenListIterator iterator;
    /* UnaryExpFunc Attributes */
    private Ident ident = null;
    private FuncRParams funcRParams = null;
    private Token leftParent; // '('
    private Token rightParent; // ')'
    private UnaryExpFunc unaryExpFunc = null;

    public UnaryExpFuncParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    /**
     * UnaryExpFunc = Ident '(' [FuncRParams] ')'
     * @return {@link UnaryExpFunc}
     */
    public UnaryExpFunc parseUnaryFuncExp() {
        /* Ident */
        IdentParser identParser = new IdentParser(this.iterator);
        this.ident = identParser.parseIdent();
        /* '(' */
        this.leftParent = this.iterator.readNextToken();
        this.rightParent = this.iterator.readNextToken();
        if (!this.rightParent.getType().equals(LexType.RPARENT)) {
            this.iterator.unReadToken(1);
            /* FuncRParams */
            FuncRParamsParser funcRParamsParser = new FuncRParamsParser(this.iterator);
            this.funcRParams = funcRParamsParser.parseFuncRParams();
            this.rightParent = this.iterator.readNextToken();
            unaryExpFunc = new UnaryExpFunc(this.ident, this.funcRParams,
                    this.leftParent, this.rightParent);
        } else {
            unaryExpFunc = new UnaryExpFunc(this.ident, this.leftParent, this.rightParent);
        }
        return unaryExpFunc;
    }
}
