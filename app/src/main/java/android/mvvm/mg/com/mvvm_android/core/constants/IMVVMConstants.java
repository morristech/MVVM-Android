package android.mvvm.mg.com.mvvm_android.core.constants;

import android.mvvm.mg.com.mvvm_android.core.base.DMBaseIConstants;

public interface IMVVMConstants extends DMBaseIConstants {

    String TAG = "MVVM";

    interface PrefName {
        String CONFIGS = "configs";
        String TOKEN = "token";
        String LANGUAGE_CODE = "language_code";
        String IS_USER_LOGGED_IN = "isUserLoggedIn";
        String IS_CHECKED_REMEMBER = "isCheckedRemember";
        String PROFILE_PHOTO = "profile_photo";

    }

    interface Language {
        String HY = "hy";
        String EN = "en";
        String RU = "ru";
    }

    interface DefaultValue {
        int FIRST_PAGE = 0;
    }

    interface BundleKey {
        String USER = "user";
    }

    interface Delay {
        int SPLASH = 1500;
    }

    interface RequestCode {
        int CAMERA = 1000;
    }

    interface Action extends BaseAction {
        int OPEN_ACCOUNT_FRAGMENT = 0;
        int OPEN_BIOMETRIC = 1;
        int OPEN_LOGIN_FRAGMENT = 2;
    }

    interface PermissionRequestCode {
        int LOCATION = 1000;
        int STORAGE = 1001;
    }

    interface SendCode {
        int LOGIN_TO_ACCOUNT = 1000;
        int CARD_TO_ACCOUNT = 1001;
        int ACCOUNT_TO_TRANSACTION = 1002;
        int ACCOUNT_TO_SETTINGS = 1003;
    }
}
