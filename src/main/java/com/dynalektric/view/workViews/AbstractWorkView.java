package com.dynalektric.view.workViews;

import javax.swing.*;
import com.dynalektric.model.Model;
import com.dynalektric.model.ModelObserver;
import com.dynalektric.constants.ViewMessages;

import java.util.Objects;

public abstract class AbstractWorkView extends JPanel implements ModelObserver {
    public abstract String getViewName();
    public abstract Integer getViewId();
    Model model;
    protected AbstractWorkView(Model model) {
        this.model = model;
        model.addModelObserver(this);
    }

    @Override
    public void update(String msg){
        switch(msg){
            case ViewMessages.ERROR_OCCURRED:
                JOptionPane.showMessageDialog(this, "An error occured" , "Error" ,JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void update(String msg , Object data){
        if(msg.equals(ViewMessages.CUSTOM_MESSAGE)){
            JOptionPane.showMessageDialog(this, "Project name is already taken" , "Warning" ,JOptionPane.WARNING_MESSAGE);
        }
    }

}