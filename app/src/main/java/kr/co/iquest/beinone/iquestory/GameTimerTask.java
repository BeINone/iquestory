package kr.co.iquest.beinone.iquestory;

import java.util.TimerTask;

/**
 * Created by BeINone on 2017-11-13.
 */

public class GameTimerTask extends TimerTask {

    private Time mTime;

    public GameTimerTask(Time time) {
        mTime = time;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            mTime.passOneSecond();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
