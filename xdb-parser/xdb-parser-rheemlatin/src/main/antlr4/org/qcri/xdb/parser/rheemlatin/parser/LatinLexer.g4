lexer grammar LatinLexer;

tokens {
 PREOP, POSTOP, BINOP, ERROR, OPERATOR_NAME, FUNC_NAME, PLATFORM }

@header {
import java.util.*;
}


@members {

    private static final int    BINARY  = 0;
    private static List<String> binaryOperators  = new ArrayList<>();
    private static List<String> prefixOperators  = new ArrayList<>();
    private static List<String> postfixOperators = new ArrayList<>();
    private static List<String> nameOperators    = new ArrayList<>();
    private static List<String> nameFunctions    = new ArrayList<>();

    private static Map<String, Integer>      map = new HashMap<>();


    public static void addBinaryOperator(String operator){
        binaryOperators.add(operator);
        map.put(operator, 1);
    }

    public static void addPrefixOperator(String operator){
        prefixOperators.add(operator);
        map.put(operator, 2);
    }

    public static void addPostfixOperator(String operator){
        postfixOperators.add(operator);
        map.put(operator, 3);
    }

    public static void addOperator(String name){
        nameOperators.add(name);
        map.put(name, 4);
    }

    public static void addFunction(String name){
        nameFunctions.add(name);
        map.put(name, 5);
    }

    public static void addPlatform(String name){
        map.put(name, 6);
    }

    private Deque<Token> deque = new LinkedList<Token>();

    private Token previousToken;
    private Token nextToken;

    @Override
    public Token nextToken() {
        Token next;
        if (!deque.isEmpty()) {
            next = deque.pollFirst();
        }else{
            next = super.nextToken();
        }

        if (next.getType() != SYMBOL) {
            return previousToken = next;
        }

        StringBuilder builder = new StringBuilder();
        int anterior = next.getStartIndex();
        boolean flag = true;
        while (next.getType() == SYMBOL && flag == true) {
            if(next.getStartIndex() - anterior > 1){
                flag = false;
            }else {
                builder.append(next.getText());
                anterior = next.getStartIndex();
                next = super.nextToken();
            }
        }
        deque.addLast(nextToken = next);

        List<Token> tokens = findOperatorCombination(builder.toString(), getOperatorType(builder.toString()));
        for (int i = tokens.size() - 1; i >= 0; i--) {
            deque.addFirst(tokens.get(i));
        }
        return deque.pollFirst();
    }


    private static List<Token> findOperatorCombination(String sequence, OperatorType type) {
        switch (type) {
        case POSTFIX:
            return getPostfixCombination(sequence);
        case PREFIX:
            return getPrefixCombination(sequence);
        case BINARY:
            return getBinaryCombination(sequence);
        case OPERATOR:
            return getOperatorCombination(sequence);
        case FUNCTION:
            return getFunctionCombination(sequence);
        case PLATFORM:
            List<Token> seq = new ArrayList<Token>();
            seq.add(0, new CommonToken(LatinParser.PLATFORM, sequence));
            return seq;
        default:
            break;
        }
        return null;
    }

    private static List<Token> getOperatorCombination(String sequence){
        List<Token> seq = new ArrayList<Token>();
        seq.add(0, new CommonToken(LatinParser.OPERATOR_NAME, sequence));
        return seq;
    }

    private static List<Token> getFunctionCombination(String sequence){
        List<Token> seq = new ArrayList<Token>();
        seq.add(0, new CommonToken(LatinParser.FUNC_NAME, sequence));
        return seq;
    }
    private static List<Token> getPrefixCombination(String sequence) {
        if (isPrefixOperator(sequence)) {
            List<Token> seq = new ArrayList<Token>(1);
            seq.add(0, new CommonToken(LatinParser.PREOP, sequence));
            return seq;
        }
        if (sequence.length() <= 1) {
            return null;
        }

        for (int i = 1; i < sequence.length(); i++) {
            List<Token> seq1 = getPrefixCombination(sequence.substring(0, i));
            List<Token> seq2 = getPrefixCombination(sequence.substring(i, sequence.length()));
            if (seq1 != null & seq2 != null) {
                seq1.addAll(seq2);
                return seq1;
            }
        }
        return null;
    }

    private static List<Token> getPostfixCombination(String sequence) {
        if (isPostfixOperator(sequence)) {
            List<Token> seq = new ArrayList<Token>(1);
            seq.add(0, new CommonToken(LatinParser.POSTOP, sequence));
            return seq;
        }
        if (sequence.length() <= 1) {
            return null;
        }

        for (int i = 1; i < sequence.length(); i++) {
            List<Token> seq1 = getPostfixCombination(sequence.substring(0, i));
            List<Token> seq2 = getPostfixCombination(sequence.substring(i, sequence.length()));
            if (seq1 != null && seq2 != null) {
                seq1.addAll(seq2);
                return seq1;
            }
        }
        return null;
    }


    private static List<Token> getBinaryCombination(String sequence) {
        for (int i = 0; i < sequence.length(); i++) { // i is number of postfix spaces
            for (int j = 0; j < sequence.length() - i; j++) { // j is number of prefix spaces
                String seqPost = sequence.substring(0, i);
                List<Token> post = getPostfixCombination(seqPost);

                String seqPre = sequence.substring(sequence.length()-j, sequence.length());
                List<Token> pre = getPrefixCombination(seqPre);

                String seqBin = sequence.substring(i, sequence.length()-j);

                if ((post != null || seqPost.isEmpty()) &&
                    (pre != null || seqPre.isEmpty()) &&
                    isBinaryOperator(seqBin)) {
                    List<Token> res = new ArrayList<Token>();
                    if (post != null)
                        res.addAll(post);
                    res.add(new CommonToken(LatinParser.BINOP, seqBin));
                    if (pre != null)
                        res.addAll(pre);
                    return res;
                }
            }
        }
        return null;
    }


    /**
     * Returns the expected operator type based on the previous and next token
     */
    private OperatorType getOperatorType(String operator) {
       /* if (isAfterAtom()) {
            if (isBeforeAtom()) {
                return OperatorType.BINARY;
            }
            return OperatorType.POSTFIX;
        }
        return OperatorType.PREFIX;*/
        switch(map.get(operator)){
            case 1:
                return OperatorType.BINARY;
            case 2:
                return OperatorType.PREFIX;
            case 3:
                return OperatorType.POSTFIX;
            case 4:
                return OperatorType.OPERATOR;
            case 5:
                return OperatorType.FUNCTION;
            case 6:
                return OperatorType.PLATFORM;
            default:
                break;
        }
        return null;
    }

    private enum OperatorType { BINARY, PREFIX, POSTFIX, OPERATOR, FUNCTION, PLATFORM };


    /**
     * Checks whether the current token is a token found at the start of atom elements
     * @return
     */
    private boolean isBeforeAtom() {
        int tokenType = nextToken.getType();
        return tokenType == LatinParser.INT ||
                tokenType == LatinParser.PLEFT;
    }

    /**
     * Checks whether the current token is a token found at the end of atom elements
     * @return
     */
    private boolean isAfterAtom() {
        int tokenType = previousToken.getType();
        return tokenType == LatinParser.INT ||
                tokenType == LatinParser.PRIGHT;

    }

    private static boolean isBinaryOperator(String operator) {
        return binaryOperators.contains(operator);
    }
    private static boolean isPrefixOperator(String operator) {
        return prefixOperators.contains(operator);
    }
    private static boolean isPostfixOperator(String operator) {
        return postfixOperators.contains(operator);
    }

}

