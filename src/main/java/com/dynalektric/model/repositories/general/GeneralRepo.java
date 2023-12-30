package com.dynalektric.model.repositories.general;


import com.dynalektric.model.repositories.project.Project;

import java.util.List;
import java.util.Set;

public interface GeneralRepo {
    public String getLoadedProjectName();
    public void setLoadedProjectName(String name);
    public List<String> getNamesOfAllProjectsCreated();

    public void setNamesOfAllProjectCreated(List<String> names);

    public void addNewCreatedProjectName(String name);
}
