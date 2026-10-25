package vn.edu.uit.nt118.lab03_3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter
        extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    public interface OnStudentActionListener {
        void onClick(Student student);
        void onLongClick(Student student);
    }

    private final List<Student> students = new ArrayList<>();
    private final OnStudentActionListener listener;

    public StudentAdapter(OnStudentActionListener listener) {
        this.listener = listener;
    }

    public void setStudents(List<Student> newStudents) {
        students.clear();
        students.addAll(newStudents);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);

        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull StudentViewHolder holder, int position) {

        Student student = students.get(position);

        holder.tvName.setText(student.getName());
        holder.tvCode.setText("MSSV: " + student.getStudentCode());
        holder.tvClass.setText("Lớp: " + student.getClassName());
        holder.tvPhone.setText("SĐT: " + student.getPhone());

        holder.itemView.setOnClickListener(
                v -> listener.onClick(student)
        );

        holder.itemView.setOnLongClickListener(v -> {
            listener.onLongClick(student);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {

        final TextView tvName;
        final TextView tvCode;
        final TextView tvClass;
        final TextView tvPhone;

        StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvCode = itemView.findViewById(R.id.tvCode);
            tvClass = itemView.findViewById(R.id.tvClass);
            tvPhone = itemView.findViewById(R.id.tvPhone);
        }
    }
}
