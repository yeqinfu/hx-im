package com.zhy.http.okhttp;

import android.content.Context;

import com.google.gson.Gson;
import com.ppandroid.im.bean.BN_Base;
import com.ppandroid.im.utils.DebugLog;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

import okhttp3.Call;

/**
 * Created by yeqinfu on 2017/7/31.
 */

public class OKUtils {

	public static void get(final Context context, String url, final MyCallBack callBack) {
		OkHttpUtils.get().url(url).build().execute(new StringCallback() {
			@Override
			public void onError(Call call, Exception e, int id) {
				e.printStackTrace();
			}

			@Override
			public void onResponse(String response, int id) {
				Gson gson = new Gson();
				try {
					BN_Base body = gson.fromJson(response, BN_Base.class);
					if (body.getResultCode() == 0) {
						callBack.onResponse(body);
					}
					else {
						callBack.onError(body);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	public static <T extends BN_Base> void post(final Context context, final String url, final Map<String, String> params, final Class<T> tt, final MyCallBack<T> callBack) {
		DebugLog.i("httppost==>" + url + "\n\n" + params.toString() + "\n\n");
		OkHttpUtils.post().params(params).url(url).build().execute(new StringCallback() {
			@Override
			public void onError(Call call, Exception e, int id) {
				e.printStackTrace();
			}

			@Override
			public void onResponse(String response, int id) {
				DebugLog.i("httppost==>" + response + "\n\n");
				Gson gson = new Gson();
				try {
					BN_Base body = gson.fromJson(response, BN_Base.class);

					if (body.getResultCode() == 0) {
						callBack.onResponse(gson.fromJson(response, tt));
					}
					else {
						callBack.onError(body);
					}
				} catch (Exception e) {
					BN_Base bn_base = new BN_Base();
					bn_base.setResultCode(-1);
					bn_base.setResultMessage("返回json解析错误");
					callBack.onError(bn_base);
					e.printStackTrace();
				}

			}
		});
	}
}
