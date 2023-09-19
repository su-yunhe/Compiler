import frontend.lexer.Lexer;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 编译器类
 *
 * @author SYH
 * @date 2023/09/18
 */
public class Compiler {
    /**
     * 主程序入口
     *
     * @param args args
     */
    public static void main(String[] args) {
        // 1. 调用词法分析程序,生成单词列表
//        useLexer(".//src//testfile.txt", ".//src//output.txt");
        useLexer("testfile.txt", "output.txt");

    }


    /**
     * 调用词法分析程序,生成单词列表
     *
     * @param inputFileName 源文件名称
     */
    private static void useLexer(String inputFileName, String outputFileName) {
        // 使用try-with-resources语句
        try (FileInputStream fis = new FileInputStream(inputFileName);
             FileWriter fileWriter = new FileWriter(outputFileName);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            Lexer lexer = Lexer.getInstance(fis);
            // 写入内容到文件
            writer.write(lexer.getTokenList());

            System.out.println("内容已成功写入到文件。");

        } catch (IOException e) {
            System.err.println("Fail to open " + inputFileName);
        }

    }
}
