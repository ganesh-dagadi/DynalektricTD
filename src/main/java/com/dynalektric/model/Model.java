package com.dynalektric.model;

import com.dynalektric.constants.SystemConstants;
import com.dynalektric.helpers.JacksonFileIOHelper;
import com.dynalektric.model.repositories.general.General;
import com.dynalektric.model.repositories.general.GeneralRepo;
import com.dynalektric.model.repositories.general.GeneralRepoJSONImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
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
    public void notifyLiveView(String msg){
        this.liveView.update(msg);
    }
    public void notifyLiveView(String msg , String data){this.liveView.update(msg , data);}

    //Used to initialize model for first time, example creating empty data files
    public void initModel(){
        File generalFile = new File(SystemConstants.GENERAL_FILE);
        if(!generalFile.exists()){
            General general = new General();
            new JacksonFileIOHelper().writeData(generalFile , general);
        }
    }
    public void clearModel(){
        model = null;
    }

    public GeneralRepo getGeneralRepo(){
        return new GeneralRepoJSONImpl();
    }
}
