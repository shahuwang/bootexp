package com.shahuwang.bootexp.listener;

public class EventPublisher implements Publisher {
    private final AppContext context;
    private final SimpleAppEventMulticaster initialMulticaster;
    public EventPublisher(AppContext context) {
        this.context = context;
        this.initialMulticaster = new SimpleAppEventMulticaster();
        for(IAppListener listener: context.getListeners()) {
            this.initialMulticaster.addAppListener(listener);
        }
    }

    @Override
    public void running(AppContext context) {
        this.context.publishEvent(new RunningEvent());
    }

    @Override
    public void started(AppContext context) {
        this.context.publishEvent(new StartedEvent());
    }

    @Override
    public void starting() {
        this.initialMulticaster.multicastEvent(new StartingEvent());
    }

}
