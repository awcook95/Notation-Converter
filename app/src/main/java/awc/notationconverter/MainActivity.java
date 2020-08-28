package awc.notationconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnInfix = findViewById(R.id.btnInfix);
        btnInfix.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openActivityInfix();
            }
        });

        Button btnPrefix = findViewById(R.id.btnPrefix);
        btnPrefix.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openActivityPrefix();
            }
        });

        Button btnPostfix = findViewById(R.id.btnPostfix);
        btnPostfix.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openActivityPostfix();
            }
        });
    }

    void openActivityInfix(){
        Intent intent = new Intent(this, activity_infix.class);
        startActivity(intent);
    }

    void openActivityPrefix(){
        Intent intent = new Intent(this, activity_prefix.class);
        startActivity(intent);
    }

    void openActivityPostfix(){
        Intent intent = new Intent(this, activity_postfix.class);
        startActivity(intent);
    }
}