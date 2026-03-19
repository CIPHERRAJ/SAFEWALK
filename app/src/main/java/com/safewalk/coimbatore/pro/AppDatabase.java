package com.safewalk.coimbatore.pro;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Junction.class}, version = 4, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract JunctionDao junctionDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "safewalk_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    populateInitialData(context);
                                }

                                @Override
                                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                    super.onOpen(db);
                                    // Also check on every open in case onCreate failed or data was lost
                                    populateInitialData(context);
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static void populateInitialData(Context context) {
        databaseWriteExecutor.execute(() -> {
            JunctionDao dao = getDatabase(context).junctionDao();
            if (dao.getCount() == 0) {
                List<Junction> junctions = JsonUtils.loadJunctionsFromAsset(context, "junctions.json");
                for (Junction junction : junctions) {
                    dao.insert(junction);
                }
            }
        });
    }
}
