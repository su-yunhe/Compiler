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

}
