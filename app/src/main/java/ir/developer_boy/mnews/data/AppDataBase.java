package ir.developer_boy.mnews.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ir.developer_boy.mnews.data.repo.LocalDataSource;

@Database(entities = {News.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase appDataBase;

    public static AppDataBase getRoom(Context context) {
        if (appDataBase == null) {
            appDataBase = Room.databaseBuilder(context, AppDataBase.class, "Mnews_database").allowMainThreadQueries().build();
        }
        return appDataBase;
    }

    public abstract LocalDataSource getLocalDataSource();
}
