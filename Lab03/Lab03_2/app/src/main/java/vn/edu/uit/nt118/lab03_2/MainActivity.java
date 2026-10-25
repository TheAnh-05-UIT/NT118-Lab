package vn.edu.uit.nt118.lab03_2;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private DatabaseHandler db;
    private ListView lvContacts;
    private List<Contact> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHandler(this);
        lvContacts = findViewById(R.id.lvContacts);

        // Chèn mẫu duy nhất một lần (không tái tạo sau khi người dùng xóa hết).
        if (!getPreferences(MODE_PRIVATE).getBoolean("sample_inserted", false)) {
            db.addContact(new Contact("Ravi", "9100000000"));
            db.addContact(new Contact("Srinivas", "9199999999"));
            db.addContact(new Contact("Tommy", "9522222222"));
            db.addContact(new Contact("Karthik", "9533333333"));
            getPreferences(MODE_PRIVATE).edit().putBoolean("sample_inserted", true).apply();
        }

        lvContacts.setOnItemLongClickListener((parent, view, position, id) -> {
            Contact selected = contacts.get(position);
            new AlertDialog.Builder(this)
                    .setTitle("Xóa liên hệ")
                    .setMessage("Bạn có muốn xóa " + selected.getName() + "?")
                    .setNegativeButton("Hủy", null)
                    .setPositiveButton("Xóa", (dialog, which) -> {
                        db.deleteContact(selected);
                        loadContacts();
                    }).show();
            return true;
        });

        loadContacts();
    }

    private void loadContacts() {
        contacts = db.getAllContacts();
        List<String> rows = new ArrayList<>();
        for (Contact contact : contacts) {
            String s = "ID: " + contact.getID() + " | "
                    + contact.getName() + " | " + contact.getPhoneNumber();
            rows.add(s);
            Log.d("CONTACT", s);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, rows
        );
        lvContacts.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        if (db != null) db.close();
        super.onDestroy();
    }
}
