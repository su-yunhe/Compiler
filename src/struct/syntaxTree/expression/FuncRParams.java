package struct.syntaxTree.expression;

import struct.token.Token;

import java.util.ArrayList;

/**
 * FuncRParams 函数实参表类
 * FuncRParams → Exp { ',' Exp } // 1.花括号内重复0次 2.花括号内重复多次 3.Exp需要覆盖数组传参和部分数组传参
 * @author SYH
 * @since 2023/09/26
 */
public class FuncRParams {
    private final String name = "<FuncRParams>";
    private Exp first;
    /* commas 和 exps大小应当相同，且commas应当只有逗号一种Token */
    /* 这里可能需要进行某种验证 */
    private ArrayList<Token> commas;
    private ArrayList<Exp> exps;

    public FuncRParams(Exp first, ArrayList<Token> commas, ArrayList<Exp> exps) {
        this.first = first;
        this.commas = commas;
        this.exps = exps;
    }


    public ArrayList<Exp> getExps() {
        ArrayList<Exp> expArrayList = new ArrayList<>();
        expArrayList.add(first);
        expArrayList.addAll(exps);
        return expArrayList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(first.toString());
        if (commas != null && exps != null && commas.size() == exps.size()) {
            int len = commas.size();
            for (int i = 0; i < len; i++) {
                sb.append(commas.get(i).toString());
                sb.append(exps.get(i).toString());
            }
        }
        sb.append(this.name).append("\n");
        return sb.toString();
    }
}
