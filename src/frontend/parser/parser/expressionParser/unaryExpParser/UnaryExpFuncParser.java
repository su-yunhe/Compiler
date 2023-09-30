package frontend.parser.parser.expressionParser.unaryExpParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.parser.expressionParser.FuncRParamsParser;
import frontend.parser.parser.terminalParser.IdentParser;
import frontend.parser.struct.expression.FuncRParams;
import frontend.parser.struct.expression.unaryExp.UnaryExpFunc;
import frontend.parser.struct.terminal.Ident;

public class UnaryExpFuncParser {
    /* UnaryExpFunc Attributes */
    private Ident ident = null;
    private FuncRParams funcRParams = null;
    private Token leftParent; // '('
    private Token rightParent; // ')'
    private UnaryExpFunc unaryExpFunc = null;
    /**
     * UnaryExpFunc = Ident '(' [FuncRParams] ')'
     * @return {@link UnaryExpFunc}
     */
    public UnaryExpFunc parseUnaryFuncExp() {
        /* Ident */
        ident = new IdentParser().parseIdent();
        /* '(' */
        leftParent = TLIterator.readNextToken();
        rightParent = TLIterator.readNextToken();
        if (!rightParent.getType().equals(LexType.RPARENT)) {
            TLIterator.unReadToken(1);
            /* FuncRParams */
            funcRParams = new FuncRParamsParser().parseFuncRParams();
            rightParent = TLIterator.readNextToken();
            unaryExpFunc = new UnaryExpFunc(this.ident, this.funcRParams,
                    this.leftParent, this.rightParent);
        } else {
            unaryExpFunc = new UnaryExpFunc(this.ident, this.leftParent, this.rightParent);
        }
        return unaryExpFunc;
    }
}
