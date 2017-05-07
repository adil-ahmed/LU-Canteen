package com.example.a3rdyearproject.lucanteen.Class.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a3rdyearproject.lucanteen.Class.Adapter.CustomListAdapter;
import com.example.a3rdyearproject.lucanteen.Class.JavaClass.Food;
import com.example.a3rdyearproject.lucanteen.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderFood extends AppCompatActivity implements OnItemSelectedListener, AdapterView.OnItemClickListener {
    Spinner foodCategory;
    EditText quantity, address, phoneNumber;
    Button amount;
    Firebase firebase;
    String foodItemName;
    private ProgressDialog progressDialog;
    String foodPrice;
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



        dataReady();


        foodCategory.setOnItemSelectedListener(this);


        firebase = new Firebase("https://lu-canteen.firebaseio.com/FoodOrder");

        amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                final String addressText = address.getText().toString();
                final String phoneNumberText = phoneNumber.getText().toString();
                final String quantityText = quantity.getText().toString();
                int foodPriceInt = Integer.parseInt(foodPrice); // convert to int
                int foodQuantityInt = Integer.parseInt(quantityText); // convert to int
                int amountToPay = foodPriceInt*foodQuantityInt;
                final String amountToPayText = Integer.toString(amountToPay);


                if(addressText.equals(null) || phoneNumberText.equals(null) || quantityText.equals(null))
                {
                    Toast.makeText(OrderFood.this, "Insert value", Toast.LENGTH_SHORT).show();
                }
                else if(foodItemName.equals(null))
                {
                    Toast.makeText(OrderFood.this, "Please select a food item", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    AlertDialog.Builder builder = new AlertDialog.Builder(OrderFood.this);
                    builder.setMessage("Total amount is : "+amountToPay);
                    //builder.setTitle("Amount of money");
                    builder.setPositiveButton("Order", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {

                            Map<String, String> info = new HashMap<String, String>();

                            info.put("Food name",foodItemName); //inserting name
                            info.put("Price of food per piece",foodPrice); //inserting name
                            info.put("Quantity of food",quantityText); // inserting price
                            info.put("Total amount",amountToPayText); // inserting price
                            info.put("Customer address",addressText); // inserting price
                            info.put("Customer phone number",phoneNumberText); // inserting price


                            firebase.push().setValue(info); // inserting in firebase
                            Toast.makeText(OrderFood.this, "Order Successful", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(OrderFood.this, NormalUser.class);
                            startActivity(intent);

                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                           // finish();
                            Toast.makeText(OrderFood.this, "Order Cancelled", Toast.LENGTH_SHORT).show();

                        }
                    });
                    builder.show();
                }


            }
        });







    }


    public void dataReady() {

        foodNameList = new ArrayList<String>();
        foodPriceList = new ArrayList<String>();

        firebase = new Firebase("https://lu-canteen.firebaseio.com/Food");

        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                progressDialog = new ProgressDialog(OrderFood.this);
                progressDialog.setMessage("Loading");
                progressDialog.show();
                for (DataSnapshot studentSnapshot : dataSnapshot.getChildren())
                {
                    Food food = studentSnapshot.getValue(Food.class);
                    // String str = studentSnapshot.getKey();

                    // Log.e("key", str);

                    foodNameList.add(food.getFoodName()); // from student class taking the list of name
                    foodPriceList.add(food.getFoodPrice()); // from student class taking the list of age
                    //Toast.makeText(getApplicationContext(),student.getName()+" "+student.getAge(),Toast.LENGTH_SHORT).show();
                }

                if (foodNameList.size() > 0)
                {
                    // Creating adapter for spinner
                    progressDialog.hide();
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(OrderFood.this, android.R.layout.simple_spinner_item, foodNameList);

                    // Drop down layout style - list view with radio button
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // attaching data adapter to spinner
                    foodCategory.setAdapter(dataAdapter);

                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // On selecting a spinner item
        foodItemName = adapterView.getItemAtPosition(i).toString();
        foodPrice = foodPriceList.get(i);






        // Showing selected spinner item
       // Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
        if (id == R.id.logOut) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(OrderFood.this, UserSignIn.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }



}
