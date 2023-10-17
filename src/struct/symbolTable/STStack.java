package struct.symbolTable;


import struct.symbol.symbol.Symbol;

import java.util.ArrayList;
import java.util.ListIterator;

public class STStack {
    private static ArrayList<SymbolTable> symbolTables = new ArrayList<>();


    /**
     * 初始化栈式符号表
     */
    public static void initSTStack(){
        symbolTables.add(new SymbolTable());
    }

    /**
     * 向栈式符号表中压入符号表
     */
    public static void pushST() {
        SymbolTable symbolTable = new SymbolTable();
        symbolTable.setCycleDepth(getCurrentTable().getCycleDepth());
        symbolTables.add(symbolTable);
    }

    /**
     * 向栈式符号表中压入 for 循环的符号表
     */
    public static void pushSTFor() {
        SymbolTable symbolTable = new SymbolTable();
        symbolTable.setCycleDepth(getCurrentTable().getCycleDepth()+ 1);
        symbolTables.add(symbolTable);
    }

    /**
     * 弹出符号表
     */
    public static void popST() {
        int size = symbolTables.size();
        if (size > 0) {
            symbolTables.remove(size - 1);
        }
    }

    /**
     * 获取当前符号表（即栈顶的符号表）
     * @return {@link SymbolTable} 当前符号表
     */
    public static SymbolTable getCurrentTable() {
        return symbolTables.get(symbolTables.size() - 1);
    }

    /**
     * symbol 是否被重定义
     * @param symbol symbol
     * @return boolean 被重定义则返回 true，未被重定义则返回 false
     */
    public static boolean isSymbolRedefined(Symbol symbol) {
        return symbolTables.get(symbolTables.size() - 1).isSymbolInTable(symbol);
    }

    /**
     * symbol 名字是否被定义过
     * @param name name
     * @return boolean 已被定义则返回 true，未被定义则返回 false
     */
    public static boolean isSymbolUndefined(String name) {
        boolean res = true;
        ListIterator<SymbolTable> iterator = symbolTables.listIterator(symbolTables.size());
        while (iterator.hasPrevious()) {
            SymbolTable symbolTable = iterator.previous();
            if (symbolTable.isSymbolInTable(name)) {
                res = false;
                break;
            }
        }
        return res;
    }

    public static Symbol getDefinedSymbol(String name) {
        Symbol res = null;
        ListIterator<SymbolTable> iterator = symbolTables.listIterator(symbolTables.size());
        while (iterator.hasPrevious()) {
            SymbolTable symbolTable = iterator.previous();
            if (symbolTable.isSymbolInTable(name)) {
                res = symbolTable.getSymbol(name);
                break;
            }
        }
        return res;
    }


    public static String STStackToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("====================================================================================").append("\n");
        sb.append("||                                   current STStack                              ||").append("\n");
        sb.append("====================================================================================").append("\n");
        ListIterator<SymbolTable> iterator = symbolTables.listIterator(symbolTables.size());
        while (iterator.hasPrevious()) {
            sb.append(iterator.previous().toString());
        }

        return sb.toString();
    }
}
