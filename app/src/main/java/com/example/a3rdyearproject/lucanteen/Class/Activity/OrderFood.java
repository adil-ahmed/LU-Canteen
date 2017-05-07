package com.example.a3rdyearproject.lucanteen.Class.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.a3rdyearproject.lucanteen.Class.JavaClass.Food;
import com.example.a3rdyearproject.lucanteen.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class OrderFood extends AppCompatActivity implements OnItemSelectedListener, AdapterView.OnItemClickListener  {
    Spinner foodCategory;
    EditText quantity,address,phoneNumber;
    Button amount;
    Firebase firebase;
   public ArrayList<String> foodNameList;
    public ArrayList<String> foodPriceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);
        foodCategory = (Spinner) findViewById(R.id.orderSpinner);
        quantity = (EditText) findViewById(R.id.foodQuantity);
        address = (EditText) findViewById(R.id.address);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        amount = (Button) findViewById(R.id.amount);



        //foodCategory.setOnItemClickListener(this);
        foodCategory.setOnItemSelectedListener(this);


        // Spinner Drop down elements
        ArrayList<String> categories = new ArrayList<String>();
        /*Admin admin = new Admin();
        for(int i=0; i<admin.foodNameList.size(); i++)
        {
            categories.add(admin.foodNameList.get(i));
        }*/

        categories.add("Singara");
        categories.add("Somosa");
        categories.add("Burger");
        categories.add("Sandwich");
        categories.add("Hot dog");
        categories.add("Petis");






        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        foodCategory.setAdapter(dataAdapter);

    }














    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        /*getMenuInflater().inflate(R.menu.main, menu);
        return true;*/
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logOut)
        {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(OrderFood.this,UserSignIn.class);
            startActivity(intent);
        }





        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // On selecting a spinner item
        String item = adapterView.getItemAtPosition(i).toString();

        // Showing selected spinner item
        Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
