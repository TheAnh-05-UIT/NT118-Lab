package vn.edu.uit.nt118.lab03_3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends Activity {

    private StudentDatabaseHelper dbHelper;
    private StudentAdapter studentAdapter;
    private TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new StudentDatabaseHelper(this);

        tvEmpty = findViewById(R.id.tvEmpty);
        Button btnAdd = findViewById(R.id.btnAdd);

        RecyclerView recyclerView =
                findViewById(R.id.recyclerStudents);

        studentAdapter = new StudentAdapter(
                new StudentAdapter.OnStudentActionListener() {

                    @Override
                    public void onClick(Student student) {
                        Intent intent = new Intent(
                                MainActivity.this,
                                StudentDetailActivity.class
                        );

                        intent.putExtra("student_id", student.getId());
                        startActivity(intent);
                    }

                    @Override
                    public void onLongClick(Student student) {
                        confirmDelete(student);
                    }
                }
        );

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        recyclerView.setAdapter(studentAdapter);

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(
                    this, StudentFormActivity.class
            );
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadStudents();
    }

    private void loadStudents() {
        List<Student> students = dbHelper.getAllStudents();

        studentAdapter.setStudents(students);

        tvEmpty.setVisibility(
                students.isEmpty() ? View.VISIBLE : View.GONE
        );
    }

    private void confirmDelete(Student student) {
        new AlertDialog.Builder(this)
                .setTitle("Xóa sinh viên")
                .setMessage(
                        "Bạn có muốn xóa " +
                                student.getName() + "?"
                )
                .setNegativeButton("Hủy", null)
                .setPositiveButton("Xóa", (dialog, which) -> {
                    int rows = dbHelper.deleteStudent(
                            student.getId()
                    );

                    Toast.makeText(
                            this,
                            rows > 0
                                    ? "Đã xóa sinh viên"
                                    : "Không tìm thấy sinh viên",
                            Toast.LENGTH_SHORT
                    ).show();

                    loadStudents();
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
