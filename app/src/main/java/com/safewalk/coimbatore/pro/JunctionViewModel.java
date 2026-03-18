package com.safewalk.coimbatore.pro;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class JunctionViewModel extends AndroidViewModel {
    private JunctionRepository mRepository;
    private final LiveData<List<Junction>> mAllJunctions;

    public JunctionViewModel(Application application) {
        super(application);
        mRepository = new JunctionRepository(application);
        mAllJunctions = mRepository.getAllJunctions();
    }

    LiveData<List<Junction>> getAllJunctions() { return mAllJunctions; }

    public void insert(Junction junction) { mRepository.insert(junction); }
}
