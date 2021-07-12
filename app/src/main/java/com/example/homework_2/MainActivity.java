package com.example.homework_2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private static final int UPDATE_STUDENT = 1;
    private static final int ADD_STUDENT = 2;

    private FloatingActionButton fab;

    private RecyclerView studentRecyclerView;
    private StudentAdapter studentAdapter;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentRecyclerView = findViewById(R.id.recycler);
        fab = findViewById(R.id.fab);

        studentAdapter = new StudentAdapter( (student, position) -> {
            this.position = position;
            Intent intent = new Intent( this, FormActivity.class );
            intent.putExtra( "student", student );
            startActivityForResult( intent, UPDATE_STUDENT );
        } ) {
        };
        studentRecyclerView.setAdapter(studentAdapter);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, FormActivity.class);
            startActivityForResult(intent, ADD_STUDENT);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null && resultCode == RESULT_OK) {
            Student updatedStudent = (Student) data.getSerializableExtra("student");
            if (requestCode == UPDATE_STUDENT) {
                studentAdapter.updateStudent(updatedStudent, position);
            } else if (requestCode == ADD_STUDENT) {
                studentAdapter.addStudent(updatedStudent);
            }
        }
    }
}
