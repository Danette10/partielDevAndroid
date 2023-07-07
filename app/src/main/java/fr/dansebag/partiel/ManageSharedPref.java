package fr.dansebag.partiel;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ManageSharedPref {
    public static final String PREFS_NAME = "users";
    public static final String PREFS_KEY = "users";

    public ManageSharedPref() {
        super();
    }

    public List<Users> load(Context context) {
        SharedPreferences settings;
        List<Users> users;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if(settings.contains(PREFS_KEY)) {
            String jsonUsers = settings.getString(PREFS_KEY, null);
            Gson gson = new Gson();
            Type userListType = new TypeToken<List<Users>>(){}.getType();
            users = gson.fromJson(jsonUsers, userListType);


        } else {
            return null;
        }
        return users;
    }

    public void save(List<Users> users, Context context) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonUsers = gson.toJson(users);

        editor.putString(PREFS_KEY, jsonUsers);
        editor.apply();
    }

    public void remove(Context context) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.remove(PREFS_KEY);
        editor.apply();
    }
}
