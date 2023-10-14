# Parser-Statement

该package是针对语句的语法树设计的数据结构类。

## 1. 语句的文法

| 非终结符          | 文法                                                    | 含义     | 备注                |
|:--------------|:------------------------------------------------------| -------- | ------------------- |
| `<Block>`     | `'{' { <BlockItem> } '}'`                             | 语句块   | 花括号内重复0或多次 |
| `<BlockItem>` | `<Decl>                                               | <Stmt>`                                 | 语句块项 | 包含两种语句块      |
| `<Stmt>`      | `<LVal> '=' <Exp> ';'`                                |          |                     |
|               | `[<Exp>] ';'`                                         |          |                     |
|               | `<Block>`                                             |          |                     |
|               | `'if' '(' <Cond> ')' <Stmt> [ 'else' <Stmt> ]`        |          | 包括有无`else`两种  |
|               | `'for' '(' [ForStmt] ';' [Cond] ';' [ForStmt] ')' Stmt` |          |  1. 无缺省 2. 缺省第一个ForStmt 3. 缺省Cond 4. 缺省第二个ForStmt                   |
|               | `'break' ';' `                                        |          |                     |
|               | `'continue' ';'`                                      |          |                     |
|               | `'return' [<Exp>] ';'`                                |          |                     |
|               | `<LVal> '=' 'getint' '(' ')' ';'`                     |          |                     |
|               | `'prinft' '(' <FormatString> {',' <Exp>} ')' ';'`     |          |                     |
| `<ForStmt>`   |  ` ForStmt → LVal '=' Exp`                            |          |                     |


## 2. 语句类型语法树的节点设计

根据语句类型相关文法设计了语法树的相关节点。
下面按照语句类型节点的递推关系介绍该类型语法树的节点设计。

### 2.1. Block 语句块类
Block → '{' { BlockItem } '}' // 1.花括号内重复0次 2.花括号内重复多次

### 2.2. BlockItem 语句块类
BlockItem → BlockItemEle = Decl | Stmt // 覆盖两种语句块项

### 2.3. BlockItemEle 语句块类文法基类接口
BlockItemEle = Decl | Stmt // 覆盖两种语句块项

### 2.4. stmt 包
该包包含语句类及其递推出的语法类型节点包。

#### 2.4.1. Stmt 语句类
Stmt → StmtEle = 
LVal '=' Exp ';' // 每种类型的语句都要覆盖 
| [Exp] ';' //有无Exp两种情况
| Block
| 'if' '(' Cond ')' Stmt [ 'else' Stmt ] // 1.有else 2.无else
| 'for' '(' [ForStmt] ';' [Cond] ';' [ForStmt] ')' Stmt // 1. 无缺省 2. 缺省第一个ForStmt 3. 缺省Cond 4. 缺省第二个ForStmt
| 'break' ';' 
| 'continue' ';'
| 'return' [Exp] ';' // 1.有Exp 2.无Exp
| LVal '=' 'getint''('')'';'
| 'printf''('FormatString{','Exp}')'';' // 1.有Exp 2.无Exp

#### 2.4.2. StmtEle 语句类文法基类接口
StmtEle =
LVal '=' Exp ';' // 每种类型的语句都要覆盖
| [Exp] ';' //有无Exp两种情况
| Block
| 'if' '(' Cond ')' Stmt [ 'else' Stmt ] // 1.有else 2.无else
| 'for' '(' [ForStmt] ';' [Cond] ';' [ForStmt] ')' Stmt // 1. 无缺省 2. 缺省第一个ForStmt 3. 缺省Cond 4. 缺省第二个ForStmt
| 'break' ';' 
| 'continue' ';'
| 'return' [Exp] ';' // 1.有Exp 2.无Exp
| LVal '=' 'getint''('')'';'
| 'printf''('FormatString{','Exp}')'';' // 1.有Exp 2.无Exp

被 StmtAssign, StmtExp, StmtNull, StmtBlock, StmtCond, StmtFor, StmtBreak, StmtContinue, StmtReturn, StmtGetInt 和 StmtPrintf 继承。

#### 2.4.3. StmtAssign 赋值语句类
StmtAssign = LVal '=' Exp ';'

#### 2.4.4. StmtExp 表达式语句类
StmtExp = [Exp] ';'

#### 2.4.5. StmtNull 空表达式语句类
StmtNull = ;

#### 2.4.6. StmtBlock 语句块语句类
StmtBlock = Block

#### 2.4.7. StmtIf if语句项
StmtIf = 'if' '(' Cond ')' Stmt [ 'else' Stmt ] // 1.有else 2.无else

#### 2.4.8. StmtFor for语句
StmtFor = 'for' '(' [ForStmt] ';' [Cond] ';' [ForStmt] ')' Stmt

#### 2.4.9. StmtBreak break语句
StmtBreak = 'break' ';'

#### 2.4.10. StmtContinue continue语句
StmtContinue = 'continue' ';'

#### 2.4.11. StmtReturn return语句
StmtReturn = 'return' [Exp] ';' // 1.有Exp 2.无Exp

#### 2.4.12. StmtGetInt getint语句
LVal '=' 'getint''('')'';'

#### 2.4.13. StmtPrintf printf语句
StmtPrintf = 'printf''('FormatString{','Exp}')'';' // 1.有 Exp 2.无 Exp

### 2.5. ForStmt类
ForStmt → LVal '=' Exp // 存在即可

