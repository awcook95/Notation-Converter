package awc.notationconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnInfix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInfix = (Button)findViewById(R.id.btnInfix);
        btnInfix.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openActivityInfix();
            }
        });
    }

    void openActivityInfix(){
        Intent intent = new Intent(this, activity_infix.class);
        startActivity(intent);
    }
}