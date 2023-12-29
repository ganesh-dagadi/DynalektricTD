package com.dynalektric.model.repositories.general;

import com.dynalektric.constants.SystemConstants;
import com.dynalektric.constants.ViewMessages;
import com.dynalektric.helpers.FileIOHelper;
import com.dynalektric.helpers.JacksonFileIOHelper;
import com.dynalektric.model.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;

public class GeneralRepoJSONImpl implements GeneralRepo{
    private final static Logger LOGGER = LogManager.getLogger(GeneralRepoJSONImpl.class);
    private final FileIOHelper fileIOHelper = new JacksonFileIOHelper();
    @Override
    public Integer getLoadedProjectId(){
        try{
            General generalData = (General)fileIOHelper.readData(new File(SystemConstants.GENERAL_FILE) , General.class);
            return generalData.loadedProject;
        }catch(Exception e){
            LOGGER.error(e.getMessage());
            Model.getSingleton().notifyLiveView(ViewMessages.ERROR_OCCURRED);
        }
        return null;
    }

    @Override
    public void setLoadedProjectId(Integer projectId) {
        try{
            General generalData = new General();
            generalData.loadedProject = projectId;
            fileIOHelper.writeData(new File(SystemConstants.GENERAL_FILE) , generalData);
        }catch(Exception e){
            LOGGER.error(e.getMessage() , e);
            Model.getSingleton().notifyLiveView(ViewMessages.ERROR_OCCURRED);
        }
    }
}
