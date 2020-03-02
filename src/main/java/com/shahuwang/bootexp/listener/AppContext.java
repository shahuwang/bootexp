package com.shahuwang.bootexp.listener;

import java.util.Set;

public class AppContext {
    private Set<IAppListener> listeners;
    private SimpleAppEventMulticaster multicaster = new SimpleAppEventMulticaster();
    public Set<IAppListener> getListeners() {
        return this.multicaster.getAppListeners();
    }

    public void addAppListeners(IAppListener listener){
        this.multicaster.addAppListener(listener);
    }

    public void publishEvent(AppEvent event){
        this.multicaster.multicastEvent(event);
    }
}
