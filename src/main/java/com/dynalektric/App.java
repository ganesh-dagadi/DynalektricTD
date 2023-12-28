package com.dynalektric;

import com.dynalektric.model.Model;
import com.dynalektric.model.repositories.general.GeneralRepo;
import com.dynalektric.model.repositories.general.GeneralRepoJSONImpl;
import com.dynalektric.view.View;

public class App 
{
    public static void main( String[] args )
    {
        Model.getSingleton();
        View view = View.getSingleton();
        view.initView();
        view.chooseWorkView();
        view.startApp();
        GeneralRepo repo = new GeneralRepoJSONImpl();
        repo.setLoadedProjectId(1);
        System.out.println(repo.getLoadedProjectId());
    }
}
