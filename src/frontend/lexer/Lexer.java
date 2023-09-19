package frontend.lexer;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 词法成分分析器【单例模式】
 * 功能：构建从源代码中识别出的所有有效 Token 所组成的 TokenList 和 TokenMap
 * @author SYH
 * @date 2023/09/18
 */
public class Lexer {
    private static Lexer lexerInstance;
    private GeneralLexerTool generalLexerTool;
    private TokenList tokenList;

    /**
     * 私有构造函数
     * @param source 输入的源程序
     */
    private Lexer(FileInputStream source) {
        this.generalLexerTool = GeneralLexerTool.getInstance(source);
        this.tokenList = new TokenList();
        this.tokenize();
    }

    /**
     * 获取 Lexer 实例
     * @param source 输入的源程序
     * @return {@link Lexer} Lexer实例
     */
    public static Lexer getInstance(FileInputStream source) {
        if (lexerInstance == null) {
            lexerInstance = new Lexer(source);
        }
        return lexerInstance;
    }


    private void tokenize() {
        while (!this.generalLexerTool.isEndOfFile()) {
            // 首先跳过空白符
            this.generalLexerTool.skipWhiteSpace();
            // 判断跳过注释的情况
            if (skipComment()) {
                continue;
            }
            next();
        }
    }

    /**
     * 跳过注释
     *
     * @return boolean 是否成功
     */
    private boolean skipComment() {
        // 单行注释
        String lineComment = "//";
        String blockCommentSt = "/*";
        if (lineComment.equals(this.generalLexerTool.getSubString(2))) {
            return this.generalLexerTool.moveNextLine();
            // 多行注释
        } else if (blockCommentSt.equals(this.generalLexerTool.getSubString(2))) {
            this.generalLexerTool.moveForward(blockCommentSt.length());
            String blockCommentEd = "*/";
            while (!this.generalLexerTool.isEndOfFile() && !blockCommentEd.equals(this.generalLexerTool.getSubString(2))) {
                this.generalLexerTool.moveForward(1);
            }
            if (!this.generalLexerTool.isEndOfFile() && blockCommentEd.equals(this.generalLexerTool.getSubString(2))) {
                this.generalLexerTool.moveForward(2);
                return true;
            } else return false;
        } else return false;

    }

    /**
     * 处理下一个单词
     */
    private void next() {
        // 检查目前指针指向的单词是否能够与LexType中的单词相匹配
        for (LexType lexType : LexType.values()) {
            String content = this.generalLexerTool.matchRegex(lexType.getPattern());
            if (content == null) {
                continue;
            } else {
                Token token = new Token(lexType, this.generalLexerTool.getCurrentLine(), content);
                this.tokenList.addToken(token);
                this.generalLexerTool.moveForward(content.length());
                break;
            }
        }
    }

    public String getTokenList() {
        return this.tokenList.toString();
    }
}
