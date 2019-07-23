package tgs.com.junglecookapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class Customer extends Fragment {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    TextView name,nodata;
    TableAdapter tableAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_customer, container, false);

        recyclerView=view.findViewById(R.id.recycler_view);
        nodata=view.findViewById(R.id.nodata);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        progressBar = view.findViewById(R.id.progress);
        Sprite doubleBounce = new Wave();
        progressBar.setIndeterminateDrawable(doubleBounce);
       getServiceResponseData();
        return view;
    }

    private void getServiceResponseData() {
        InterfaceApi api = ApiClient.getClient().create(InterfaceApi.class);
        Call<CustomerModel> call = api.customer_report("5199");
        call.enqueue(new Callback<CustomerModel>() {
            @Override
            public void onResponse(Call<CustomerModel> call, Response<CustomerModel> response) {
             progressBar.setVisibility(View.GONE);
             recyclerView.setVisibility(View.VISIBLE);
                final CustomerModel status = response.body();
                if (status.getResponse().size()>0) {
                    tableAdapter = new TableAdapter(getActivity(),status);
                    recyclerView.setAdapter(tableAdapter);
                } else {
                    nodata.setVisibility(View.VISIBLE);
                   recyclerView.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(Call<CustomerModel> call, Throwable t) {
                Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }
        });
    }

    private class TableAdapter extends RecyclerView.Adapter<TableAdapter.MyViewHolder>  {
        private Context mContext;
        private List<CustomerModel.Response> albumList;
        public TableAdapter(Context mContext,CustomerModel albumList) {
            this.mContext = mContext;
            this.albumList = albumList.getResponse();
        }

        @Override
        public TableAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.customer_single111, parent, false);
            return new TableAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull final TableAdapter.MyViewHolder holder, int position) {
            final CustomerModel.Response table = albumList.get(position);

            holder.CustomerName.setText(table.getName());
            holder.Mobile.setText(table.getPhone());
            holder.Email.setText(table.getEmail());
            holder.Discount.setText(table.getDiscount());
            holder.Date.setText(table.getCreated_at());
        }

        @Override
        public int getItemCount() {
            return albumList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView CustomerName,Mobile,Email,Discount,Date;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                CustomerName=itemView.findViewById(R.id.CustomerName);
                Mobile=itemView.findViewById(R.id.Mobile);
                Email=itemView.findViewById(R.id.Email);
                Discount=itemView.findViewById(R.id.Discount);
                Date=itemView.findViewById(R.id.Date);
            }
        }
    }
}