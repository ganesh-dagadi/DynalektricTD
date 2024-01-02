package com.dynalektric.view.workViews;

import com.dynalektric.constants.DisplayConstant;
import com.dynalektric.constants.StyleConstants;
import com.dynalektric.constants.ViewMessages;
import com.dynalektric.control.Control;
import com.dynalektric.model.Model;
import com.dynalektric.view.View;
import com.dynalektric.view.ViewMessage;
import com.dynalektric.view.components.MenuBar;
import com.dynalektric.view.components.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class InputWorkView extends AbstractWorkView{
    private final JPanel logoPanel = new JPanel();
    private final JPanel mainPanel = new JPanel(new BorderLayout());

    private final static Logger LOGGER = LogManager.getLogger(WelcomeWorkView.class);

    public final static String VIEW_NAME = "Input Work view";
    Control controller = new Control();
    public InputWorkView(Model model) {
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
        this.initializeLogoPanel();
        mainPanel.add(new MenuBar(this) , BorderLayout.NORTH);
        this.add(mainPanel,BorderLayout.CENTER);
        JPanel inputWorkViewPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        inputWorkViewPanel.setLayout(new GridLayout(0, 2));
        leftPanel.setLayout(new BoxLayout(leftPanel , BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel , BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.BLUE);
        JPanel mainLeftPanel = new JPanel();
        mainLeftPanel.setLayout(new GridLayout(2,0));
        JPanel inputPanel = new JPanel();
        JPanel dropDownPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0,2));
        dropDownPanel.setLayout(new GridLayout(0,2));
        JPanel inputLeftPanel = new JPanel();
        JPanel dropDownLeftPanel = new JPanel();
        JPanel dropDownRightPanel = new JPanel();
        dropDownLeftPanel.setLayout(new BoxLayout(dropDownLeftPanel,BoxLayout.Y_AXIS));
        dropDownRightPanel.setLayout(new BoxLayout(dropDownRightPanel,BoxLayout.Y_AXIS));
        inputLeftPanel.setLayout(new BoxLayout(inputLeftPanel,BoxLayout.Y_AXIS));
        JPanel inputRightPanel = new JPanel();
        inputRightPanel.setLayout(new BoxLayout(inputRightPanel,BoxLayout.Y_AXIS));
        JPanel defaultPanel = new JPanel();
        defaultPanel.setLayout(new GridLayout(0,2));
        JPanel defaultLeftPanel = new JPanel();
        JPanel defaultRightPanel = new JPanel();
        defaultLeftPanel.setLayout(new BoxLayout(defaultLeftPanel,BoxLayout.Y_AXIS));
        defaultRightPanel.setLayout(new BoxLayout(defaultRightPanel,BoxLayout.Y_AXIS));
        defaultLeftPanel.setBackground(Color.GRAY);
        defaultRightPanel.setBackground(Color.green);
        dropDownLeftPanel.setBackground(Color.white);
        dropDownRightPanel.setBackground(Color.black);
        inputRightPanel.setBackground(Color.RED);
        inputLeftPanel.setBackground(Color.CYAN);
        inputPanel.add(inputLeftPanel);
        inputPanel.add(inputRightPanel);
        dropDownPanel.add(dropDownLeftPanel);
        dropDownPanel.add(dropDownRightPanel);
        rightPanel.add(defaultPanel);
        defaultPanel.add(defaultLeftPanel);
        defaultPanel.add(defaultRightPanel);
        mainLeftPanel.add(inputPanel);
        mainLeftPanel.add(dropDownPanel);
        leftPanel.add(mainLeftPanel);
        inputWorkViewPanel.add(leftPanel);
        inputWorkViewPanel.add(rightPanel);
        mainPanel.add(inputWorkViewPanel);

    }

    private void initializeLogoPanel(){

        logoPanel.setLayout(new FlowLayout(FlowLayout.LEADING));

        logoPanel.setPreferredSize(new Dimension(
                (int) View.SCREEN_DIMENSION.getWidth(),
                (int)(View.SCREEN_DIMENSION.getHeight()*0.1)));
        this.add(logoPanel,BorderLayout.NORTH);
        try{
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

            logoPanel.add(Box.createHorizontalStrut((int)(this.getPreferredSize().getWidth()*0.1)));
            logoPanel.add(nhceIconLabel);
            logoPanel.add(Box.createHorizontalStrut((int)(this.getPreferredSize().getWidth()*0.6)));
            logoPanel.add(dynaIconLabel);
            logoPanel.add(Box.createHorizontalStrut((int)(this.getPreferredSize().getWidth()*0.02)));
        }
        catch(IOException e){
            LOGGER.error(e.getMessage() ,e);
        }

    }
    @Override
    public void captureEventFromChildSubFrame(ViewMessage message) {
        switch (message.getMsgType()) {
            case ViewMessages.CLOSE_OPENED_PROJECT:
                controller.closeOpenedProject();
                break;
        }
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }

    @Override
    public Integer getViewId() {
        return 2;
    }

}
