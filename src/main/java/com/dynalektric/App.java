package com.dynalektric;

import com.dynalektric.constants.SystemConstants;
import com.dynalektric.model.Model;
import com.dynalektric.view.View;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App 
{
    public static void main( String[] args )
    {
        Model.getSingleton();
        View view = View.getSingleton();
        view.initView();
        view.chooseWorkView();
        view.startApp();
    }
}
