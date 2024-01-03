package com.dynalektric.view.workViews;

import com.dynalektric.constants.StyleConstants;
import com.dynalektric.constants.ViewMessages;
import com.dynalektric.control.Control;
import com.dynalektric.model.Model;
import com.dynalektric.view.ViewMessage;
import com.dynalektric.view.components.MenuBar;

import javax.swing.*;
import java.awt.*;

public class OutputTwoWorkView extends AbstractWorkView{

    private final OutputTwoWorkView thisReference = this;
    private final Control mainController = new Control();
    public final static String VIEW_NAME = "OutputWorkViewTwo";

    private final JPanel mainPanel = new JPanel();
    private final JPanel contentPanel = new JPanel();
    private final JTable dimensionTable = new JTable(10  , 3);
    private final JTable impedanceVoltageTable = new JTable(10 , 3);
    private final JPanel dimensionPanel = new JPanel();
    private final JPanel impedancePanel = new JPanel();
    public OutputTwoWorkView(Model model) {
        super(model);
        this.setLayout(new BorderLayout());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initializeUI();
            }
        });
    }

    private void initializeUI() {
        this.mainPanel.setLayout(new BorderLayout());
        this.contentPanel.setLayout(new GridLayout(0 , 2));
        BoxLayout dimensionLayout = new BoxLayout(this.dimensionPanel , BoxLayout.Y_AXIS);
        this.dimensionPanel.setLayout(dimensionLayout);
        BoxLayout impedanceLayout = new BoxLayout(this.impedancePanel , BoxLayout.Y_AXIS);
        this.impedancePanel.setLayout(impedanceLayout);
        JLabel dimensionLabel = new JLabel("Dimensions");
        dimensionLabel.setFont(StyleConstants.HEADING_SUB1);
        this.dimensionPanel.add(dimensionLabel);
        this.dimensionPanel.add(dimensionTable);
        JLabel impedanceHeading = new JLabel("Impedance");
        impedanceHeading.setFont(StyleConstants.HEADING_SUB1);
        this.impedancePanel.add(impedanceHeading);
        this.impedancePanel.add(impedanceVoltageTable);
        this.impedancePanel.setBorder(BorderFactory.createEmptyBorder(20 , 20 , 20 ,20));
        this.dimensionPanel.setBorder(BorderFactory.createEmptyBorder(20 , 20 ,20 ,20));
        this.contentPanel.add(dimensionPanel);
        this.contentPanel.add(impedancePanel);
        this.mainPanel.add(contentPanel , BorderLayout.CENTER);
        this.mainPanel.add(new MenuBar(thisReference) , BorderLayout.NORTH);
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
        return 4;
    }

    @Override
    public void refreshUI() {

    }
}
