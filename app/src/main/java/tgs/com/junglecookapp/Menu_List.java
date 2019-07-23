package tgs.com.junglecookapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
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

public class Menu_List extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    TextView name;
    ImageView rightimage;
   TableAdapter tableAdapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        /** Making this activity, full screen */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);
        toolbar = findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) Menu_List.this;
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(getActivity(),"bakc",Toast.LENGTH_SHORT).show();
                Menu_List.this.onBackPressed();
            }
        });

        recyclerView=findViewById(R.id.recycler_view);
        //rightimage=findViewById(R.id.rightimage);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(Menu_List.this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        progressBar = findViewById(R.id.progress);
        Sprite doubleBounce = new Wave();
        progressBar.setIndeterminateDrawable(doubleBounce);
        getServiceResponseData();

       /* rightimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuSearchingReport fragment = new MenuSearchingReport();
                FragmentManager fm = Menu_List.this.getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.setCustomAnimations(R.animator.fade_in,
                        R.animator.fade_out);
                ft.replace(R.id.frag_container, fragment);
                ft.commit();
            }
        });*/


    }


    private void getServiceResponseData() {
        InterfaceApi api = ApiClient.getClient().create(InterfaceApi.class);
        Call<MenuModel> call = api.menu_report("5199","");
        call.enqueue(new Callback<MenuModel>() {
            @Override
            public void onResponse(Call<MenuModel> call, Response<MenuModel> response) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                final MenuModel status = response.body();
                if (status.getStatus().equals("1")) {
                    tableAdapter = new TableAdapter(Menu_List.this,status);
                    recyclerView.setAdapter(tableAdapter);
                } else {
                    Toast.makeText(Menu_List.this, ""+status.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<MenuModel> call, Throwable t) {
                Toast.makeText(Menu_List.this, "Failed", Toast.LENGTH_SHORT).show();
                Menu_List.this.onBackPressed();
            }
        });
    }

    public class TableAdapter extends RecyclerView.Adapter<Menu_List.TableAdapter.MyViewHolder> {
        private Context mContext;
        private List<MenuModel.Response> albumList;
        public TableAdapter(Context mContext,MenuModel albumList) {
            this.mContext = mContext;
            this.albumList = albumList.getResponse();
        }
        @Override
        public TableAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.menu_single, parent, false);
            return new TableAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(TableAdapter.MyViewHolder holder, int position) {
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
            public MyViewHolder(View itemView) {
                super(itemView);
                Name=itemView.findViewById(R.id.Name);
                Category=itemView.findViewById(R.id.Category);
                Price=itemView.findViewById(R.id.Price);
            }
        }
    }
}
