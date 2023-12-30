package com.dynalektric.view.modals;

import com.dynalektric.constants.DisplayConstant;
import com.dynalektric.constants.StyleConstants;
import com.dynalektric.view.workViews.AbstractWorkView;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class OpenProjectModal extends AbstractModal{
    Set<String>projectNames;
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
        JPanel resultPanel = new JPanel();
        JPanel mainPanel = new JPanel();
        BoxLayout layout = new BoxLayout(mainPanel , BoxLayout.Y_AXIS);
        BoxLayout layout1 = new BoxLayout(resultPanel , BoxLayout.Y_AXIS);
        resultPanel.setLayout(layout1);
        mainPanel.setLayout(layout);
        JLabel heading  = new JLabel(DisplayConstant.OPEN_PROJECT);
        heading.setFont(StyleConstants.HEADING_SUB1);
        mainPanel.add(heading);

        for(String project : this.projectNames) {
            resultPanel.add(Box.createVerticalStrut(15));
            System.out.println(project);
            resultPanel.add(createCardForProject(project));
            resultPanel.add(Box.createVerticalStrut(15));
        }
        JScrollPane scrollPane = new JScrollPane(resultPanel);
        mainPanel.add(scrollPane);
        this.setContentPane(mainPanel);
        this.setSize(600 , 600);
        this.setVisible(true);
    }
    private JPanel createCardForProject(String project){
        JPanel cardContent = new JPanel(new FlowLayout(FlowLayout.LEADING));
        cardContent.setBackground(StyleConstants.BACKGROUND_SECONDARY);
        JButton OpenProjectButton = new JButton(project);
        OpenProjectButton.setFont(StyleConstants.NEW_PROJECT);
        OpenProjectButton.setBackground(StyleConstants.BACKGROUND_SECONDARY);
        OpenProjectButton.setPreferredSize(new Dimension(540 , OpenProjectButton.getPreferredSize().height));
        OpenProjectButton.setHorizontalAlignment(SwingConstants.LEFT);
        cardContent.add(OpenProjectButton);
        cardContent.setMaximumSize(new Dimension(550 , cardContent.getPreferredSize().height));
        cardContent.setAlignmentX(LEFT_ALIGNMENT);
        return cardContent;
    }

}
