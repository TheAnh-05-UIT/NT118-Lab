package vn.edu.uit.nt118.lab02_2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvPerson;
    private ListView lvPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPerson = findViewById(R.id.tv_person);
        lvPerson = findViewById(R.id.lv_person);

        String[] names = {"Tèo", "Tý", "Bin", "Bo", "Nguyen Quang The Anh", "24520115"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                names
        );

        lvPerson.setAdapter(adapter);

        lvPerson.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        lvPerson.setOnItemClickListener(
                (parent, view, position, id) -> {

                    lvPerson.setItemChecked(position, true);

                    tvPerson.setText(
                            "Position: " + position
                                    + " ; Value: " + names[position]
                    );
                }
        );
    }
}