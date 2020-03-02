package com.shahuwang.bootexp.listener;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

public class SimpleAppEventMulticaster {
    private Set<IAppListener> listeners;
    public SimpleAppEventMulticaster() {
        this.listeners = new HashSet<>();
    }
    public void addAppListener(IAppListener listener){
        this.listeners.add(listener);
    }

    public void multicastEvent(AppEvent event){
        for(IAppListener listener: this.listeners) {
            listener.onAppEvent(event);
        }
    }

    public Set<IAppListener> getAppListeners(){
        return this.listeners;
    }
}
