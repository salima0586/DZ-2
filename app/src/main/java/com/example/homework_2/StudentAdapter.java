package com.example.homework_2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class  StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private ArrayList<Student> students = new ArrayList<>();
    private final OnItemClickListener onItemClickListener;


public StudentAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        }

public void submitList(ArrayList<Student> students) {
        this.students = students;
        notifyDataSetChanged();
        }

public void updateStudent(Student student, int position) {
        students.remove(position);
        students.add(position, student);
        notifyDataSetChanged();
        }

public void addStudent(Student student) {
        students.add(student);
        notifyDataSetChanged();
        }
        @NonNull
        @Override
public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context ctx = parent.getContext();
        View itemView = LayoutInflater.from(ctx).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(itemView);
        }

@Override
public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = students.get(position);
        holder.bind(student, position);
    if (position%2 == 0){
        holder.itemView.setBackgroundColor( Color.parseColor("#FF000000"));
    } else {
        holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
    }
    holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            students.remove(position);
            notifyDataSetChanged();
            return true;
        }
    });
}

@Override
public int getItemCount() {
        return students.size();
        }

class StudentViewHolder extends RecyclerView.ViewHolder {

    private Student currentStudent;
    private int currentPosition;

    private final TextView nameTextView;
    private final TextView surnameTextView;

    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.name);
        surnameTextView = itemView.findViewById(R.id.surname );
        itemView.setOnClickListener(v -> onItemClickListener.click(currentStudent, currentPosition));
    }

    public void bind(Student student, int position) {
        currentStudent = student;
        currentPosition = position;
        nameTextView.setText(currentStudent.getName());
        surnameTextView.setText(currentStudent.getSurname());
    }
}


public interface OnItemClickListener {
    void click(Student student, int position);
}
}

