package com.journaldev.mvpdagger2retroiftrxjava.mvp;

import com.google.gson.JsonObject;
import com.journaldev.mvpdagger2retroiftrxjava.pojo.CryptoData;
import com.journaldev.mvpdagger2retroiftrxjava.retrofit.APIInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import javax.inject.Inject;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PresenterImpl implements MainActivityContract.Presenter {

    APIInterface apiInterface;
    MainActivityContract.View mView;

    @Inject
    public PresenterImpl(APIInterface apiInterface, MainActivityContract.View mView) {
        this.apiInterface = apiInterface;
        this.mView = mView;
    }

    @Override
    public void loadData() {

        mView.showProgress();

        apiInterface.getData("1000").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CryptoData>>() {
                    @Override
                    public void onCompleted() {
                        mView.showComplete();
                        mView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError("Error occurred");
                        mView.hideProgress();
                    }

                    @Override
                    public void onNext(List<CryptoData> data) {
                        mView.showData(data);
                    }
                });
    }

    @Override
    public void fogotdetails() {


        mView.showProgress();
        JsonObject paramObject = new JsonObject();
            paramObject.addProperty("username", "sudhirs745@gmail.com");
            paramObject.addProperty("type", "2");
            

        
        apiInterface.getforgotDetails(paramObject).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onCompleted() {
                        mView.showComplete();
                        mView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError("Error occurred");
                        mView.hideProgress();
                    }

                    @Override
                    public void onNext(JsonObject data) {
                        mView.setDataData(data);
                    }
                });

    }
}
