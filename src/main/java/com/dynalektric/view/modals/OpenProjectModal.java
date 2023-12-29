package com.dynalektric.view.modals;

import com.dynalektric.constants.DisplayConstant;
import com.dynalektric.constants.StyleConstants;
import com.dynalektric.view.workViews.AbstractWorkView;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class OpenProjectModal extends AbstractModal{
    Set<String >projectNames;
    public OpenProjectModal(AbstractWorkView parentView, Set<String>projectNames){
        super(parentView, DisplayConstant.OPEN_PROJECT);
        this.parentView = parentView;
        this.projectNames = projectNames;
    }

    @Override
    public void init() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initializeUI();
            }
        });
    }

    private void initializeUI(){

        JPanel mainPanel = new JPanel();
        BoxLayout layout = new BoxLayout(mainPanel , BoxLayout.Y_AXIS);
        mainPanel.setLayout(layout);
        JLabel heading  = new JLabel(DisplayConstant.OPEN_PROJECT);
        heading.setFont(StyleConstants.HEADING_SUB1);
        mainPanel.add(heading);
        for(String project : this.projectNames) {
            System.out.println(project);
            mainPanel.add(createCardForProject(project));
            mainPanel.add(Box.createVerticalStrut(7));
            mainPanel.add(Box.createHorizontalStrut(7));
        }
        this.setContentPane(mainPanel);
        this.setSize(600 , 600);
        this.setVisible(true);
    }
    private JPanel createCardForProject(String project){
        JPanel cardContent = new JPanel(new FlowLayout());
        cardContent.setBackground(StyleConstants.BACKGROUND_PRIMARY);
        cardContent.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JLabel openProjectLabel = new JLabel(project);
        openProjectLabel.setFont(StyleConstants.NORMAL_TEXT);
        openProjectLabel.setSize(new Dimension(350 , 50));
        cardContent.add(openProjectLabel);
        cardContent.setPreferredSize(new Dimension(350 , 50));
        return cardContent;
    }

}
