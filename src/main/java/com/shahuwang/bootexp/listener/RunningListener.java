package com.shahuwang.bootexp.listener;

public class RunningListener implements IAppListener {
    @Override
    public void onAppEvent(AppEvent event) {
        if(event instanceof RunningEvent) {
            System.out.println("Running========");
        }
    }
}
