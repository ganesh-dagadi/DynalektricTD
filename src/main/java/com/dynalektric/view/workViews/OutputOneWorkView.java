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
    private final JTable LV_HV_Table = new JTable(10  , 3);
    private final JTable coreWdgTable = new JTable(10 , 3);
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

    @Override
    public void refreshUI() {
        this.initializeCoreWDGTable();
        this.initializeLVHVTable();
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
        this.LV_HVPanel.add(LV_HVHeading);
        this.LV_HVPanel.add(Box.createVerticalStrut(10));
        this.LV_HVPanel.add(LV_HV_Table);
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
        navigationPanel.add(nextBtn);
        return navigationPanel;
    }
    private void initializeLVHVTable(){
        LV_HV_Table.setValueAt("Parameter" , 0 , 0);
        LV_HV_Table.setValueAt("LV" , 0 , 1);
        LV_HV_Table.setValueAt("HV" , 0 , 2);
    }

    private void initializeCoreWDGTable(){
        coreWdgTable.setValueAt("Parameter" , 0 , 0);
        coreWdgTable.setValueAt("W" , 0 , 1);
        coreWdgTable.setValueAt("D" , 0 , 2);
        coreWdgTable.setAlignmentX(CENTER_ALIGNMENT);
    }
}
