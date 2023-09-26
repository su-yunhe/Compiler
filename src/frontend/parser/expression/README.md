# parser-expression 包 

该package是针对表达式的语法树设计的数据结构类。

## 1. 表达式的文法
以下是 SysY 语言中表达式类型的文法总结。

### 1.1. 原始表达式的文法

| 非终结符        | 文法                                                         | 含义         | 备注                                   |
| --------------- | ------------------------------------------------------------ | ------------ | -------------------------------------- |
| `<Exp>`         | `<AddExp>`                                                   | 表达式       | SysY表达式是int型表达式                |
| `<Cond>`        | `<LOrExp>`                                                   | 条件表达式   | 存在即可                               |
| `<LVal>`        | `<Ident>{ '[' <Exp> ']' }`                                   | 左值表达式   | 1.普通变量2.一维数组3.二维数组         |
| `<PrimaryExp>`  | `'(' <Exp> ')' | <LVal> | <Number>`                          | 基本表达式   | 覆盖三种情况：表达式、左值表达式、数值 |
| `<Number>`      | `<IntConst>`                                                 | 数值         | 存在即可                               |
| `<UnaryExp>`    | `<PrimaryExp> | <Ident> '(' [<FuncRParams>] ')' | <UnaryOp> <UnaryExp>` | 一元表达式   | 存在即可；                             |
| `<UnaryOp>`     | `'+' | '-' | '!'`                                            | 单目运算符   | `'!'`仅出现在条件表达式中              |
| `<FuncRParams>` | `<Exp> {',' <Exp>}`                                          | 函数实参表   |                                        |
| `<MulExp>`      | `<UnaryExp> | <MulExp> ('*' | '/' | '%') <UnaryExp>`         | 乘除模表达式 | <font color=red>须消除左递归</font>    |
| `<AddExp>`      | `<MulExp> | <AddExp> ('+' | '-') <MulExp>`                   | 加减表达式   | <font color=red>须消除左递归</font>    |
| `<RelExp>`      | `<AddExp> | <RelExp> ('<' | '>' | '<=' | '>=') <AddExp>`     | 关系表达式   | <font color=red>须消除左递归</font>    |
| `<EqExp>`       | `<RelExp> | <EqExp> ('==' | '!=') <RelExp>`                  | 相等性表达式 | <font color=red>须消除左递归</font>    |
| `<LAndExp>`     | `<EqExp> | <LAndExp> '&&' <EqExp>`                           | 逻辑与表达式 | <font color=red>须消除左递归</font>    |
| `<LOrExp>`      | `<LAdnExp> | <LOrExp> '||' <LAndExp>`                        | 逻辑或表达式 | <font color=red>须消除左递归</font>    |
| `<ConstExp>`    | `<AddExp>`                                                   | 常量表达式   | 使用的`Ident`必须是常量                |

### 1.2. 消除左递归的表达式文法

原始的表达式文法中存在6处左递归情况。针对这6中情况，需要进行文法修改，消除左递归。以下是消除左递归后的文法表达式。【其余文法保持不变】

| 非终结符        | 文法                                                         | 含义         | 备注                                   |
| --------------- | ------------------------------------------------------------ | ------------ | -------------------------------------- |
| `<MulExp>`      | `<UnaryExp> { ('*' | '/' | '%') <UnaryExp> }`                | 乘除模表达式 | <font color=red>消除左递归</font>      |
| `<AddExp>`      | `<MulExp> { ('+' | '-') <MulExp> }`                          | 加减表达式   | <font color=red>消除左递归</font>      |
| `<RelExp>`      | `<AddExp> { ('<' | '>' | '<=' | '>=') <AddExp> }`            | 关系表达式   | <font color=red>消除左递归</font>      |
| `<EqExp>`       | `<RelExp> { ('==' | '!=') <RelExp> }`                        | 相等性表达式 | <font color=red>消除左递归</font>      |
| `<LAndExp>`     | `<EqExp> { '&&' <EqExp> }`                                   | 逻辑与表达式 | <font color=red>消除左递归</font>      |
| `<LOrExp>`      | `<LAndExp> { '||' <LAndExp> }`                               | 逻辑或表达式 | <font color=red>消除左递归</font>      |

