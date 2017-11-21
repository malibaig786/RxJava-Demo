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

//        subscribeOnAtStart();
//        observeOnAtStart();

//        subscribeOnInMiddle();
        observeOnInMiddle();

//        subscribeOnAtEnd();
//        observeOnAtEnd();

//        subscribeOnObserveOn();
//        observeOnSubscribeOn();

//        twoSubscribeOn();
//        twoObserveOn();

//        observeOn();
//        groupBy();
//        concatWith();
//        concat();
//        fromIterable();
    }


    private void subscribeOnAtStart() {
        Log.w(TAG, "subscribeOnAtStart: Method");
        Observable<Integer> seq1 = Observable.range(10, 6);

        seq1
                .subscribeOn(Schedulers.io())
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "1) Filter " + integer + "  " + Thread.currentThread().getName());
                        return true;
                    }
                })
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "2) Map " + integer + "  " + Thread.currentThread().getName());
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
                        Log.e(TAG, "onSubscribe: " + Thread.currentThread().getName());
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
                        Log.e(TAG, "onComplete: Subscribe() method " + " Thread Name " + Thread.currentThread().getName());
                    }
                });
    }

    private void observeOnAtStart() {
        Log.w(TAG, "observeOnAtStart: method");
        Observable<Integer> seq1 = Observable.range(10, 6);

        seq1
                .observeOn(Schedulers.computation())
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "1) Filter " + integer + "  " + Thread.currentThread().getName());
                        return true;
                    }
                })
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "2) Map " + integer + "  " + Thread.currentThread().getName());
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
                        Log.e(TAG, "onSubscribe: " + Thread.currentThread().getName());
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
                        Log.e(TAG, "onComplete: Subscribe() method " + " Thread Name " + Thread.currentThread().getName());
                    }
                });
    }


    private void subscribeOnInMiddle() {
        Log.w(TAG, "subscribeOnInMiddle: Method");
        Observable<Integer> seq1 = Observable.range(10, 6);

        seq1
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "1) Filter " + integer + "  " + Thread.currentThread().getName());
                        return true;
                    }
                })
                .subscribeOn(Schedulers.io())
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "2) Map " + integer + "  " + Thread.currentThread().getName());
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
                        Log.e(TAG, "onSubscribe: " + Thread.currentThread().getName());
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
                        Log.e(TAG, "onComplete: Subscribe() method " + " Thread Name " + Thread.currentThread().getName());
                    }
                });
    }

    private void observeOnInMiddle() {
        Log.w(TAG, "observeOnInMiddle: method");
        Observable<Integer> seq1 = Observable.range(10, 6);

        seq1
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "1) Filter " + integer + "  " + Thread.currentThread().getName());
                        return true;
                    }
                })
                .observeOn(Schedulers.computation())
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "2) Map " + integer + "  " + Thread.currentThread().getName());
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
                        Log.e(TAG, "onSubscribe: " + Thread.currentThread().getName());
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
                        Log.e(TAG, "onComplete: Subscribe() method " + " Thread Name " + Thread.currentThread().getName());
                    }
                });
    }


    private void subscribeOnAtEnd() {
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
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e(TAG, "onSubscribe: " + Thread.currentThread().getName());
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
                        Log.e(TAG, "onComplete: Subscribe() method " + " Thread Name " + Thread.currentThread().getName());
                    }
                });
    }

    private void observeOnAtEnd() {
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
                .observeOn(Schedulers.computation())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e(TAG, "onSubscribe: " + Thread.currentThread().getName());
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
                        Log.e(TAG, "onComplete: ObserveOn() " + " Thread Name " + Thread.currentThread().getName());
                    }
                });

    }


    private void subscribeOnObserveOn() {
        Log.w(TAG, "subscribeOnObserveOn() Method");
        Observable<Integer> seq1 = Observable.range(10, 5);

        seq1
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "1) Filter: " + integer + "  ThreadName: " + Thread.currentThread().getName());
                        return true;
                    }
                })
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "1) Map: " + integer + "  ThreadName: " + Thread.currentThread().getName());
                        return integer;
                    }
                })
                .subscribeOn(Schedulers.io())
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        Log.w(TAG, "subscribeOn() Method" + Thread.currentThread().getName());
                        Log.w(TAG, "2) Filter: " + integer + "  ThreadName: " + Thread.currentThread().getName());
                        return true;
                    }
                })
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.w(TAG, "2) Map: " + integer + "  ThreadName: " + Thread.currentThread().getName());
                        return integer;
                    }
                })
                .observeOn(Schedulers.computation())
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        Log.w(TAG, "observeOn() Method" + Thread.currentThread().getName());
                        Log.e(TAG, "3) Filter " + Thread.currentThread().getName());
                        return true;
                    }
                })
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "3) Map " + Thread.currentThread().getName());
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
                        Log.e(TAG, "onSubscribe: " + Thread.currentThread().getName());
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
                        Log.e(TAG, "onComplete: Subscribe() method " + " Thread Name " + Thread.currentThread().getName());
                    }
                });
    }

    private void observeOnSubscribeOn() {
        Log.w(TAG, "ObserveOnSubscribeOn() Method");
        Observable<Integer> seq1 = Observable.range(10, 5);

        seq1
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "1) Filter: " + integer + "  ThreadName: " + Thread.currentThread().getName());
                        return true;
                    }
                })
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "1) Map: " + integer + "  ThreadName: " + Thread.currentThread().getName());
                        return integer;
                    }
                })
                .observeOn(Schedulers.io())
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        Log.w(TAG, "ObserveOn() Method " + Thread.currentThread().getName());
                        Log.w(TAG, "2) Filter: " + integer + "  ThreadName: " + Thread.currentThread().getName());
                        return true;
                    }
                })
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.w(TAG, "2) Map: " + integer + "  ThreadName: " + Thread.currentThread().getName());
                        return integer;
                    }
                })
                .subscribeOn(Schedulers.computation())
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        Log.w(TAG, "subscribeOn() Method" + Thread.currentThread().getName());
                        Log.e(TAG, "3) Filter " + Thread.currentThread().getName());
                        return true;
                    }
                })
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "3) Map " + Thread.currentThread().getName());
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
                        Log.e(TAG, "onSubscribe: " + Thread.currentThread().getName());
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
                        Log.e(TAG, "onComplete: Subscribe() method " + " Thread Name " + Thread.currentThread().getName());
                    }
                });
    }


    private void twoSubscribeOn() {
        Log.w(TAG, "twoSubscribeOn() Method");
        Observable<Integer> seq1 = Observable.range(10, 5);

        seq1
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "1) Filter: " + integer + "  ThreadName: " + Thread.currentThread().getName());
                        return true;
                    }
                })
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "1) Map: " + integer + "  ThreadName: " + Thread.currentThread().getName());
                        return integer;
                    }
                })
                .subscribeOn(Schedulers.io())
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        Log.w(TAG, "First subscribeOn() Method " + Thread.currentThread().getName());
                        Log.w(TAG, "2) Filter: " + integer + "  ThreadName: " + Thread.currentThread().getName());
                        return true;
                    }
                })
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.w(TAG, "2) Map: " + integer + "  ThreadName: " + Thread.currentThread().getName());
                        return integer;
                    }
                })
                .subscribeOn(Schedulers.computation())
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        Log.w(TAG, "Second subscribeOn() Method" + Thread.currentThread().getName());
                        Log.e(TAG, "3) Filter " + Thread.currentThread().getName());
                        return true;
                    }
                })
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "3) Map " + Thread.currentThread().getName());
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
                        Log.e(TAG, "onSubscribe: " + Thread.currentThread().getName());
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
                        Log.e(TAG, "onComplete: Subscribe() method " + " Thread Name " + Thread.currentThread().getName());
                    }
                });
    }

    private void twoObserveOn() {
        Log.w(TAG, "twoObserveOn() Method");
        Observable<Integer> seq1 = Observable.range(10, 5);

        seq1
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "1) Filter: " + integer + "  ThreadName: " + Thread.currentThread().getName());
                        return true;
                    }
                })
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "1) Map: " + integer + "  ThreadName: " + Thread.currentThread().getName());
                        return integer;
                    }
                })
                .observeOn(Schedulers.io())
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        Log.w(TAG, "First ObserveOn() Method " + Thread.currentThread().getName());
                        Log.w(TAG, "2) Filter: " + integer + "  ThreadName: " + Thread.currentThread().getName());
                        return true;
                    }
                })
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.w(TAG, "2) Map: " + integer + "  ThreadName: " + Thread.currentThread().getName());
                        return integer;
                    }
                })
                .observeOn(Schedulers.computation())
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        Log.w(TAG, "Second observer() Method " + Thread.currentThread().getName());
                        Log.e(TAG, "3) Filter " + Thread.currentThread().getName());
                        return true;
                    }
                })
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "3) Map " + Thread.currentThread().getName());
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
                        Log.e(TAG, "onSubscribe: " + Thread.currentThread().getName());
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
                        Log.e(TAG, "onComplete: Subscribe() method " + " Thread Name " + Thread.currentThread().getName());
                    }
                });
    }


    @Override
    protected void onStop() {
        super.onStop();
        disposable.dispose();
    }
}
