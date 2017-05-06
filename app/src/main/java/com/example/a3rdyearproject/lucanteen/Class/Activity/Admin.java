package com.example.a3rdyearproject.lucanteen.Class.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.util.ArrayMap;

import com.example.a3rdyearproject.lucanteen.Class.Adapter.CustomListAdapter;
import com.example.a3rdyearproject.lucanteen.Class.JavaClass.Food;
import com.example.a3rdyearproject.lucanteen.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Admin extends AppCompatActivity {

    private Firebase firebase;
    private ListView listView;
    //private ArrayList<String> foodNameList;
    //private ArrayList<String> foodPriceList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        listView = (ListView) findViewById(R.id.listView);


       // dataReady();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        FirebaseListAdapter<Food> adapter = new FirebaseListAdapter<Food>(Admin.this,Food.class,
                R.layout.style_admin,databaseReference.child("Food"))
        {
            @Override
            protected void populateView(View v, Food model, int position)
            {
                TextView foodName = (TextView) v.findViewById(R.id.foodNameStyle);
                TextView foodPrice = (TextView) v.findViewById(R.id.foodPriceStyle);
                /*foodName.setText(model.getFoodName());
                foodPrice.setText(model.getFoodPrice());*/
                foodName.setText(model.getFoodName());
                foodPrice.setText(model.getFoodPrice());

            }


        };
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

   /* public void dataReady(){

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
                    *//*String str = studentSnapshot.getKey();

                    Log.e("key", str);*//*

                    foodNameList.add(food.getFoodName()); // from student class taking the list of name
                    foodPriceList.add(food.getFoodPrice()); // from student class taking the list of age
                    *//*Toast.makeText(getApplicationContext(),student.getName()+" "+student.getAge(),Toast.LENGTH_SHORT).show();*//*
                }

                if(foodNameList.size()>0) {
                    CustomListAdapter customListAdapter = new CustomListAdapter(Admin.this,
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
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
        //MenuInflater inflater = getMenuInflater();

        //inflater.inflate(R.menu.menu, menu);

        //return true;

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
            Intent intent = new Intent(Admin.this,UserSignIn.class);
            startActivity(intent);
        }
        else if(id == R.id.adminPanel)
        {
            Intent intent = new Intent(Admin.this,InsertDataAdminPanel.class);
            startActivity(intent);

        }



        return super.onOptionsItemSelected(item);
    }


}
