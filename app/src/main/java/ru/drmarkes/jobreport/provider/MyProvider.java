package ru.drmarkes.jobreport.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;

import java.util.HashMap;

/**
 * Created by Андрей on 24.01.2016.
 */
public class MyProvider extends ContentProvider {
    private static final int DATABASE_VERSION = 1;
    private static final int JOB = 1;
    private static final int JOB_ID = 2;
    private static HashMap<String, String> sJobProjectionMap;
    private static final UriMatcher sUriMatcher;
    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(ContractClass.AUTHORITY, "job", JOB);
        sUriMatcher.addURI(ContractClass.AUTHORITY, "job/#", JOB_ID);

        sJobProjectionMap = new HashMap<>();
        for(int i = 0; i < ContractClass.Job.DEFAULT_PROJECTION.length; i++) {
            sJobProjectionMap.put(ContractClass.Job.DEFAULT_PROJECTION[i],
                    ContractClass.Job.DEFAULT_PROJECTION[i]);
        }
    }

    private static class DataBaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "ContractClassDB";

        public static final String DATABASE_TABLE_JOB = ContractClass.Job.TABLE_NAME;

        public static final String KEY_ROWID = "_id";
        public static final String KEY_DATE = "date";
        public static final String KEY_ORDER = "order";
        public static final String KEY_DEPARTMENT = "department";
        public static final String KEY_MANIPULATION = "manipulation";
        public static final String KEY_PATIENT = "patient";
        public static final String KEY_ROOM_HISTORY = "room_history";

        private static final String DATABASE_CREATE_TABLE_JOB =
                "create table " + DATABASE_TABLE_JOB + " ("
                + KEY_ROWID + " integer primary key autoincrement, "
                + KEY_DATE + " integer "
                + KEY_ORDER + " text "
                + KEY_DEPARTMENT + " text "
                + KEY_MANIPULATION + " text "
                + KEY_PATIENT + " text "
                + KEY_ROOM_HISTORY + " integer ";

        private Context context;

        DataBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE_TABLE_JOB);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_JOB);
            onCreate(db);
        }
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
