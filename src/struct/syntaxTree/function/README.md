# Parser-Function

该package是针对函数的语法树设计的数据结构类。

## 1. 函数的文法

| 非终结符        | 文法                                                 | 含义       | 备注           |
| --------------- | ---------------------------------------------------- | ---------- | -------------- |
| `<FuncDef>`     | `<FuncType> <Ident> '(' [<FuncFParams>] ')' <Block>` | 函数定义   | 无形参/有形参  |
| `<MainFuncDef>` | `'int' 'main' '(' ')' <Block>`                       | 主函数定义 | 存在`main`函数 |
| `<FuncType>`    | `'void' | 'int'`                                     | 函数类型   |                |
| `<FuncFParams>` | `<FuncFParam> { ',' <FuncFParam> }`                  | 函数形参表 |                |
| `<FuncFParam>`  | `<BType> <Ident> [ '[' ']' { '[' <ConstExp> ']' } ]` | 函数形参   |                |

## 2. 函数类型语法树的节点设计

根据函数类型相关文法设计了语法树的相关节点。
下面按照函数类型节点的递推关系介绍该类型语法树的节点设计。

### 2.1. FuncDef 函数定义类
FuncDef → FuncType Ident '(' [FuncFParams] ')' Block // 1.无形参 2.有形参


### 2.2. funcType包
该包包含函数类型类及其递推出的语法类型节点包。

#### 2.2.1. FuncType 函数类型类
FuncType =  FuncTypeEle = 'void' | 'int'

#### 2.2.2. FuncTypeEle 函数类型类文法基类接口
FuncTypeEle = 'void' | 'int'
被 FuncVoid 和 FuncInt 类继承

#### 2.2.3. FuncVoid void型函数类
FuncTypeVoid = 'void'

#### 2.2.4. FuncInt int型函数类
FuncTypeInt = 'int'

### 2.3. FuncFParams 函数形参表类
FuncFParams → FuncFParam { ',' FuncFParam } // 1.花括号内重复0次 2.花括号内重复多次

### 2.4. FuncFParam 函数形参类
FuncFParam → BType Ident ['[' ']' { '[' ConstExp ']' }] // 1.普通变量2.一维 数组变量 3.二维数组变量

### 2.5. MainFuncDef 主函数定义类
MainFuncDef → 'int' 'main' '(' ')' Block // 存在main函数
