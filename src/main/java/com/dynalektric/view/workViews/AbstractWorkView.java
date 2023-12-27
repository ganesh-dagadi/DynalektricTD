package com.dynalektric.view.workViews;

import javax.swing.*;
import com.dynalektric.model.Model;
import com.dynalektric.model.ModelObserver;
public abstract class AbstractWorkView extends JPanel implements ModelObserver {
    public abstract String getViewName();
    public abstract Integer getViewId();
    Model model;
    protected AbstractWorkView(Model model) {
        this.model = model;
        model.addModelObserver(this);
    }

}