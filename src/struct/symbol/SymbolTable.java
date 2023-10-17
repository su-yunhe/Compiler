package struct.symbol;

import enums.SymbolType;
import struct.symbol.symbol.Symbol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class SymbolTable {
    /* 符号名 -> 符号obj */
    private HashMap<String, Symbol> symbols;
    private int cycleDepth;

    public SymbolTable() {
        this.symbols = new HashMap<>();
        this.cycleDepth = 0;
    }

    public int getCycleDepth() {
        return cycleDepth;
    }

    public void setCycleDepth(int cycleDepth) {
        this.cycleDepth = cycleDepth;
    }


    public void addSymbol(Symbol symbol) {
        this.symbols.put(symbol.getName(), symbol);
    }

    /**
     * 判断符号是否存在【只比较名字】
     * @param name name
     * @return boolean
     */
    public boolean isSymbolInTable(String name) {
        return symbols.containsKey(name);
    }

    /**
     * 判断符号是否存在【比较名字和类型】
     * @param symbol symbol
     * @return boolean
     */
    public boolean isSymbolInTable(Symbol symbol) {
        Symbol res = symbols.get(symbol.getName());
        if (res == null) {
            return false;
        }
        return res.getSymbolType().equals(symbol.getSymbolType());
    }


    /**
     * 判断符号是否存在【比较名字和类型】
     * @param name name
     * @param symbolType symbolType
     * @return boolean
     */
    public boolean isSymbolInTable(String name, SymbolType symbolType) {
        Symbol res = symbols.get(name);
        if (res == null) {
            return false;
        }
        return res.getSymbolType().equals(symbolType);
    }

    /**
     * 获取符号【名字】
     * @param name name
     * @return {@link Symbol}
     */
    public Symbol getSymbol(String name) {
        return symbols.get(name);
    }

    /**
     * 获取符号【名字和类别】
     * @param name name
     * @param symbolType symbolType
     * @return {@link Symbol}
     */
    public Symbol getSymbol(String name, SymbolType symbolType) {
        Symbol res = symbols.get(name);
        if (res != null && !res.getSymbolType().equals(symbolType)) {
            res = null;
        }
        return res;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SymbolTable:\n");
        sb.append("cycleDepth: ").append(cycleDepth).append("\n");
        ArrayList<Symbol> symbols1 = new ArrayList<>(symbols.values());
        Collections.sort(symbols1);
        for (Symbol value : symbols1) {
            sb.append(value.toString());
        }
        sb.append("-----------------------------------------------------------------------------------").append("\n");
        return sb.toString();
    }
}
