package tgs.com.junglecookapp;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.twotoasters.jazzylistview.recyclerview.JazzyRecyclerViewScrollListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tgs.com.junglecookapp.retrofit.ApiClient;
import tgs.com.junglecookapp.retrofit.InterfaceApi;


public class ProfitReport extends Fragment {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Button manageprofile, fee_structure, change_pwd;
    ImageView rightimage;
    TextView name, activityName,nodata;
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
        View view = inflater.inflate(R.layout.activity_profit, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        recyclerView=view.findViewById(R.id.recycler_view);
        //rightimage=view.findViewById(R.id.rightimage);
       // activityName=view.findViewById(R.id.activityName);
        nodata=view.findViewById(R.id.nodata);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        JazzyRecyclerViewScrollListener jazzyScrollListener = new JazzyRecyclerViewScrollListener();
        recyclerView.setOnScrollListener(jazzyScrollListener);
        jazzyScrollListener.setTransitionEffect(11);
        progressBar = (ProgressBar)view.findViewById(R.id.progress);
        Sprite doubleBounce = new Wave();
        progressBar.setIndeterminateDrawable(doubleBounce);

        //setHasOptionsMenu(true);
        getServiceResponseData();
       /* rightimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               *//* ProfitSearchReport fragment = new ProfitSearchReport();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.setCustomAnimations(R.animator.fade_in,
                        R.animator.fade_out);
                ft.replace(R.id.frag_container, fragment);
                ft.commit();*//*
            }
        });
*/
        return view;
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.calender:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

  @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menusearch, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }*/



    private void getServiceResponseData() {

        InterfaceApi api = ApiClient.getClient().create(InterfaceApi.class);
        Call<ProfitModel> call = api.profit_report("5199");
        call.enqueue(new Callback<ProfitModel>() {
            @Override
            public void onResponse(Call<ProfitModel> call, Response<ProfitModel> response) {
              progressBar.setVisibility(View.GONE);
              recyclerView.setVisibility(View.VISIBLE);
                final ProfitModel status = response.body();

                    tableAdapter = new TableAdapter(getActivity(),status);
                    recyclerView.setAdapter(tableAdapter);


            }
            @Override
            public void onFailure(Call<ProfitModel> call, Throwable t) {

                Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();

            }
        });
    }

    private class TableAdapter extends RecyclerView.Adapter<TableAdapter.MyViewHolder>  {
        private Context mContext;
        private List<ProfitModel.Response> albumList;
        public TableAdapter(Context mContext,ProfitModel albumList) {
            this.mContext = mContext;
            this.albumList = albumList.getResponse();
        }
        @Override
        public TableAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.profit_single, parent, false);
            return new TableAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull final TableAdapter.MyViewHolder holder, int position) {
            final ProfitModel.Response table = albumList.get(position);

            holder.date.setText(table.getDuration());
            holder.sale.setText(table.getSale());
            holder.expense.setText(table.getExpense());
            holder.amount.setText(table.getProfit_loss());
            holder.status.setText(table.getStatus());

            if(table.getStatus().equals("Loss")){
                holder.status.setTextColor(Color.RED);
            }else{
                holder.status.setTextColor(Color.BLUE);
            }


        }

        @Override
        public int getItemCount() {

            return albumList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView date,sale,expense,amount,status;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                date=itemView.findViewById(R.id.date);
                sale=itemView.findViewById(R.id.sale);
                expense=itemView.findViewById(R.id.expense);
                amount=itemView.findViewById(R.id.amount);
                status=itemView.findViewById(R.id.status);

            }
        }
    }

}
