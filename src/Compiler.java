import compiler.Lexer;
import compiler.parser.Parser;
import struct.syntaxTree.CompUnit;
import struct.error.ErrorTable;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 编译器类
 *
 * @author SYH
 * @since 2023/09/18
 */
public class Compiler {
    /**
     * 主程序入口
     *
     * @param args args
     */
    public static void main(String[] args) {
        // 1. 调用词法分析程序,生成单词列表
//        useLexer("testfile.txt", "output.txt", true);
//        useLexer(".//src//testfile.txt", ".//src//output.txt", true);
        useLexer("testfile.txt", "error.txt", true);
//        useLexer(".//src//file//testfile.txt", ".//src//file//error.txt", true);
    }

    /**
     * 调用词法分析程序,生成单词列表
     *
     * @param inputFileName  源文件名称
     * @param outputFileName
     * @param isPrint
     */
    private static void useLexer(String inputFileName, String outputFileName, Boolean isPrint) {
        // 使用try-with-resources语句
        try (FileInputStream fis = new FileInputStream(inputFileName);
             FileWriter fileWriter = new FileWriter(outputFileName);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            Lexer lexer = Lexer.getInstance(fis);
            Parser parser = new Parser();
            CompUnit compUnit = parser.parseCompUnit();
            // 写入内容到文件
            if (isPrint) {
//                writer.write(TokenList.TokenList2String());
//                writer.write(compUnit.toString());
                writer.write(ErrorTable.output());
                System.out.println("内容已成功写入到文件。");
                System.out.println(ErrorTable.output());
            }
        } catch (IOException e) {
            System.err.println("Fail to open " + inputFileName);
        }
    }
}
