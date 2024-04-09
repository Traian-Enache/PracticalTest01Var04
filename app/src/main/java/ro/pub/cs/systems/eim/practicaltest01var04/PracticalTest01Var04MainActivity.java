package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01Var04MainActivity extends AppCompatActivity {
    Button navigate, display;
    EditText row1, row2, hidden;
    CheckBox cbox1, cbox2;

    private ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_main);

        navigate = findViewById(R.id.nav_secondary_button);
        display = findViewById(R.id.display_button);
        row1 = findViewById(R.id.text_row_1);
        row2 = findViewById(R.id.text_row_2);
        hidden = findViewById(R.id.text_hidden);
        cbox1 = findViewById(R.id.check_1);
        cbox2 = findViewById(R.id.check_2);


        display.setOnClickListener(v -> {
            String str1 = row1.getText().toString();
            String str2 = row2.getText().toString();

            boolean checked1 = cbox1.isChecked();
            boolean checked2 = cbox2.isChecked();
            String finalText = "";

            if (checked1 && str1.isEmpty() || checked2 && str2.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Empty text box", Toast.LENGTH_LONG).show();
            } else {

                if (checked1) {
                    finalText += str1;

                    if (checked2)
                        finalText += " ";
                }

                if (checked2)
                    finalText += str2;
            }

            hidden.setText(finalText);
            hidden.setVisibility(View.VISIBLE);
        });

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                //int sum = result.getData().getIntExtra(Constants.SUM, 0);
                Toast.makeText(getApplicationContext(), "The activity returned with result OK", Toast.LENGTH_LONG).show();
            } else if (result.getResultCode() == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "The activity returned with result CANCELED", Toast.LENGTH_LONG).show();
            }
        });

        navigate.setOnClickListener(v -> {
            String txt1 = row1.getText().toString();
            String txt2 = row2.getText().toString();

            Intent intent = new Intent(getApplicationContext(), PracticalTest01Var04SecondaryActivity.class);
            intent.putExtra(Constants.TEXT_1, txt1);
            intent.putExtra(Constants.TEXT_2, txt2);
            activityResultLauncher.launch(intent);
        });

    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(Constants.TEXT_1, row1.getText().toString());
        outState.putString(Constants.TEXT_2, row2.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey(Constants.TEXT_1)) {
            row1.setText(savedInstanceState.getString(Constants.TEXT_1));
        } else {
            row1.setText("");
        }

        if (savedInstanceState.containsKey(Constants.TEXT_2)) {
            row2.setText(savedInstanceState.getString(Constants.TEXT_2));
        } else {
            row2.setText("");
        }
    }
}