package com.dynalektric;

import com.dynalektric.control.Calculations;
import com.dynalektric.control.Control;
import com.dynalektric.control.WelcomeWorkViewController;
import com.dynalektric.model.Model;
import com.dynalektric.model.repositories.project.OutputData;
import com.dynalektric.view.View;

import javax.swing.*;
import java.awt.*;

public class App 
{
    public static void main( String[] args )
    {
        Model model = Model.getSingleton();
        model.initModel();
        View view = View.getSingleton();
        view.initView();
        view.chooseWorkView();
        view.startApp();

    }
}
