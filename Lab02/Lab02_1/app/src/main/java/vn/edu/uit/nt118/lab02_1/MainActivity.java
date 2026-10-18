package vn.edu.uit.nt118.lab02_1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText edtName;
    private Button btnAdd;
    private TextView tvPerson;
    private ListView lvPerson;
    private ArrayList<String> names;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ View
        edtName = findViewById(R.id.edtName);
        btnAdd = findViewById(R.id.btnAdd);
        tvPerson = findViewById(R.id.tv_person);
        lvPerson = findViewById(R.id.lv_person);

        names = new ArrayList<>();

        names.add("Tèo");
        names.add("Tý");
        names.add("Bin");
        names.add("Bo");

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                names
        );

        lvPerson.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {

            String name = edtName.getText()
                    .toString().trim();

            if (name.isEmpty()) {
                edtName.setError("Vui lòng nhập tên");
                return;
            }
            if(names.contains(name)){
                edtName.setError("Tên đã tồn tại");
                return;
            }
            names.add(name);
            adapter.notifyDataSetChanged();
            edtName.setText("");
            Toast.makeText(
                    this,
                    "Thêm thành công",
                    Toast.LENGTH_SHORT
            ).show();
        });

        lvPerson.setOnItemClickListener((parent, view, position, id) -> {

            lvPerson.setItemChecked(position, true);

            tvPerson.setText(
                    "Position: " + position +
                            " ; Value: " + names.get(position)
            );
        });

        // Nhấn giữ để xóa
        lvPerson.setOnItemLongClickListener(
            (parent, view, position, id) -> {
                String name = names.get(position);
                names.remove(position);
                adapter.notifyDataSetChanged();
                tvPerson.setText(
                        "Đã xóa: " + name
                );
                Toast.makeText(
                        this,
                        "Xóa thành công",
                        Toast.LENGTH_SHORT
                ).show();
                return true;
            }
        );
    }
}
