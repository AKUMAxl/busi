package com.yangchedou.lib_common.NetWork;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.util.SparseArray;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.yangchedou.lib_common.AllData;
import com.yangchedou.lib_common.MobileInfo;
import com.yangchedou.lib_common.NetState.NetManagerUtil;
import com.yangchedou.lib_common.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * Created by AKUMA on 2017/8/28.
 */

public class OkHttpUtil {

    private static final int cacheSize = 10 * 1024 * 1024; // 10 MiB
    private static String cacheDirectory = AllData.BasePath + "/okttpcaches"; // 设置缓存文件路径
    private static Cache cache = new Cache(new File(cacheDirectory), cacheSize);  //
    //File sdcache = getExternalCacheDir();

    //应用拦截器
    static Interceptor appInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            HttpUrl url = request.url();
            //---------请求之前-----
/*            Logger.d("app interceptor:begin");
            Logger.i("request url:"+url.toString());*/
            Response  response = chain.proceed(request);
            //Logger.d("app interceptor:end");
            //---------请求之后------------
            return response;
        }
    };

    static Interceptor networkInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            //---------请求之前-----
            //Logger.d("network interceptor:begin");
            Response  response = chain.proceed(request);
            //Logger.i("response state:"+response.isSuccessful());
            //Logger.d("network interceptor:end");
            return response;
        }
    };


    private static OkHttpClient okHttpClient;

    private static volatile OkHttpUtil intance;

    public static OkHttpUtil getIntance(){

        if (intance == null){
            synchronized (OkHttpUtil.class){
                if (intance==null){
                    intance = new OkHttpUtil();
                }
            }
        }
        return intance;
    }


    public static OkHttpClient getOKHttpClient(){

        final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
            if (okHttpClient==null){
                okHttpClient = new OkHttpClient.Builder()
                        //设置缓存
                        .cookieJar(new CookieJar() {
                            @Override
                            public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                                cookieStore.put(httpUrl.host(), list);
                            }

                            @Override
                            public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                                List<Cookie> cookies = cookieStore.get(httpUrl.host());
                                return cookies != null ? cookies : new ArrayList<Cookie>();
                            }
                        })
                        //设置应用拦截器
                        .addInterceptor(appInterceptor)
                        //设置网络拦截器
                        .addNetworkInterceptor(networkInterceptor)
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(10,TimeUnit.SECONDS)
                        .writeTimeout(10,TimeUnit.SECONDS)
                        .cache(cache)
                        .build();
            }
            return okHttpClient;
    }


    public void getOKHttpResultByPostJson(final Context context, final String interfaceName, JSONObject jsonObject, final okHttpResponse okHttpResponse){
        Logger.i("cachePath:"+cacheDirectory);
        final okHttpResponse okHttpResponse1 = new okHttpResponseImpl();

        int netState = NetManagerUtil.getNEtState(context);
        if (netState==NetManagerUtil.NET_NONE){
            okHttpResponse1.dealFailureResult(context.getResources().getString(R.string.wangluocuowu));
            okHttpResponse.requsetFailure(context.getResources().getString(R.string.qingqiuyichang));
            return;
        }

        String os = MobileInfo.getSystemVersion();
        String device = MobileInfo.getSystemModel();
        String imei = MobileInfo.getIMEI(context);
        Logger.i(interfaceName+"--params:"+jsonObject.toString());
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonObject.toString());
        Request.Builder requestBuilder = new Request.Builder()
                .url(AllData.url)
                .header("Content-Type","application/json")
                .addHeader("app_code","business")
                .addHeader("service_code",interfaceName)
                .addHeader("screen_code",interfaceName)
                .addHeader("os",os)
                .addHeader("device",device)
                .addHeader("imei",imei)
                .addHeader("user_id","")
                .addHeader("city_code","")
                .post(requestBody);
        Request request = requestBuilder.build();
        String a = request.url().encodedPath();
        getIntance().getOKHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                okHttpResponse1.dealFailureResult(context.getResources().getString(R.string.qingqiuyichang));
                okHttpResponse.requsetFailure(context.getResources().getString(R.string.qingqiuyichang));
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result_str = response.body().string();
                Logger.i(interfaceName+" response result:"+result_str);
                Boolean isSuccess = false;
                String message = "";
                try {
                    JSONObject object = new JSONObject(result_str);
                    isSuccess = object.getBoolean("success");
                    message = object.getString("message");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (isSuccess)
                    okHttpResponse.responseSuccess(result_str);
                else
                    okHttpResponse.dealFailureResult(message);
            }
        });
    }

    public void getOKHttpResultByPostJsonWithourHeader(final Context context, JSONObject jsonObject,String introduce, final okHttpResponse okHttpResponse){

        final okHttpResponse okHttpResponse1 = new okHttpResponseImpl();

        int netState = NetManagerUtil.getNEtState(context);
        if (netState==NetManagerUtil.NET_NONE){
            okHttpResponse1.dealFailureResult(context.getResources().getString(R.string.wangluocuowu));
            okHttpResponse.requsetFailure(context.getResources().getString(R.string.qingqiuyichang));
            return;
        }

        Logger.i(AllData.INFO_UPDATE+"--params:"+jsonObject.toString());
        /*String[] arr_file = {};
        try {
            arr_file = jsonObject.getString("file0").split("\\|");
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        //MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        /*for (String path:arr_file) {
            File file = new File(path);
            if (file.exists()){
                Logger.i("文件："+path);
                builder.addFormDataPart("text", file.getName(),RequestBody.create(MediaType.parse("image/png"),file));
                //builder.addPart(RequestBody.create(MediaType.parse("image/jpeg"),file));
            }else {
                Logger.i("文件不存在："+path);
            }
        }*/
        Map<String,String> map = new HashMap<>();
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String,String>>(){}.getType();
        map = gson.fromJson(jsonObject.toString(),type);

        Set<String> set = map.keySet();
        FormBody.Builder builder = new FormBody.Builder();
        for (String str:set){
            builder.add(str,map.get(str));
            Logger.i("map:"+str+"---------"+map.get(str));
        }
        //builder.add("introduce","456");
        FormBody formBody = builder.build();

        Request request = new Request.Builder()
                .url(AllData.INFO_UPDATE)
                .post(formBody)
                .build();

        getIntance().getOKHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Request request1 = call.request();
                System.out.println("request = " + request1.url().toString());
                System.out.println("e.getLocalizedMessage() = " + e.getLocalizedMessage());
                okHttpResponse1.dealFailureResult(context.getResources().getString(R.string.qingqiuyichang));
                okHttpResponse.requsetFailure(context.getResources().getString(R.string.qingqiuyichang));
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result_str = response.body().string();
                Logger.i(AllData.INFO_UPDATE+" response result:"+result_str);
                Boolean isSuccess = false;
                String message = "";
                try {
                    JSONObject object = new JSONObject(result_str);
                    isSuccess = object.getBoolean("success");
                    message = object.getString("message");
                } catch (JSONException e) {
                    e.printStackTrace();
                    okHttpResponse.dealFailureResult(message);
                }
                if (isSuccess)
                    okHttpResponse.responseSuccess(result_str);
                else
                    okHttpResponse.dealFailureResult(message);
            }
        });
    }

}

