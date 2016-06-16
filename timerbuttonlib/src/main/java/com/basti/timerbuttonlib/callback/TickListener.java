package com.basti.timerbuttonlib.callback;

/**
 * Created by Bowen on 2016-06-16.
 */
public interface TickListener {

    void onTick(long nowTime);
    void onFinished();

}
