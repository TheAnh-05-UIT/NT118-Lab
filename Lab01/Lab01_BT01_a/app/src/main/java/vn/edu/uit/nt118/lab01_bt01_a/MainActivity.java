package vn.edu.uit.nt118.lab01_bt01_a;

import android.graphics.Insets;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout llNameContainer, llAddressContainer, llStudentIDContainer, llParentContainer;

    private void createNameContainer() {
        llNameContainer = new LinearLayout(this);
        llNameContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        llNameContainer.setOrientation(LinearLayout.HORIZONTAL);
        TextView tvName = new TextView(this);
        tvName.setText("Name: ");
        tvName.setTextSize(20);
        llNameContainer.addView(tvName);
        TextView tvNameValue = new TextView(this);
        tvNameValue.setText("Nguyễn Quang Thế Anh");
        tvNameValue.setTextSize(20);
        llNameContainer.addView(tvNameValue);
    }

    private void createStudentIDContainer() {
        llStudentIDContainer = new LinearLayout(this);
        llStudentIDContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        llStudentIDContainer.setOrientation(LinearLayout.HORIZONTAL);
        TextView tvStudentID = new TextView(this);
        tvStudentID.setText("Student ID number: ");
        tvStudentID.setTextSize(20);
        llStudentIDContainer.addView(tvStudentID);
        TextView tvNameValue = new TextView(this);
        tvNameValue.setText("24520115");
        tvNameValue.setTextSize(20);
        llStudentIDContainer.addView(tvNameValue);
    }

    private void createAddressContainer() {
        llAddressContainer = new LinearLayout(this);
        llAddressContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        llAddressContainer.setOrientation(LinearLayout.HORIZONTAL);
        TextView tvAddress = new TextView(this);
        tvAddress.setText("Address:");
        tvAddress.setTextSize(20);
        llAddressContainer.addView(tvAddress);
        TextView tvAddressValue = new TextView(this);
        tvAddressValue.setText("TPHCM");
        tvAddressValue.setTextSize(20);
        llAddressContainer.addView(tvAddressValue);
    }
    private void createParentContainer() {
        llParentContainer = new LinearLayout(this);
        llParentContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        llParentContainer.setOrientation(LinearLayout.VERTICAL);
        llParentContainer.addView(llNameContainer);
        llParentContainer.addView(llStudentIDContainer);
        llParentContainer.addView(llAddressContainer);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createNameContainer();
        createStudentIDContainer();
        createAddressContainer();
        createParentContainer();

        setContentView(llParentContainer);
        ViewCompat.setOnApplyWindowInsetsListener(
                llParentContainer,
                (v, insets) -> {
                    Insets systemBars =
                            insets.getInsets(WindowInsetsCompat.Type.systemBars()).toPlatformInsets();
                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );
                    return insets;
                }
        );
    }
}