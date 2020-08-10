package awc.notationconverter;

public class Node {
    char data;
    Node left;
    Node right;

    Node(char data){
        this.data = data;
        left = null;
        right = null;
    }
}