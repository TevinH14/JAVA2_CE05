// Tevin Hamilton
// JAV2 - 2003
// DataProvider

package com.example.hamiltontevin_ce05.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;



public class DataProvider extends ContentProvider {

    private static final int TABLE_MATCH = 10;
    private DatabaseHelper mDatabase;
    private UriMatcher mMatcher;

    public DataProvider() {
    }

    @Override
    public boolean onCreate() {
        mDatabase = DatabaseHelper.getInstance(getContext());
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mMatcher.addURI(DataContract.URI_AUTHORITY, DataContract.DATA_TABLE, TABLE_MATCH);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        int uriType = mMatcher.match(uri);
        if(uriType == TABLE_MATCH) {
            return mDatabase.query(projection,selection,selectionArgs,sortOrder);
        }
        throw new IllegalArgumentException("Uri did not match... check your authority or table name.");    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int uriType = mMatcher.match(uri);
        if(uriType == TABLE_MATCH) {
            return "vnd.android.cursor.dir/vnd." + DataContract.URI_AUTHORITY + "." + DataContract.DATA_TABLE;
        }
        throw new IllegalArgumentException("Uri did not match... check your authority or table name.");
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        throw new UnsupportedOperationException("Not yet implemented");    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");    }
}
