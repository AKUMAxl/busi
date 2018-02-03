package com.yangchedou.lib_common.Weight;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.yangchedou.lib_common.R;




/**
 * Created by ADMIN on 2017/11/10.
 */

public class ClearnEditText extends RelativeLayout
{

    private EditText et,et_password;
    private TextView tv,tv_headPic;

    private OnClickListener clickListener;

    private boolean et_hasContent = false;
    private boolean et_password_hasContent = false;

    public ClearnEditText(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ClearnEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs){
        View view = LayoutInflater.from(context).inflate(R.layout.clearn_edittext,this,true);

        et = view.findViewById(R.id.et);
        et_password = view.findViewById(R.id.et_password);
        tv = view.findViewById(R.id.cross);
        tv_headPic = view.findViewById(R.id.headPic);

        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.ClearnEditText);

        int Res_id = array.getResourceId(R.styleable.ClearnEditText_headPic,R.drawable.ic_perm_identity_black_36dp);

        int inputType = array.getInt(R.styleable.ClearnEditText_type,0);

        array.recycle();

        tv_headPic.setBackgroundResource(Res_id);

        switch (inputType){
            case 0:
                et.setVisibility(VISIBLE);
                et_password.setVisibility(GONE);
                break;
            case 1:
                et.setVisibility(GONE);
                et_password.setVisibility(VISIBLE);
                break;
        }

        et_password.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b)
                    HidenCross();
                if (b&&et_password_hasContent){
                    ShowCross();
                }
            }
        });

        et.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b)
                    HidenCross();
                if (b&&et_hasContent){
                    ShowCross();
                }
            }
        });

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence)){
                    ShowCross();
                    et_hasContent = true;
                }else {
                    HidenCross();
                    et_hasContent = false;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence)){
                    ShowCross();
                    et_password_hasContent = true;
                }else {
                    HidenCross();
                    et_password_hasContent = false;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        clickListener = new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et.getVisibility()==VISIBLE){
                    et.setText("");
                }else {
                    et_password.setText("");
                }

            }
        };
        tv.setOnClickListener(clickListener);

    }

    public void ShowCross(){
        tv.setVisibility(VISIBLE);
    }

    public void HidenCross(){
        tv.setVisibility(View.INVISIBLE);
    }


    public void setText(String text){
        if (et.getVisibility()==VISIBLE)
            et.setText(text);
        else
            et_password.setText(text);
    }

    public String getText(){
        StringBuilder sb = new StringBuilder();
        sb.append(et_password.getText().toString().trim());
        sb.append(et.getText().toString().trim());
        return sb.toString();
    }

}
