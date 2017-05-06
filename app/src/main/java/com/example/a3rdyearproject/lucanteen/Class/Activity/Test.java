package com.example.a3rdyearproject.lucanteen.Class.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a3rdyearproject.lucanteen.R;

public class Test extends AppCompatActivity {

    Button insert,see;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        insert= (Button) findViewById(R.id.insert);
        see = (Button) findViewById(R.id.see);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Test.this,InsertDataAdminPanel.class);
                startActivity(intent);
            }
        });
        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Test.this,Admin.class);
                startActivity(intent);
            }
        });
    }
}
