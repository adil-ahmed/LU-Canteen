package com.example.a3rdyearproject.lucanteen.Class.Adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.a3rdyearproject.lucanteen.R;

import java.util.ArrayList;

/**
 * Created by Adil on 5/3/2017.
 */

public class CustomListAdapter extends BaseAdapter
{

    private Activity activity;
    private ArrayList<String> foodName, foodPrice;
    public CustomListAdapter(Activity activity,
                             ArrayList<String> foodName,
                             ArrayList<String> foodPrice) {
        this.activity = activity;
        this.foodName = foodName;
        this.foodPrice= foodPrice;
    }

    @Override
    public int getCount() {
        return foodName.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if(view == null){
            view = activity.getLayoutInflater().inflate(R.layout.style_admin, viewGroup, false);// style layout contain the custom design
        }
        TextView foodNameText = (TextView) view.findViewById(R.id.foodNameStyle);
        TextView foodPriceText = (TextView) view.findViewById(R.id.foodPriceStyle);


        foodNameText.setText(foodName.get(i));
        foodPriceText.setText(foodPrice.get(i));

        return view;
    }
}