## 2. 表达式类型语法树的节点设计

根据表达式类型相关文法设计了语法树的相关节点。
下面按照声明类型节点的递推关系介绍该类型语法树的节点设计。

### 2.1. Exp 表达式类
Exp → AddExp 注：SysY 表达式是int 型表达式 // 存在即可

### 2.2. Cond 条件表达式类
Cond → LOrExp

### 2.3. multi 二元表达式类包
该包包含二元表达式类所包含的具体类节点的包。
SysY 文法中，二元表达式包括 MulExp, AddExp, RelExp, EqExp, LAndExp 和 LOrExp。

#### 2.3.1. AbstractMultiExp 二元表达式抽象类
由 MulExp, AddExp, RelExp, EqExp, LAndExp, LOrExp 继承。

#### 2.3.2. AddExp 加减表达式类
AddExp -> MulExp { ('+' | '-') MulExp } 

#### 2.3.3. MulExp 乘除模表达式
MulExp -> UnaryExp { ('*' | '/' | '%') UnaryExp }

#### 2.3.4. LOrExp 逻辑或表达式
LOrExp -> LAndExp { '||' LAndExp }

#### 2.3.5. LAndExp 逻辑与表达式
LAndExp -> EqExp { '&&' EqExp }

#### 2.3.6. EqExp 相等性表达式
EqExp -> RelExp { ('==' | '!=') RelExp }

#### 2.3.7. RelExp 关系表达式
RelExp -> AddExp { ('<' | '>' | '<=' | '>=') AddExp }

### 2.4. unaryExp 一元表达式类包
该包包含一元表达式类及其递推出的语法类型节点包。

#### 2.4.1. UnaryExp 一元表达式类
UnaryExp → UnaryExpEle = PrimaryExp | Ident '(' [FuncRParams] ')' | UnaryOp UnaryExp

#### 2.4.2. UnaryExpEle 一元表达式类文法基类接口
UnaryExpEle = PrimaryExp | UnaryExpFunc = Ident '(' [FuncRParams] ')' | UnaryExpOp = UnaryOp UnaryExp
被 PrimaryExp、UnaryExpFunc 和 UnaryExpOp 继承

#### 2.4.3. UnaryExpFunc 一元表达式函数类
UnaryExpFunc = Ident '(' [FuncRParams] ')'

#### 2.4.4. UnaryExpOp 一元表达式单目运算符类
UnaryExpOp = UnaryOp UnaryExp

#### 2.4.5. UnaryOp 单目运算符类
合法的类别只有+, -, !，且!仅能出现在条件表达式中

### 2.5. primaryExp 基本表达式包
该包包含基本表达式类及其递推出的语法类型节点包。

#### 2.5.1. PrimaryExp 基本表达式类
PrimaryExp  -> PriMaryExpEle = '(' Exp ')' | LVal | Number

#### 2.5.2. PrimaryExpEle 基本表达式文法基类接口
PrimaryExpEle = PrimaryExpExp = '(' Exp ')' | LVal | Number
被 PrimaryExpExp、LVal 和 Number 继承

#### 2.5.3. PrimaryExpExp 基本表达式表达式类
PrimaryExpExp = '(' Exp ')'

#### 2.5.4. LVal 左值表达式类
LVal → Ident {'[' Exp ']'} //1.普通变量 2.一维数组 3.二维数组

#### 2.5.5. Number 数值类
Number → IntConst // 存在即可

### 2.6. FuncRParams 函数实参表类
FuncRParams → Exp { ',' Exp } // 1.花括号内重复0次 2.花括号内重复多次 3.Exp需要覆盖数组传参和部分数组传参

### 2.7. ConstExp 常量表达式
ConstExp → AddExp 注：使用的Ident 必须是常量 // 存在即可


