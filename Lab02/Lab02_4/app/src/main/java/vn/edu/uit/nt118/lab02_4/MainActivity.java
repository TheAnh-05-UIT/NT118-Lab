package vn.edu.uit.nt118.lab02_4;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText etId, etFullName;
    private CheckBox cbManager;
    private Button btnAdd;
    private ListView lvEmployee;
    private ArrayList<Employee> employees;
    private EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etId = findViewById(R.id.etId);
        etFullName = findViewById(R.id.etFullName);
        cbManager = findViewById(R.id.cbManager);
        btnAdd = findViewById(R.id.btnAdd);
        lvEmployee = findViewById(R.id.lvEmployee);

        employees = new ArrayList<>();

        // Dữ liệu mẫu
        employees.add(new Employee("NV01", "Nhân", true));
        employees.add(new Employee("NV02", "Văn", false));
        employees.add(new Employee("NV03", "An", false));
        employees.add(new Employee("NV04", "Bình", true));

        adapter = new EmployeeAdapter(
                this,
                R.layout.item_employee,
                employees
        );

        lvEmployee.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> addEmployee());
    }

    private void addEmployee() {

        String id = etId.getText().toString().trim();
        String fullName = etFullName.getText().toString().trim();

        if (id.isEmpty()) {
            etId.setError("Vui lòng nhập ID");
            return;
        }

        if (fullName.isEmpty()) {
            etFullName.setError("Vui lòng nhập họ tên");
            return;
        }

        // Kiểm tra ID trùng
        for (Employee e : employees) {
            if (e.getId().equalsIgnoreCase(id)) {
                etId.setError("ID đã tồn tại");
                return;
            }
        }

        boolean isManager = cbManager.isChecked();

        Employee employee = new Employee(
                id,
                fullName,
                isManager
        );

        employees.add(employee);

        adapter.notifyDataSetChanged();

        etId.setText("");
        etFullName.setText("");
        cbManager.setChecked(false);

        Toast.makeText(
                this,
                "Thêm nhân viên thành công",
                Toast.LENGTH_SHORT
        ).show();
    }
}