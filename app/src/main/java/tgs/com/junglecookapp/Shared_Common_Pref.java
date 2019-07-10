package tgs.com.junglecookapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

class Shared_Common_Pref {

    public static final String LOGGED = "logged";
    public static final String usergroupname = "asdf";
    public static final String userid = "asdf";
    public static final String username = "asdf";
    public static final String groupid = "asdf";


    private SharedPreferences Common_pref;
    private SharedPreferences.Editor editor;
    protected Activity activity;
    private Context _context;

    public Shared_Common_Pref(Activity Ac) {
        activity = Ac;
        if (activity != null) {
            Common_pref = activity.getSharedPreferences("Preference_values", Context.MODE_PRIVATE);
            editor = Common_pref.edit();
        }
    }

    public Shared_Common_Pref(Context cc) {
        this._context = cc;
        Common_pref = cc.getSharedPreferences("Preference_values", Context.MODE_PRIVATE);
        editor = Common_pref.edit();
    }

    public void save(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getvalue(String key) {
        String text = null;
        text = Common_pref.getString(key, null);
        return text;
    }

    public Boolean getFiltervalue(String key) {
        Boolean b = false;
        b = Common_pref.getBoolean(key, false);
        return b;
    }

    public void removeFiltersvalue(String key) {
        editor.remove(key);
        editor.commit();
    }

    public void saveFiltersvalue(String key, Boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void save_Longvalue(String key, Long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public Long getLong_value(String key) {
        Long text = null;
        text = Common_pref.getLong(key, 0);
        return text;
    }




}
