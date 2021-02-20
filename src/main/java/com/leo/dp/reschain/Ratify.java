package com.leo.dp.reschain;

public interface Ratify {

    Result deal(Chain chain);

    interface Chain{

        Request request();

        Result proceed(Request request);
    }
}
