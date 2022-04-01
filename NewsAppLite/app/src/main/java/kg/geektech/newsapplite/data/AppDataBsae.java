package kg.geektech.newsapplite.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import kg.geektech.newsapplite.models.News;

@Database(entities = {News.class}, version = 2 )
public abstract class AppDataBsae extends RoomDatabase {
    public abstract NewsDao newsDao();
}
