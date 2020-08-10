package awc.notationconverter;

import java.util.Stack;
public class InfixTree {

    Node root;

    InfixTree(Node root){
        this.root = root;
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

    InfixTree(String expr){ //constructor and parser for the input expression
        Stack<Character> cStack = new Stack<Character>();
        Stack<Node> nStack = new Stack<Node>();

        expr = parAdder(expr); //add outer parenthesis in case of none
        if(inputCheck(expr) == false){ //make sure input is valid
            root = null; //if input is invalid, set root to null
            return;
        }
        for(int i = 0; i < expr.length(); i++){ //character by character
            System.out.println(expr.charAt(i));
            if(expr.charAt(i) == ' ') continue; //skip whitespace
            if(isOpenPar(expr.charAt(i))){ //if character is '('
                System.out.println("pushing '(' to char stack");
                cStack.push(expr.charAt(i)); //push to char stack
            }
            else if(Character.isLetter(expr.charAt(i))) { //if operand
                System.out.println("pushing '" + expr.charAt(i) + "' to node stack");
                nStack.push(new Node(expr.charAt(i))); //create a new node and push to node stack
            }
            else if(isOp(expr.charAt(i))){ //if operator
                //if current operator's priority is <= that of the top of the char stack
                while(!cStack.isEmpty() && !isOpenPar(cStack.peek()) && priority(cStack.peek(), expr.charAt(i))){
                    System.out.println("rearranging priority");
                    Node parent = new Node(cStack.pop());
                    Node rChild = nStack.pop();
                    Node lChild = nStack.pop();

                    parent.right = rChild;
                    parent.left = lChild;

                    nStack.push(parent);
                }
                System.out.println("pushing '" + expr.charAt(i) + "' to char stack");
                cStack.push(expr.charAt(i)); //push the operator to char stack
            }
            else if(isClosePar(expr.charAt(i))){
                System.out.println("closing parenthesis");
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

    boolean isOpenPar(char c){
        if(c == '(') return true;
        else return false;
    }

    boolean isClosePar(char c){
        if(c == ')') return true;
        else return false;
    }

    boolean inputCheck(String expr){ //checks if expression is valid
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

    String parAdder(String input){
        return "(" + input + ")";
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

}
