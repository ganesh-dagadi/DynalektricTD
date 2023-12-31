package com.dynalektric.view;

import javax.swing.*;
import java.awt.*;

import com.dynalektric.model.Model;
import com.dynalektric.view.workViews.AbstractWorkView;
public class MainPanel extends JPanel{
    private JPanel workArea = new JPanel();
    private CardLayout workLayout = new CardLayout();

    MainPanel(LayoutManager manager){
        super(manager);
        workArea.setLayout(workLayout);
        this.add(workArea);
    }

    public void displayWorkView(String viewName) {
        if(Model.getSingleton().getLoadedProject() != null){
            Model.getSingleton().notifyListeners("MODEL_UPDATED");
        }
        this.workLayout.show(this.workArea , viewName);
    }

    public void loadWorkView(AbstractWorkView view) throws NullPointerException{
        this.workArea.add(view , view.getViewName());
    }
}
