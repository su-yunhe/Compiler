package struct.symbol.symbol;

import enums.SymbolType;
import struct.symbol.symbol.Symbol;

public class SymbolCon extends Symbol {
    private Integer value;
    public SymbolCon(int lineNum, String name, SymbolType symbolType, int dimension) {
        super(lineNum, name, symbolType, dimension);
        this.value = null;
    }

    public SymbolCon(int lineNum, String name, SymbolType symbolType, int dimension, Integer value) {
        super(lineNum, name, symbolType, dimension);
        this.value = value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("lineNum: ").append(lineNum).append(", ");
        sb.append("name: ").append(name).append(", ");
        sb.append("symbolType: ").append(symbolType).append(", ");
        sb.append("dimension: ").append(dimension).append(", ");
        if (value == null) {
            sb.append("initVal: ").append("undefined").append("\n");
        } else {
            sb.append("initVal: ").append(value).append("\n");
        }

        return sb.toString();
    }
}
