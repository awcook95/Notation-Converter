Author: Alexander Cook

Description: This simple android app converts between infix/prefix/postfix math expressions

How it works: For each notation a function parses the input string into an expression tree.
An expression tree is a data structure where each parent node is an operator and each child node
is an operand. To generate the output, recursive inorder/preorder/postorder tree traversals are
called.