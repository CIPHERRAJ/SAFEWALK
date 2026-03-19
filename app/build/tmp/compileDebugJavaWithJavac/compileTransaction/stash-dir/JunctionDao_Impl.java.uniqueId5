package com.safewalk.coimbatore.pro;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class JunctionDao_Impl implements JunctionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Junction> __insertionAdapterOfJunction;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public JunctionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfJunction = new EntityInsertionAdapter<Junction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `junctions` (`id`,`name`,`safetyScore`,`details`,`tamilDetails`,`latitude`,`longitude`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Junction entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getSafetyScore() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getSafetyScore());
        }
        if (entity.getDetails() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDetails());
        }
        if (entity.getTamilDetails() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getTamilDetails());
        }
        statement.bindDouble(6, entity.getLatitude());
        statement.bindDouble(7, entity.getLongitude());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM junctions";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Junction junction) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfJunction.insert(junction);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public LiveData<List<Junction>> getAllJunctions() {
    final String _sql = "SELECT * FROM junctions ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"junctions"}, false, new Callable<List<Junction>>() {
      @Override
      @Nullable
      public List<Junction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSafetyScore = CursorUtil.getColumnIndexOrThrow(_cursor, "safetyScore");
          final int _cursorIndexOfDetails = CursorUtil.getColumnIndexOrThrow(_cursor, "details");
          final int _cursorIndexOfTamilDetails = CursorUtil.getColumnIndexOrThrow(_cursor, "tamilDetails");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final List<Junction> _result = new ArrayList<Junction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Junction _item;
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpSafetyScore;
            if (_cursor.isNull(_cursorIndexOfSafetyScore)) {
              _tmpSafetyScore = null;
            } else {
              _tmpSafetyScore = _cursor.getString(_cursorIndexOfSafetyScore);
            }
            final String _tmpDetails;
            if (_cursor.isNull(_cursorIndexOfDetails)) {
              _tmpDetails = null;
            } else {
              _tmpDetails = _cursor.getString(_cursorIndexOfDetails);
            }
            final String _tmpTamilDetails;
            if (_cursor.isNull(_cursorIndexOfTamilDetails)) {
              _tmpTamilDetails = null;
            } else {
              _tmpTamilDetails = _cursor.getString(_cursorIndexOfTamilDetails);
            }
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            _item = new Junction(_tmpName,_tmpSafetyScore,_tmpDetails,_tmpTamilDetails,_tmpLatitude,_tmpLongitude);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public int getCount() {
    final String _sql = "SELECT COUNT(*) FROM junctions";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if (_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
