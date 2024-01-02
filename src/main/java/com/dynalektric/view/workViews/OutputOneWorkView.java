package com.dynalektric.view.workViews;

import com.dynalektric.constants.StyleConstants;
import com.dynalektric.constants.ViewMessages;
import com.dynalektric.control.Control;
import com.dynalektric.model.Model;
import com.dynalektric.view.ViewMessage;
import com.dynalektric.view.components.MenuBar;

import javax.swing.*;
import java.awt.*;

public class OutputOneWorkView extends AbstractWorkView{
    private Control mainController = new Control();
    public final static String VIEW_NAME = "OutputViewOne";
    private final JPanel mainPanel = new JPanel();
    private final JPanel LV_HVPanel = new JPanel();
    private final JPanel coreDetailsPanel = new JPanel();
    public OutputOneWorkView(Model model) {
        super(model);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initializeUI();
            }
        });
    }
    private void initializeUI(){
        this.setLayout(new BorderLayout());
        this.add(new MenuBar(this) , BorderLayout.NORTH);
        this.initializeMainPanel();
        this.add(mainPanel , BorderLayout.CENTER);
    }
    @Override
    public void captureEventFromChildSubFrame(ViewMessage message) {
        switch (message.getMsgType()) {
            case ViewMessages.CLOSE_OPENED_PROJECT:
                mainController.closeOpenedProject();
                break;
        }
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }

    @Override
    public Integer getViewId() {
        return 3;
    }
    private void initializeMainPanel(){
        this.mainPanel.setLayout(new GridLayout(0 , 2));
        this.initializeLV_HVPanel();
        this.initializeCoreDetailsPanel();
        this.mainPanel.add(LV_HVPanel);
        this.mainPanel.add(coreDetailsPanel);
    }
    private void initializeLV_HVPanel(){
        BoxLayout layout = new BoxLayout(this.LV_HVPanel , BoxLayout.Y_AXIS);
        this.LV_HVPanel.setLayout(layout);
        JTable table = new JTable(10 , 3);
        table.setValueAt("Parameter" , 0 , 0);
        table.setValueAt("LV" , 0 , 1);
        table.setValueAt("HV" , 0 , 2);
        JLabel LV_HVHeading = new JLabel("LV HV data");
        LV_HVHeading.setFont(StyleConstants.HEADING_SUB1);
        LV_HVHeading.setAlignmentX(CENTER_ALIGNMENT);
        table.setAlignmentX(CENTER_ALIGNMENT);
        this.LV_HVPanel.add(LV_HVHeading);
        this.LV_HVPanel.add(Box.createVerticalStrut(10));
        this.LV_HVPanel.add(table);
        this.LV_HVPanel.setBackground(new Color(230 , 230 , 230));
        this.LV_HVPanel.setBorder(BorderFactory.createEmptyBorder(20, 20 , 20 ,20));
    }
    private void initializeCoreDetailsPanel(){
        BoxLayout layout = new BoxLayout(this.coreDetailsPanel , BoxLayout.Y_AXIS);
        this.coreDetailsPanel.setLayout(layout);
        JTable table = new JTable(10 , 3);
        table.setValueAt("Parameter" , 0 , 0);
        table.setValueAt("W" , 0 , 1);
        table.setValueAt("D" , 0 , 2);
        JLabel coreDetailsHeading = new JLabel("Core details");
        coreDetailsHeading.setFont(StyleConstants.HEADING_SUB1);
        coreDetailsHeading.setAlignmentX(CENTER_ALIGNMENT);
        table.setAlignmentX(CENTER_ALIGNMENT);
        this.coreDetailsPanel.add(coreDetailsHeading);
        this.coreDetailsPanel.add(Box.createVerticalStrut(10));
        this.coreDetailsPanel.add(table);
        this.coreDetailsPanel.setBackground(new Color(230 , 230 , 230));
        this.coreDetailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20 , 20 ,20));
    }
}
