package struct.symbol;

import eumes.SymbolType;

import java.util.ArrayList;

public class SymbolCon1 extends Symbol {
    private ArrayList<Integer> value;

    public SymbolCon1(int lineNum, String name, SymbolType symbolType, int dimension) {
        super(lineNum, name, symbolType, dimension);
        this.value = null;
    }

    public SymbolCon1(int lineNum, String name, SymbolType symbolType, int dimension, ArrayList<Integer> value) {
        super(lineNum, name, symbolType, dimension);
        this.value = value;
    }

    public void setValue(ArrayList<Integer> value) {
        this.value = value;
    }

    public ArrayList<Integer> getValue() {
        return value;
    }

    public String value2String() {
        StringBuilder sb = new StringBuilder();
        if (value == null) {
            sb.append("undefined").append("\n");
        } else {
            sb.append("{ ");
            for (Integer i : (ArrayList<Integer>)value) {
                sb.append(i).append(" ");
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
