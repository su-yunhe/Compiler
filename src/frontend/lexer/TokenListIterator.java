package frontend.lexer;

import java.util.ListIterator;

public class TokenListIterator {
    private TokenList tokenList;
    private ListIterator<Token> iterator;
    private Token last;

    public TokenListIterator(TokenList tokenList) {
        this.tokenList = tokenList;
        this.iterator = this.tokenList.getTokenList().listIterator();
    }

    public ListIterator<Token> getIterator() {
        return iterator;
    }

    /**
     * 返回 TokenList 中的下一个元素并前进光标位置。
     * @return {@link Token}
     */
    public Token readNextToken() {
        return last = this.iterator.next();
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public void unReadToken(int k) {
        int cnt = k;
        while (cnt > 0) {
            cnt--;
            if (this.iterator.hasPrevious()) {
                last = this.iterator.previous();
            } else {
                break;
            }
        }
    }
}
