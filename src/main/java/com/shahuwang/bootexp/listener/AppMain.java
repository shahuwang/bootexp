package com.shahuwang.bootexp.listener;

public class AppMain {
    public static void main(String [] args){
        AppContext context = new AppContext();
        IAppListener starting = new StartingListener();
        IAppListener started = new StartedListener();
        IAppListener running = new RunningListener();
        context.addAppListeners(starting);
        context.addAppListeners(started);
        context.addAppListeners(running);
        context.publishEvent(new StartingEvent());
        context.publishEvent(new StartedEvent());
        context.publishEvent(new RunningEvent());
    }
}
