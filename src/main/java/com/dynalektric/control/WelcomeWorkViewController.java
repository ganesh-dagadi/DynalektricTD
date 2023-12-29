package com.dynalektric.control;

import com.dynalektric.constants.ViewMessages;
import com.dynalektric.model.Model;
import com.dynalektric.model.repositories.general.GeneralRepo;
import com.dynalektric.model.repositories.project.Project;

import java.util.Set;

public class WelcomeWorkViewController {
    Model model = Model.getSingleton();
    public void createNewProject(String name){
        Project newProject = new Project();
        newProject.projectName = name;
        GeneralRepo repo = model.getGeneralRepo();
        Set<String> createdProjects = repo.getNamesOfAllProjectsCreated();
        if(createdProjects.contains(name)) {
            model.notifyLiveView(ViewMessages.CUSTOM_MESSAGE , "Project name already exists");
            return;
        }
        repo.addNewCreatedProjectName(name);
        repo.createNewProject(newProject);
    }

    public void deleteProjectByName(String name){
        GeneralRepo repo= model.getGeneralRepo();
        repo.deleteProjectByName(name);
    }
    public void openProjectWithName(String name){
        model.clearProjectData();
        GeneralRepo repo = model.getGeneralRepo();
        repo.setLoadedProjectName(name);
        Project project = repo.getProjectByName(name);
        model.setLoadedProjectInput(project.inputs);
        model.loadNewProject(project);
        //TODO after workspacePanel is created
//        View.getSingleton().setView(null);
    }
}
