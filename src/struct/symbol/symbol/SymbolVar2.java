package struct.symbol.symbol;

import enums.SymbolType;
import struct.symbol.symbol.Symbol;

import java.util.ArrayList;

public class SymbolVar2 extends Symbol {

    private ArrayList<ArrayList<Integer>> value;
    public SymbolVar2(int lineNum, String name, SymbolType symbolType, int dimension) {
        super(lineNum, name, symbolType, dimension);
        this.value = null;
    }

    public SymbolVar2(int lineNum, String name, SymbolType symbolType, int dimension, ArrayList<ArrayList<Integer>> value) {
        super(lineNum, name, symbolType, dimension);
        this.value = value;
    }

    public String value2String() {
        StringBuilder sb = new StringBuilder();
        if (value == null) {
            sb.append("undefined").append("\n");
        } else {
            sb.append("{ ");
            for (ArrayList<Integer> arrayList : value) {
                sb.append("( ");
                for (Integer i: arrayList) {
                    sb.append(i).append(" ");
                }
                sb.append(") ");
            }
            sb.append("}").append("\n");
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
        sb.append("initVal: ").append(value2String());
        return sb.toString();
    }
}
