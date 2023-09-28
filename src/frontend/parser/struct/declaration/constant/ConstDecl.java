package frontend.parser.struct.declaration.constant;

import frontend.lexer.Token;
import frontend.parser.struct.declaration.BType;
import frontend.parser.struct.declaration.DeclEle;

import java.util.ArrayList;

/**
 * ConstDecl 常量声明类
 *  ConstDecl → 'const' BType ConstDef { ',' ConstDef } ';' // 1.花括号内重复0次 2.花括号内重复多次
 * @author SYH
 * @date 2023/09/26
 */
public class ConstDecl implements DeclEle {
    private final String name = "<ConstDecl>";
    private Token constTk; // 'const'
    private BType btype;
    private ConstDef first;
    private ArrayList<Token> commas; // commas
    private ArrayList<ConstDef> constDefs; // constDefs
    private Token semicn; // ';'

    public ConstDecl(Token constTk,
                     BType btype,
                     ConstDef first,
                     Token semicn) {
        this.constTk = constTk;
        this.btype = btype;
        this.first = first;
        this.semicn = semicn;
    }

    public ConstDecl(Token constTk,
                     BType btype,
                     ConstDef first,
                     ArrayList<Token> commas,
                     ArrayList<ConstDef> constDefs,
                     Token semicn) {
        this(constTk, btype, first, semicn);
        this.commas = commas;
        this.constDefs = constDefs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(constTk.toString());
        sb.append(btype.toString());
        sb.append(first.toString());
        if (commas != null && constDefs != null && commas.size() == constDefs.size()) {
            int len = commas.size();
            for (int i = 0; i < len; i++) {
                sb.append(this.commas.get(i).toString());
                sb.append(this.constDefs.get(i).toString());
            }
        }
        sb.append(this.semicn.toString());
        sb.append(this.name).append("\n");
        return sb.toString();
    }
}
