package htd.com.amsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import htd.com.amsystem.R;
import htd.com.amsystem.database.DatabaseHelper;
import htd.com.amsystem.database.StudentDetails;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ViewHolderStudent> {

    public ArrayList<StudentDetails> studentDetails;
    private Context context;
    private DatabaseHelper db;
    private StudentListAdapter.onItemLongClickListener onItemLongClickListener;

    public StudentListAdapter(Context mContext, ArrayList<StudentDetails> studentDetailsArrayList) {
        this.context = mContext;
        this.studentDetails = studentDetailsArrayList;
        db = new DatabaseHelper(context);
    }

    public void setOnItemLongClickListener(StudentListAdapter.onItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @NonNull
    @Override
    public StudentListAdapter.ViewHolderStudent onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_row, parent, false);
        StudentListAdapter.ViewHolderStudent viewHolderStudent = new StudentListAdapter.ViewHolderStudent(view);
        return viewHolderStudent;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentListAdapter.ViewHolderStudent holder, final int position) {
        int stdId, age;
        String sfname, slname, contact, email, dob;

        sfname = studentDetails.get(position).getFname();
        slname = studentDetails.get(position).getLname();
        stdId = studentDetails.get(position).getStudId();
        contact = studentDetails.get(position).getContact();
        email = studentDetails.get(position).getEmail();
        dob = studentDetails.get(position).getDob();
        age = studentDetails.get(position).getAge();

        holder.sid.setText(stdId + "");
        holder.sname.setText(sfname + " " + slname);
        holder.scontact.setText(contact + "");
        holder.semail.setText(email);
        holder.sdob.setText(dob + "");
        holder.sage.setText(age + "");

        holder.relLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemLongClickListener.onItemLongClick(position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentDetails != null ? studentDetails.size() : 0;
    }

    public static abstract interface onItemLongClickListener {
        public abstract void onItemLongClick(int position);
    }

    public class ViewHolderStudent extends RecyclerView.ViewHolder {
        private final TextView sid;
        private final TextView sname;
        private final TextView semail;
        private final TextView scontact;
        private final TextView sdob;
        private final TextView sage;
        private final RelativeLayout relLayout;

        public ViewHolderStudent(@NonNull View itemView) {
            super(itemView);

            sid = itemView.findViewById(R.id.sid);
            sname = itemView.findViewById(R.id.sname);
            semail = itemView.findViewById(R.id.semail);
            scontact = itemView.findViewById(R.id.sphone);
            sdob = itemView.findViewById(R.id.sdob);
            sage = itemView.findViewById(R.id.sage);
            relLayout = itemView.findViewById(R.id.relLayoyt);
        }

    }
}
