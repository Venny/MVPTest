package com.example.vdimitrova.mvptest.features.books_list.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.vdimitrova.mvptest.R;
import com.example.vdimitrova.mvptest.base.model.custom_events.JustASimpleTestEvent;
import com.example.vdimitrova.mvptest.base.model.custom_events.UpdateBooksEvent;
import com.example.vdimitrova.mvptest.database.model_tables.BookDbModel;
import com.example.vdimitrova.mvptest.base.fragments.BaseFragment;
import com.example.vdimitrova.mvptest.features.single_event.ui.fragments.SingleEventFragment;
import com.example.vdimitrova.mvptest.features.steps_counter.ui.fragments.StepsCounterFragment;
import com.example.vdimitrova.mvptest.features.books_list.presenter.BooksListPresenter;
import com.example.vdimitrova.mvptest.features.books_list.ui.adapters.UpcomingEventsAdapter;
import com.example.vdimitrova.mvptest.features.books_list.view.BooksListViewContractor;
import com.example.vdimitrova.mvptest.utils.Constants;
import com.example.vdimitrova.mvptest.utils.SharedPrefsUtils;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

import static android.content.ContentValues.TAG;


/**
 * Created by vdimitrova on 12.05.17.
 */

public class BooksListFragment extends BaseFragment implements BooksListViewContractor {
    private BooksListPresenter presenter;
    private UpcomingEventsAdapter adapter;
    private FirebaseAnalytics mFirebaseAnalytics;

    @BindView(R.id.events_recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar spinner;
    @BindView(R.id.btn_step_counter)
    Button stepCounterBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_books_list, container, false);
        ButterKnife.bind(this, layout);

        init();
        return layout;
    }

    private void init() {
        presenter = new BooksListPresenter(getActivity().getApplicationContext(), this);

        // init the recycler
        initRecyclerView();


        if (isAppVisitedBefore()) {
            // Load events.
            presenter.loadEvents();
        } else {
            // Download events.
            presenter.downloadBooksIfNeeded();
        }

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());

    }

    @Override
    public void loadEvents(List<BookDbModel> events) {
        adapter.setData(events);
    }

    @Override
    public void onRequestStarted() {
        spinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResponseReceived(List<BookDbModel> books) {
        loadEvents(books);
        spinner.setVisibility(View.GONE);
    }

    @Override
    public boolean isAppVisitedBefore() {
        if (SharedPrefsUtils.getBooleanFromPrefs(getContext(), Constants.APP_FIRST_VISITED)) {
            return true;
        }

        SharedPrefsUtils.setBooleanToPrefs(getContext(), Constants.APP_FIRST_VISITED, true);
        return false;
    }

    // setting event bus register
    @Override
    public void onResume() {
        super.onResume();

        EventBus.getDefault().registerSticky(this);
    }

    // setting event bus unregister
    @Override
    public void onPause() {
        EventBus.getDefault().unregister(this);

        super.onPause();
    }

    @OnClick(R.id.btn_step_counter)
    void onClick() {
        showFragmentAndAddToBackStack(new StepsCounterFragment(), "Step counter", R.id.main_container);
    }

    // This method will be called when a JustASimpleTestEvent is posted
    public void onEvent(JustASimpleTestEvent event) {

        // our implementation
        Toast.makeText(getActivity(), event.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public void onEvent(UpdateBooksEvent event) {
        // Load events.
        if (event.isReadyForUpdate()) {
            presenter.loadEvents();
        }
    }


    private void startSingleEventFragment(int position) {
        Fragment book = new SingleEventFragment();
        Bundle args = new Bundle();

        Log.d(TAG, "startSingleEventFragment: " + position);
        args.putInt(Constants.KEY_POSITION, position);
        book.setArguments(args);
        showFragmentAndAddToBackStack(book, "Single event", R.id.main_container);
    }

    private void initRecyclerView() {
        adapter = new UpcomingEventsAdapter(new UpcomingEventsAdapter.ItemListener() {

            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(position));
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, adapter.getItem(position).getTitle());
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "book");

                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

                startSingleEventFragment(position);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }
}
