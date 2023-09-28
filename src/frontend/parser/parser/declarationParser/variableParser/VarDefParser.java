package frontend.parser.parser.declarationParser.variableParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.parser.declarationParser.variableParser.InitValParser;
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
    private TokenListIterator iterator;
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

    public VarDefParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    public VarDef parseVarDef() {
        this.leftBrackets = new ArrayList<>();
        this.constExps = new ArrayList<>();
        this.rightBrackets = new ArrayList<>();
        IdentParser identParser = new IdentParser(this.iterator);
        this.ident = identParser.parseIdent();
        Token token = this.iterator.readNextToken();
        while (token.getType().equals(LexType.LBRACK)) {
            this.leftBrackets.add(token);
            ConstExpParser expParser = new ConstExpParser(this.iterator);
            this.constExps.add(expParser.parseConstExp());
            token = this.iterator.readNextToken();
            this.rightBrackets.add(token);
            token = this.iterator.readNextToken();
        }
        if (token.getType().equals(LexType.ASSIGN)) { // '='
            this.eq = token;
            InitValParser initValParser = new InitValParser(this.iterator);
            this.initVal = initValParser.parseInitVal();
            this.varDefEle = new VarDefInit(this.ident, this.leftBrackets,
                    this.constExps, this.rightBrackets, this.eq, this.initVal);
        } else {
            // token now is ';', need to backspace
            this.iterator.unReadToken(1);
            this.varDefEle = new VarDefNull(this.ident, this.leftBrackets,
                    this.constExps, this.rightBrackets);
        }
        return new VarDef(this.varDefEle);
    }
}
