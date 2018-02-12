///*
//package com.error_found.kotdroid.phonebook.models.preferences;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//
//*/
///**
// * Created by user12 on 12/2/18.
// *//*
//
//
//public class UserPrefsManager {
//    // SharedPreference Keys
//    private static final String PREFS_FILENAME = "myRH";
//    private static final int PREFS_MODE = 0;
//    private static final String PREFS_USER_PROFILE = "userProfile";
//    private static final String PREFS_IS_LOGINED = "isLogined";
//    private static final String PREFS_SESSION_ID = "sessionId";
//    private static final String PREFS_IN_CHAT_SCREEN = "inChatScreen";
//    private static final String PREFS_IN_CHAT_CLIENT_ID = "inChatClientId";
//
//    private final SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
//
//    public UserPrefsManager(Context context) {
//        mSharedPreferences = context.getSharedPreferences(PREFS_FILENAME,
//                PREFS_MODE);
//        mEditor = mSharedPreferences.edit();
//    }
//
//    public void clearUserPrefs() {
//        ApplicationGlobal.setSessionId("");
//        mEditor.clear();
//        mEditor.apply();
//    }
//
//    public boolean getIsLogined() {
//        return mSharedPreferences.getBoolean(PREFS_IS_LOGINED, false);
//    }
//
//    public void saveUserProfile(boolean isRememberMe, String sessionId, UserProfile userProfile) {
//        if (null != userProfile) {
//            ApplicationGlobal.setSessionId(sessionId);
//            if (isRememberMe) {
//                mEditor.putBoolean(PREFS_IS_LOGINED, true);
//            }
//            mEditor.putString(PREFS_SESSION_ID, sessionId);
//            mEditor.putString(PREFS_USER_PROFILE, new Gson().toJson(userProfile));
//            mEditor.apply();
//        }
//    }
//
//    public UserProfile getUserProfile() {
//        return new Gson().fromJson((mSharedPreferences.getString(PREFS_USER_PROFILE, "")),
//                UserProfile.class);
//    }
//
//    public void updateUserProfile(UserProfile userProfile) {
//        if (null != userProfile) {
//            mEditor.putString(PREFS_USER_PROFILE, new Gson().toJson(userProfile));
//            mEditor.apply();
//        }
//    }
//
//    public String getSessionId() {
//        return mSharedPreferences.getString(PREFS_SESSION_ID, "");
//    }
//
//    public boolean getInChatScreen() {
//        return mSharedPreferences.getBoolean(PREFS_IN_CHAT_SCREEN, false);
//    }
//
//    public void saveInChatScreen(Boolean inChatScreen) {
//        mEditor.putBoolean(PREFS_IN_CHAT_SCREEN, inChatScreen);
//        mEditor.apply();
//    }
//
//    public int getInChatClientId() {
//        return mSharedPreferences.getInt(PREFS_IN_CHAT_CLIENT_ID, -1);
//    }
//
//    public void saveInChatClientId(int clientId) {
//        mEditor.putInt(PREFS_IN_CHAT_CLIENT_ID, clientId);
//        mEditor.apply();
//    }
//}
//*/
