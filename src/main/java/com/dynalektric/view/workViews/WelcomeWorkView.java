package com.dynalektric.view.workViews;

import com.dynalektric.model.Model;

import javax.swing.*;
import java.awt.*;

public class WelcomeWorkView extends AbstractWorkView{
    public WelcomeWorkView(Model model) {
        super(model);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initializeUI();
            }
        });
    }

    @Override
    public void update(String msg) {
        super.update(msg);
    }

    @Override
    public void update(String msg, Object data) {
        super.update(msg , data);
    }

    @Override
    public String getViewName() {
        return null;
    }

    @Override
    public Integer getViewId() {
        return null;
    }

    private void initializeUI(){
        JLabel label = new JLabel("Welcome work view");
        add(label);
        setBackground(new Color(0 , 0 , 0));
        setPreferredSize(new Dimension(300 , 300));
    }
}
