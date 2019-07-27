package tgs.com.junglecookapp;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class Dashboard_Main extends AppCompatActivity {

    private static final String TAG = Dashboard_Main.class.getSimpleName();

    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_single);
        position = getIntent().getIntExtra("position",0);
        Fragment fragment = getFragmentName(position);
        if (savedInstanceState == null) {
            vibrate();
            Fragment fragmentToReplace = null;
            FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
            // fragmentToReplace = new StuSelectCourseFrag();
            transaction.setCustomAnimations(R.animator.fade_in,
                    R.animator.fade_out);
            fragmentToReplace = fragment;

            transaction.replace(R.id.frag_container, fragmentToReplace, TAG);
            transaction.commit();
        }
    }

    private void vibrate() {
        Vibrator v = (Vibrator) Dashboard_Main.this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(100); // 5000 miliseconds = 5 seconds

    }
    private Fragment getFragmentName(int position) {
        switch (position){

            case 0:
                return new ProfitReport();
            case 1:
                return new ExpenseReport();
            case 2:
                return new SaleReport();
            case 3:
                return new Customer();
            case 4:
                return new Table();
            case 5:
                return new ProductReport();
            case 6:
                return new ExpenseReport();
            case 7:
                return new ExpenseReport();

            default:
                return null;

        }
    }
}
