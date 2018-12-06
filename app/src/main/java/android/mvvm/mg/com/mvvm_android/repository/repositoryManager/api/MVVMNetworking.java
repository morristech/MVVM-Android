package android.mvvm.mg.com.mvvm_android.repository.repositoryManager.api;

import alertdialog.dm.com.dmalertdialog.configs.DMBaseDialogConfigs;
import android.content.Context;
import android.mvvm.mg.com.mvvm_android.BuildConfig;
import android.mvvm.mg.com.mvvm_android.R;
import android.mvvm.mg.com.mvvm_android.constants.IConstants;
import android.mvvm.mg.com.mvvm_android.constants.IUrls;
import android.mvvm.mg.com.mvvm_android.dialog.MVVMAlertDialog;
import android.mvvm.mg.com.mvvm_android.repository.repositoryManager.preference.MVVMPrefUtils;
import com.dm.dmnetworking.api_client.base.DMBaseRequest;
import com.dm.dmnetworking.api_client.base.DMBaseTokenHandler;
import com.dm.dmnetworking.api_client.listeners.DMIStatusHandleListener;
import org.json.JSONException;
import org.json.JSONObject;


public class MVVMNetworking extends DMBaseRequest {

    private static MVVMNetworking ourInstance;

    private final static String INVALID_DATA = "INVALID_DATA";

    private MVVMNetworking() {
    }

    public static MVVMNetworking getInstance() {
        if (ourInstance == null) {
            ourInstance = new MVVMNetworking();
        }

        return ourInstance;
    }

    @Override
    protected void handleStatuses(final Context context, final int statusCode, final JSONObject jsonObject, final DMIStatusHandleListener listener) {
        try {
            String status = "";
            if (jsonObject != null) {
                status = jsonObject.getString("status");
            }
            switch (status) {
                case INVALID_DATA:
                    listener.onError(status, jsonObject);
                    break;
//                case "REFRESH_TOKEN":
//                    listener.onTokenUpdate();
//                    break;
                default:
                    listener.onComplete(status, jsonObject);
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onError(e.getMessage(), jsonObject);
        }
    }

    @Override
    protected int getRequestTimeOut() {
        return 20000;
    }

    @Override
    protected String getFullUrl(final Context context, final String url) {
        final String fullUrl = BuildConfig.BASE_URL + "/" + url;
//        + "/" + "?deviceType=android&applicationId="
//                + DMAppInfo.getDeviceID(context) + "&applicationVersion=" + DMAppInfo.getAppVersionCode(context) +
//                "&deviceScale=" + DMAppInfo.getDeviceDensity(context);
//
//        if (MVVMPrefUtils.isUserLoggedIn()) {
//            return fullUrl + "&jwt=" + MVVMPrefUtils.prefGetToken();
//        }

        return fullUrl;
    }

    @Override
    public String getTagForLogger() {
        return IConstants.TAG;
    }

    @Override
    public boolean isEnableLogger() {
        return true;
    }

    @Override
    public DMBaseTokenHandler onTokenRefresh() {
        return new DMBaseTokenHandler(IUrls.Method.REFRESH_TOKEN) {

            @Override
            protected void onTokenRefreshed(final Context context, final JSONObject jsonObject) {
                try {
                    final String token = jsonObject.getString("data");
                    MVVMPrefUtils.setToken(token);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onTokenRefreshFailure(final Context context, final JSONObject jsonObject) {

            }

            @Override
            protected void onNoInternetConnection(final Context context) {
                new MVVMAlertDialog().showWarningDialog(new DMBaseDialogConfigs<>(context)
                        .setContentRes(R.string.dialog_no_internet_connection));

            }
        };
    }
}