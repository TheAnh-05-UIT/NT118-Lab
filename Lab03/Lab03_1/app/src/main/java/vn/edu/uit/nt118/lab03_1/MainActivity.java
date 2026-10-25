package vn.edu.uit.nt118.lab03_1;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private DbAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbAdapter = new DbAdapter(this).open();
        dbAdapter.deleteAllUsers();
        for (int i = 0; i < 10; i++) {
            dbAdapter.createUser("Nguyễn Quang Thế Anh " + i);
        }

        List<String> users = new ArrayList<>();
        Cursor cursor = dbAdapter.getAllUsers();
        try {
            while (cursor.moveToNext()) {
                users.add(cursor.getString(cursor.getColumnIndexOrThrow(DbAdapter.KEY_NAME)));
            }
        } finally {
            cursor.close();
        }

        ListView listView = findViewById(R.id.lv_user);
        listView.setAdapter(new ArrayAdapter<>(this, R.layout.item_user, R.id.tv_user, users));
    }

    @Override
    protected void onDestroy() {
        if (dbAdapter != null) dbAdapter.close();
        super.onDestroy();
    }
}
