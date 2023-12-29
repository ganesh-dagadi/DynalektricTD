package com.dynalektric.view.workViews;

import com.dynalektric.constants.Constant;
import com.dynalektric.constants.DisplayConstant;
import com.dynalektric.constants.StyleConstants;
import com.dynalektric.control.Control;
import com.dynalektric.control.WelcomeWorkViewController;
import com.dynalektric.model.Model;
import com.dynalektric.view.ChildFrameListener;
import com.dynalektric.view.View;
import com.dynalektric.view.ViewMessage;
import com.dynalektric.view.modals.AbstractModal;
import com.dynalektric.view.modals.NewProjectModal;
import com.dynalektric.view.modals.OpenProjectModal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Set;


public class WelcomeWorkView extends AbstractWorkView implements ChildFrameListener {

    private final JPanel logoPanel = new JPanel();
    private final WelcomeWorkViewController controller = new WelcomeWorkViewController();
    private final WelcomeWorkView thisReference = this;
    private final static Logger LOGGER = LogManager.getLogger(WelcomeWorkView.class);
    public WelcomeWorkView(Model model) {
        super(model);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initializeUI();
            }
        });
    }

    @Override
    public void update(String msg) {
        super.update(msg);
    }

    @Override
    public void update(String msg, Object data) {
        super.update(msg , data);
    }

    @Override
    public String getViewName() {
        return null;
    }

    @Override
    public Integer getViewId() {
        return null;
    }

    private void initializeUI(){
        this.setLayout(new BorderLayout());
        this.initializeLogoPanel();
        JPanel welcomeWorkViewMainPanel = new JPanel();
        welcomeWorkViewMainPanel.setLayout(new GridLayout(0, 2));

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        rightPanel.setLayout(new BoxLayout(rightPanel , BoxLayout.Y_AXIS));
        leftPanel.setLayout(new BoxLayout(leftPanel , BoxLayout.Y_AXIS));


        JLabel heading = new JLabel(DisplayConstant.APP_TITLE);
        JLabel recentProjects = new JLabel(DisplayConstant.RECENT_PROJECTS);

        JButton newProjectButton = new JButton(DisplayConstant.NEW_PROJECT);
        JButton openProjectButton = new JButton(DisplayConstant.OPEN_PROJECT);


        heading.setFont(StyleConstants.HEADING_MAIN);
        heading.setAlignmentX(CENTER_ALIGNMENT);
        newProjectButton.setFont(StyleConstants.NEW_PROJECT);
        newProjectButton.setAlignmentX(CENTER_ALIGNMENT);
        openProjectButton.setFont(StyleConstants.NEW_PROJECT);
        openProjectButton.setAlignmentX(CENTER_ALIGNMENT);
        recentProjects.setFont(StyleConstants.RECENT_PROJECTS);
        recentProjects.setAlignmentX(CENTER_ALIGNMENT);



        leftPanel.add(Box.createVerticalStrut(50));
        leftPanel.add(recentProjects);


        rightPanel.add(Box.createVerticalStrut(50));
        rightPanel.add(heading);
        rightPanel.add(Box.createVerticalStrut(30));
        rightPanel.add(newProjectButton);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(openProjectButton);

        welcomeWorkViewMainPanel.add(leftPanel);
        welcomeWorkViewMainPanel.add(rightPanel);

        welcomeWorkViewMainPanel.setBackground(new Color(0 , 0 , 0));

        this.add(welcomeWorkViewMainPanel ,BorderLayout.CENTER);

        newProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                AbstractModal newProject = new NewProjectModal(thisReference);
                newProject.init();
            }
        });

        openProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Set<String> projects = model.getGeneralRepo().getNamesOfAllProjectsCreated();
                AbstractModal openProject = new OpenProjectModal(thisReference, projects);
                openProject.init();
            }
        });
    }
    private void initializeLogoPanel(){
        logoPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
       
        logoPanel.setPreferredSize(new Dimension(
                (int)View.SCREEN_DIMENSION.getWidth(),
                (int)(View.SCREEN_DIMENSION.getHeight()*0.1)));
        this.add(logoPanel,BorderLayout.NORTH);
        try{
            System.out.println(this.getPreferredSize());
            BufferedImage nhceLogoImage = ImageIO.read(this.getClass().getResource("NHCE.jpg"));
            ImageIcon nhceLogoIcon = new ImageIcon(nhceLogoImage);
            Image nhceScaledLogoImage = nhceLogoIcon.getImage().getScaledInstance((int)(this.getPreferredSize().getWidth()*0.15), -1, Image.SCALE_SMOOTH);
            ImageIcon nhceScaledLogoIcon = new ImageIcon(nhceScaledLogoImage);
            JLabel nhceIconLabel = new JLabel(nhceScaledLogoIcon);

            BufferedImage dynaLogoImage = ImageIO.read(this.getClass().getResource("DYNA.jpg"));
            ImageIcon dynaLogoIcon = new ImageIcon(dynaLogoImage);
            Image dynaScaledLogoImage = dynaLogoIcon.getImage().getScaledInstance((int)(this.getPreferredSize().getWidth()*0.04), -1, Image.SCALE_SMOOTH);
            ImageIcon dynaScaledLogoIcon = new ImageIcon(dynaScaledLogoImage);
            JLabel dynaIconLabel = new JLabel(dynaScaledLogoIcon);

            logoPanel.add(Box.createHorizontalStrut((int)(this.getPreferredSize().getWidth()*0.02)));
            logoPanel.add(nhceIconLabel);
            logoPanel.add(Box.createHorizontalStrut((int)(this.getPreferredSize().getWidth()*0.75)));
            logoPanel.add(dynaIconLabel);
            logoPanel.add(Box.createHorizontalStrut((int)(this.getPreferredSize().getWidth()*0.02)));

        }
        catch(IOException e){
            LOGGER.error(e.getMessage() ,e);
        }

    }

    @Override
    public void captureEventFromChildSubFrame(ViewMessage message){
        switch(message.getMsgType()) {
            case Constant.ViewMessages.NEW_PROJECT_NAME:
                LOGGER.info("Creating new project {} ", message.getMsgData());
                controller.createNewProject((String) message.getMsgData());
                System.out.println(message.getMsgData());
                break;
        }
    }
}
