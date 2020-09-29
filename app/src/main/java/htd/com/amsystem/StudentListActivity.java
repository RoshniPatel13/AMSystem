package htd.com.amsystem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import htd.com.amsystem.adapter.StudentListAdapter;
import htd.com.amsystem.database.DatabaseHelper;
import htd.com.amsystem.database.StudentDetails;

public class StudentListActivity extends AppCompatActivity implements StudentListAdapter.onItemLongClickListener {
    DatabaseHelper databaseHelper;
    ArrayList<StudentDetails> studentDetails;
    StudentListAdapter studentListAdapter;
    private RecyclerView rvStudentList;
    private Button btnNewStudent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        databaseHelper = new DatabaseHelper(this);
        studentDetails = new ArrayList<StudentDetails>();
        studentDetails = databaseHelper.getAllStudentData();

        rvStudentList = findViewById(R.id.rvStudentList);
        btnNewStudent = findViewById(R.id.newStudent);

        btnNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),StudentProfile.class));
                finish();
            }
        });

        rvStudentList.setLayoutManager(new GridLayoutManager(this, 1));
        studentListAdapter = new StudentListAdapter(StudentListActivity.this, studentDetails);
        rvStudentList.setHasFixedSize(true);
        studentListAdapter.setOnItemLongClickListener(this);
        rvStudentList.setAdapter(studentListAdapter);
    }

    private void deleteStudent(final int i) {
        final StudentDetails studentDetails = studentListAdapter.studentDetails.get(i);
        Log.i("ContentValues", "SEND ID: " + studentDetails.getsID());
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Delete Student Details");
        alertDialog.setMessage("Are you sure you want to delete this student details ?");
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                databaseHelper.deleteStudentDetail(studentDetails.getsID());
                studentListAdapter.studentDetails.remove(i);
                studentListAdapter.notifyDataSetChanged();
            }
        });
        alertDialog.setNegativeButton("NO", null);

        alertDialog.show();
    }

    @Override
    public void onItemLongClick(int position) {
        deleteStudent(position);
    }
}
