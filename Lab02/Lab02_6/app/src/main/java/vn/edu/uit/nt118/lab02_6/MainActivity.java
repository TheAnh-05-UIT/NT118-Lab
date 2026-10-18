package vn.edu.uit.nt118.lab02_6;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etId, etFullName;
    private CheckBox cbManager;
    private Button btnAdd;
    private RecyclerView recyclerEmployees;

    private ArrayList<Employee> employees;
    private RecyclerEmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Ánh xạ giao diện
        etId = findViewById(R.id.etId);
        etFullName = findViewById(R.id.etFullName);
        cbManager = findViewById(R.id.cbManager);
        btnAdd = findViewById(R.id.btnAdd);

        recyclerEmployees = findViewById(
                R.id.recyclerEmployees);

        // 2. Khởi tạo ArrayList nhân viên
        employees = new ArrayList<>();

        // Dữ liệu mẫu
        employees.add(
                new Employee("NV01", "Nhân", true));

        employees.add(
                new Employee("NV02", "Văn", false));

        employees.add(
                new Employee("NV03", "An", false));

        employees.add(
                new Employee("NV04", "Bình", true));

        // 3. Khởi tạo RecyclerView Adapter
        adapter = new RecyclerEmployeeAdapter(employees);

        // 4. Thiết lập bố cục danh sách dọc
        recyclerEmployees.setLayoutManager(
                new LinearLayoutManager(this));

        // 5. Gắn Adapter vào RecyclerView
        recyclerEmployees.setAdapter(adapter);

        // 6. Xử lý nút ADD
        btnAdd.setOnClickListener(v -> addEmployee());
    }

    // Hàm thêm nhân viên
    private void addEmployee() {

        String id = etId.getText()
                .toString().trim();

        String fullName = etFullName.getText()
                .toString().trim();

        // Kiểm tra ID rỗng
        if (id.isEmpty()) {
            etId.setError("Vui lòng nhập ID");
            etId.requestFocus();
            return;
        }

        // Kiểm tra tên rỗng
        if (fullName.isEmpty()) {
            etFullName.setError("Vui lòng nhập họ tên");
            etFullName.requestFocus();
            return;
        }

        // Kiểm tra ID trùng
        for (Employee e : employees) {
            if (e.getId().equalsIgnoreCase(id)) {
                etId.setError("ID đã tồn tại");
                return;
            }
        }

        // Lấy trạng thái Manager
        boolean isManager = cbManager.isChecked();

        // Tạo nhân viên mới
        Employee employee = new Employee(
                id,
                fullName,
                isManager
        );

        // Thêm vào ArrayList
        employees.add(employee);

        // Cập nhật RecyclerView tại vị trí vừa thêm
        int position = employees.size() - 1;
        adapter.notifyItemInserted(position);

        // Cuộn đến nhân viên mới
        recyclerEmployees.scrollToPosition(position);

        // Reset dữ liệu nhập
        etId.setText("");
        etFullName.setText("");
        cbManager.setChecked(false);

        // Thông báo thành công
        Toast.makeText(
                this,
                "Thêm nhân viên thành công",
                Toast.LENGTH_SHORT
        ).show();
    }
}