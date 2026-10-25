package vn.edu.uit.nt118.lab03_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class StudentDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "studentsManager.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_STUDENTS = "students";
    private static final String COL_ID = "id";
    private static final String COL_CODE = "student_code";
    private static final String COL_NAME = "name";
    private static final String COL_CLASS = "class_name";
    private static final String COL_PHONE = "phone";

    public StudentDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_STUDENTS + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_CODE + " TEXT NOT NULL UNIQUE, "
                + COL_NAME + " TEXT NOT NULL, "
                + COL_CLASS + " TEXT NOT NULL, "
                + COL_PHONE + " TEXT NOT NULL"
                + ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        onCreate(db);
    }

    private ContentValues toValues(Student student) {
        ContentValues values = new ContentValues();

        values.put(COL_CODE, student.getStudentCode());
        values.put(COL_NAME, student.getName());
        values.put(COL_CLASS, student.getClassName());
        values.put(COL_PHONE, student.getPhone());

        return values;
    }

    public long addStudent(Student student) {
        SQLiteDatabase db = getWritableDatabase();

        return db.insert(
                TABLE_STUDENTS,
                null,
                toValues(student)
        );
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_STUDENTS,
                null,
                null,
                null,
                null,
                null,
                COL_ID + " DESC"
        );

        try {
            while (cursor.moveToNext()) {
                students.add(cursorToStudent(cursor));
            }
        } finally {
            cursor.close();
        }
        return students;
    }

    public Student getStudent(long id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_STUDENTS,
                null,
                COL_ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );

        try {
            if (cursor.moveToFirst()) {
                return cursorToStudent(cursor);
            }
        } finally {
            cursor.close();
        }
        return null;
    }

    private Student cursorToStudent(Cursor cursor) {
        long id = cursor.getLong(
                cursor.getColumnIndexOrThrow(COL_ID)
        );

        String code = cursor.getString(
                cursor.getColumnIndexOrThrow(COL_CODE)
        );

        String name = cursor.getString(
                cursor.getColumnIndexOrThrow(COL_NAME)
        );

        String className = cursor.getString(
                cursor.getColumnIndexOrThrow(COL_CLASS)
        );

        String phone = cursor.getString(
                cursor.getColumnIndexOrThrow(COL_PHONE)
        );

        return new Student(id, code, name, className, phone);
    }

    public int updateStudent(Student student) {
        SQLiteDatabase db = getWritableDatabase();

        return db.update(
                TABLE_STUDENTS,
                toValues(student),
                COL_ID + "=?",
                new String[]{String.valueOf(student.getId())}
        );
    }

    public int deleteStudent(long id) {
        SQLiteDatabase db = getWritableDatabase();

        return db.delete(
                TABLE_STUDENTS,
                COL_ID + "=?",
                new String[]{String.valueOf(id)}
        );
    }
}
