package vn.edu.uit.nt118.lab03_3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StudentDetailActivity extends Activity {

    private StudentDatabaseHelper dbHelper;
    private long studentId;

    private TextView tvDetailCode;
    private TextView tvDetailName;
    private TextView tvDetailClass;
    private TextView tvDetailPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        dbHelper = new StudentDatabaseHelper(this);

        studentId = getIntent().getLongExtra("student_id", -1);

        tvDetailCode = findViewById(R.id.tvDetailCode);
        tvDetailName = findViewById(R.id.tvDetailName);
        tvDetailClass = findViewById(R.id.tvDetailClass);
        tvDetailPhone = findViewById(R.id.tvDetailPhone);

        Button btnEdit = findViewById(R.id.btnEdit);
        Button btnDelete = findViewById(R.id.btnDelete);

        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(
                    this, StudentFormActivity.class
            );

            intent.putExtra("student_id", studentId);
            startActivity(intent);
        });

        btnDelete.setOnClickListener(v -> confirmDelete());
    }

    @Override
    protected void onResume() {
        super.onResume();

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

        tvDetailCode.setText(student.getStudentCode());
        tvDetailName.setText(student.getName());
        tvDetailClass.setText(student.getClassName());
        tvDetailPhone.setText(student.getPhone());
    }

    private void confirmDelete() {
        new AlertDialog.Builder(this)
                .setTitle("Xác nhận xóa")
                .setMessage(
                        "Xóa sinh viên này khỏi cơ sở dữ liệu?"
                )
                .setNegativeButton("Hủy", null)
                .setPositiveButton("Xóa", (dialog, which) -> {
                    dbHelper.deleteStudent(studentId);
                    finish();
                })
                .show();
    }

    @Override
    protected void onDestroy() {
        if (dbHelper != null) {
            dbHelper.close();
        }

        super.onDestroy();
    }
}
