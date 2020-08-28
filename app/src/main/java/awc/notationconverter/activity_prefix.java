package awc.notationconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class activity_prefix extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefix);
    }

    public void onClkConvert(View view){
        EditText userPrefix = findViewById(R.id.editInput); //get user input from EditText
        ExprTree exprTree = new ExprTree();
        exprTree.parsePrefix(userPrefix.getText().toString()); //parse user input to generate tree

        TextView infixResult = findViewById(R.id.textResultInfix); //get output TextViews
        TextView postfixResult = findViewById(R.id.textResultPostfix);

        if(exprTree.root == null){ //invalid inputs will result in a null root
            infixResult.setText("Invalid Input");
            postfixResult.setText("Invalid Input");
        }
        else{ //set output of TextViews
            infixResult.setText("Prefix: " + exprTree.inOrder());
            postfixResult.setText("Postfix: " + exprTree.postOrder());
        }

    }
}