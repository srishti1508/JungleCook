package tgs.com.junglecookapp;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.twotoasters.jazzylistview.recyclerview.JazzyRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import tgs.com.junglecookapp.R;
import static tgs.com.junglecookapp.Login.PREFS_NAME;


public  class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,BaseSliderView.OnSliderClickListener{
    private RecyclerView recyclerView;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    ActionBarDrawerToggle drawerToggle;
    CoordinatorLayout rootLayout;
    Toolbar toolbar;
    SliderLayout slider;
    TextView marquee, notice;
    private SliderLayout mDemoSlider;
    private List<Album> albumList;
    private AlbumAdapter adapter;
    SharedPreferences settings;
    Shared_Common_Pref shared_common_pref;
    public static String user_group_name;
    public static String user_id;
    public static String user_name;
    public static String group_id;
    public static String referals;
    public static String emp_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupDeawer();
       // setupSlider();
        albumList = new ArrayList<>();
        adapter = new AlbumAdapter(this, albumList);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*toolbar.setVisibility(View.GONE);
                MenuList fragment = new MenuList();
                FragmentManager fm = MainActivity.this.getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.setCustomAnimations(R.animator.fade_in,
                        R.animator.fade_out);
                ft.replace(R.id.frag_container, fragment);
                ft.commit();*/

                Intent intent=new Intent(MainActivity.this,Menu_List.class);
                startActivity(intent);
            }
        });

        String referal= getIntent().getStringExtra("Outside_referal");
        shared_common_pref = new Shared_Common_Pref(MainActivity.this);
        settings = getSharedPreferences(PREFS_NAME, 0);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }
        settings = getSharedPreferences(PREFS_NAME, 0);
        user_group_name = settings.getString("user_group_name", "");
        user_id = settings.getString("user_id", "");
        user_name = settings.getString("user_name", "");
        group_id = settings.getString("group_id", "");
        emp_id = settings.getString("emp_id", "");
        referals = settings.getString("referals", referal);

        if(settings.getString("user_group_name", "").toString().equals("admin")){
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
            recyclerView.setLayoutManager(mLayoutManager);
            JazzyRecyclerViewScrollListener jazzyScrollListener = new JazzyRecyclerViewScrollListener();
            recyclerView.setOnScrollListener(jazzyScrollListener);
            jazzyScrollListener.setTransitionEffect(11);
            recyclerView.setAdapter(adapter);
            prepareAlbums();
        }
    }
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.profits,
                R.drawable.expense_report,
                R.drawable.report,
                R.drawable.rest_customer,
                R.drawable.tablechair,
                R.drawable.product_report,
                R.drawable.menu,
                R.drawable.restaurant,
        };
        Album a = new Album("Profit Report", 13, covers[0]);
        albumList.add(a);
        a = new Album("Expense Report", 11, covers[1]);
        albumList.add(a);
        a = new Album("Sales Report", 11, covers[2]);
        albumList.add(a);
        a= new Album("Customers", 11, covers[3]);
        albumList.add(a);
        a = new Album("Tables", 12, covers[4]);
        albumList.add(a);
        a= new Album("Product Report", 14, covers[5]);
        albumList.add(a);
        adapter.notifyDataSetChanged();
    }
    private void setupDeawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, toolbar, R.string.action_play_next, R.string.action_settings);
        mDrawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);
        //  rootLayout = (CoordinatorLayout) findViewById(R.id.main_content);
        drawerToggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.main_drawer);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.remove("logged");
            editor.clear().commit();
            Intent i = new Intent(MainActivity.this, Login.class);
            startActivity(i);
            finish();
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private void setupSlider() {
      //  mDemoSlider = (SliderLayout) findViewById(R.id.slider);
       /* HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
*/
        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Hannibal", R.drawable.slide1);
        file_maps.put("Big Bang Theory", R.drawable.slide2);
        file_maps.put("House of Cards", R.drawable.slide3);
        file_maps.put("Game of Thrones", R.drawable.slide4);
        //file_maps.put("Big Bang ", R.drawable.banner);
        for (String name : file_maps.keySet()) {
            TextSliderViews textSliderView = new TextSliderViews(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);
            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        //  mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(3000);
    }


    @Override
    public void onSliderClick(BaseSliderView slider) {

    }
}