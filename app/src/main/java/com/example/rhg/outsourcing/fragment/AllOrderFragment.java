package com.example.rhg.outsourcing.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.activity.PayActivity;
import com.example.rhg.outsourcing.apapter.RecycleSellerAdapter;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.bean.BaseSellerModel;
import com.example.rhg.outsourcing.mvp.presenter.TestPresenter;
import com.example.rhg.outsourcing.utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by remember on 2016/5/17.
 */
public class AllOrderFragment extends SuperFragment implements RecycleSellerAdapter.OnListItemClick {
    RecyclerView rcv;
    SwipeRefreshLayout swipeRefreshLayout;
    List<BaseSellerModel> mData = new ArrayList<>();
    TestPresenter testPresenter;

    public AllOrderFragment() {
        for (int i = 0; i < 6; i++) {
            BaseSellerModel baseSellerModel = new BaseSellerModel("哈哈", R.drawable.recommend_default_icon_1
                    , "待付款", "下单时间:20160517", "订单号:0000", "30");
            mData.add(baseSellerModel);
        }
        testPresenter = new TestPresenter(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.rcv_item;
    }

    @Override
    protected void initView(View view) {
        rcv = (RecyclerView) view.findViewById(R.id.recycleview);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);

    }

    @Override
    protected void initData() {
        RecycleSellerAdapter recycleSellerAdapter = new RecycleSellerAdapter(getContext()
                , mData, AppConstants.TypeOrder);
        recycleSellerAdapter.setOnListItemClick(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcv.setLayoutManager(linearLayoutManager);
        rcv.setHasFixedSize(true);
        rcv.setAdapter(recycleSellerAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i("RHG", "OnRefresh");
                testPresenter.getData();
            }

        });
    }

    @Override
    protected void showFailed() {

    }

    @Override
    public void showSuccess(Object o) {
        ToastHelper.getInstance()._toast(o.toString());
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void itemClick(View v, int position) {
//        ToastHelper.getInstance()._toast("item " + position + "is click.");
        Intent intent = new Intent(getContext(), PayActivity.class);
        startActivity(intent);
    }
}
