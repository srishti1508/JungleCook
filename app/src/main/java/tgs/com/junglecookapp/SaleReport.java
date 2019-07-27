package tgs.com.junglecookapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.twotoasters.jazzylistview.recyclerview.JazzyRecyclerViewScrollListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tgs.com.junglecookapp.retrofit.ApiClient;
import tgs.com.junglecookapp.retrofit.InterfaceApi;


public class SaleReport extends Fragment {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Button manageprofile, fee_structure, change_pwd;
    ImageView rightimage;
    TextView name, activityName,nodata,dateshow,search;
    String date="";
    TableAdapter tableAdapter;
    Dialog dialog;
    EditText fromdate;
    String month, SelectedYear;
    private List<TableModel> albumList;
    public static  String SelectedMonth;
    int currnet_year, decrease, increase;
    int mYear, mMonth, mDay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_sale_copy, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        recyclerView=view.findViewById(R.id.recycler_view);
        fromdate=view.findViewById(R.id.fromdate);
        search=view.findViewById(R.id.search);
        activityName=view.findViewById(R.id.activityName);
        nodata=view.findViewById(R.id.nodata);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateandTime = sdf.format(new Date());
        fromdate.setText(currentDateandTime);
        fromdate.setOnClickListener(new View.OnClickListener() {
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
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        fromdate.setText(sdf.format(newDate.getTime()));
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.getDatePicker().setCalendarViewShown(false);
                mDatePicker.show();
            }
        });

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        JazzyRecyclerViewScrollListener jazzyScrollListener = new JazzyRecyclerViewScrollListener();
        recyclerView.setOnScrollListener(jazzyScrollListener);
        jazzyScrollListener.setTransitionEffect(1);
        progressBar = (ProgressBar)view.findViewById(R.id.progress);
        Sprite doubleBounce = new Wave();
        progressBar.setIndeterminateDrawable(doubleBounce);

        //setHasOptionsMenu(true);
        getServiceResponseData(date);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrate();
                recyclerView.setVisibility(View.GONE);
                getServiceResponseData(fromdate.getText().toString());
               /* SaleDateReport fragment = new SaleDateReport();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.setCustomAnimations(R.animator.fade_in,
                        R.animator.fade_out);
                ft.replace(R.id.frag_container, fragment);
                ft.commit();*/
            }
        });
        return view;
    }

    private void vibrate() {
        Vibrator v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(100); // 5000 miliseconds = 5 seconds

    }

    private void getServiceResponseData(String Date) {

        InterfaceApi api = ApiClient.getClient().create(InterfaceApi.class);
        Call<SaleModel> call = api.get_sale_report("5199",Date);
        call.enqueue(new Callback<SaleModel>() {
            @Override
            public void onResponse(Call<SaleModel> call, Response<SaleModel> response) {
              progressBar.setVisibility(View.GONE);
              recyclerView.setVisibility(View.VISIBLE);
                final SaleModel status = response.body();

                if (status.getResponse().size()>0) {
                    if(status.getGrand_total().equals("")){
                        activityName.setText("Total Sale : 0");
                    }else {
                        activityName.setText("Total Sale : " + status.getGrand_total());
                    }
                    tableAdapter = new TableAdapter(getActivity(),status);
                    recyclerView.setAdapter(tableAdapter);
                    nodata.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    nodata.setVisibility(View.VISIBLE);
                    //Toast.makeText(getActivity(), ""+status.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<SaleModel> call, Throwable t) {

                Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }
        });
    }



    private class TableAdapter extends RecyclerView.Adapter<TableAdapter.MyViewHolder>  {
        private Context mContext;
        private List<SaleModel.Response> albumList;
        public TableAdapter(Context mContext,SaleModel albumList) {
            this.mContext = mContext;
            this.albumList = albumList.getResponse();
        }
        @Override
        public TableAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.sale_single_copy, parent, false);
            return new TableAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull final TableAdapter.MyViewHolder holder, int position) {
            final SaleModel.Response table = albumList.get(position);

            holder.subtotal.setText(table.getSubtotal());
            holder.total.setText(table.getTotal());
            holder.paid.setText(table.getStatus());
            holder.Date.setText(fromdate.getText().toString());

            if(table.getDiscount().equals("")){
                holder.discount.setText("0");
            }else{
                holder.discount.setText(table.getDiscount());
            }
        }
        @Override
        public int getItemCount() {
            return albumList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView subtotal,discount,total,paid,Date;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                subtotal=itemView.findViewById(R.id.subtotal);
                discount=itemView.findViewById(R.id.discount);
                total=itemView.findViewById(R.id.total);
                paid=itemView.findViewById(R.id.paid);
                Date=itemView.findViewById(R.id.Date);
            }
        }
    }
}
