package kr.co.iquest.beinone.iquestory;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import java.util.List;
import java.util.Timer;

/**
 * Created by BeINone on 2017-11-14.
 */

public class Iquestory {

    private static Company iquest;
    private static Time.OnTimeChangedListener onTimeChangedListener;

    public static void startGame(Time.OnTimeChangedListener listener) {
        // 아이퀘스트 설립
        iquest = new Company();
        onTimeChangedListener = listener;
        setTimer();
    }

    public static void releaseProducts(List<Product> products) {
        iquest.setProducts(products);
    }

    private static void setTimer() {
        Time time = new Time(new Handler(), onTimeChangedListener);
        new Timer().schedule(new GameTimerTask(time), 10, 10);
    }
}
