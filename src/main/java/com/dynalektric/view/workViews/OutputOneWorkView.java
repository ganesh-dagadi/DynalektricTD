package com.dynalektric.view.workViews;

import com.dynalektric.constants.StyleConstants;
import com.dynalektric.constants.ViewMessages;
import com.dynalektric.control.Control;
import com.dynalektric.model.Model;
import com.dynalektric.view.View;
import com.dynalektric.view.ViewMessage;
import com.dynalektric.view.components.MenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OutputOneWorkView extends AbstractWorkView{
    private final Control mainController = new Control();
    public final static String VIEW_NAME = "OutputViewOne";
    private final JPanel mainPanel = new JPanel();
    private final JPanel LV_HVPanel = new JPanel();
    private final JPanel coreDetailsPanel = new JPanel();
    private final JTable LV_HV_Table = new JTable(18  , 3);
    private final JTable wireDetailTable = new JTable(3 , 5);
    private final JTable coreWdgTable = new JTable(13 , 3);
    private final JTable coreWeightTable = new JTable(1 , 5);
    private final JLabel cDistLabel = new JLabel("C Dist : ");
    private final JLabel yokeL = new JLabel("Yoke L : ");
    private final JLabel leads = new JLabel("Leads : ");

    JLabel VByTOutput = new JLabel("24.5");
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
        this.add(initializeNavigationPanel() , BorderLayout.SOUTH);
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
        initializeLVHVTable();
        JLabel LV_HVHeading = new JLabel("LV HV data");
        LV_HVHeading.setFont(StyleConstants.HEADING_SUB1);
        LV_HVHeading.setAlignmentX(CENTER_ALIGNMENT);
        LV_HV_Table.setAlignmentX(CENTER_ALIGNMENT);
        this.VByTOutput.setFont(StyleConstants.NORMAL_TEXT);
        JLabel VByTLabel = new JLabel("V/T");
        VByTLabel.setFont(StyleConstants.HEADING_SUB2);
        this.LV_HVPanel.add(LV_HVHeading);
        this.LV_HVPanel.add(Box.createVerticalStrut(10));
        this.LV_HVPanel.add(LV_HV_Table);
        this.LV_HVPanel.add(Box.createVerticalStrut(10));
        this.LV_HVPanel.add(VByTLabel);
        this.LV_HVPanel.add(Box.createVerticalStrut(5));
        this.LV_HVPanel.add(VByTOutput);
        this.LV_HVPanel.add(Box.createVerticalStrut(10));
        this.LV_HVPanel.add(this.wireDetailTable);
        this.LV_HVPanel.add(Box.createVerticalStrut(40));
        this.LV_HVPanel.add(this.coreWeightTable);
        this.LV_HVPanel.setBackground(new Color(230 , 230 , 230));
        this.LV_HVPanel.setBorder(BorderFactory.createEmptyBorder(20, 20 , 20 ,20));
    }
    private void initializeCoreDetailsPanel(){
        BoxLayout layout = new BoxLayout(this.coreDetailsPanel , BoxLayout.Y_AXIS);
        this.coreDetailsPanel.setLayout(layout);
        JLabel coreDetailsHeading = new JLabel("Core details");
        coreDetailsHeading.setFont(StyleConstants.HEADING_SUB1);
        coreDetailsHeading.setAlignmentX(CENTER_ALIGNMENT);
        this.initializeCoreWDGTable();
        this.coreDetailsPanel.add(coreDetailsHeading);
        this.coreDetailsPanel.add(Box.createVerticalStrut(10));
        this.coreDetailsPanel.add(coreWdgTable);
        this.coreDetailsPanel.setBackground(new Color(230 , 230 , 230));
        this.coreDetailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20 , 20 ,20));

        this.cDistLabel.setAlignmentX(LEFT_ALIGNMENT);
        this.coreDetailsPanel.add(cDistLabel);
        this.coreDetailsPanel.add(yokeL);
        this.coreDetailsPanel.add(leads);
    }
    private JPanel initializeNavigationPanel(){
        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JButton previousBtn = new JButton("Previous");
        JButton nextBtn = new JButton("Next");
        navigationPanel.add(previousBtn);
        previousBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                View.getSingleton().setView(InputWorkView.VIEW_NAME);
            }
        });
        nextBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                View.getSingleton().setView(OutputWorkViewTwo.VIEW_NAME);
            }
        });
        navigationPanel.add(nextBtn);
        return navigationPanel;
    }
    private void initializeLVHVTable(){
        LV_HV_Table.setValueAt("Parameter" , 0 , 0);
        LV_HV_Table.setValueAt("LV" , 0 , 1);
        LV_HV_Table.setValueAt("HV" , 0 , 2);
        LV_HV_Table.setValueAt("Rated Voltage" , 1 , 0);
        LV_HV_Table.setValueAt("Rated Current" , 2 , 0);
        LV_HV_Table.setValueAt("Cross Section sqmm" , 3 , 0);
        LV_HV_Table.setValueAt("Current density" , 4 , 0);
        LV_HV_Table.setValueAt("Turns/Limb" , 5 , 0);
        LV_HV_Table.setValueAt("Turns/Layer" , 6 , 0);
        LV_HV_Table.setValueAt("wdg lg-imp calc" , 7 , 0);
        LV_HV_Table.setValueAt("Wind Length(AXL)" , 8 , 0);
        LV_HV_Table.setValueAt("Limb length" , 9 , 0);
        LV_HV_Table.setValueAt("Wind-radial depth"  , 10 , 0);
        LV_HV_Table.setValueAt("Turn length" , 11 , 0);
        LV_HV_Table.setValueAt("Wire Length" , 12 , 0);
        LV_HV_Table.setValueAt("Resistance (ohms)" , 13 , 0);
        LV_HV_Table.setValueAt("Stray Loss (%)" , 14 , 0);
        LV_HV_Table.setValueAt("Wire Length" , 15 , 0);
        LV_HV_Table.setValueAt("Load Loss (Watts)" , 16 , 0);
        LV_HV_Table.setValueAt("S.a-m(wdg)" , 17 , 0);

        wireDetailTable.setValueAt("Parameter" , 0 ,0);
        wireDetailTable.setValueAt("LV 1" , 0 ,1);
        wireDetailTable.setValueAt("LV 2" , 0 ,2);
        wireDetailTable.setValueAt("HV 1" , 0 ,3);
        wireDetailTable.setValueAt("HV 2" , 0 ,4);
        wireDetailTable.setValueAt("Wire bare" , 1 , 0);
        wireDetailTable.setValueAt("Wire insulated" , 2 , 0);
        coreWeightTable.setValueAt("Conductor in KG" , 0 , 0);
    }

    private void initializeCoreWDGTable(){
        coreWdgTable.setValueAt("Parameter" , 0 , 0);
        coreWdgTable.setValueAt("W" , 0 , 1);
        coreWdgTable.setValueAt("D" , 0 , 2);
        //parameter names
        coreWdgTable.setValueAt("Core" , 1 , 0);
        coreWdgTable.setValueAt("Limb Plate" , 2 , 0);
        coreWdgTable.setValueAt("CORE''" , 3 , 0);
        coreWdgTable.setValueAt("gap/bobin" , 4 , 0);
        coreWdgTable.setValueAt("ID(1)" , 5 , 0);
        coreWdgTable.setValueAt("LV wdg" , 6 , 0);
        coreWdgTable.setValueAt("OD(1)" , 7, 0);
        coreWdgTable.setValueAt("δ" , 8 , 0);
        coreWdgTable.setValueAt("ID(2)" , 9 , 0);
        coreWdgTable.setValueAt("HV wdg" , 10 , 0);
        coreWdgTable.setValueAt("OD(2)" , 11 , 0);
        coreWdgTable.setValueAt("am" , 12 , 0);
        coreWdgTable.setAlignmentX(CENTER_ALIGNMENT);
    }
}
