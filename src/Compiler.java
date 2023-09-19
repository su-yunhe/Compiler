import frontend.lexer.Lexer;

import java.io.Console;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 编译器类
 * @author SYH
 * @date 2023/09/18
 */
public class Compiler {
    /**
     * 主程序入口
     * @param args args
     */
    public static void main(String[] args) {
        // 1. 读取testfile
        FileInputStream source = getSource("testfile.txt");

        // 2. 词法分析程序,生成单词列表
        // Lexer lexer = Lexer.getInstance(source);
    }


    /**
     * 读取源文件
     * @param inputFileName 源文件名称
     * @return {@link FileInputStream}
     */
    private static FileInputStream getSource(String inputFileName) {
        String currentDir = System.getProperty("user.dir");
        System.out.println(currentDir + "\\input\\" + inputFileName);
        // 使用try-with-resources语句
        try (FileInputStream fis = new FileInputStream(currentDir + "\\input\\" + inputFileName)) {
            Lexer lexer = Lexer.getInstance(fis);
            System.out.println(lexer.getTokenList());

            return fis;
        } catch (IOException e) {
            System.err.println("Fail to open " + inputFileName);
            return null;
        }
    }
}