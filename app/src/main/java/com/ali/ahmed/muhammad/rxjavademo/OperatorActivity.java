package com.ali.ahmed.muhammad.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;

public class OperatorActivity extends AppCompatActivity {

    private static final String TAG = OperatorActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator);
    }

    private void concatMap() {
        Observable<Integer> seq1 = Observable.range(0, 10);

    }

    private void concat() {
        Observable<Integer> seq1 = Observable.range(1, 3);
        Observable<Integer> seq2 = Observable.range(4, 6);
        Observable<Integer> seq3 = Observable.range(10, 6);

        Observable.concat(seq1, seq2, seq3)
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "Map " + Thread.currentThread().getName());
                        return integer * 10;
                    }
                })
                .subscribeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e(TAG, "onSubscribe: Concat " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Log.w(TAG, "onNext: " + integer + " Thread Name " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: Concat " + " Thread Name " + Thread.currentThread().getName());
                    }
                });

    }

    private void concatWith() {
        Observable<Integer> seq1 = Observable.range(1, 3);
        Observable<Integer> seq2 = Observable.range(4, 6);
        Observable<Integer> seq3 = Observable.range(10, 6);

        seq1.concatWith(seq2)
                .concatWith(seq3)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e(TAG, "onSubscribe: Concat With");
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Log.w(TAG, "onNext: " + integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: Concat Withs");
                    }
                });
    }

    private void fromIterable() {
        // Making each element of list an emission of stream.
        Observable<ApiUser> obs = Observable.fromIterable(getApiUserList());
        obs.subscribe(new Observer<ApiUser>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e(TAG, "onSubscribe:  FromIterable");
            }

            @Override
            public void onNext(@NonNull ApiUser apiUser) {
                Log.w(TAG, apiUser.toString());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: FromIterable");
            }
        });
    }

    private List<ApiUser> getApiUserList() {
        List apiUserList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            apiUserList.add(new ApiUser(i, "FirstName " + i, "LastName " + i));
        }

        return apiUserList;
    }

    private Observable<List<User>> getCricketFansObservable() {
        return Rx2AndroidNetworking.get("https://fierce-cove-29863.herokuapp.com/getAllCricketFans")
                .build()
                .getObjectListObservable(User.class);
    }

    private void groupBy() {
        Observable<String> words = Observable.just(
                "First",
                "Second",
                "Third",
                "Fourth",
                "Fifth",
                "Sixth"
        );

        words.groupBy(new Function<String, Object>() {
            @Override
            public Object apply(@NonNull String s) throws Exception {
                return s.charAt(0);
            }
        }).subscribe(new Observer<GroupedObservable<Object, String>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull GroupedObservable<Object, String> objectStringGroupedObservable) {
                objectStringGroupedObservable.subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e(TAG, "onSubscribe: GroupBy");
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.w(TAG, "onNext: " + s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: GroupBy");
                    }
                });
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}
