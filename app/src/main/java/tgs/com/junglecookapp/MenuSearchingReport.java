package tgs.com.junglecookapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class MenuSearchingReport extends Fragment {

    EditText date;
    Button Search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_itemsearch, container, false);
         date=view.findViewById(R.id.date);
         Search=view.findViewById(R.id.search);



        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MenuSearchedList fragment = new MenuSearchedList();
                Bundle args = new Bundle();
                args.putString("Item", date.getText().toString());
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
