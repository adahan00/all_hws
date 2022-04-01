package kg.geektech.newsapplite;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    private SharedPreferences preferences;

    public Prefs(Context context) {
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }
    public void saveBoardState() {
        preferences.edit().putBoolean("board_state", true).apply();
    }

    public boolean isBoardingShown() {
        return preferences.getBoolean("board_state",false);
    }
    public void saveImageFromProfile(String image) {
        preferences.edit().putString("key", image).apply();
    }

    public String getImageFromProfile() {
        return preferences.getString("key","");
    }


   public void saveName(String name) {
        preferences.edit().putString("name", name).apply();
    }

    public String getName() {
        return preferences.getString("name","");
    }



}
