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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tgs.com.junglecookapp.retrofit.ApiClient;
import tgs.com.junglecookapp.retrofit.InterfaceApi;

public class MenuSearchedList extends Fragment {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    TextView name;
    ImageView rightimage;
    TableAdapter tableAdapter;
    String item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_menu, container, false);

        recyclerView=view.findViewById(R.id.recycler_view);
        rightimage=view.findViewById(R.id.rightimage);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        progressBar = view.findViewById(R.id.progress);
        Bundle bundle=getArguments();
        item=bundle.getString("Item");
        Sprite doubleBounce = new Wave();
        progressBar.setIndeterminateDrawable(doubleBounce);
        getServiceResponseData(item);

        rightimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuSearchingReport fragment = new MenuSearchingReport();
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

    private void getServiceResponseData(String Item) {
        InterfaceApi api = ApiClient.getClient().create(InterfaceApi.class);
        Call<MenuModel> call = api.menu_report("5199",Item);
        call.enqueue(new Callback<MenuModel>() {
            @Override
            public void onResponse(Call<MenuModel> call, Response<MenuModel> response) {
             progressBar.setVisibility(View.GONE);
             recyclerView.setVisibility(View.VISIBLE);
                final MenuModel status = response.body();

                if (status.getStatus().equals("1")) {
                    tableAdapter = new TableAdapter(getActivity(),status);
                    recyclerView.setAdapter(tableAdapter);
                } else {
                    Toast.makeText(getActivity(), ""+status.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<MenuModel> call, Throwable t) {
                Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }
        });
    }

    private class TableAdapter extends RecyclerView.Adapter<TableAdapter.MyViewHolder>  {
        private Context mContext;
        private List<MenuModel.Response> albumList;
        public TableAdapter(Context mContext,MenuModel albumList) {
            this.mContext = mContext;
            this.albumList = albumList.getResponse();
        }
        @Override
        public TableAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.menu_single, parent, false);
            return new TableAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull final TableAdapter.MyViewHolder holder, int position) {
            final MenuModel.Response table = albumList.get(position);

            holder.Name.setText(table.getName());
            holder.Category.setText(table.getCategory());
            holder.Price.setText(table.getPrice());

        }

        @Override
        public int getItemCount() {
            return albumList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView Name,Category,Price;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                Name=itemView.findViewById(R.id.Name);
                Category=itemView.findViewById(R.id.Category);
                Price=itemView.findViewById(R.id.Price);

            }
        }
    }
}