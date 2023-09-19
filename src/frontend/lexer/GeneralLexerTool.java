package frontend.lexer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 词法分析器通用工具类【单例模式】
 * 拥有一个当前字符的指针，提供对源程序的通用分析接口。
 *
 * @author SYH
 * @date 2023/09/17
 */
public class GeneralLexerTool {
    /**
     * 单例实例
     */
    private static GeneralLexerTool generalLexerToolInstance;
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
     * 私有构造方法
     * @param source 源程序
     */
    private GeneralLexerTool(InputStream source) {
        this.source = source;
        this.lines = new ArrayList<>();
        this.curLine = 0;
        this.curColumn = 0;
        source2Lines();
    }

    /**
     * 获取 GeneralLexerTool 的单例实例
     * @param source 源程序
     * @return 单例实例
     */
    public static GeneralLexerTool getInstance(InputStream source) {
        if (generalLexerToolInstance == null) {
            generalLexerToolInstance = new GeneralLexerTool(source);
        }
        return generalLexerToolInstance;
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
        } catch (IOException e) {
            System.err.println("Fail when readLines()");
        }
    }

    /**
     * 判断当前字符串位置指针是否已经指向文件结尾
     * @return boolean 是否指向文件结尾
     */
    public boolean isEndOfFile() {
        return this.curLine >= this.lines.size();
    }

    /**
     * 判断当前字符串位置指针是否已经指向当前行的结尾
     * @return boolean 是否指向当前行结尾
     */
    public boolean isEndOfLine() {
        if (isEndOfFile() || curLine >= this.lines.size()) {
            return true;
        }
        return this.curColumn >= this.lines.get(curLine).length();
    }



    /**
     * 获取当前行（即位置指针指向的行）
     * @return {@link String}
     */
    public String getCurLine() {
        if (isEndOfFile()) {
            return null;
        } else {
            return this.lines.get(curLine);
        }
    }

    /**
     * 获取当前字符（即将要接信息的下一个字符）
     * @return char 当前字符
     */
    public char getCurChar() {
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
    public String getSubString(int len) {
        if (isEndOfFile()) {
            return null;
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
    public void moveForward(int steps) {
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
    public void skipWhiteSpace() {
        while (!isEndOfFile() && Character.isWhitespace(getCurChar())) {
            moveForward(1);
        }
    }

    /**
     * 移动到下一行
     * @return boolean 是否成功
     */
    public boolean moveNextLine() {
        if (isEndOfFile()) {
            return false;
        } else {
            curLine++;
            curColumn = 0;
            return true;
        }
    }

    /**
     * 获取当前行的剩余内容
     * @return {@link String}
     */
    public String getRemainedOfLine() {
        if (isEndOfFile() || isEndOfLine()) {
            return null;
        } else {
            return getCurLine().substring(curColumn);
        }
    }

    /**
     * 正则表达式匹配
     * @param pattern 正则
     * @return {@link String}
     */
    public String matchRegex(Pattern pattern) {
        String remainedOfLine = this.getRemainedOfLine();
        Matcher matcher = pattern.matcher(remainedOfLine);
        if (matcher.find()) {
            return matcher.group(0);
        } else {
            return null;
        }
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("index: [curLine: ").append(curLine).append(", curColumn: ").append(curColumn).append("]\n");
        sb.append("Source textfile in lines:\n");
        int index = 0;
        for (String line : lines) {
            sb.append("     [line ").append(index).append("]:  ").append(line).append('\n');
            index++;
        }
        return sb.toString();
    }
}
