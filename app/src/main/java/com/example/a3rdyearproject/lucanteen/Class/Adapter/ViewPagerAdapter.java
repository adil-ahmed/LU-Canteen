package com.example.a3rdyearproject.lucanteen.Class.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adil on 5/6/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {


    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> tittleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager)
    {
        super(manager);
    }
    public void addFragment(Fragment fragment,String tittle)
    {
        fragmentList.add(fragment);
        tittleList.add(tittle);
    }
    @Override
    public android.support.v4.app.Fragment getItem(int position)
    {
        return fragmentList.get(position);
    }

    @Override
    public int getCount()
    {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tittleList.get(position);
    }
}
