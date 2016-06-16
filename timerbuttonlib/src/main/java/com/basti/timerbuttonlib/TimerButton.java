package com.basti.timerbuttonlib;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.basti.timerbuttonlib.callback.TickListener;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Bowen on 2016-06-16.
 */
public class TimerButton extends Button {

    private Timer timer;
    private TimerTask timerTask;

    private long totalTime = 60 * 1000;//默认60s
    private long intervalTime = 1*1000;//默认1s
    private long startTime = totalTime;//开始时间

    private TickListener mListener;
    public TimerButton(Context context) {
        this(context,null);
    }

    public TimerButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TimerButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        timer = new Timer(true);
        timerTask = new TimerTask() {
            @Override
            public void run() {
                startTime = startTime - intervalTime;

                if (startTime < 0){
                    timer.cancel();
                    if (mListener!=null){
                        mListener.onFinished();
                    }
                }else {
                    mListener.onTick(startTime);
                }

            }
        };
    }

    public void start(){
        timer.schedule(timerTask,0,intervalTime);
    }
}
