package struct.syntaxTree.statement;

import enums.ReturnType;

/**
 * BlockItemEle 语句块类文法基类接口
 * BlockItemEle = Decl | Stmt // 覆盖两种语句块项
 * 被 Decl 和 Stmt 继承
 * @author SYH
 * @date 2023/09/26
 */
public interface BlockItemEle {
    ReturnType getReturnType();
}