NUMBER    :   ( '0'..'9' )+   ;
PLEFT     :   '(';
PRIGHT    :   ')';
ACCO_LEFT :   '{';
ACCO_RIGHT:   '}';
COR_LEFT  :   '[';
COR_RIGHT :   ']';
LOAD      :   'LOAD';//|'load';
AS        :   'AS';//|'as';
COLON     :   ':';
COMA      :   ',';
SEMI_COLON:   ';';
ID        :   ('a'..'z') ('a'..'z'|'A'..'Z'|'0'..'9'|'_'|'-')*;
ASSING    :   '=';
ARROW     :   '->';
ARROW_I   :   '<-';
BOOLEAN   :   'BOOLEAN';//|'boolean';
INT       :   'INT';//|'int';
LONG      :   'LONG';//|'long';
FLOAT     :   'FLOAT';//|'float';
BIGDECIMAL:   'BIGDECIMAL';//|'bigdecimal';
BIGINTEGER:   'BIGINTEGER';//|'biginteger';
DOUBLE    :   'DOUBLE';//|'double';
DATETIME  :   'DATETIME';//|'datetime';
STRING    :   'STRING';//|'string';
JSON      :   'JSON';//|'json';
SQL       :   'SQL';//|'sql';
WITH      :   'WITH';//|'with';
DOT       :   '.';
TRUE      :   'TRUE';//'true'|'TRUE'|'True';
FALSE     :   'FALSE' ;//'false'|'FALSE'|'False';
BAG       :   'BAG';//|'bag';
IMPORT    :   'IMPORT';//|'import'; //import an unique class or method for work in the rheemlatin
INCLUDE   :   'INCLUDE';//|'include'; //import a script of the rheemLatin inside of other is like copy and paste with parameters
//deberia ser algo al estilo ID = INCLUDE 'path' ID*;
BROADCAST :   'BROADCAST';//|'broadcast';
PLATFORM  :   'PLATFORM';//|'platform';
DELIMITER :   'DELIMITER';
KEY       :   'KEY';
LIKE      :   'LIKE';
FOR       :   'FOR';




