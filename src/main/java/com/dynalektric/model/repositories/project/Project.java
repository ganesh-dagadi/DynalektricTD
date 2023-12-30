package com.dynalektric.model.repositories.project;

import java.util.Date;

public class Project {
    public String projectName;
    public Date createdOn = new Date();
    public InputData inputs = new InputData();
}
