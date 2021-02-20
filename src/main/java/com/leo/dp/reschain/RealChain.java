package com.leo.dp.reschain;

import java.util.List;


public class RealChain implements Ratify.Chain {

    public Request request;
    public List<Ratify> ratifies;
    public int index;

    public RealChain(List<Ratify> ratifies, Request request, int index) {
        this.request = request;
        this.ratifies = ratifies;
        this.index = index;
    }

    @Override
    public Request request() {
        return request;
    }

    /*
    转发功能
     */
    @Override
    public Result proceed(Request request) {
        Result proceed = null;
        if (ratifies.size() > index) {
            RealChain realChain = new RealChain(ratifies, request, index + 1);
            Ratify ratify = ratifies.get(index);
            proceed = ratify.deal(realChain);
        }
        return proceed;
    }
}
