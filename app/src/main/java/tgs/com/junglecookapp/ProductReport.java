package tgs.com.junglecookapp;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class ProductReport extends Fragment {
    RecyclerView recyclerView;
    ImageView rightimage;
    ProgressBar progressBar;
    TextView name;
    TableAdapter tableAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_product, container, false);

        recyclerView=view.findViewById(R.id.recycler_view);
        rightimage=view.findViewById(R.id.rightimage);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        JazzyRecyclerViewScrollListener jazzyScrollListener = new JazzyRecyclerViewScrollListener();
        recyclerView.setOnScrollListener(jazzyScrollListener);
        jazzyScrollListener.setTransitionEffect(11);
        progressBar = (ProgressBar)view.findViewById(R.id.progress);
        Sprite doubleBounce = new Wave();
        progressBar.setIndeterminateDrawable(doubleBounce);
        getServiceResponseData();

        rightimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductSearchingReport fragment = new ProductSearchingReport();
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

    private void getServiceResponseData() {
        InterfaceApi api = ApiClient.getClient().create(InterfaceApi.class);
        Call<ProductModel> call = api.productwise_report("5199","");
        call.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
               progressBar.setVisibility(View.GONE);
               recyclerView.setVisibility(View.VISIBLE);
                final ProductModel status = response.body();

                if (status.getStatus().equals("1")) {
                    tableAdapter = new TableAdapter(getActivity(),status);
                    recyclerView.setAdapter(tableAdapter);
                } else {
                    Toast.makeText(getActivity(), ""+status.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }
        });
    }
    private class TableAdapter extends RecyclerView.Adapter<TableAdapter.MyViewHolder>  {
        private Context mContext;
        private List<ProductModel.Response> albumList;
        public TableAdapter(Context mContext,ProductModel albumList) {
            this.mContext = mContext;
            this.albumList = albumList.getResponse();
        }
        @Override
        public TableAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.product_single_copy, parent, false);
            return new TableAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull final TableAdapter.MyViewHolder holder, int position) {
            final ProductModel.Response table = albumList.get(position);

            holder.ProductName.setText(table.getPro_name());
            holder.Category.setText(table.getPro_category());
            holder.Quantity.setText(table.getPro_quantity());
            holder.Rate.setText(table.getPro_price());
            holder.Cost.setText(table.getPro_total());
        }

        @Override
        public int getItemCount() {
            return albumList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView ProductName,Category,Quantity,Rate,Cost;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                ProductName=itemView.findViewById(R.id.ProductName);
                Category=itemView.findViewById(R.id.Category);
                Quantity=itemView.findViewById(R.id.Quantity);
                Rate=itemView.findViewById(R.id.Rate);
                Cost=itemView.findViewById(R.id.Cost);
            }
        }
    }
}
