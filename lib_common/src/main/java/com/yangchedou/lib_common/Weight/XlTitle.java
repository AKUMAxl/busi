package com.yangchedou.lib_common.Weight;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.yangchedou.lib_common.R;


public class XlTitle extends LinearLayout {
	private TextView textview;
	private Button button_left, button_right;
	private LinearLayout layout;

	public XlTitle(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		//Init(context,attrs);
		Init(context);
	}

	public XlTitle(Context context, AttributeSet attrs) {
		super(context, attrs);
		Init(context);
	}

	public XlTitle(Context context) {
		super(context);
		Init(context);
	}

	//public void Init(Context context,AttributeSet attrs){
	public void Init(Context context){

		View view = LayoutInflater.from(context).inflate(R.layout.mytitle, this, true);

		button_left = view.findViewById(R.id.mytitle_button_left);
		
		button_right = view.findViewById(R.id.mytitle_button_right);
		
		layout = view.findViewById(R.id.mytitle_layout);
		
		textview = view.findViewById(R.id.mytitle_textview_title);

		/*TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.XlTitle);

		String titletext = array.getString(R.styleable.XlTitle_titletext);

		int leftBg_ResId = array.getResourceId(R.styleable.XlTitle_leftbg,R.mipmap.actionbar_back_hl);

		int rightBg_ResId = array.getResourceId(R.styleable.XlTitle_rightbg,R.mipmap.arrow_right);

		boolean leftVisiable = array.getBoolean(R.styleable.XlTitle_leftvisiable,false);

		boolean rightVisiable = array.getBoolean(R.styleable.XlTitle_rightvisiable,false);

		array.recycle();

		textview.setText(titletext);

		button_left.setBackgroundResource(leftBg_ResId);
		button_right.setBackgroundResource(rightBg_ResId);

		if (leftVisiable){
			button_left.setVisibility(VISIBLE);
		} else{
			button_left.setVisibility(GONE);
		}


		if (rightVisiable){
			button_right.setVisibility(VISIBLE);
		} else{
			button_right.setVisibility(GONE);
		}*/

		textview.setClickable(false);
		textview.setFocusable(false);
		
		textview.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
		
		layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

			}
		});
		

	}

	public void setText(String title) {
		textview.setText(title);
	}

	public void setLeftVisibility(boolean visibility){
		if (visibility)
			button_left.setVisibility(VISIBLE);
		else
			button_left.setVisibility(INVISIBLE);
	}

	public void setRightVisibility(boolean visibility){
		if (visibility)
			button_right.setVisibility(VISIBLE);
		else
			button_right.setVisibility(INVISIBLE);
	}

	public void setleftClickListener(OnClickListener listener1) {
		button_left.setOnClickListener(listener1);

	}

	public void setrightClickListener(OnClickListener listener2) {
		button_right.setOnClickListener(listener2);
	}

	public void setleftText(int text_id){
		button_left.setText(getResources().getString(text_id));
	}

	public void setrightText(int text_id){
		button_right.setText(getResources().getString(text_id));
	}

	public void setleftbg(int drawable_id){
		button_left.setBackgroundResource(drawable_id);
	}

	public void setrightbg(int drawable_id){
		button_right.setBackgroundResource(drawable_id);
	}

	public void setleftcolor(int color_id){
		button_left.setBackgroundColor(color_id);
	}

	public void setrightcolor(int color_id){
		button_right.setBackgroundColor(color_id);
	}

	public void setlefttextcolor(int text_color){
		button_left.setTextColor(text_color);
	}

	public void setrighttextcolor(int text_color){
		button_right.setTextColor(text_color);
	}

}
