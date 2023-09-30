package frontend.parser;

import frontend.lexer.Token;
import frontend.lexer.TokenList;

import java.util.ListIterator;

public class TLIterator {
    private static ListIterator<Token> iterator;

    public static void initTokenListIterator() {
        iterator = TokenList.getTokenList().listIterator();
    }

    public static ListIterator<Token> getIterator() {
        return iterator;
    }

    /**
     * 返回 TokenList 中的下一个元素并前进光标位置。
     * @return {@link Token}
     */
    public static Token readNextToken() {
        return iterator.next();
    }

    public static boolean hasNext() {
        return iterator.hasNext();
    }

    public static void unReadToken(int k) {
        int count = Math.min(k, iterator.nextIndex());
        for (int i = 0; i < count; i++) {
            iterator.previous();
        }
    }
}
