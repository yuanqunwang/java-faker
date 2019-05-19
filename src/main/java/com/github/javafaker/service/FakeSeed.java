package com.github.javafaker.service;

import java.util.Locale;

public class FakeSeed {
    private FakeValuesInterface fakeValuesInterfaces;
    private Class<?> fakeClazz;


    public FakeSeed(String fileName, Class<?> clazz){
        initFakeValuesInterface(fileName);
        this.fakeClazz = clazz;
    }

    private void initFakeValuesInterface(String fileName){
        this.fakeValuesInterfaces = new FakeValues(new Locale("zh-CN"), fileName, fileName);
    }

    public FakeValuesInterface getFakeValuesInterfaces() {
        return fakeValuesInterfaces;
    }

    public Class<?> getFakeClazz() {
        return fakeClazz;
    }
}
