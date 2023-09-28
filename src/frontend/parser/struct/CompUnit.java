package frontend.parser.struct;

import frontend.parser.struct.declaration.Decl;
import frontend.parser.struct.function.FuncDef;
import frontend.parser.struct.function.MainFuncDef;

import java.util.ArrayList;

/**
 * CompUnit 编译单元类
 * CompUnit → {Decl} {FuncDef} MainFuncDef // 1.是否存在Decl 2.是否存在FuncDef
 * @author SYH
 * @date 2023/09/26
 */
public class CompUnit {
    private final String name = "<CompUnit>";
    private ArrayList<Decl> decls = null; // MAY exist
    private ArrayList<FuncDef> funcDefs = null; // MAY exist
    private MainFuncDef mainFuncDef;

    public CompUnit(MainFuncDef mainFuncDef) {
        this.mainFuncDef = mainFuncDef;
    }

    public CompUnit(ArrayList<Decl> decls,
                    ArrayList<FuncDef> funcDefs,
                    MainFuncDef mainFuncDef) {
        this(mainFuncDef);
        this.decls = decls;
        this.funcDefs = funcDefs;
    }
}
