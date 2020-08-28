package awc.notationconverter;
import java.util.Stack;
public class ExprTree {

    Node root;

    ExprTree(){
        this.root = null;
    }

    void parseInfix(String expr){ //constructor and parser for the input expression
        Stack<Character> cStack = new Stack<Character>();
        Stack<Node> nStack = new Stack<Node>();

        expr = parAdder(expr); //add outer parenthesis in case there are none
        if(validInfix(expr) == false){ //make sure input is valid
            this.root = null; //if input is invalid, set root to null
            return;
        }
        for(int i = 0; i < expr.length(); i++){ //character by character
            if(expr.charAt(i) == ' ') continue; //skip whitespace
            if(isOpenPar(expr.charAt(i))){ //if character is '('
                cStack.push(expr.charAt(i)); //push to char stack
            }
            else if(Character.isLetter(expr.charAt(i))) { //if operand
                nStack.push(new Node(expr.charAt(i))); //create a new node and push to node stack
            }
            else if(isOp(expr.charAt(i))){ //if operator
                //if current operator's priority is <= that of the top of the char stack
                while(!cStack.isEmpty() && !isOpenPar(cStack.peek()) && priority(cStack.peek(), expr.charAt(i))){
                    Node parent = new Node(cStack.pop());
                    Node rChild = nStack.pop();
                    Node lChild = nStack.pop();

                    parent.right = rChild;
                    parent.left = lChild;

                    nStack.push(parent);
                }
                cStack.push(expr.charAt(i)); //push the operator to char stack
            }
            else if(isClosePar(expr.charAt(i))){
                while((!cStack.empty())){
                    if(isOpenPar(cStack.peek())) break;
                    Node parent = new Node(cStack.pop());
                    Node rChild = nStack.pop();
                    Node lChild = nStack.pop();

                    parent.right = rChild;
                    parent.left = lChild;

                    nStack.push(parent);
                }
                cStack.pop(); //remove the '(' that paired with the encountered ')'
            }
        }
        root = nStack.peek(); //set root to last node in node stack
    }

    void parsePrefix(String expr){
        if(validPrefix(expr) == false){ //make sure input is valid
            this.root = null;
            return;
        }
        Stack<Node> nStack = new Stack<Node>();
        for(int i = expr.length()-1; i >= 0; i--){ //iterate backwards through input
            if(expr.charAt(i) == ' ') continue; //skip whitespace
            if(Character.isLetter(expr.charAt(i))){ //found operand
                Node cNode = new Node(expr.charAt(i));
                nStack.push(cNode);
            }
            else if(isOp(expr.charAt(i))){ //found operator
                Node parent = new Node(expr.charAt(i));
                Node lChild = nStack.pop(); //left goes first since parsing backwards
                Node rChild = nStack.pop();

                parent.right = rChild;
                parent.left = lChild;
                nStack.push(parent);
            }
            else{ //invalid character
                this.root = null;
            }
        }
        this.root = nStack.pop();
    }

    void parsePostfix(String expr){
        if(validPostfix(expr) == false){ //make sure input is valid
            this.root = null;
            return;
        }
        Stack<Node> nStack = new Stack<Node>();
        for(int i = 0; i < expr.length(); i++){
            if(expr.charAt(i) == ' ') continue; //skip whitespace
            if(Character.isLetter(expr.charAt(i))){ //found operand
                Node cNode = new Node(expr.charAt(i));
                nStack.push(cNode);
            }
            else if(isOp(expr.charAt(i))){ //found operator
                Node parent = new Node(expr.charAt(i));
                Node rChild = nStack.pop();
                Node lChild = nStack.pop();

                parent.right = rChild;
                parent.left = lChild;
                nStack.push(parent);
            }
            else{ //invalid character
                this.root = null;
            }
        }
        this.root = nStack.pop();

    }

    String inOrder(){
        StringBuilder string = new StringBuilder();
        return inOrderRec(string, root);
    }

    private String inOrderRec(StringBuilder string, Node node){
        if(isOp(node.data)) string.append('('); //add parentheses around each operation
        if(node.left != null) inOrderRec(string, node.left);
        string.append(node.data);
        if(node.right != null) inOrderRec(string, node.right);
        if(isOp(node.data)) string.append(')');
        return string.toString();
    }

    String preOrder(){
        StringBuilder string = new StringBuilder();
        return preOrderRec(string, root);
    }

    private String preOrderRec(StringBuilder string, Node node){
        string.append(node.data);
        if(node.left != null) preOrderRec(string, node.left);
        if(node.right != null) preOrderRec(string, node.right);
        return string.toString();
    }

    String postOrder(){
        StringBuilder string = new StringBuilder();
        return postOrderRec(string, root);
    }

    private String postOrderRec(StringBuilder string, Node node){
        if(node.left != null) postOrderRec(string, node.left);
        if(node.right != null) postOrderRec(string, node.right);
        string.append(node.data);
        return string.toString();
    }

    boolean validInfix(String expr){ //checks if expression has matching operands/operators/parentheses and is not empty
        int operatorCount = 0;
        int operandCount = 0;
        int openParCount = 0;
        int closeParCount = 0;

        for(int i = 0; i < expr.length(); i++){
            char c = expr.charAt(i);
            if(c == ' ') continue; //ignore whitespace
            if(!isOp(c) && !isOpenPar(c) && !isClosePar(c) && !Character.isLetter(c)){
                return false;
            }
            if(isOp(c)) operatorCount++;
            if(Character.isLetter(c)) operandCount++;
            if(isOpenPar(c)) openParCount++;
            if(isClosePar(c)) closeParCount++;
            if(closeParCount > openParCount) return false;
        }
        if(operandCount - operatorCount != 1) return false;
        if(openParCount != closeParCount) return false;
        else return true;
    }

    boolean validPrefix(String expr){
        int operatorCount = 0;
        int operandCount = 0;
        for(int i = expr.length()-1; i >=0; i--){
            char c = expr.charAt(i);
            if(c == ' ') continue; //ignore whitespace
            if(Character.isLetter(c)){
                operandCount++;
            }
            else if(isOp(c)){
                operatorCount++;
            }
            else{ //character is not operator or operand
                return false;
            }
            if(operatorCount >= operandCount){ //if not enough operands per operator
                return false;
            }

        }
        if(operandCount - operatorCount != 1) return false;
        return true;
    }

    boolean validPostfix(String expr){
        int operatorCount = 0;
        int operandCount = 0;
        for(int i = 0; i < expr.length(); i++){
            char c = expr.charAt(i);
            if(c == ' ') continue; //ignore whitespace
            if(Character.isLetter(c)){
                operandCount++;
            }
            else if(isOp(c)){
                operatorCount++;
            }
            else{ //character is not operator or operand
                return false;
            }
            if(operatorCount >= operandCount){ //if not enough operands per operator
                return false;
            }

        }
        if(operandCount - operatorCount != 1) return false;
        return true;
    }

    boolean isOp(char s){
        if(s == '+' || s == '-' || s == '*' || s == '/')
            return true;
        else return false;
    }

    boolean priority(Character op1, Character op2){ //returns true if 1st arg is same or higher priority
        int temp1 = 0;
        int temp2 = 0;
        if(op1 == '/' || op1 == '*') temp1 = 1;
        if(op2 == '/' || op2 == '*') temp2 = 1;

        if(temp1 >= temp2) return true;
        else return false;
    }

    boolean isOpenPar(char c){
        if(c == '(') return true;
        else return false;
    }

    boolean isClosePar(char c){
        if(c == ')') return true;
        else return false;
    }

    String parAdder(String input){
        return "(" + input + ")";
    }

}
