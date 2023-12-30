package com.dynalektric.control;

import com.dynalektric.constants.ViewMessages;
import com.dynalektric.model.Model;
import com.dynalektric.model.repositories.general.GeneralRepo;
import com.dynalektric.model.repositories.project.Project;
import com.dynalektric.model.repositories.project.ProjectRepo;

import java.util.List;

public class WelcomeWorkViewController {
    Model model = Model.getSingleton();
    public void createNewProject(String name){
        Project newProject = new Project();
        newProject.projectName = name;
        GeneralRepo generalRepo = model.getGeneralRepo();
        ProjectRepo projectRepo = model.getProjectRepo();
        List<String> createdProjects = generalRepo.getNamesOfAllProjectsCreated();
        if(createdProjects.contains(name)) {
            model.notifyLiveView(ViewMessages.CUSTOM_MESSAGE , "Project name already exists");
            return;
        }
        generalRepo.addNewCreatedProjectName(name);
        projectRepo.createNewProject(newProject);
    }

    public void deleteProjectByName(String name){
        ProjectRepo projectRepo = model.getProjectRepo();
        projectRepo.deleteProjectByName(name);
    }
    public void openProjectWithName(String name){
        model.clearProjectData();
        GeneralRepo repo = model.getGeneralRepo();
        ProjectRepo projectRepo = model.getProjectRepo();
        repo.setLoadedProjectName(name);
        Project project = projectRepo.getProjectByName(name);
        if(project == null){
            model.notifyLiveView(ViewMessages.CUSTOM_MESSAGE , "Project Not found");
            return;
        }
        model.loadNewProject(project);
        model.setLoadedProjectInput(project.inputs);
        //TODO after workspacePanel is created
//        View.getSingleton().setView(null);
    }

    public void closeOpenedProject(){
        if(model.hasUnsavedChanges()){
            new Control().saveProject();
            model.setHasUnsavedChanges(false);
        }
        model.clearProjectData();
    }
}
