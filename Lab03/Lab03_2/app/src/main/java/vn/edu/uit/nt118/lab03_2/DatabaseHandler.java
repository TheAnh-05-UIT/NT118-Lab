package vn.edu.uit.nt118.lab03_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactsManager";
    private static final String TABLE_CONTACTS = "contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_CONTACTS + " ("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_NAME + " TEXT, "
                + KEY_PH_NO + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    public void addContact(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());
        db.insert(TABLE_CONTACTS, null, values);
    }

    public Contact getContact(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, null, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null);
        try {
            if (cursor.moveToFirst()) {
                return fromCursor(cursor);
            }
            return null;
        } finally {
            cursor.close();
        }
    }

    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, null, null, null, null, null,
                KEY_ID + " ASC");
        try {
            while (cursor.moveToNext()) {
                contacts.add(fromCursor(cursor));
            }
        } finally {
            cursor.close();
        }
        return contacts;
    }

    private Contact fromCursor(Cursor cursor) {
        return new Contact(
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_PH_NO))
        );
    }

    public int updateContact(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());
        return db.update(TABLE_CONTACTS, values, KEY_ID + "=?",
                new String[]{String.valueOf(contact.getID())});
    }

    public void deleteContact(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + "=?",
                new String[]{String.valueOf(contact.getID())});
    }
}
