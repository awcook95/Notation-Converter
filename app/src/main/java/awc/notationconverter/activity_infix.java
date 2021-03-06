package awc.notationconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class activity_infix extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infix);
    }

    public void onClkConvert(View view){
        EditText userInfix = findViewById(R.id.editInput); //get user input from EditText
        ExprTree exprTree = new ExprTree();
        exprTree.parseInfix(userInfix.getText().toString()); //parse user input to generate tree

        TextView prefixResult = findViewById(R.id.textResultPrefix); //get output TextViews
        TextView postfixResult = findViewById(R.id.textResultPostfix);

        if(exprTree.root == null){ //invalid inputs will result in a null root
            prefixResult.setText("Invalid Input");
            postfixResult.setText("Invalid Input");
        }
        else{ //set output of TextViews
            prefixResult.setText("Prefix: " + exprTree.preOrder());
            postfixResult.setText("Postfix: " + exprTree.postOrder());
        }

    }
}