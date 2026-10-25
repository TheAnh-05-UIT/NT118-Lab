package vn.edu.uit.nt118.lab03_3;

import android.app.Activity;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StudentFormActivity extends Activity {

    private StudentDatabaseHelper dbHelper;

    private EditText edtCode;
    private EditText edtName;
    private EditText edtClass;
    private EditText edtPhone;

    private long studentId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);

        dbHelper = new StudentDatabaseHelper(this);

        edtCode = findViewById(R.id.edtCode);
        edtName = findViewById(R.id.edtName);
        edtClass = findViewById(R.id.edtClass);
        edtPhone = findViewById(R.id.edtPhone);

        TextView tvTitle = findViewById(R.id.tvFormTitle);
        Button btnSave = findViewById(R.id.btnSave);

        studentId = getIntent().getLongExtra("student_id", -1);

        // Nếu có student_id thì chuyển sang chế độ sửa
        if (studentId != -1) {
            tvTitle.setText("Sửa thông tin sinh viên");

            Student student = dbHelper.getStudent(studentId);

            if (student == null) {
                Toast.makeText(
                        this,
                        "Không tìm thấy sinh viên",
                        Toast.LENGTH_SHORT
                ).show();

                finish();
                return;
            }

            edtCode.setText(student.getStudentCode());
            edtName.setText(student.getName());
            edtClass.setText(student.getClassName());
            edtPhone.setText(student.getPhone());
        }

        btnSave.setOnClickListener(v -> saveStudent());
    }

    private void saveStudent() {
        String code = edtCode.getText().toString().trim();
        String name = edtName.getText().toString().trim();
        String className = edtClass.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();

        // Kiểm tra dữ liệu nhập
        if (code.isEmpty()) {
            edtCode.setError("Vui lòng nhập MSSV");
            edtCode.requestFocus();
            return;
        }

        if (name.isEmpty()) {
            edtName.setError("Vui lòng nhập họ tên");
            edtName.requestFocus();
            return;
        }

        if (className.isEmpty()) {
            edtClass.setError("Vui lòng nhập lớp");
            edtClass.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            edtPhone.setError("Vui lòng nhập số điện thoại");
            edtPhone.requestFocus();
            return;
        }

        try {
            if (studentId == -1) {
                // THÊM MỚI
                Student student = new Student(
                        code, name, className, phone
                );

                long result = dbHelper.addStudent(student);

                if (result == -1) {
                    edtCode.setError(
                            "MSSV đã tồn tại hoặc không hợp lệ"
                    );
                    return;
                }

            } else {
                // CẬP NHẬT
                Student student = new Student(
                        studentId, code, name, className, phone
                );

                int result = dbHelper.updateStudent(student);

                if (result == 0) {
                    Toast.makeText(
                            this,
                            "Không tìm thấy sinh viên",
                            Toast.LENGTH_SHORT
                    ).show();
                    return;
                }
            }

            Toast.makeText(
                    this, "Lưu thành công", Toast.LENGTH_SHORT
            ).show();

            finish();

        } catch (SQLiteConstraintException e) {
            edtCode.setError("MSSV đã tồn tại");
        }
    }

    @Override
    protected void onDestroy() {
        if (dbHelper != null) {
            dbHelper.close();
        }

        super.onDestroy();
    }
}
