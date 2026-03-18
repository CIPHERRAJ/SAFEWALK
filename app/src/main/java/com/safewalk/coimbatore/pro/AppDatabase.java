package com.safewalk.coimbatore.pro;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Junction.class}, version = 2, exportSchema = false)
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
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            databaseWriteExecutor.execute(() -> {
                JunctionDao dao = INSTANCE.junctionDao();
                if (dao.getCount() == 0) {
                    // Initial data population if empty
                    dao.insert(new Junction("Gandhipuram Junction", "High Risk",
                            "High traffic, complex pedestrian crossings.",
                            "காந்திபுரம் சந்திப்பு: அதிக வாகன போக்குவரத்து மற்றும் பாதசாரிகள் கடப்பதில் சிக்கல்கள் உள்ள பகுதி. மிகுந்த எச்சரிக்கையுடன் கடக்கவும்.",
                            11.0183, 76.9682));
                    dao.insert(new Junction("Lakshmi Mills Junction", "Moderate Risk",
                            "Heavy flow during peak hours, improved signals.",
                            "லட்சுமி மில்ஸ் சந்திப்பு: அலுவலக நேரங்களில் போக்குவரத்து நெரிசல் அதிகமாக இருக்கும். மேம்படுத்தப்பட்ட சிக்னல்கள் இருப்பதால் மிதமான ஆபத்து உடையது.",
                            11.0135, 76.9856));
                    dao.insert(new Junction("Hope College Junction", "High Risk",
                            "Bridge construction area, narrow paths.",
                            "ஹோப் காலேஜ் சந்திப்பு: மேம்பாலப் பணிகள் நடைபெறுவதால் சாலை குறுகலாக உள்ளது. விபத்து அபாயம் அதிகம், கவனம் தேவை.",
                            11.0252, 77.0011));
                    dao.insert(new Junction("Ukkadam Junction", "High Risk",
                            "Busy bus terminal area, missing footpaths.",
                            "உக்கடம் சந்திப்பு: பேருந்து நிலையம் அருகில் இருப்பதால் எப்போதும் பரபரப்பாக இருக்கும். முறையான நடைபாதைகள் இல்லாததால் பாதசாரிகளுக்கு அதிக ஆபத்து.",
                            10.9950, 76.9620));
                }
            });
        }
    };
}
