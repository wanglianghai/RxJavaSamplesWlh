package com.bignerdranch.android.rxjavasampelswlh.module;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bignerdranch.android.rxjavasampelswlh.BaseFragment;
import com.bignerdranch.android.rxjavasampelswlh.R;
import com.bignerdranch.android.rxjavasampelswlh.model.Zb;
import com.bignerdranch.android.rxjavasampelswlh.network.Network;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.observers.Observers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/6/10/010.
 */

public class ElementaryFragment extends BaseFragment {
    private static final String TAG = "ElementaryFragment";

    @Bind(R.id.recycler_view_elementary) RecyclerView mRecyclerView;
    @Bind(R.id.swipe_refresh_layout) SwipeRefreshLayout mRefreshLayout;

    Observer<List<Zb>> mObserver = new Observer<List<Zb>>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Toast.makeText(getActivity(), R.string.loading_failed, Toast.LENGTH_SHORT).show();
            Log.e(TAG, "onError: ", e);
        }

        @Override
        public void onNext(List<Zb> zbs) {
            Log.i(TAG, "onNext: size:" + zbs.size());
        }
    };

    @OnCheckedChanged({R.id.search_Rb_110, R.id.search_Rb_cute, R.id.search_Rb_myself, R.id.search_Rb_zb})
    void onTagChanged(RadioButton searchRb, boolean lastClicked) {
        if (lastClicked) {
            unSubscription();
            search(searchRb.getText().toString());
        }
    }

    private void search(String key) {
        mSubscription = Network.zbApi()
                .search(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_elementary, container, false);
        ButterKnife.bind(this, view);

        mRefreshLayout.setColorSchemeColors(Color.BLACK, Color.GRAY, Color.WHITE);

        return view;
    }

    @Override
    protected int getDialogRes() {
        return R.layout.dialog_elementary;
    }

    @Override
    protected int getTitleRes() {
        return R.string.title_elementary;
    }
}
