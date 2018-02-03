package com.yangchedou.lib_common.Weight;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.yangchedou.lib_common.R;


public class MyProgressDialog extends ProgressDialog {

	private MyProgressDismissListener myProgressDismissListener;

	public interface MyProgressDismissListener{
		void onDismiss();
	}

	public void setMyProgressDismissListener(MyProgressDismissListener myProgressDismissListener){
		this.myProgressDismissListener = myProgressDismissListener;
	}

	private Dialog progressDialog;

	private TextView msg;

	public MyProgressDialog(Context context, boolean cancelable, String msgStr) {
		super(context);

		progressDialog = new Dialog(context, R.style.progress_dialog);
		//progressDialog = new Dialog(context);
		progressDialog.setContentView(R.layout.wait_dialog_d);
		progressDialog.getWindow().setBackgroundDrawableResource(
				android.R.color.transparent);
		progressDialog.setCancelable(cancelable);
		msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);

		if (TextUtils.isEmpty(msgStr)) {
			msg.setText("请稍等...");
		} else {
			msg.setText(msgStr);
		}

		progressDialog.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialogInterface) {
				if (myProgressDismissListener!=null){
					myProgressDismissListener.onDismiss();
				}
			}
		});
	}

	public void setText(String text){
		msg.setText(text==null?"请稍等...":text);
	}

	@Override
	public void show() {
		progressDialog.show();
	}

	@Override
	public void dismiss() {
		progressDialog.dismiss();
	}

}