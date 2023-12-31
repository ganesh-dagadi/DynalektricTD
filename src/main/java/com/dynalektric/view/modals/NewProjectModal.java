package com.dynalektric.view.modals;

import com.dynalektric.constants.DisplayConstant;
import com.dynalektric.constants.StyleConstants;
import com.dynalektric.constants.ViewMessages;
import com.dynalektric.view.ViewMessage;
import com.dynalektric.view.workViews.AbstractWorkView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewProjectModal extends AbstractModal{
        public NewProjectModal(AbstractWorkView parentView){
            super(parentView, DisplayConstant.NEW_PROJECT);
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

    private void initializeUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel contentPanel = new JPanel();
        BoxLayout layout = new BoxLayout(contentPanel , BoxLayout.Y_AXIS);
        contentPanel.setLayout(layout);
        contentPanel.setBackground(Color.white);
        //heading
        JLabel heading = new JLabel(DisplayConstant.NEW_PROJECT);
        heading.setFont(StyleConstants.HEADING_SUB1);
        heading.setForeground(StyleConstants.FOREGROUND_PRIMARY);
        //session name
        JLabel sessionNameLabel = new JLabel(DisplayConstant.NEW_PROJECT_NAME);
        sessionNameLabel.setFont(StyleConstants.NORMAL_TEXT);
        sessionNameLabel.setForeground(StyleConstants.FOREGROUND_PRIMARY);
        JTextField sessionNameTB = new JTextField();
        sessionNameTB.setMaximumSize(new Dimension(600 , 25));

        JButton submitButton = new JButton(DisplayConstant.CREATE_TEXT);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(heading);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(sessionNameLabel);
        contentPanel.add(Box.createVerticalStrut(5));
        contentPanel.add(sessionNameTB);
        contentPanel.add(Box.createVerticalStrut(25));
        contentPanel.add(submitButton);
        mainPanel.add(contentPanel , BorderLayout.CENTER);
        JPanel leftBorder = new JPanel();
        leftBorder.setSize(200 , 600);
        leftBorder.setBackground(Color.white);
        mainPanel.add(leftBorder , BorderLayout.WEST);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String projectName = sessionNameTB.getText();
                dispose();
                parentView.captureEventFromChildSubFrame(new ViewMessage(ViewMessages.NEW_PROJECT_NAME , projectName));

            }
        });
        this.setContentPane(mainPanel);
        this.setSize(600 , 600);
        this.setVisible(true);

    }
}
