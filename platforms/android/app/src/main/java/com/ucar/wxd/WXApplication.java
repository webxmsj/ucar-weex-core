package com.ucar.wxd;

import android.app.Application;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.ucar.weex.UWXInit;
import com.ucar.weex.init.utils.UWLog;
import com.ucar.weex.update.UWXResManager;
import com.ucar.weex.update.WXPackageInfo;
import com.ucar.weex.utils.ArrayUtils;

import java.io.IOException;

/**
 * weex 初始化
 */
public class WXApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        UWXInit.init(this);
        UWXResManager.getInstance().addWXResFromAssert(this, getWXPackageFileName("weex"));
//        UWXResManager.getInstance().checkUpdate(new UWXResManager.CheckUpdateCallback() {
//            @Override
//            public void callback(int code, String msg, WXPackageInfo info) {
//                Toast.makeText(WXApplication.this, msg, Toast.LENGTH_LONG).show();
//                UWLog.d("WXApp", msg);
//                //重启 提示
//            }
//        });
    }

    @NonNull
    private String getWXPackageFileName(String weexFileName) {
        try {
            String[] assets = this.getAssets().list(weexFileName);
            if (!ArrayUtils.isEmpty(assets)) {
                String asset = assets[0];
                int i = asset.indexOf(".");
                String rnName = asset.substring(0, i);
                weexFileName = weexFileName + "/" + rnName;
                UWLog.v("Weex", "Weex=" + weexFileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weexFileName;
    }
}
