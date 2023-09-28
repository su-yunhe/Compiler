package frontend.parser.struct.declaration.variable;

import frontend.lexer.Token;
import frontend.parser.struct.declaration.BType;
import frontend.parser.struct.declaration.DeclEle;
import frontend.parser.struct.declaration.variable.varDef.VarDef;

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

}
