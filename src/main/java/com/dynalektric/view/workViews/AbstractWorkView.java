package com.dynalektric.view.workViews;

import javax.swing.*;
import com.dynalektric.model.Model;
import com.dynalektric.model.ModelObserver;
import com.dynalektric.constants.ViewMessages;
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

}