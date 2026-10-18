package vn.edu.uit.nt118.lab02_5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DishAdapter extends ArrayAdapter<Dish> {

    public DishAdapter(
            Context context,
            List<Dish> dishes) {

        super(context, 0, dishes);
    }

    @NonNull
    @Override
    public View getView(
            int position,
            @Nullable View convertView,
            @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater
                    .from(getContext())
                    .inflate(
                            R.layout.item_dish,
                            parent,
                            false
                    );
        }

        Dish dish = getItem(position);

        ImageView imgDish =
                convertView.findViewById(R.id.imgDish);

        TextView tvDishName =
                convertView.findViewById(R.id.tvDishName);

        TextView tvStar =
                convertView.findViewById(R.id.tvStar);

        if (dish != null) {

            // Hiển thị ảnh
            imgDish.setImageResource(
                    dish.getThumbnail().getImg()
            );

            // Hiển thị tên món ăn
            tvDishName.setText(dish.getName());

            // Chạy chữ khi tên quá dài
            tvDishName.setSelected(true);

            // Hiển thị icon khuyến mãi
            if (dish.isPromotion()) {
                tvStar.setVisibility(View.VISIBLE);
            } else {
                tvStar.setVisibility(View.GONE);
            }
        }

        return convertView;
    }
}
