package com.example.a3rdyearproject.lucanteen.Class.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.a3rdyearproject.lucanteen.Class.Adapter.CustomListAdapter;
import com.example.a3rdyearproject.lucanteen.Class.Adapter.CustomListAdapter2;
import com.example.a3rdyearproject.lucanteen.Class.JavaClass.Food;
import com.example.a3rdyearproject.lucanteen.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NormalUser extends AppCompatActivity
{

    private Firebase firebase;
    private ListView listView;
    ProgressDialog progressDialog;
    //private ArrayList<String> foodNameList;
    //private ArrayList<String> foodPriceList;
    //private Switch sswitch;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_user);
        listView = (ListView) findViewById(R.id.listView);
        //sswitch = (Switch) findViewById(R.id.available);
        progressDialog = new ProgressDialog(NormalUser.this);
        progressDialog.setMessage("Loading");
        progressDialog.show();


       // dataReady();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        FirebaseListAdapter<Food> adapter = new FirebaseListAdapter<Food>(NormalUser.this,Food.class,
                R.layout.style_normal,databaseReference.child("Food"))
        {
            @Override
            protected void populateView(View v, Food model, int position)
            {
                TextView foodName = (TextView) v.findViewById(R.id.foodNameStyle);
                TextView foodPrice = (TextView) v.findViewById(R.id.foodPriceStyle);
                foodName.setText(model.getFoodName());
                foodPrice.setText(model.getFoodPrice());
                progressDialog.hide();

            }


        };
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    /*public void dataReady()
    {

        foodNameList = new ArrayList<String>();
        foodPriceList = new ArrayList<String>();

        firebase = new Firebase("https://lu-canteen.firebaseio.com/Food");

        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for(DataSnapshot studentSnapshot: dataSnapshot.getChildren())
                {
                    Food food = studentSnapshot.getValue(Food.class);

                    foodNameList.add(food.getFoodName()); // from student class taking the list of name
                    foodPriceList.add(food.getFoodPrice()); // from student class taking the list of age
                    *//*Toast.makeText(getApplicationContext(),student.getName()+" "+student.getAge(),Toast.LENGTH_SHORT).show();*//*
                }

                if(foodNameList.size()>0) {
                    CustomListAdapter2 customListAdapter = new CustomListAdapter2(NormalUser.this,
                            foodNameList, foodPriceList);
                    listView.setAdapter(customListAdapter); // to make custom design go to CustomlistAdapter class
                    customListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError)
            {

            }
        });
    }*/
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
            Intent intent = new Intent(NormalUser.this,UserSignIn.class);
            startActivity(intent);
        }
        else if (id == R.id.orderFood)
        {
            Intent intent = new Intent(NormalUser.this,OrderFood.class);
            startActivity(intent);
        }




        return super.onOptionsItemSelected(item);
    }

}

