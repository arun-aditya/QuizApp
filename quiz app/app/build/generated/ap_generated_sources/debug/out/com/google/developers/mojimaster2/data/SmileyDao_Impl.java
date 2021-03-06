package com.google.developers.mojimaster2.data;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.DataSource.Factory;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.paging.LimitOffsetDataSource;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SmileyDao_Impl implements SmileyDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Smiley> __insertionAdapterOfSmiley;

  private final EntityDeletionOrUpdateAdapter<Smiley> __deletionAdapterOfSmiley;

  public SmileyDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSmiley = new EntityInsertionAdapter<Smiley>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `smiley` (`unicode`,`name`,`emoji`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Smiley value) {
        if (value.getCode() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getCode());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getEmoji() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getEmoji());
        }
      }
    };
    this.__deletionAdapterOfSmiley = new EntityDeletionOrUpdateAdapter<Smiley>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `smiley` WHERE `unicode` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Smiley value) {
        if (value.getCode() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getCode());
        }
      }
    };
  }

  @Override
  public void insertAll(final Smiley... smiley) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSmiley.insert(smiley);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(final Smiley smiley) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSmiley.insert(smiley);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Smiley smiley) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfSmiley.handle(smiley);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public DataSource.Factory<Integer, Smiley> getAll() {
    final String _sql = "SELECT * FROM SMILEY ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new DataSource.Factory<Integer, Smiley>() {
      @Override
      public LimitOffsetDataSource<Smiley> create() {
        return new LimitOffsetDataSource<Smiley>(__db, _statement, false , "SMILEY") {
          @Override
          protected List<Smiley> convertRows(Cursor cursor) {
            final int _cursorIndexOfMCode = CursorUtil.getColumnIndexOrThrow(cursor, "unicode");
            final int _cursorIndexOfMName = CursorUtil.getColumnIndexOrThrow(cursor, "name");
            final int _cursorIndexOfMEmoji = CursorUtil.getColumnIndexOrThrow(cursor, "emoji");
            final List<Smiley> _res = new ArrayList<Smiley>(cursor.getCount());
            while(cursor.moveToNext()) {
              final Smiley _item;
              final String _tmpMCode;
              _tmpMCode = cursor.getString(_cursorIndexOfMCode);
              final String _tmpMName;
              _tmpMName = cursor.getString(_cursorIndexOfMName);
              final String _tmpMEmoji;
              _tmpMEmoji = cursor.getString(_cursorIndexOfMEmoji);
              _item = new Smiley(_tmpMCode,_tmpMName,_tmpMEmoji);
              _res.add(_item);
            }
            return _res;
          }
        };
      }
    };
  }

  @Override
  public LiveData<List<Smiley>> getRandom(final int limit) {
    final String _sql = "SELECT * FROM smiley ORDER BY RANDOM() LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    return __db.getInvalidationTracker().createLiveData(new String[]{"smiley"}, false, new Callable<List<Smiley>>() {
      @Override
      public List<Smiley> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMCode = CursorUtil.getColumnIndexOrThrow(_cursor, "unicode");
          final int _cursorIndexOfMName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfMEmoji = CursorUtil.getColumnIndexOrThrow(_cursor, "emoji");
          final List<Smiley> _result = new ArrayList<Smiley>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Smiley _item;
            final String _tmpMCode;
            _tmpMCode = _cursor.getString(_cursorIndexOfMCode);
            final String _tmpMName;
            _tmpMName = _cursor.getString(_cursorIndexOfMName);
            final String _tmpMEmoji;
            _tmpMEmoji = _cursor.getString(_cursorIndexOfMEmoji);
            _item = new Smiley(_tmpMCode,_tmpMName,_tmpMEmoji);
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
  public Smiley getSmiley() {
    final String _sql = "SELECT * FROM smiley ORDER BY RANDOM() LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMCode = CursorUtil.getColumnIndexOrThrow(_cursor, "unicode");
      final int _cursorIndexOfMName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfMEmoji = CursorUtil.getColumnIndexOrThrow(_cursor, "emoji");
      final Smiley _result;
      if(_cursor.moveToFirst()) {
        final String _tmpMCode;
        _tmpMCode = _cursor.getString(_cursorIndexOfMCode);
        final String _tmpMName;
        _tmpMName = _cursor.getString(_cursorIndexOfMName);
        final String _tmpMEmoji;
        _tmpMEmoji = _cursor.getString(_cursorIndexOfMEmoji);
        _result = new Smiley(_tmpMCode,_tmpMName,_tmpMEmoji);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
