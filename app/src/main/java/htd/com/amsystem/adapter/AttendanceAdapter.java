package htd.com.amsystem.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import htd.com.amsystem.AttendanceActivity;
import htd.com.amsystem.AttendanceViewActivity;
import htd.com.amsystem.FacultyActivity;
import htd.com.amsystem.R;
import htd.com.amsystem.database.AttendanceDetails;
import htd.com.amsystem.database.DatabaseHelper;
import htd.com.amsystem.database.StudentDetails;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.ViewHolderStudent> {

    public ArrayList<StudentDetails> studentDetails;
    String attendance;
    int stdId;
    String sfname, slname;
    ArrayList<AttendanceDetails> attendanceDetailsArrayList;
    int subid;
    private Context context;
    private DatabaseHelper db;
    private AttendanceAdapter.onItemLongClickListener onItemLongClickListener;
    private Dialog dialog;
    private Button btnExit;
    private Button btnView;

    public AttendanceAdapter(Context mContext, ArrayList<StudentDetails> studentDetailsArrayList) {
        this.context = mContext;
        this.studentDetails = studentDetailsArrayList;
        db = new DatabaseHelper(context);

    }

    public void setOnItemLongClickListener(AttendanceAdapter.onItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @NonNull
    @Override
    public AttendanceAdapter.ViewHolderStudent onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_row, parent, false);
        AttendanceAdapter.ViewHolderStudent viewHolderStudent = new ViewHolderStudent(view);
        return viewHolderStudent;
    }

    @Override
    public void onBindViewHolder(@NonNull final AttendanceAdapter.ViewHolderStudent holder, final int position) {

        subid = AttendanceActivity.sub_id;

        sfname = studentDetails.get(position).getFname();
        slname = studentDetails.get(position).getLname();
        stdId = studentDetails.get(position).getStudId();

        holder.stdName.setText(sfname + " " + slname);
        holder.stdID.setText(stdId + "");

        attendance = holder.sPA.getText().toString();

        if (AttendanceViewActivity.viewAttendance != null)
            if (AttendanceViewActivity.viewAttendance.equals("view")) {
                stdId = studentDetails.get(position).getStudId();
                subid = AttendanceActivity.sub_id;
                attendanceDetailsArrayList = db.getStudentAttendance(stdId, subid);
                holder.sPA.setVisibility(View.GONE);
                holder.txtViewPA.setText(attendanceDetailsArrayList.get(0).getStatus());
                holder.txtViewPA.setVisibility(View.VISIBLE);
                AttendanceActivity.linearLayout.setVisibility(View.GONE);
            } else {
                AttendanceActivity.linearLayout.setVisibility(View.VISIBLE);
            }

        holder.sPA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                AttendanceActivity.linearLayout.setVisibility(View.VISIBLE);

                sfname = studentDetails.get(position).getFname();
                slname = studentDetails.get(position).getLname();
                stdId = studentDetails.get(position).getStudId();
                attendance = holder.sPA.getTextOn().toString();

                db.UpdateAttendanceDetails(stdId, subid, 45 + "", attendance, "20/2/2020");
                Log.e("stddet", stdId + " " + attendance);
            }
        });

        AttendanceActivity.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTitleDialog();
            }
        });

        db.UpdateAttendanceDetails(stdId, subid, 45 + "", attendance, "20/2/2020");
    }

    public void showTitleDialog() {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.view_attendance_dialog);

        View v = dialog.getWindow().getDecorView();
        v.setBackgroundResource(android.R.color.transparent);

        btnExit = dialog.findViewById(R.id.no);
        btnView = dialog.findViewById(R.id.yes);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                context.startActivity(new Intent(context, FacultyActivity.class));
                ((Activity) context).finish();
            }
        });

        Log.e("sub", subid + "");
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AttendanceViewActivity.class);
                i.putExtra("ViewAttendance", "view");
                i.putExtra("subId", subid);
                context.startActivity(i);
                ((Activity) context).finish();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return studentDetails != null ? studentDetails.size() : 0;
    }

    public static abstract interface onItemLongClickListener {
        public abstract void onItemLongClick(int position);
    }

    public class ViewHolderStudent extends RecyclerView.ViewHolder {
        private final TextView stdName;
        private final TextView stdID;
        private final ToggleButton sPA;
        private final TextView txtViewPA;

        public ViewHolderStudent(@NonNull View itemView) {
            super(itemView);

            stdName = itemView.findViewById(R.id.sName);
            stdID = itemView.findViewById(R.id.studID);
            sPA = itemView.findViewById(R.id.spa);
            txtViewPA = itemView.findViewById(R.id.txtViewPA);
        }
    }
}
