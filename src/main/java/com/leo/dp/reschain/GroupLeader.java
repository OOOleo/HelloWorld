package com.leo.dp.reschain;


/**
 * 组长
 */
public class GroupLeader implements Ratify {
    @Override
    public Result deal(Chain chain) {
        Request request = chain.request();
        System.out.println("GroupLeader===>request:" + request.toString());

        if (request.days() > 1) {
            Request newRequest = new Request.Builder().newRequest(request)
                    .setGroupLeaderInfo(request.name() + "平时表现不错")
                    .build();
            //转发到上级
            return chain.proceed(newRequest);
        }
        return new Result(true, "GroupLeader:早去早回");
    }
}
