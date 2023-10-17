package struct.token;

import enums.LexType;

/**
 * 单词类，保存单词信息
 *
 * @author SYH
 * @date 2023/09/25
 * @since 2023/09/17
 */
public class Token {
    /**
     * 单词种类
     */
    private LexType lexType;
    /**
     * 单词所在行数
     */
    private int lineNum;
    /**
     * 单词的内容（即字符串）
     */
    private String content;

    /**
     * 单词类的构造函数
     * @param lexType 单词种类的枚举类
     * @param lineNum 单词所在的行数
     * @param content 单词的内容（即字符串）
     */
    public Token(LexType lexType, int lineNum, String content) {
        this.lexType = lexType;
        this.lineNum = lineNum;
        this.content = content;
    }

    /**
     * 获取单词种类
     * @return {@link LexType}
     */
    public LexType getLexType() {
        return lexType;
    }

    /**
     * 获取单词所在行数
     * @return int
     */
    public int getLineNum() {
        return lineNum;
    }

    /**
     * 获取单词内容
     * @return {@link String}
     */
    public String getContent() {
        return content;
    }

    public LexType getType() {
        return lexType;
    }


    /** 设置单词种类
     * @param lexType 单词种类
     */
    public void setLexType(LexType lexType) {
        this.lexType = lexType;
    }

    /** 设置单词内容
     * @param content 单词内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /** 设置单词所在行数
     * @param lineNum 单词所在行数
     */
    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    @Override
    public String toString() {
        return this.lexType.toString() + ' ' + this.content + '\n';
    }

}
