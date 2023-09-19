package frontend.lexer;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 词法成分分析器【单例模式】
 * 功能：构建从源代码中识别出的所有有效 Token 所组成的 TokenList 和 TokenMap
 * @author SYH
 * @date 2023/09/18
 */
public class Lexer {
    /**
     * 词法成分分析器实例
     */
    private static Lexer lexerInstance;
    /**
     * 输入源程序
     */
    private InputStream source;
    /**
     * 按行存放的源程序列表
     */
    private ArrayList<String> lines;
    /**
     *  当前字符串位置指针【行数】
     */
    private int curLine;
    /**
     * 当前字符串位置指针【行内列数】
     */
    private int curColumn;
    /**
     * 从源程序识别出的 token 列表
     */
    private TokenList tokenList;

    /**
     * 私有构造函数
     * @param source 输入的源程序
     */
    private Lexer(FileInputStream source) {
        this.source = source;
        this.lines = new ArrayList<>();
        this.curLine = 0;
        this.curColumn = 0;
        this.tokenList = new TokenList();
        source2Lines();
        tokenAnalyse();
    }


    /********************************** 对外提供的接口 ********************************************/

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

    /**
     * 获取当前指针所在行数
     * @return int 当前行数
     */
    public int getCurrentLine() {
        return this.curLine + 1;
    }

    public ArrayList<String> getLines() {
        return this.lines;
    }

    public String getTokenList() {
        return this.tokenList.toString();
    }

    /**
     * token 分析
     * 思路：通过循环，不断处理【空白符】【注释】，并调用 next() 处理【下一个单词】
     */
    private void tokenAnalyse() {
        while (!isEndOfFile()) {
            // 首先跳过空白符
            skipWhiteSpace();
            // 判断跳过注释的情况
            if (skipComment()) {
                continue;
            }
            next();
        }
    }

    /**
     * 跳过注释
     * @return boolean 是否成功
     */
    private boolean skipComment() {
        String lineComment = "//";
        String blockCommentSt = "/*";
        String blockCommentEd = "*/";
        if (lineComment.equals(getSubString(2))) {
            return moveNextLine();
        } else if (blockCommentSt.equals(getSubString(2))) {
            moveForward(2);

            while (!isEndOfFile() && !blockCommentEd.equals(getSubString(2))) {
                moveForward(1);
            }
            if (!isEndOfFile() && blockCommentEd.equals(getSubString(2))) {
                moveForward(2);
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
            String content = this.matchRegex(lexType.getPattern());
            if (Objects.equals(content, "")) {
                continue;
            } else {
                Token token = new Token(lexType, this.getCurrentLine(), content);
                this.tokenList.addToken(token);
                this.moveForward(content.length());
                break;
            }
        }
    }

    /**
     * 初始化时，将源程序按行读取，并储存在行列表中
     */
    private void source2Lines() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.source))) {
            String lineNow;
            while ((lineNow = bufferedReader.readLine()) != null) {
                this.lines.add(lineNow);
            }
            System.out.println(lines);
        } catch (IOException e) {
            System.err.println("Fail when readLines()");
        }
    }

    /**
     * 判断当前字符串位置指针是否已经指向文件结尾
     * @return boolean 是否指向文件结尾
     */
    private boolean isEndOfFile() {
        return this.curLine >= this.lines.size();
    }

    /**
     * 判断当前字符串位置指针是否已经指向当前行的结尾
     * @return boolean 是否指向当前行结尾
     */
    private boolean isEndOfLine() {
        if (isEndOfFile() || curLine >= this.lines.size()) {
            return true;
        }
        return this.curColumn >= this.lines.get(curLine).length();
    }


    /**
     * 获取当前行（即位置指针指向的行）
     * @return {@link String}
     */
    private String getCurLine() {
        if (isEndOfFile()) {
            return "";
        } else {
            return this.lines.get(curLine);
        }
    }

    /**
     * 获取当前字符（即将要接信息的下一个字符）
     * @return char 当前字符
     */
    private char getCurChar() {
        if (isEndOfFile()) {
            return '\0';
        } else if (isEndOfLine()) {
            return '\n';
        } else {
            return getCurLine().charAt(curColumn);
        }
    }

    /**
     * 获取当前行当前位置起指定长度的子串
     * @param len 要获取的子串长度
     * @return {@link String} 子串
     */
    private String getSubString(int len) {
        if (isEndOfFile()) {
            return "";
        }
        String curLine = getCurLine();
        int lineLength = curLine.length();
        if (this.curColumn + len >= lineLength) {
            return curLine.substring(this.curColumn);
        } else {
            return curLine.substring(this.curColumn, this.curColumn + len);
        }
    }

    /**
     * 向前移动指定步数
     * @param steps 向前移动的步数
     */
    private void moveForward(int steps) {
        String cachedLine; // 用于缓存行内容的变量
        int cnt = steps; // 剩余步数

        while (!isEndOfFile() && cnt > 0) {
            cachedLine = getCurLine();

            int lineLen = cachedLine.length();

            if (curColumn + cnt >= lineLen) {
                curLine++;
                cnt -= (lineLen - curColumn + 1);
                curColumn = 0;
            } else {
                curColumn += cnt;
                cnt = 0;
            }
        }
    }

    /**
     * 跳过空白字符（包括空格、制表符、换行符等）
     */
    private void skipWhiteSpace() {
        while (!isEndOfFile() && Character.isWhitespace(getCurChar())) {
            moveForward(1);
        }
    }

    /**
     * 移动到下一行
     * @return boolean 是否成功
     */
    private boolean moveNextLine() {
        if (isEndOfFile()) {
            return false;
        } else {
            curLine++;
            curColumn = 0;
            return true;
        }
    }

    /**
     * 获取当前行的剩余内容（用于匹配下一个单词之用）
     * @return {@link String}
     */
    private String getRemainedOfLine() {
        if (isEndOfFile() || isEndOfLine()) {
            return "";
        } else {
            return getCurLine().substring(curColumn);
        }
    }

    /**
     * 正则表达式匹配
     * @param pattern 正则
     * @return {@link String}
     */
    private String matchRegex(Pattern pattern) {
        String remainedOfLine = this.getRemainedOfLine();
        Matcher matcher = pattern.matcher(remainedOfLine);
        if (matcher.find()) {
            return matcher.group(0);
        } else {
            return "";
        }
    }


}
