package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01Var04SecondaryActivity extends AppCompatActivity {
    Button okButton, cancelButton;
    TextView numeView, grupaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_secondary);

        okButton = findViewById(R.id.OkButton);
        cancelButton = findViewById(R.id.CancelButton);
        numeView = findViewById(R.id.nume);
        grupaView = findViewById(R.id.grupa);

        Intent intent = getIntent();
        String numetext = intent.getStringExtra(Constants.TEXT_1);
        String grupatext = intent.getStringExtra(Constants.TEXT_2);
        numeView.setText("Nume: " + numetext);
        grupaView.setText("Grupa: " + grupatext);

        okButton.setOnClickListener(v -> {
            Intent result = new Intent();
            setResult(RESULT_OK, result);
            finish();
        });

        cancelButton.setOnClickListener(v -> {
            Intent result = new Intent();
            setResult(RESULT_CANCELED, result);
            finish();
        });
    }
}