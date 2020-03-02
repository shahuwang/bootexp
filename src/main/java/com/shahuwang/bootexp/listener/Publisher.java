package com.shahuwang.bootexp.listener;

public interface Publisher {
    default void starting() {

    }

    default void started(AppContext context){

    }

    default void running(AppContext context){

    }
}
