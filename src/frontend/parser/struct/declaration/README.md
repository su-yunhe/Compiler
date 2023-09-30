# Parser-Declaration

该package是针对变量/常量声明的语法树设计的数据结构类。

## 1. 声明类型的文法

以下是 SysY 语言中声明类型的文法总结。

| 非终结符         | 文法                                                         | 含义     | 备注                         |
| ---------------- | ------------------------------------------------------------ | -------- | ---------------------------- |
| `<Decl>`         | `<ConstDecl> | <VarDecl>`                                    | 声明     | 变量声明、常量声明两种       |
| `<ConstDecl>`    | `'Const' <BType> <ConstDef> { ',' <ConstDef> } ';'`          | 常量声明 |                              |
| `<BType>`        | `'int'`                                                      | 基本类型 |                              |
| `<ConstDef>`     | `<Ident> { '[' <ConstExp> ']' } '=' <ConstInitVal>`          | 常数定义 | 普通变量、一维数组、二维数组 |
| `<ConstInitVal>` | `<ConstExp> | '{' [ <ConstInitVal> { ',' <ConstInitVal> } ] '}'` | 常量初值 |                              |
| `<VarDecl>`      | `<BType> <VarDef> { ',' <VarDef> } ';'`                      | 变量声明 |                              |
| `<VarDef>`       | `<Ident> { '[' <ConstExp> ']'} | <Ident> { '[' <ConstExp> ']' } '=' <InitVal>` | 变量定义 |                              |
| `<InitVal>`      | `<Exp> | '{' [ <InitVal> { ',' <InitVal> } ] '}'`            | 变量初值 |                              |

## 2. 声明类型语法树的节点设计

根据声明类型相关文法设计了语法树的相关节点。
下面按照声明类型节点的递推关系介绍该类型语法树的节点设计。

### 2.1. Decl 声明类
Decl → DeclEle = [ConstDecl | VarDecl] 【常量声明/变量声明】

### 2.2. DeclEle 声明类文法基类接口
DeclEle = [ConstDecl | VarDecl] 【常量声明/变量声明】
被 ConstDecl(常量声明) 和 VarDecl(变量声明) 继承

### 2.3. constant 常量声明类包
该包包含常量声明类及其递推出的语法类型节点包。
#### 2.3.1. ConstDecl 常量声明类
ConstDecl → 'const' BType ConstDef { ',' ConstDef } ';' // 1.花括号内重复0次 2.花括号内重复多次

#### 2.3.2. ConstDef 常数定义类
ConstDef → Ident { '[' ConstExp ']' } '=' ConstInitVal // 包含普通变量、一维数组、二维数组共三种情况

#### 2.3.3. ConstInitVal 常量初值类
ConstInitVal → ConstInitEle = ConstExp | '{' [ ConstInitVal { ',' ConstInitVal } ] '}'

#### 2.3.4. ConstInitValEle 常量初值类文法基类接口
包含 ConstExp | ConstInitValMulti = '{' [ ConstInitVal { ',' ConstInitVal } ] '}'
被 ConstExp 和 ConstInitValMulti 继承

#### 2.3.5. ConstInitValMulti 常量数组初值类
ConstInitValMulti -> '{' [ <ConstInitVal> { ',' <ConstInitVal> } ] '}'

### 2.4. variable 变量类包
该包包含变量声明类及其递推出的语法类型节点包。
#### 2.4.1. VarDecl 变量声明类
VarDecl → BType VarDef { ',' VarDef } ';' // 1.花括号内重复0次 2.花括号内重复多次

#### 2.4.2. varDef 变量定义类包
该包包含变量定义类及其递推出的语法类型节点包。

##### 2.4.2.1. VarDef 变量定义类
VarDef → varDefEle = Ident { '[' ConstExp ']' } | Ident { '[' ConstExp ']' } '=' InitVal

##### 2.4.2.2. varDefEle 变量定义类文法基类
包含 VarDelNull = Ident { '[' ConstExp ']' } 和 VarDelInit = Ident { '[' ConstExp ']' } '=' InitVal
被 VarDelNull 和 VarDelInit 继承

##### 2.4.2.3. VarDefNull 无初始化的变量定义类
VarDefNull = Ident { '[' ConstExp ']' }

##### 2.4.2.4. VarDefInit 初始化的变量定义类
VarDefInit = Ident { '[' ConstExp ']' } '=' InitVal

### 2.5. BType 基本类型类
BType → 'int' // 存在即可
