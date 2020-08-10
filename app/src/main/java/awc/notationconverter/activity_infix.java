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
        InfixTree infixTree = new InfixTree(userInfix.getText().toString()); //parse string

        TextView prefixResult = findViewById(R.id.textResultPrefix); //get output TextViews
        TextView postfixResult = findViewById(R.id.textResultPostfix);

        if(infixTree.root == null){ //valid input check
            prefixResult.setText("Invalid Input");
            postfixResult.setText("Invalid Input");
        }
        else{ //set output of TextViews
            prefixResult.setText("Prefix: " + infixTree.preOrder());
            postfixResult.setText("Postfix: " + infixTree.postOrder());
        }

    }
}