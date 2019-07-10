package tgs.com.junglecookapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tgs.com.junglecookapp.retrofit.ApiClient;
import tgs.com.junglecookapp.retrofit.InterfaceApi;


public class ExpenseSearchedReport extends Fragment {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ImageView rightimage;
    TextView name, activityName,nodata,dateshow;
    String date="";
    TableAdapter tableAdapter;
    Dialog dialog;
    String month, SelectedYear;
    private List<TableModel> albumList;
    public static  String SelectedMonth;
    int currnet_year, decrease, increase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_expenseseached, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        recyclerView=view.findViewById(R.id.recycler_view);
        rightimage=view.findViewById(R.id.rightimage);
        activityName=view.findViewById(R.id.activityName);
        nodata=view.findViewById(R.id.nodata);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        progressBar = (ProgressBar)view.findViewById(R.id.progress);
        Sprite doubleBounce = new Wave();
        progressBar.setIndeterminateDrawable(doubleBounce);

        Bundle bundle=getArguments();
        date=bundle.getString("Date");

        getServiceResponseData(date);
        rightimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ExpenseDateReport fragment = new ExpenseDateReport();
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
    private void getServiceResponseData(String Date) {
        InterfaceApi api = ApiClient.getClient().create(InterfaceApi.class);
        Call<ExpenseModel> call = api.expense_report("5199",Date);
        call.enqueue(new Callback<ExpenseModel>() {
            @Override
            public void onResponse(Call<ExpenseModel> call, Response<ExpenseModel> response) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                final ExpenseModel status = response.body();

                if (status.getResponse().size()>0) {
                    if(status.getTotal_expense().equals("")){
                        activityName.setText("Total Expense : 0");
                    }else {
                        activityName.setText("Total Expense : " + status.getTotal_expense());
                    }
                    tableAdapter = new TableAdapter(getActivity(),status);
                    recyclerView.setAdapter(tableAdapter);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    nodata.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onFailure(Call<ExpenseModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }
        });
    }

    private class TableAdapter extends RecyclerView.Adapter<TableAdapter.MyViewHolder>  {
        private Context mContext;
        private List<ExpenseModel.Response> albumList;
        public TableAdapter(Context mContext,ExpenseModel albumList) {
            this.mContext = mContext;
            this.albumList = albumList.getResponse();
        }
        @Override
        public TableAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.expense_single, parent, false);
            return new TableAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull final TableAdapter.MyViewHolder holder, int position) {
            final ExpenseModel.Response table = albumList.get(position);

            holder.expense.setText(table.getExpense_on());
            holder.category.setText(table.getExp_category());
            holder.reason.setText(table.getExpense_note());
            holder.Amount.setText(table.getAmount());
            holder.date.setText(table.getExp_date());
            holder.attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Attachment fragment = new Attachment();
                Bundle args = new Bundle();
                args.putString("url",table.getAttachment_url());
                fragment.setArguments(args);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_container, fragment);
                ft.commit();
            }
        });
    }
        @Override
        public int getItemCount() {
            return albumList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView expense,category,Amount,date,reason;
            ImageView attachment;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                expense=itemView.findViewById(R.id.expense);
                reason=itemView.findViewById(R.id.reason);
                category=itemView.findViewById(R.id.category);
                Amount=itemView.findViewById(R.id.Amount);
                date=itemView.findViewById(R.id.date);
                attachment=itemView.findViewById(R.id.attachment);
            }
        }
    }
}