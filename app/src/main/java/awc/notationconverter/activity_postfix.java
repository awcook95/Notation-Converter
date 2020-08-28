package awc.notationconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class activity_postfix extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postfix);
    }

    public void onClkConvert(View view){
        EditText userPostfix = findViewById(R.id.editInput); //get user input from EditText
        ExprTree exprTree = new ExprTree();
        exprTree.parsePostfix(userPostfix.getText().toString()); //parse user input to generate tree

        TextView infixResult = findViewById(R.id.textResultInfix); //get output TextViews
        TextView prefixResult = findViewById(R.id.textResultPrefix);

        if(exprTree.root == null){ //invalid inputs will result in a null root
            infixResult.setText("Invalid Input");
            prefixResult.setText("Invalid Input");
        }
        else{ //set output of TextViews
            infixResult.setText("Prefix: " + exprTree.inOrder());
            prefixResult.setText("Postfix: " + exprTree.preOrder());
        }

    }
}