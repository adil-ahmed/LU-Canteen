package com.example.a3rdyearproject.lucanteen.Class.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a3rdyearproject.lucanteen.Class.Activity.Admin;
import com.example.a3rdyearproject.lucanteen.Class.Activity.TabPenAdmin;
import com.example.a3rdyearproject.lucanteen.Class.JavaClass.Food;
import com.example.a3rdyearproject.lucanteen.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowItem extends Fragment {

    private ListView listView;
    public ShowItem() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_item, container, false);

        //listView = (ListView) view.findViewById(R.id.listView);
        /*listView = (ListView) listView.findViewById(R.id.listView);


        // dataReady();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        FirebaseListAdapter<Food> adapter = new FirebaseListAdapter<Food>(Admin,Food.class,
                R.layout.style_admin,databaseReference.child("Food"))
        {
            @Override
            protected void populateView(View v, Food model, int position)
            {
                TextView foodName = (TextView) v.findViewById(R.id.foodNameStyle);
                TextView foodPrice = (TextView) v.findViewById(R.id.foodPriceStyle);
                *//*foodName.setText(model.getFoodName());
                foodPrice.setText(model.getFoodPrice());*//*
                foodName.setText(model.getFoodName());
                foodPrice.setText(model.getFoodPrice());

            }


        };
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();*/
    }

}
