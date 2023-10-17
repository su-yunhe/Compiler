package struct.symbol.symbol;

import enums.SymbolType;

/**
 * 符号类
 *
 * @author SYH
 * @date 2023/10/11
 */
public abstract class Symbol implements Comparable<Symbol>{
    protected int lineNum; // 从1开始
    protected String name;
    protected SymbolType symbolType = null;
    protected int dimension; // 维数

    public Symbol(int lineNum, String name, SymbolType symbolType, int dimension) {
        this.lineNum = lineNum;
        this.name = name;
        this.symbolType = symbolType;
        this.dimension = dimension;
    }

    public String getName() {
        return name;
    }

    public int getLineNum() {
        return lineNum;
    }

    public SymbolType getSymbolType() {
        return symbolType;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getDimension() {
        return dimension;
    }

    @Override
    public int compareTo(Symbol o) {
        return Integer.compare(this.lineNum, o.lineNum);
    }

}
