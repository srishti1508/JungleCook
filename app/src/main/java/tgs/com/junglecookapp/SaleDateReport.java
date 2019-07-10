package tgs.com.junglecookapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SaleDateReport extends Fragment {

    EditText date;
    Button Search;
    int mYear, mMonth, mDay;
    String Dates="";
    TextView activityName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dateexpense, container, false);
         date=view.findViewById(R.id.date);
         Search=view.findViewById(R.id.search);
        activityName=view.findViewById(R.id.activityName);
        activityName.setText("Sales");
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(selectedyear, selectedmonth, selectedday);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        date.setText(sdf.format(newDate.getTime()));
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.getDatePicker().setCalendarViewShown(false);
                mDatePicker.show();
            }
        });

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SaleSearchedReport fragment = new SaleSearchedReport();
                Bundle args = new Bundle();
                args.putString("Date", date.getText().toString());
                fragment.setArguments(args);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.setCustomAnimations(R.animator.fade_in,
                        R.animator.fade_out);
                ft.replace(R.id.frag_container, fragment);
                ft.commit();
            }
        });
        return view;
    }
}
