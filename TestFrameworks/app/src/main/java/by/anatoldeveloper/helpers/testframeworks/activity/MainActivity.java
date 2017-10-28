package by.anatoldeveloper.helpers.testframeworks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import by.anatoldeveloper.helpers.testframeworks.R;
import by.anatoldeveloper.helpers.testframeworks.activity.login.LoginActivity;

/**
 * Created by Nublo on 28.08.2016.
 * Copyright Nublo
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app);

        Fragment content = new ListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_content, content);
        transaction.commit();
    }

    public static class ListFragment extends Fragment {

        private static final String[] items = {"Login Activity", "Cards Activity", "Recycler Activity"};

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.items_main, container, false);
            ListView hallListView = rootView.findViewById(R.id.items);
            ArrayAdapter<String> listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, items);
            hallListView.setAdapter(listAdapter);

            hallListView.setOnItemClickListener((parent, view, position, id) -> {
                switch (position) {
                    case 0:
                        Intent loginIntent = new Intent(getContext(), LoginActivity.class);
                        startActivity(loginIntent);
                        break;
                    case 1:
                        Intent cardsIntent = new Intent(getContext(), CardsActivity.class);
                        startActivity(cardsIntent);
                        break;
                    case 2:
                        Intent recyclerIntent = new Intent(getContext(), RecyclerActivity.class);
                        startActivity(recyclerIntent);
                        break;
                }
            });
            return rootView;
        }

    }

}