package vn.edu.uit.nt118.lab02_5;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtName;
    private Spinner spThumbnail;
    private CheckBox cbPromotion;
    private Button btnAdd;
    private GridView gridDishes;

    private ArrayList<Dish> dishes;
    private DishAdapter dishAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Ánh xạ giao diện
        edtName = findViewById(R.id.edtName);
        spThumbnail = findViewById(R.id.spThumbnail);
        cbPromotion = findViewById(R.id.cbPromotion);
        btnAdd = findViewById(R.id.btnAdd);
        gridDishes = findViewById(R.id.gridDishes);

        // 2. Khởi tạo Spinner
        ThumbnailAdapter thumbnailAdapter =
                new ThumbnailAdapter(this);

        spThumbnail.setAdapter(thumbnailAdapter);

        // 3. Tạo danh sách món ăn
        dishes = new ArrayList<>();

        // Dữ liệu mẫu
        dishes.add(
                new Dish(
                        "Dish 01",
                        Thumbnail.Thumbnail1,
                        true
                )
        );

        dishes.add(
                new Dish(
                        "Dish 02",
                        Thumbnail.Thumbnail2,
                        false
                )
        );

        dishes.add(
                new Dish(
                        "Dish 03",
                        Thumbnail.Thumbnail3,
                        false
                )
        );

        // 4. Khởi tạo GridView
        dishAdapter = new DishAdapter(this, dishes);

        gridDishes.setNumColumns(2);
        gridDishes.setAdapter(dishAdapter);

        // 5. Xử lý nút thêm món ăn
        btnAdd.setOnClickListener(v -> addNewDish());
    }

    // Hàm thêm món ăn mới
    private void addNewDish() {

        // Lấy tên món ăn
        String name = edtName.getText()
                .toString()
                .trim();

        // Kiểm tra tên rỗng
        if (name.isEmpty()) {
            edtName.setError("Please enter dish name");
            edtName.requestFocus();
            return;
        }

        // Lấy thumbnail đã chọn
        Thumbnail thumbnail =
                (Thumbnail) spThumbnail.getSelectedItem();

        // Lấy trạng thái khuyến mãi
        boolean promotion = cbPromotion.isChecked();

        // Tạo đối tượng Dish
        Dish dish = new Dish(
                name,
                thumbnail,
                promotion
        );

        // Thêm món ăn vào danh sách
        dishes.add(dish);

        // Cập nhật GridView
        dishAdapter.notifyDataSetChanged();

        // Reset các trường nhập
        edtName.setText("");
        spThumbnail.setSelection(0);
        cbPromotion.setChecked(false);

        // Hiển thị thông báo
        Toast.makeText(
                this,
                "Added successfully",
                Toast.LENGTH_SHORT
        ).show();
    }
}