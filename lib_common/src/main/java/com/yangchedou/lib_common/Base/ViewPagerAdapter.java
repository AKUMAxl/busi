package com.yangchedou.lib_common.Base;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ADMIN on 2017/11/15.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<String> list;
    private List<? extends Fragment> fs;

    public ViewPagerAdapter(FragmentManager fm,List<String> list,List<? extends Fragment> fs) {
        super(fm);
        this.list = list;
        this.fs = fs;
    }

    @Override
    public int getCount() {
        return list.size();
    }



    @Override
    public Fragment getItem(int position) {
        return fs.get(position);
    }



    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }



    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }


}