package htd.com.amsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import htd.com.amsystem.adapter.AttendanceAdapter;
import htd.com.amsystem.database.AttendanceDetails;
import htd.com.amsystem.database.DatabaseHelper;
import htd.com.amsystem.database.StudentDetails;

public class AttendanceViewActivity extends AppCompatActivity {

    public static String viewAttendance;
    DatabaseHelper databaseHelper;
    ArrayList<AttendanceDetails> attendanceDetailsArrayList;
    ArrayList<StudentDetails> studentDetailsArrayList;
    int subId;
    private TextView subjects;
    private RecyclerView rvStudents;
    private AttendanceAdapter attendanceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_view);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            viewAttendance = bundle.getString("ViewAttendance");
            subId = bundle.getInt("subId");
        }

        databaseHelper = new DatabaseHelper(this);
        attendanceDetailsArrayList = new ArrayList<>();
        studentDetailsArrayList = new ArrayList<>();

        studentDetailsArrayList = databaseHelper.getAllStudentData();

        subjects = findViewById(R.id.subjectName);
        rvStudents = findViewById(R.id.rvStudents);

        if (subId == 1) {
            subjects.setText("Database Design");
        } else if (subId == 2) {
            subjects.setText("Java Programming");
        } else if (subId == 3) {
            subjects.setText("Introduction to swift Programming");
        } else {
            subjects.setText("Default Subject");
        }

        rvStudents.setLayoutManager(new GridLayoutManager(this, 1));
        attendanceAdapter = new AttendanceAdapter(this, studentDetailsArrayList);
        rvStudents.setHasFixedSize(true);
        rvStudents.setAdapter(attendanceAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), FacultyActivity.class);
        startActivity(i);
        viewAttendance = "new";
        finish();
        super.onBackPressed();
    }
}
