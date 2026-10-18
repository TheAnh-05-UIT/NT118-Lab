package vn.edu.uit.nt118.lab02_6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerEmployeeAdapter
        extends RecyclerView.Adapter<
        RecyclerEmployeeAdapter.EmployeeViewHolder> {

    private final List<Employee> employees;

    public RecyclerEmployeeAdapter(List<Employee> employees) {
        this.employees = employees;
    }

    // 1. ViewHolder lưu các View của một dòng
    public static class EmployeeViewHolder
            extends RecyclerView.ViewHolder {

        TextView tvFullName;
        TextView tvPosition;
        ImageView ivManager;
        LinearLayout llParent;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFullName = itemView.findViewById(
                    R.id.item_employee_tv_fullname);

            tvPosition = itemView.findViewById(
                    R.id.item_employee_tv_position);

            ivManager = itemView.findViewById(
                    R.id.item_employee_iv_manager);

            llParent = itemView.findViewById(
                    R.id.item_employee_ll_parent);
        }
    }

    // 2. Khởi tạo giao diện một dòng nhân viên
    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(
                        R.layout.item_employee,
                        parent,
                        false
                );

        return new EmployeeViewHolder(view);
    }

    // 3. Gắn dữ liệu nhân viên vào ViewHolder
    @Override
    public void onBindViewHolder(
            @NonNull EmployeeViewHolder holder,
            int position) {

        Employee employee = employees.get(position);

        // Hiển thị họ tên
        holder.tvFullName.setText(employee.getFullName());

        // Kiểm tra Manager
        if (employee.isManager()) {

            holder.ivManager.setVisibility(View.VISIBLE);
            holder.tvPosition.setVisibility(View.GONE);

        } else {

            holder.ivManager.setVisibility(View.GONE);
            holder.tvPosition.setVisibility(View.VISIBLE);
            holder.tvPosition.setText("Staff");
        }

        // Màu nền xen kẽ
        if (position % 2 == 0) {
            holder.llParent.setBackgroundResource(
                    R.color.white);
        } else {
            holder.llParent.setBackgroundResource(
                    R.color.light_blue);
        }
    }

    // 4. Trả về số lượng nhân viên
    @Override
    public int getItemCount() {
        return employees.size();
    }
}