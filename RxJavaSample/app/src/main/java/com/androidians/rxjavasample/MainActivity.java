package com.androidians.rxjavasample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // MAP operator - transform each element in the stream to another by applying a function on it.
        Observable.just(1,2,3, 13, 20, 18)
                .map(x->10*x)
                .subscribe(x-> Log.e(TAG, "X: "+x));


        // FILTER operator - elements will pass from source stream to the resulting stream only when some conditions met
        Observable.just(1,2,3, 13, 20, 18)
                .filter(x-> x > 10)
                .subscribe(x-> Log.e(TAG, "X: "+x));

        // CONCAT operator - emits items to the resulting stream from two or more observables without mixing them
        // (one after other)
        Observable<Integer> obs1 = Observable.just(1, 1, 1);
        Observable<Integer> obs2 = Observable.just(2,2);
        Observable.concat(obs1, obs2)
                .subscribe(x -> Log.e(TAG, "item: " + x));

        try {
            flatMap();
        } catch (Exception e) {
            Log.e(TAG, "error in flatMap(), "+e);
            e.printStackTrace();
        }
    }

    private void flatMap() throws Exception {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        TestScheduler scheduler = new TestScheduler();
        Observable.fromIterable(list)
                .flatMap(s -> {
                    final int delay = new Random().nextInt(10);
                    return Observable.just(s + "x")
                            .delay(delay, TimeUnit.SECONDS, scheduler);
                }).doOnNext(System.out::println).subscribe();
        scheduler.advanceTimeBy(1, TimeUnit.MINUTES);
    }
}
