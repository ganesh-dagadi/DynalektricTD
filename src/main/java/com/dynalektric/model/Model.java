package com.dynalektric.model;

import java.util.HashSet;
import java.util.Set;
public class Model {
    Set<ModelObserver> listeners = new HashSet<>();
    private static Model model;
    private Model(){};
    public static Model getSingleton(){
        if(model == null){
            model = new Model();
        }
        return model;
    }

    public void notifyListeners(String msg){
        for(ModelObserver listener : listeners){
            listener.update(msg);
        }
    }
    public void notifyListeners(String msg , Object data){
        for(ModelObserver listener : listeners){
            listener.update(msg);
        }
    }

    public void addModelObserver(ModelObserver observer){
        model.listeners.add(observer);
    }
}
