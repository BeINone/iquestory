package kr.co.iquest.beinone.iquestory;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by BeINone on 2017-11-13.
 */

public class Time {

    public int MAX_REALTIME = 60;
    public int MAX_MONTH = 12;

    private int year;
    private int month;
    private int realTime;
    private Handler mHandler;
    private OnTimeChangedListener mOnTimeChangedListener;

    public Time(Handler handler, OnTimeChangedListener listener) {
        mHandler = handler;
        mOnTimeChangedListener = listener;
    }

    public void passOneSecond() {
        realTime += 1;
        if (realTime == MAX_REALTIME) {
            month += 1;
            realTime = 0;
            if (month == MAX_MONTH) {
                year += 1;
                year = 0;
            }
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mOnTimeChangedListener.onTimeChanged(Time.this);
                }
            });
        }

        Log.d("Time.passOneSecond", year + "년, " + month + "개월, " + realTime + "초");
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getRealTime() {
        return realTime;
    }

    public void setRealTime(int realTime) {
        this.realTime = realTime;
    }

    public interface OnTimeChangedListener {
        void onTimeChanged(Time time);
    }
}
