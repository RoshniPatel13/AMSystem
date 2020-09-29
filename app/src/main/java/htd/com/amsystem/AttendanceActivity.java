package htd.com.amsystem;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import htd.com.amsystem.adapter.AttendanceAdapter;
import htd.com.amsystem.database.AttendanceDetails;
import htd.com.amsystem.database.DatabaseHelper;
import htd.com.amsystem.database.StudentDetails;

public class AttendanceActivity extends AppCompatActivity {
    public static Button btnSubmit;
    AttendanceAdapter attendanceAdapter;
    DatabaseHelper databaseHelper;
    ArrayList<StudentDetails> studentDetails;
    ArrayList<AttendanceDetails> attendanceDetails;
    private RecyclerView rvStudents;
    private TextView subjectName;
    public static int sub_id;
    private String date;
    public static LinearLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sub_id = extras.getInt("subject");
            date = extras.getString("date");
        }

        databaseHelper = new DatabaseHelper(this);
        studentDetails = new ArrayList<>();
        attendanceDetails = new ArrayList<AttendanceDetails>();
        studentDetails = databaseHelper.getAllStudentData();
        attendanceDetails = databaseHelper.getAllAttendanceData();

        rvStudents = findViewById(R.id.rvStudent);
        btnSubmit = findViewById(R.id.submit);
        subjectName = findViewById(R.id.subject);
        linearLayout = findViewById(R.id.ll3);


        if (sub_id == 1) {
            subjectName.setText("Database Design");
        } else if (sub_id == 2) {
            subjectName.setText("Java Programming");
        } else if (sub_id == 3) {
            subjectName.setText("Introduction To Swift Programming");
        } else {
            subjectName.setText("Database Design");
        }

        rvStudents.setLayoutManager(new GridLayoutManager(this, 1));
        attendanceAdapter = new AttendanceAdapter(AttendanceActivity.this, studentDetails);
        rvStudents.setHasFixedSize(true);
        rvStudents.setAdapter(attendanceAdapter);

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}

