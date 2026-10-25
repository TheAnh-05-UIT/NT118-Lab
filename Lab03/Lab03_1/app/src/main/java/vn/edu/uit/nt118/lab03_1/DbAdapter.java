package vn.edu.uit.nt118.lab03_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbAdapter {
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    private static final String DATABASE_NAME = "Database_Demo";
    private static final String DATABASE_TABLE = "users";
    private static final int DATABASE_VERSION = 2;

    private final Context context;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DbAdapter(Context context) {
        this.context = context;
    }

    public DbAdapter open() {
        dbHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (dbHelper != null) dbHelper.close();
    }

    public long createUser(String name) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        return database.insert(DATABASE_TABLE, null, values);
    }

    public boolean deleteUser(long id) {
        return database.delete(DATABASE_TABLE, KEY_ID + "=?",
                new String[]{String.valueOf(id)}) > 0;
    }

    public boolean deleteAllUsers() {
        return database.delete(DATABASE_TABLE, null, null) > 0;
    }

    public Cursor getAllUsers() {
        return database.query(DATABASE_TABLE,
                new String[]{KEY_ID, KEY_NAME}, null, null, null, null, KEY_ID + " ASC");
    }
}
