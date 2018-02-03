package com.yangchedou.module_personal.BusinessBrief;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.orhanobut.logger.Logger;
import com.yangchedou.lib_common.AllData;
import com.yangchedou.lib_common.Base.BaseActivity;
import com.yangchedou.lib_common.Weight.XlTitle;
import com.yangchedou.lib_common.Zxing.android.Intents;
import com.yangchedou.lib_common.utils.SharedPreferencesUtil;
import com.yangchedou.lib_common.utils.TakePhotoUtil;
import com.yangchedou.module_personal.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ADMIN on 2017/11/25.
 */
@Route(path = "/module_persional/businessBrief")
public class BusinessBriefActivity extends BaseActivity implements BusinessBriefAdapter.onBusinesPicClickListener,TakePhoto.TakeResultListener,InvokeListener {

    private XlTitle title;
    private RecyclerView rv;
    private EditText businessbrief_et;

    private GridLayoutManager gridLayoutManager;
    private BusinessBriefAdapter businessBriefAdapter;
    private List<File> list_file = new ArrayList<>();

    private InvokeParam invokeParam;
    private TakePhoto takePhoto;

    @Override
    public void loadView() {
        setContentView(R.layout.activity_businessbrief);
    }

    @Override
    public void initData() {
        title = findViewById(R.id.businessbrief_title);
        rv = findViewById(R.id.businessbrief_rv);
        businessbrief_et = findViewById(R.id.businessbrief_et);

        title.setText(getResources().getString(R.string.shangjiajianjie));
        title.setleftbg(R.mipmap.actionbar_back_hl);
        title.setrightText(R.string.queding);
        title.setleftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oThis.finish();
            }
        });
        title.setrightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list_temp = new ArrayList<>();
                for (File file:list_file){
                    list_temp.add(file.getAbsolutePath());
                }
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("pic_list",list_temp);
                bundle.putString("content",businessbrief_et.getText().toString());
                intent.putExtras(bundle);
                setResult(AllData.SET_SJJJ_RESPONSE_CODE,intent);
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle.getString("content")!=null&&bundle.getString("pic_list")!=null){
            businessbrief_et.setText(bundle.getString("content"));
            for (String str_url:bundle.getStringArrayList("pic_list")) {
                Logger.i("========"+str_url);
                File file = new File(str_url);
                list_file.add(file);
            }
        }

        gridLayoutManager = new GridLayoutManager(oThis,3);
        businessBriefAdapter = new BusinessBriefAdapter(oThis,list_file);

        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(businessBriefAdapter);

        takePhoto= (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this,this));
        TakePhotoUtil.getIntance().initData(takePhoto);

    }

    @Override
    protected void onStart() {
        super.onStart();
        businessBriefAdapter.setOnBusinesPicClickListener(this);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        takePhoto.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        takePhoto.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type=PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(this,type,invokeParam,this);
    }

    @Override
    public void ClickPic(int position) {
        if (list_file==null||list_file.size()==0){
            takePhoto.onPickMultiple(9);
        }else if (list_file.size()==position){
            takePhoto.onPickMultiple(9-position);
        }else if (list_file.size()==8||position==8){
            list_file.remove(position);
        }else {
            list_file.remove(position);
        }
        businessBriefAdapter.notifyDataSetChanged();

    }

    @Override
    public void takeSuccess(TResult result) {
        List<TImage> temp = new ArrayList<>();
        temp = result.getImages();
        for (int i=0;i<temp.size();i++){
            list_file.add(new File(temp.get(i).getOriginalPath()));
        }
        businessBriefAdapter.notifyDataSetChanged();
    }

    @Override
    public void takeFail(TResult result, String msg) {
    }

    @Override
    public void takeCancel() {
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam;
        }
        return type;
    }


    //获得照片的输出保存Uri
    private Uri getImageCropUri() {
        File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists())file.getParentFile().mkdirs();
        return Uri.fromFile(file);
    }

}
