package com.leo.dp.reschain;

import java.util.ArrayList;

public class ChainOfResponsibilityClient {
    private ArrayList<Ratify> ratifies;

    public ChainOfResponsibilityClient() {
        ratifies = new ArrayList<>();
    }

    public void addRatify(Ratify ratify) {
        ratifies.add(ratify);
    }

    /**
     * 执行请求
     */
    public Result execute(Request request) {
        ArrayList<Ratify> arrayList = new ArrayList<>();
        arrayList.addAll(ratifies);
        arrayList.add(new GroupLeader());
        arrayList.add(new Manager());
        arrayList.add(new DepartmentHeader());

        RealChain realChain = new RealChain(arrayList, request, 0);
        return realChain.proceed(request);

    }
}
