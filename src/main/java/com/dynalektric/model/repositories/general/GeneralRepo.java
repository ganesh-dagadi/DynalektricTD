package com.dynalektric.model.repositories.general;


import com.dynalektric.model.repositories.project.Project;

import java.util.Set;

public interface GeneralRepo {
    public String getLoadedProjectName();
    public void setLoadedProjectName(String name);
    public Set<String> getNamesOfAllProjectsCreated();
    public void addNewCreatedProjectName(String name);
    public void createNewProject(Project project);
    public Project getProjectByName(String name);
}