QUOTEDSTRING :  '\'' .*?  '\''


/*(   ( ~ ( '\'' | '\\' | '\n' | '\r' ) )
                       | ( '\\' ( ( 'N' | 'T' | 'B' | 'R' | 'F' | '\\' | '\'' ) ) )
                       | ( '\\U' ( '0'..'9' | 'A'..'F' )
                                 ( '0'..'9' | 'A'..'F' )
                                 ( '0'..'9' | 'A'..'F' )
                                 ( '0'..'9' | 'A'..'F' )  )
                     )* */

;

MULTILINE_QUOTEDSTRING :  '\'' (   ( ~ ( '\'' | '\\' ) )
                                 | ( '\\' ( ( 'N' | 'T' | 'B' | 'R' | 'F' | '\\' | '\'' | 'n' | 'r' ) ) )
                                 | ( '\\U' ( '0'..'9' | 'A'..'F' )
                                           ( '0'..'9' | 'A'..'F' )
                                           ( '0'..'9' | 'A'..'F' )
                                           ( '0'..'9' | 'A'..'F' )  )
                               )*
                '\''
;
/*
COMMENT : '/*' (   ( ~ ( '\'' | '\\' ) )
                                                 | ( '\\' ( ( 'N' | 'T' | 'B' | 'R' | 'F' | '\\' | '\'' | 'n' | 'r' ) ) )
                                                 | ( '\\U' ( '0'..'9' | 'A'..'F' )
                                                           ( '0'..'9' | 'A'..'F' )
                                                           ( '0'..'9' | 'A'..'F' )
                                                           ( '0'..'9' | 'A'..'F' )  )
                                               )* '* /' -> skip;
*/


COMMENT : '/*' .*? '*/' -> skip;
LINE_COMMENT : '//' ~[\r\n]* -> skip;
WS        :   [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

SYMBOL
    :  . | '_'
;



