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
    private final JTable tankDimensionTable = new JTable(7  , 2);
    private final JTable impedanceVoltageTable = new JTable(8 , 2);
    private final JTable lossesTable = new JTable(10 , 2);
    private final JPanel dimensionPanel = new JPanel();
    private final JPanel impedancePanel = new JPanel();
    private final JPanel lossesPanel = new JPanel();
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
        this.contentPanel.setLayout(new GridLayout(0 , 3));
        BoxLayout dimensionLayout = new BoxLayout(this.dimensionPanel , BoxLayout.Y_AXIS);
        this.dimensionPanel.setLayout(dimensionLayout);
        BoxLayout impedanceLayout = new BoxLayout(this.impedancePanel , BoxLayout.Y_AXIS);
        this.impedancePanel.setLayout(impedanceLayout);
        BoxLayout lossesLayout = new BoxLayout(this.lossesPanel , BoxLayout.Y_AXIS);
        this.lossesPanel.setLayout(lossesLayout);
        JLabel dimensionLabel = new JLabel("Tank Dimensions");
        dimensionLabel.setFont(StyleConstants.HEADING_SUB1);
        this.dimensionPanel.add(dimensionLabel);
        this.dimensionPanel.add(Box.createVerticalStrut(30));
        this.initializeTankDimensionTable();
        this.dimensionPanel.add(tankDimensionTable);
        JLabel impedanceHeading = new JLabel("Impedance");
        impedanceHeading.setFont(StyleConstants.HEADING_SUB1);
        this.impedancePanel.add(impedanceHeading);
        this.initializeImpedanceTable();
        this.impedancePanel.add(Box.createVerticalStrut(30));
        this.impedancePanel.add(impedanceVoltageTable);
        JLabel lossesHeading = new JLabel("Losses");
        lossesHeading.setFont(StyleConstants.HEADING_SUB1);
        this.lossesPanel.add(lossesHeading);
        this.initializeLossesTable();
        this.lossesPanel.add(Box.createVerticalStrut(30));
        this.lossesPanel.add(lossesTable);
        this.impedancePanel.setBorder(BorderFactory.createEmptyBorder(20 , 20 , 20 ,20));
        this.lossesPanel.setBorder(BorderFactory.createEmptyBorder(20 , 20 , 20 , 20));
        this.dimensionPanel.setBorder(BorderFactory.createEmptyBorder(20 , 20 ,20 ,20));
        this.contentPanel.add(dimensionPanel);
        this.contentPanel.add(impedancePanel);
        this.contentPanel.add(lossesPanel);
        this.mainPanel.add(contentPanel , BorderLayout.CENTER);
        this.mainPanel.add(new MenuBar(thisReference) , BorderLayout.NORTH);
        this.add(mainPanel , BorderLayout.CENTER);
    }

    private void initializeLossesTable() {
    }

    private void initializeImpedanceTable() {
        this.impedanceVoltageTable.setValueAt("h" , 0 , 0);
        this.impedanceVoltageTable.setValueAt("b" , 1 , 0);
        this.impedanceVoltageTable.setValueAt( "kr",2 , 0);
        this.impedanceVoltageTable.setValueAt( "Ls",3 , 0);
        this.impedanceVoltageTable.setValueAt( "δ`",4 , 0);
        this.impedanceVoltageTable.setValueAt( "ex",5 , 0);
        this.impedanceVoltageTable.setValueAt( "Er",6 , 0);
        this.impedanceVoltageTable.setValueAt( "Ek",7 , 0);
    }

    private void initializeTankDimensionTable() {
        this.tankDimensionTable.setValueAt( "Parameter",0 , 0);
        this.tankDimensionTable.setValueAt( "mtr",0 , 1);
        this.tankDimensionTable.setValueAt( "Active L",1 , 0);
        this.tankDimensionTable.setValueAt("Active H" , 2 , 0);
        this.tankDimensionTable.setValueAt("Active B" , 3 , 0);
        this.tankDimensionTable.setValueAt( "Overall L",4 , 0);
        this.tankDimensionTable.setValueAt("Overall H" , 5 , 0);
        this.tankDimensionTable.setValueAt("Overall B" , 6 , 0);
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

}
