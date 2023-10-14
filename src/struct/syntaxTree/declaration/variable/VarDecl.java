package struct.syntaxTree.declaration.variable;

import struct.token.Token;
import struct.syntaxTree.declaration.BType;
import struct.syntaxTree.declaration.DeclEle;
import struct.syntaxTree.declaration.variable.varDef.VarDef;

import java.util.ArrayList;

/**
 * VarDecl 变量声明类
 * VarDecl → BType VarDef { ',' VarDef } ';' // 1.花括号内重复0次 2.花括号内重复多次
 * @author SYH
 * @date 2023/09/26
 */
public class VarDecl implements DeclEle {
    private final String name = "<VarDecl>";
    private BType btype;
    private VarDef first;
    private ArrayList<Token> commas; //,
    private ArrayList<VarDef> varDefs;
    private Token semi;

    public VarDecl(BType btype,
                   VarDef first,
                   ArrayList<Token> commas,
                   ArrayList<VarDef> varDefs,
                   Token semi) {
        this.btype = btype;
        this.first = first;
        this.commas = commas;
        this.varDefs = varDefs;
        this.semi = semi;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.btype.toString());
        sb.append(this.first.toString());
        if (this.commas != null && this.varDefs != null &&
                this.commas.size() == this.varDefs.size()) {
            int len = this.commas.size();
            for (int i = 0; i < len; i++) {
                sb.append(this.commas.get(i).toString());
                sb.append(this.varDefs.get(i).toString());
            }
        }
        sb.append(this.semi.toString());
        sb.append(this.name + "\n");
        return sb.toString();
    }
}
