package com.ali.ahmed.muhammad.rxjavademo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    final CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        observeOn();
        subscribeOn();
//        observeOn();
//        groupBy();
//        concatWith();
//        concat();
//        fromIterable();

    }

    private void subscribeOn() {
        Log.w(TAG, "subscribeOn: Method");
        Observable<Integer> seq1 = Observable.range(10, 6);

        seq1
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "Filter " + Thread.currentThread().getName());
                        return true;
                    }
                })

                .subscribeOn(Schedulers.io())
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "Map " + Thread.currentThread().getName());
                        return integer;
                    }
                })
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "doOnDispose: " + Thread.currentThread().getName());
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e(TAG, "onSubscribe: Concat " + Thread.currentThread().getName());
                        disposable.add(d);
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

    private void observeOn() {
        Log.w(TAG, "observeOn: method");
        Observable<Integer> seq1 = Observable.range(10, 6);

        seq1
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "Filter " + Thread.currentThread().getName());
                        return true;
                    }
                })

                .observeOn(Schedulers.io())
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "Map " + Thread.currentThread().getName());
                        return integer;
                    }
                })
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "doOnDispose: " + Thread.currentThread().getName());
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e(TAG, "onSubscribe: Concat " + Thread.currentThread().getName());
                        disposable.add(d);
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



    @Override
    protected void onStop() {
        super.onStop();
        disposable.dispose();
    }
}
