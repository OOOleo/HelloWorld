package com.leo.dp.reschain;

/**
 * 经理
 */
public class Manager implements Ratify {
    @Override
    public Result deal(Chain chain) {
        Request request = chain.request();
        System.out.println("Manager====>request:" + request.toString());

        if (request.days() > 3) {
            //构建新的request
            Request newRequest = new Request.Builder().newRequest(request)
                    .setManagerInfo(request.name() + "每月的KPI考核还不错，可以批准")
                    .build();
            return chain.proceed(newRequest);
        }
        return new Result(true, "早点把事情办完");
    }
}
