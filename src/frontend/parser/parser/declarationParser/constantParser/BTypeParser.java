package frontend.parser.parser.declarationParser.constantParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.struct.declaration.BType;

public class BTypeParser {
    private TokenListIterator iterator;

    public BTypeParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    /**
     * BType → 'int'
     * @return {@link BType}
     */
    public BType parseBtype() {
        Token first = this.iterator.readNextToken();
        if (!first.getType().equals(LexType.INTTK)) {
            System.out.println("ERROR : EXPECT INTTK");
        }
        BType btype = new BType(first);
        return btype;
    }
}
