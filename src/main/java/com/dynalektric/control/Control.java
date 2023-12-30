package com.dynalektric.control;

import com.dynalektric.model.Model;
import com.dynalektric.model.repositories.project.Project;
import com.dynalektric.model.repositories.project.ProjectRepo;

public class Control {
    Model model = Model.getSingleton();
    Calculations calculations = new Calculations();
    public Control(){
    }

    //Get the project object from model and persist into file
    public void saveProject(){
        ProjectRepo projectRepo = model.getProjectRepo();
        Project toSaveProject = model.getLoadedProject();
        projectRepo.updateProject(toSaveProject);
    }
    public WelcomeWorkViewController getWelcomeWorkViewController(){
        return new WelcomeWorkViewController();
    }
}
