package com.dynalektric.model.repositories.project;

public interface ProjectRepo {
    public void deleteProjectByName(String name);
    public Project getProjectByName(String name);
    public void createNewProject(Project project);

    void updateProject(Project project);
}
