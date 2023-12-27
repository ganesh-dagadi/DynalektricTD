package com.dynalektric.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;


public class Model {
    private final static Logger LOGGER = LogManager.getLogger(Model.class);
    private Set<ModelObserver> listeners = new HashSet<>();
    private ModelObserver liveView;
    private static Model model;
    private Model(){};
    public static Model getSingleton(){
        if(model == null){
            LOGGER.info("Initializing model");
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

    public void setLiveView(ModelObserver observer){
        this.liveView = observer;
    }

    public void clearModel(){
        LOGGER.info("Clearing model");
    }
}
