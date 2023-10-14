package compiler.parser.declarationParser.constantParser;

import eumes.LexType;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.declaration.BType;

public class BTypeParser {
    /**
     * BType → 'int'
     * @return {@link BType}
     */
    public BType parseBtype() {
        Token first = TLIterator.readNext();
        if (!first.getType().equals(LexType.INTTK)) {
            System.out.println("ERROR: expect INTTK but not found in BTypeParser!");
        }
        return new BType(first);
    }
}
