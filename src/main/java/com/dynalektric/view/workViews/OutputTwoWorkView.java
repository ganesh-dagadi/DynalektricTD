package com.dynalektric.view.workViews;

import com.ctc.wstx.shaded.msv_core.util.Util;
import com.dynalektric.constants.StyleConstants;
import com.dynalektric.constants.ViewMessages;
import com.dynalektric.control.Control;
import com.dynalektric.model.Model;
import com.dynalektric.model.repositories.project.InputData;
import com.dynalektric.model.repositories.project.OutputData;
import com.dynalektric.utils.UtilFunction;
import com.dynalektric.view.View;
import com.dynalektric.view.ViewMessage;
import com.dynalektric.view.components.MenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OutputTwoWorkView extends AbstractWorkView{

    private final OutputTwoWorkView thisReference = this;
    private final Control mainController = new Control();
    public final static String VIEW_NAME = "OutputWorkViewTwo";

    private final JPanel mainPanel = new JPanel();
    private final JPanel contentPanel = new JPanel();
    private final JTable tankDimensionTable = new JTable(7  , 2);
    private final JTable impedanceVoltageTable = new JTable(8 , 2);
    private final JTable lossesTable = new JTable(9 , 2);
    private final JTable billTable = new JTable(11, 2);
    private final JTable surfaceAreaTable = new JTable(5,2);
    private final JTable vaTable = new JTable(7,3);
    private final JPanel dimensionPanel = new JPanel();
    private final JPanel impedancePanel = new JPanel();
    private final JPanel lossesPanel = new JPanel();
    private final JPanel billPanel = new JPanel();
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

    @Override
    public void update(String msg){
        if(msg.equals("MODEL_UPDATED")){
            this.initializeTankDimensionTable();
            this.initializeBillTable();
            this.initializeImpedanceTable();
            this.initializeLossesTable();
            this.initializeSurfaceTable();
            this.initializeVATable();
        }
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
        BoxLayout billLayout = new BoxLayout(this.billPanel, BoxLayout.Y_AXIS);
        this.billPanel.setLayout(billLayout);
        JLabel dimensionLabel = new JLabel("Tank Dimensions");
        dimensionLabel.setFont(StyleConstants.HEADING_SUB1);
        this.dimensionPanel.add(dimensionLabel);
        this.dimensionPanel.add(Box.createVerticalStrut(30));
        if(model.getLoadedProject() != null)
            this.initializeTankDimensionTable();
        this.dimensionPanel.add(tankDimensionTable);
        JLabel impedanceHeading = new JLabel("Impedance");
        impedanceHeading.setFont(StyleConstants.HEADING_SUB1);
        this.impedancePanel.add(impedanceHeading);
        if(model.getLoadedProject() != null)
            this.initializeImpedanceTable();
        this.impedancePanel.add(Box.createVerticalStrut(30));
        this.impedancePanel.add(impedanceVoltageTable);
        this.impedancePanel.add(Box.createVerticalStrut(15));
        JLabel surfaceAreaLabel = new JLabel("Surface Area:");

        surfaceAreaLabel.setFont(StyleConstants.HEADING_SUB1);
        this.impedancePanel.add(surfaceAreaLabel);
        this.impedancePanel.add(Box.createVerticalStrut(15));
        this.impedancePanel.add(surfaceAreaTable);
        if(model.getLoadedProject() != null)
            this.initializeSurfaceTable();
        //
        JLabel lossesHeading = new JLabel("Losses");
        lossesHeading.setFont(StyleConstants.HEADING_SUB1);
        this.lossesPanel.add(lossesHeading);
        if(model.getLoadedProject() != null)
            this.initializeLossesTable();
        this.lossesPanel.add(Box.createVerticalStrut(30));
        this.lossesPanel.add(lossesTable);
        JLabel vaLabel = new JLabel("VA Table");
        this.lossesPanel.add(Box.createVerticalStrut(15));
        vaLabel.setFont(StyleConstants.HEADING_SUB1);
        this.lossesPanel.add(vaLabel);
        this.lossesPanel.add(Box.createVerticalStrut(15));
        if(model.getLoadedProject() != null)
            this.initializeVATable();
        this.lossesPanel.add(vaTable);

        this.dimensionPanel.add(Box.createVerticalStrut(15));
        JLabel billHeading = new JLabel("Bill of material in Kg");
        billHeading.setFont(StyleConstants.HEADING_SUB1);
        this.dimensionPanel.add(billHeading);
        if(model.getLoadedProject() != null)
            this.initializeBillTable();
        this.dimensionPanel.add(Box.createVerticalStrut(30));
        this.dimensionPanel.add(billTable);
        this.impedancePanel.setBorder(BorderFactory.createEmptyBorder(20 , 20 , 20 ,20));
        this.lossesPanel.setBorder(BorderFactory.createEmptyBorder(20 , 20 , 20 , 20));
        this.dimensionPanel.setBorder(BorderFactory.createEmptyBorder(20 , 20 ,20 ,20));
        this.contentPanel.add(dimensionPanel);
        this.contentPanel.add(impedancePanel);
        this.contentPanel.add(lossesPanel);
        this.mainPanel.add(contentPanel , BorderLayout.CENTER);
        this.mainPanel.add(new MenuBar(thisReference) , BorderLayout.NORTH);
        this.mainPanel.add(initializeNavigationPanel() , BorderLayout.SOUTH);
        this.add(mainPanel , BorderLayout.CENTER);
    }

    public void initializeSurfaceTable(){
        OutputData outputData = Model.getSingleton().getOutputData();
        this.surfaceAreaTable.setValueAt("Core s-a ",0,0);
        this.surfaceAreaTable.setValueAt("Wdg s-a",1,0);
        this.surfaceAreaTable.setValueAt("∑ s-a",2,0);
        this.surfaceAreaTable.setValueAt("∑ Loss",3,0);
        this.surfaceAreaTable.setValueAt("Θ(k)",4,0);
        this.surfaceAreaTable.setValueAt(outputData.CORE_SA,0,1);
        this.surfaceAreaTable.setValueAt(outputData.WDG_SA,1,1);
        this.surfaceAreaTable.setValueAt(outputData.SUM_SA,2,1);
        this.surfaceAreaTable.setValueAt(outputData.SUM_LOSS,3,1);
        this.surfaceAreaTable.setValueAt(outputData.THETA_K,4,1);

    }
    public void initializeVATable(){

        OutputData outputData = Model.getSingleton().getOutputData();
        this.vaTable.setValueAt("Mass Limb",0,0);
        this.vaTable.setValueAt("Mass Yoke",1,0);
        this.vaTable.setValueAt("Mass Corner",2,0);
        this.vaTable.setValueAt("Gap VA",3,0);
        this.vaTable.setValueAt("∑ VA",4,0);
        this.vaTable.setValueAt("%N.L.Current",5,0);
        this.vaTable.setValueAt("Extra-N.L.Loss",6,0);
        this.vaTable.setValueAt(outputData.MASS_LIMB,0,1);
        this.vaTable.setValueAt(outputData.MASS_YOKE,1,1);
        this.vaTable.setValueAt(outputData.MASS_CORNER,2,1);
        this.vaTable.setValueAt(outputData.MASS_LIMB_DASH,0,2);
        this.vaTable.setValueAt(outputData.MASS_YOKE_DASH,1,2);
        this.vaTable.setValueAt(outputData.MASS_CORNER_DASH,2,2);
        this.vaTable.setValueAt(outputData.GAP_VA,3,2);
        this.vaTable.setValueAt(outputData.SUM_VA,4,2);
        this.vaTable.setValueAt(outputData.NL_CURRENT_PERCENTAGE,5,2);
        this.vaTable.setValueAt(outputData.EXTRA_NL_LOSS,6,2);

    }
    public void initializeBillTable() {
        OutputData outputData = Model.getSingleton().getOutputData();
        InputData inputData = Model.getSingleton().getLoadedProjectInput();


        // setting parameter name
        this.billTable.setValueAt("Core", 0, 0);
        this.billTable.setValueAt("Core-Steel + SS", 1, 0);
        this.billTable.setValueAt(inputData.CONDUCTOR, 2, 0);
        this.billTable.setValueAt("Leads", 3, 0);
        this.billTable.setValueAt("Insulation-FG", 4, 0);
        this.billTable.setValueAt("Connection-FG", 5, 0);
        this.billTable.setValueAt("Insulation-CL-H", 6, 0);
        this.billTable.setValueAt("RESIN-VT50", 7, 0);
        this.billTable.setValueAt("MISC", 8, 0);
        this.billTable.setValueAt("CRCA ENCL", 9, 0);
        this.billTable.setValueAt("Total Mass", 10, 0);

        // setting values
        this.billTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.BOM_CORE, 3), 0, 1);
        this.billTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.BOM_CORE_STEEL, 3), 1, 1);
        this.billTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.BOM_CONDUCTOR_WT, 3), 2, 1);
        this.billTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.BOM_LEADS, 3), 3, 1);
        this.billTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.BOM_INSULATION_FG, 3), 4, 1);
        this.billTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.BOM_CONNECTION_FG, 3), 5, 1);
        this.billTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.BOM_INSULATION_CL_H, 3), 6, 1);
        this.billTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.BOM_RESIN_VT50, 3), 7, 1);
        this.billTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.BOM_MISC, 3), 8, 1);
        this.billTable.setValueAt(UtilFunction.RoundedToNDecimal(inputData.BOM_CRCA_ENCL, 3), 9, 1);
        this.billTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.BOM_TOTAL_MASS, 3), 10, 1);

    }

    private void initializeLossesTable() {

        OutputData outputData = Model.getSingleton().getOutputData();

        this.lossesTable.setValueAt("Mass of the Conductor", 0, 0);
        this.lossesTable.setValueAt("Load Loss LV Watts",1,0);
        this.lossesTable.setValueAt("Load Loss HV Watts",2,0);
        this.lossesTable.setValueAt("Tank Watts",3,0);
        this.lossesTable.setValueAt("Total Obtained Watts",4,0);
        this.lossesTable.setValueAt("Total Core Mass Kg",5,0);
        this.lossesTable.setValueAt("Net Cross Section cm^2",6,0);
        this.lossesTable.setValueAt("Spec Losses %",7,0);
        this.lossesTable.setValueAt("Calc Loss watts",8,0);

        this.lossesTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.MASS_OF_CONDUCTOR, 3),0,1);
        this.lossesTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.LOAD_LOSS_LV, 3),1,1);
        this.lossesTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.LOAD_LOSS_HV, 3),2,1);
        this.lossesTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.TANK, 3), 3, 1);
        this.lossesTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.OBTAINED_LOSS, 3), 4, 1);
        this.lossesTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.TOTAL_CORE_MASS, 3), 5, 1);
        this.lossesTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.NET_CROSS_SECTION, 3), 6, 1);
        this.lossesTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.SPEC_LOSSES, 3), 7, 1);
        this.lossesTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.CALC_LOSS, 3), 8, 1);

    }

    private void initializeImpedanceTable() {

        OutputData outputData = Model.getSingleton().getOutputData();

        this.impedanceVoltageTable.setValueAt("h" , 0 , 0);
        this.impedanceVoltageTable.setValueAt("b" , 1 , 0);
        this.impedanceVoltageTable.setValueAt( "kr",2 , 0);
        this.impedanceVoltageTable.setValueAt( "Ls",3 , 0);
        this.impedanceVoltageTable.setValueAt( "δ`",4 , 0);
        this.impedanceVoltageTable.setValueAt( "ex %",5 , 0);
        this.impedanceVoltageTable.setValueAt( "Er %",6 , 0);
        this.impedanceVoltageTable.setValueAt( "Ek %",7 , 0);

        // setting values
        this.impedanceVoltageTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.H, 3), 0, 1);
        this.impedanceVoltageTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.B, 3), 1, 1);
        this.impedanceVoltageTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.KR, 3), 2, 1);
        this.impedanceVoltageTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.LS, 3), 3, 1);
        this.impedanceVoltageTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.DELTA_DASH, 3), 4, 1);
        this.impedanceVoltageTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.EX, 3), 5, 1);
        this.impedanceVoltageTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.ER, 3), 6, 1);
        this.impedanceVoltageTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.EK, 3), 7, 1);
    }

    private void initializeTankDimensionTable() {

        OutputData outputData = Model.getSingleton().getOutputData();

        this.tankDimensionTable.setValueAt( "Parameter",0 , 0);
        this.tankDimensionTable.setValueAt( "mm",0 , 1);
        this.tankDimensionTable.setValueAt( "Active L",1 , 0);
        this.tankDimensionTable.setValueAt("Active H" , 2 , 0);
        this.tankDimensionTable.setValueAt("Active B" , 3 , 0);
        this.tankDimensionTable.setValueAt( "Overall L",4 , 0);
        this.tankDimensionTable.setValueAt("Overall H" , 5 , 0);
        this.tankDimensionTable.setValueAt("Overall B" , 6 , 0);

        // setting values
        this.tankDimensionTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.L_ACTIVE, 3), 1, 1);
        this.tankDimensionTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.H_ACTIVE, 3), 2, 1);
        this.tankDimensionTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.B_ACTIVE, 3), 3, 1);
        this.tankDimensionTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.L_MECHANICAL, 3), 4, 1);
        this.tankDimensionTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.H_MECHANICAL, 3), 5, 1);
        this.tankDimensionTable.setValueAt(UtilFunction.RoundedToNDecimal(outputData.B_MECHANICAL, 3), 6, 1);

    }

    private JPanel initializeNavigationPanel(){
        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JButton previousBtn = new JButton("Previous");
        navigationPanel.add(previousBtn);
        previousBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                View.getSingleton().setView(OutputOneWorkView.VIEW_NAME);
            }
        });
        return navigationPanel;
    }

    @Override
    public void captureEventFromChildSubFrame(ViewMessage message) {
        switch (message.getMsgType()) {
            case ViewMessages.CLOSE_OPENED_PROJECT:
                mainController.closeOpenedProject();
                break;
            case ViewMessages.SAVE_PROJECT:
                mainController.saveProject();
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
