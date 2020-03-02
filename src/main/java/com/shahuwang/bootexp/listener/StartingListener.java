package com.shahuwang.bootexp.listener;

public class StartingListener implements IAppListener {
    @Override
    public void onAppEvent(AppEvent event) {
       if(event instanceof StartingEvent) {
           System.out.println("starting----------");
       }
    }
}
