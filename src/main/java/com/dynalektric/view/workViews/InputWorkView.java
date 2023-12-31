package com.dynalektric.view.workViews;

import com.dynalektric.model.Model;
import com.dynalektric.view.ViewMessage;

import javax.swing.*;
import java.awt.*;

public class InputWorkView extends AbstractWorkView{
    private final static String VIEW_NAME = "Input Work view";
    public InputWorkView(Model model) {
        super(model);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initializeUI();
            }
        });
    }

    private void initializeUI(){
        JLabel label = new JLabel("Input panel");
        JPanel mainPanel = new JPanel();
        mainPanel.add(label);
        this.setLayout(new BorderLayout());
        this.add(mainPanel , BorderLayout.CENTER);
    }
    @Override
    public void captureEventFromChildSubFrame(ViewMessage message) {

    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }

    @Override
    public Integer getViewId() {
        return 2;
    }
}
