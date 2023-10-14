package struct.symbolTable;

import struct.symbol.Symbol;

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

    public boolean isSymbolInTable(Symbol symbol) {
        return symbols.containsKey(symbol.getName());
    }

    public boolean isSymbolInTable(String name) {
        return symbols.containsKey(name);
    }

    public Symbol getSymbol(String name) {
        return symbols.get(name);
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
