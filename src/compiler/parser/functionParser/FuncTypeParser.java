package compiler.parser.functionParser;

import enums.LexType;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.function.funcType.FuncType;
import struct.syntaxTree.function.funcType.FuncTypeEle;
import struct.syntaxTree.function.funcType.FuncTypeInt;
import struct.syntaxTree.function.funcType.FuncTypeVoid;

public class FuncTypeParser {
    /* FuncType Attributes */
    private FuncTypeEle funcTypeEle = null;

    /**
     * FuncType =  FuncTypeEle = 'void' | 'int'
     * @return {@link FuncType}
     */
    public FuncType parseFuncType() {
        Token token = TLIterator.readNext();
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
