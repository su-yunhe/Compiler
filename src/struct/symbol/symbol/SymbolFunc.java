package struct.symbol.symbol;

import enums.SymbolType;
import struct.symbol.symbol.Symbol;

import java.util.ArrayList;

public class SymbolFunc extends Symbol {
    private ArrayList<Symbol> value;

    public SymbolFunc(int lineNum, String name, SymbolType symbolType, int dimension) {
        super(lineNum, name, symbolType, dimension);
        this.value = null;
    }

    public SymbolFunc(int lineNum, String name, SymbolType symbolType, int dimension, ArrayList<Symbol> value) {
        super(lineNum, name, symbolType, dimension);
        this.value = value;
    }

    public void setValue(ArrayList<Symbol> value) {
        this.value = value;
    }

    public ArrayList<Symbol> getValue() {
        return value;
    }

    public String value2String() {
        StringBuilder sb = new StringBuilder();
        if (value == null) {
            sb.append("undefined");
        } else if (value.isEmpty()) {
            sb.append("the func has no parameter!");
        } else {
            sb.append("( ");
            for (Symbol symbol: value) {
                sb.append(symbol.getSymbolType()).append(" ");
            }
            sb.append(")");
        }
        return sb.toString();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("lineNum: ").append(lineNum).append(", ");
        sb.append("name: ").append(name).append(", ");
        sb.append("symbolType: ").append(symbolType).append(", ");
        sb.append("dimension: ").append(dimension).append(", ");
        sb.append("initVal: ").append(value2String()).append("\n");
        return sb.toString();
    }
}
