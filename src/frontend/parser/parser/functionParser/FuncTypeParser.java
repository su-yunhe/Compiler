package frontend.parser.parser.functionParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.struct.function.funcType.FuncType;
import frontend.parser.struct.function.funcType.FuncTypeEle;
import frontend.parser.struct.function.funcType.FuncTypeInt;
import frontend.parser.struct.function.funcType.FuncTypeVoid;

public class FuncTypeParser {
    private TokenListIterator iterator;
    /* FuncType Attributes */
    private FuncTypeEle funcTypeEle = null;

    public FuncTypeParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    /**
     * FuncType =  FuncTypeEle = 'void' | 'int'
     * @return {@link FuncType}
     */
    public FuncType parseFuncType() {
        Token token = this.iterator.readNextToken();
        if (token.getType().equals(LexType.VOIDTK)) {
            /* void */
            this.funcTypeEle = new FuncTypeVoid(token);
        } else if (token.getType().equals(LexType.INTTK)) {
            /* int */
            this.funcTypeEle = new FuncTypeInt(token);
        } else {
            System.out.println("EXPECT VOID OR INT HERE");
        }
        return new FuncType(this.funcTypeEle);
    }
}
