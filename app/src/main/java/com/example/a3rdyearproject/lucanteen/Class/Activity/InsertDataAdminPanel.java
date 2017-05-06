package com.example.a3rdyearproject.lucanteen.Class.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a3rdyearproject.lucanteen.R;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;

public class InsertDataAdminPanel extends AppCompatActivity
{

    private Firebase firebase;
    private EditText foodName,foodPrice;
    private Button insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data_admin_panel);
        foodName = (EditText) findViewById(R.id.foodName);
        foodPrice = (EditText) findViewById(R.id.foodPrice);
        insert = (Button) findViewById(R.id.insert);


       firebase = new Firebase("https://lu-canteen.firebaseio.com/Food");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                String foodNameText = foodName.getText().toString();
                String foodPriceText = foodPrice.getText().toString();

                if(foodNameText.equals(null) || foodPriceText.equals(null))
                {
                    Toast.makeText(InsertDataAdminPanel.this, "Insert value", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Map<String, String> info = new HashMap<String, String>();
                    info.put("foodName",foodNameText); //inserting name
                    info.put("foodPrice",foodPriceText); // inserting price
                    //info.put("Avaiability","yes"); // inserting price

                    firebase.push().setValue(info); // inserting in firebase

                    Intent intent = new Intent(InsertDataAdminPanel.this, Admin.class);
                    startActivity(intent);
                }




            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if(id == android.R.id.home)
        {
            onBackPressed();
        }


        return super.onOptionsItemSelected(item);
    }
}
