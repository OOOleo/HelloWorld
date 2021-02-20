package com.leo.dp.reschain;

public class Main {
    public static void main(String[] args) {
        Request request = new Request.Builder().setName("张三").setDays(5).setReason("事假").build();
        ChainOfResponsibilityClient client = new ChainOfResponsibilityClient();
        Result execute = client.execute(request);
        System.out.println("结果：" + execute.toString());
    }
}
