package struct.symbol;


import enums.SymbolType;
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
        symbolTables.add(new SymbolTable());
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

    public static Symbol getSymbolInCurrent(String name) {
        return getCurrentTable().getSymbol(name);

    }

    public static Symbol getSymbolInCurrent(String name, SymbolType symbolType) {
        return getCurrentTable().getSymbol(name, symbolType);
    }

    public static Symbol getSymbol(String name) {
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
    public static Symbol getSymbol(String name, SymbolType symbolType) {
        Symbol res = null;
        ListIterator<SymbolTable> iterator = symbolTables.listIterator(symbolTables.size());
        while (iterator.hasPrevious()) {
            SymbolTable symbolTable = iterator.previous();
            if (symbolTable.isSymbolInTable(name, symbolType)) {
                res = symbolTable.getSymbol(name, symbolType);
                break;
            }
        }
        return res;
    }


    public static String STStackToString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" =======================================================================================").append("\n");
        sb.append("||                                   current STStack                                   ||").append("\n");
        sb.append(" =======================================================================================").append("\n");
        ListIterator<SymbolTable> iterator = symbolTables.listIterator(symbolTables.size());
        while (iterator.hasPrevious()) {
            sb.append(iterator.previous().toString());
        }

        return sb.toString();
    }
}
