package frontend.parser.parser.functionParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.struct.function.funcType.FuncType;
import frontend.parser.struct.function.funcType.FuncTypeEle;
import frontend.parser.struct.function.funcType.FuncTypeInt;
import frontend.parser.struct.function.funcType.FuncTypeVoid;

public class FuncTypeParser {
    /* FuncType Attributes */
    private FuncTypeEle funcTypeEle = null;

    /**
     * FuncType =  FuncTypeEle = 'void' | 'int'
     * @return {@link FuncType}
     */
    public FuncType parseFuncType() {
        Token token = TLIterator.readNextToken();
        if (token.getType().equals(LexType.VOIDTK)) {
            /* void */
            funcTypeEle = new FuncTypeVoid(token);
        } else if (token.getType().equals(LexType.INTTK)) {
            /* int */
            funcTypeEle = new FuncTypeInt(token);
        } else {
            System.out.println("EXPECT VOID OR INT HERE");
        }
        return new FuncType(funcTypeEle);
    }
}
