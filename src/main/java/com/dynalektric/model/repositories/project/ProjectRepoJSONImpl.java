package com.dynalektric.model.repositories.project;

import com.dynalektric.constants.SystemConstants;
import com.dynalektric.helpers.FileHelpers;
import com.dynalektric.helpers.FileIOHelper;
import com.dynalektric.helpers.JacksonFileIOHelper;
import com.dynalektric.model.repositories.general.General;
import com.dynalektric.model.repositories.general.GeneralRepo;
import com.dynalektric.model.repositories.general.GeneralRepoJSONImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;
import java.util.Set;

public class ProjectRepoJSONImpl implements ProjectRepo{
    private final static Logger LOGGER = LogManager.getLogger(GeneralRepoJSONImpl.class);
    private final FileIOHelper fileIOHelper = new JacksonFileIOHelper();
    GeneralRepo generalRepo = new GeneralRepoJSONImpl();
    @Override
    public void deleteProjectByName(String name) {
        LOGGER.info("Deleting project with name {}" , name);
        List<String> projectNames = generalRepo.getNamesOfAllProjectsCreated();
        projectNames.remove(name);
        generalRepo.setNamesOfAllProjectCreated(projectNames);
        FileHelpers.deleteFile(new File(SystemConstants.DATABASE_DIR+name+".json"));
    }

    @Override
    public void createNewProject(Project project) {
        fileIOHelper.writeData(new File(SystemConstants.DATABASE_DIR+project.projectName+".json") , project);
    }

    @Override
    public Project getProjectByName(String name) {
        return (Project) fileIOHelper.readData(new File(SystemConstants.DATABASE_DIR+name+".json") , Project.class);
    }

    @Override
    public void updateProject(Project project){
        fileIOHelper.writeData(new File(SystemConstants.DATABASE_DIR+project.projectName+".json") , project);
    }
}
