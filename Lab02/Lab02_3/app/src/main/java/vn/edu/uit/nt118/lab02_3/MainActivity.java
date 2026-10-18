package vn.edu.uit.nt118.lab02_3;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText etId, etName;
    private RadioGroup rgType;
    private Button btnAdd;
    private ListView lvEmployee;
    private ArrayList<Employee> employees;
    private ArrayAdapter<Employee> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        rgType = findViewById(R.id.rgType);
        btnAdd = findViewById(R.id.btnAdd);
        lvEmployee = findViewById(R.id.lvEmployee);

        employees = new ArrayList<>();

        employees.add(
                new EmployeeFullTime(
                        "m1", "Nguyen Thi Teo"
                )
        );

        employees.add(
                new EmployeePartTime(
                        "m2", "Tran Van Ty"
                )
        );

        employees.add(
                new EmployeeFullTime(
                        "m3", "Ho DO"
                )
        );

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                employees
        );

        lvEmployee.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> addNewEmployee());
    }

    public void addNewEmployee() {

        // Lấy dữ liệu từ EditText
        String id = etId.getText().toString().trim();
        String name = etName.getText().toString().trim();

        if (id.isEmpty()) {
            etId.setError("Vui lòng nhập mã nhân viên");
            etId.requestFocus();
            return;
        }

        if (name.isEmpty()) {
            etName.setError("Vui lòng nhập tên nhân viên");
            etName.requestFocus();
            return;
        }

        for (Employee e : employees) {
            if (e.getId().equalsIgnoreCase(id)) {
                etId.setError("Mã nhân viên đã tồn tại");
                return;
            }
        }

        int radId = rgType.getCheckedRadioButtonId();

        Employee employee;

        if (radId == R.id.rd_chinhthuc) {
            employee = new EmployeeFullTime();
        } else {
            employee = new EmployeePartTime();
        }

        employee.setId(id);
        employee.setName(name);

        employees.add(employee);

        adapter.notifyDataSetChanged();

        etId.setText("");
        etName.setText("");

        rgType.check(R.id.rd_chinhthuc);

        Toast.makeText(
                this,
                "Thêm nhân viên thành công",
                Toast.LENGTH_SHORT
        ).show();
    }
}