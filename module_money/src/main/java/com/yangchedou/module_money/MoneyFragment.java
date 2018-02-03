package com.yangchedou.module_money;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yangchedou.lib_common.Base.BaseFragment;
import com.yangchedou.lib_common.Weight.XlTitle;

/**
 * Created by ADMIN on 2017/11/11.
 */

public class MoneyFragment extends BaseFragment {

    private XlTitle title;

    public MoneyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_money, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        title = getActivity().findViewById(R.id.money_title);
        title.setText(getResources().getString(R.string.shoukuan));
        title.setLeftVisibility(false);
        title.setRightVisibility(false);
    }
}
