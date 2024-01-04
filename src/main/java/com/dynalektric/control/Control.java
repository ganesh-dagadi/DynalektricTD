package com.dynalektric.control;

import com.dynalektric.model.Model;
import com.dynalektric.model.repositories.project.Project;
import com.dynalektric.model.repositories.project.ProjectRepo;
import com.dynalektric.view.View;
import com.dynalektric.view.workViews.WelcomeWorkView;

public class Control {
    Model model = Model.getSingleton();
    Calculations calculations = new Calculations();
    public Control(){
    }

    //Get the project object from model and persist into file
    public void saveProject(){
        ProjectRepo projectRepo = model.getProjectRepo();
        Project toSaveProject = model.getLoadedProject();
        if(toSaveProject != null)
            projectRepo.updateProject(toSaveProject);
    }
    public void closeOpenedProject(){
        this.saveProject();
        model.getGeneralRepo().setLoadedProjectName(null);
        model.clearProjectData();
        View view = View.getSingleton();
        view.setView(view.loadedViews.get(WelcomeWorkView.VIEW_NAME));
    }

    public void beginCalculations(){
        calculations.beginCalculations();
        model.notifyListeners("MODEL_UPDATED");
    }
    public WelcomeWorkViewController getWelcomeWorkViewController(){
        return new WelcomeWorkViewController();
    }
}
