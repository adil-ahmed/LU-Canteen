package com.example.a3rdyearproject.lucanteen.Class.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import com.example.a3rdyearproject.lucanteen.Class.Adapter.ViewPagerAdapter;
import com.example.a3rdyearproject.lucanteen.Class.Fragment.InsertItem;
import com.example.a3rdyearproject.lucanteen.Class.Fragment.ShowItem;
import com.example.a3rdyearproject.lucanteen.R;

public class TabPenAdmin extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_pen_admin);

        toolbar = (Toolbar)findViewById(R.id.toolBar);
        tabLayout = (TabLayout)findViewById(R.id.tabs);
        viewPager = (ViewPager)findViewById(R.id.viewPager);

        setSupportActionBar(toolbar);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new ShowItem(), "Available Items");
        viewPagerAdapter.addFragment(new InsertItem(), "Insert Items");



        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);







    }
}
