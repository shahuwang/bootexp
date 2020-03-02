package com.shahuwang.bootexp.listener;

public class StartedListener implements IAppListener {
    @Override
    public void onAppEvent(AppEvent event) {
        if(event instanceof StartedEvent){
            System.out.println("started++++++++");
        }
    }
}
