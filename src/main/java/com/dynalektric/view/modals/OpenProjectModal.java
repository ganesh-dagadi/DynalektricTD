package com.dynalektric.view.modals;

import com.dynalektric.constants.DisplayConstant;
import com.dynalektric.constants.StyleConstants;
import com.dynalektric.constants.ViewMessages;
import com.dynalektric.view.ChildFrameListener;
import com.dynalektric.view.ViewMessage;
import com.dynalektric.view.workViews.AbstractWorkView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class OpenProjectModal extends AbstractModal implements ChildFrameListener {
    List<String>projectNames;
    private final OpenProjectModal thisReference = this;
        public OpenProjectModal(AbstractWorkView parentView, List<String>projectNames){
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
        JButton openProjectButton = new JButton(project);
        openProjectButton.setFont(StyleConstants.NEW_PROJECT);
        openProjectButton.setBackground(StyleConstants.BACKGROUND_SECONDARY);
        openProjectButton.setPreferredSize(new Dimension(540 , openProjectButton.getPreferredSize().height));
        openProjectButton.setHorizontalAlignment(SwingConstants.LEFT);
        openProjectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1) {
                    dispose();
                    parentView.captureEventFromChildSubFrame(new ViewMessage(ViewMessages.OPEN_SELECTED_PROJECT , project));
                }else {
                    ProjectPopUpMenu popMenu = new ProjectPopUpMenu(project , thisReference);
                    popMenu.show(e.getComponent() , e.getX() , e.getY());
                }
            }
        });
        cardContent.add(openProjectButton);
        cardContent.setMaximumSize(new Dimension(550 , cardContent.getPreferredSize().height));
        cardContent.setAlignmentX(LEFT_ALIGNMENT);
        return cardContent;
    }

    @Override
    public void captureEventFromChildSubFrame(ViewMessage message) {
        switch(message.getMsgType()) {
            case ViewMessages.DELETE_SELECTED_PROJECT:
                dispose();
                parentView.captureEventFromChildSubFrame(message);
                break;
            case ViewMessages.OPEN_SELECTED_PROJECT:
                dispose();
                parentView.captureEventFromChildSubFrame(message);
        }
    }
}
