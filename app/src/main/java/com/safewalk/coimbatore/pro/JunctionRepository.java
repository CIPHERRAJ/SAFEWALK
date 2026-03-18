package com.safewalk.coimbatore.pro;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

public class JunctionRepository {
    private JunctionDao mJunctionDao;
    private LiveData<List<Junction>> mAllJunctions;

    JunctionRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mJunctionDao = db.junctionDao();
        mAllJunctions = mJunctionDao.getAllJunctions();
    }

    LiveData<List<Junction>> getAllJunctions() {
        return mAllJunctions;
    }

    void insert(Junction junction) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mJunctionDao.insert(junction);
        });
    }
}
