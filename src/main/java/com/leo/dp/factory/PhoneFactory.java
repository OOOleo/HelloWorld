package com.leo.dp.factory;

public class PhoneFactory {
    public Phone makePhone(String phoneType) {
        if ("MiPhone".equals(phoneType)) {
            return new MiPhone();
        } else if ("iPhone".equals(phoneType)) {
            return new IPhone();
        }
        return null;
    }
}
