package frontend.parser.parser.declarationParser.variableParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.parser.expressionParser.ConstExpParser;
import frontend.parser.parser.terminalParser.IdentParser;
import frontend.parser.struct.declaration.variable.initVal.InitVal;
import frontend.parser.struct.declaration.variable.varDef.VarDef;
import frontend.parser.struct.declaration.variable.varDef.VarDefEle;
import frontend.parser.struct.declaration.variable.varDef.VarDefInit;
import frontend.parser.struct.declaration.variable.varDef.VarDefNull;
import frontend.parser.struct.expression.ConstExp;
import frontend.parser.struct.terminal.Ident;

import java.util.ArrayList;

public class VarDefParser {
    /* VarDef Attributes */
    private Ident ident = null;
    private ArrayList<Token> leftBrackets = new ArrayList<>();
    private ArrayList<ConstExp> constExps = new ArrayList<>();
    private ArrayList<Token> rightBrackets = new ArrayList<>();
    /* Init Val */
    private Token eq = null;
    private InitVal initVal = null;
    /* VarDefEle */
    private VarDefEle varDefEle = null;

    /**
     *  VarDef → Ident { '[' ConstExp ']' } // 包含普通变量、一维数组、二维数组定义
     * | Ident { '[' ConstExp ']' } '=' InitVal
     * @return {@link VarDef}
     */
    public VarDef parseVarDef() {
        leftBrackets = new ArrayList<>();
        constExps = new ArrayList<>();
        rightBrackets = new ArrayList<>();

        ident = new IdentParser().parseIdent();
        Token token = TLIterator.readNextToken();
        while (token.getType().equals(LexType.LBRACK)) {
            leftBrackets.add(token);
            constExps.add(new ConstExpParser().parseConstExp());
            token = TLIterator.readNextToken();
            rightBrackets.add(token);
            token = TLIterator.readNextToken();
        }
        if (token.getType().equals(LexType.ASSIGN)) { // '='
            eq = token;
            initVal = new InitValParser().parseInitVal();
            varDefEle = new VarDefInit(ident, leftBrackets, constExps, rightBrackets, eq, initVal);
        } else {
            // token now is ';', need to backspace
            TLIterator.unReadToken(1);
            varDefEle = new VarDefNull(ident, leftBrackets, constExps, rightBrackets);
        }
        return new VarDef(varDefEle);
    }
}
