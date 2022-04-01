package kg.geektech.newsapplite;

import android.app.Application;

import androidx.room.Room;

import kg.geektech.newsapplite.data.AppDataBsae;

public class App extends Application {

    public static AppDataBsae dataBsae;
    @Override
    public void onCreate() {
        super.onCreate();
        dataBsae = Room.databaseBuilder(getApplicationContext(),AppDataBsae.class,"news_db").fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }
}
