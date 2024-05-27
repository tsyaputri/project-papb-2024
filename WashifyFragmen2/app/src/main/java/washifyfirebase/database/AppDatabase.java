package projek.washifyfirebase.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import projek.washifyfirebase.database.dao.PesananDao;
import projek.washifyfirebase.database.entitas.Pesanan;

@Database(entities = {Pesanan.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract PesananDao pesananDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "pesanan_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
