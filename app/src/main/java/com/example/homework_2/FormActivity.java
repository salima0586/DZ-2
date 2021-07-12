package com.example.homework_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText surnameEditText;
    private Button saveButton;

    private Student currenyStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        nameEditText = findViewById(R.id.et_name);
        surnameEditText = findViewById(R.id.et_surname);
        saveButton = findViewById(R.id.btn_save);

        Intent currentIntent = getIntent();
        if (currentIntent != null) {
            currenyStudent = (Student) currentIntent.getSerializableExtra("student");
        }

        if (currenyStudent != null) {
            nameEditText.setText(currenyStudent.getName());
            surnameEditText.setText(currenyStudent.getSurname());
        }

        saveButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            String name = nameEditText.getText().toString();
            String surname = surnameEditText.getText().toString();
            if (name.isEmpty() || surname.isEmpty()) {
                Toast.makeText(this, "Fill name, surname", Toast.LENGTH_LONG).show();
            } else {
                if (currenyStudent == null) {
                    currenyStudent = new Student(name, surname);
                } else {
                    currenyStudent.setName(name);
                    currenyStudent.setSurname(surname);
                }
                intent.putExtra("student", currenyStudent);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


}