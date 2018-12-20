package android.mvvm.mg.com.mvvm_android.core.constants;

public interface IMVVMConstants {

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

    enum Action {
        OPEN_ACCOUNT_FRAGMENT,
        OPEN_BIOMETRIC,
        OPEN_ERROR_DIALOG,
        OPEN_LOGIN_FRAGMENT,
        DO_LOGIN,
        SHOW_NO_INTERNET,
        SHOW_TOAST
    }

    interface PermissionRequestCode {
        int LOCATION = 1000;
        int STORAGE = 1001;
    }
}
