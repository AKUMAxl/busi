package com.yangchedou.module_personal;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.yangchedou.lib_common.Base.BaseFragment;
import com.yangchedou.lib_common.Base.ChangeActivity;
import com.yangchedou.lib_common.Base.ViewManager;
import com.yangchedou.lib_common.Weight.XlTitle;
import com.yangchedou.lib_common.utils.SharedPreferencesUtil;

/**
 * Created by ADMIN on 2017/11/9.
 */

public class PersonalFragment extends BaseFragment implements View.OnClickListener{

    private XlTitle title;
    private Button btn_exit;
    private RelativeLayout rl_jbxx;

    public PersonalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        title = getActivity().findViewById(R.id.sz_title);
        btn_exit = getActivity().findViewById(R.id.btn_exit);
        rl_jbxx = getActivity().findViewById(R.id.rl_hbxx);

        title.setText(getResources().getString(R.string.shezhi));
        title.setRightVisibility(false);
        title.setLeftVisibility(false);

        btn_exit.setOnClickListener(this);
        rl_jbxx.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id==R.id.btn_exit){
            SharedPreferencesUtil.getIntance(getContext()).SaveData(SharedPreferencesUtil.KEY.HAS_LOGIN,String.valueOf(false));
            SharedPreferencesUtil.getIntance(getContext()).SaveData(SharedPreferencesUtil.KEY.USER_ID,String.valueOf(""));
            ChangeActivity.getIntance().ToNextActivity(getActivity(),"/module_login/login",null,true);
            //ViewManager.getInstance().finishActivity(getActivity());
        }else if (id==R.id.rl_hbxx){
            ChangeActivity.getIntance().ToNextActivity(getActivity(), "/module_personal/baseInfo",null,false);
        }

    }
}
