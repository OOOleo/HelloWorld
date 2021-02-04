package com.leo.thread.test;

import java.lang.ref.WeakReference;

public class WeakRef {   //弱引用
    public static void main(String[] args) {
        WeakReference w = new WeakReference<>(new M());
        System.out.println(w.get());
        System.gc();
        System.out.println(w.get());
    }
}
