package com.dynalektric.model.repositories.general;

import com.dynalektric.helpers.FileIOHelper;
import com.dynalektric.helpers.JacksonFileIOHelper;

public class GeneralRepoJSONImpl implements GeneralRepo{
    private final FileIOHelper fileIOHelper = new JacksonFileIOHelper();
    @Override
    public Integer getLoadedProjectId(){

        return null;
    }

    @Override
    public void setLoadedProjectId(Integer projectId) {

    }
}
