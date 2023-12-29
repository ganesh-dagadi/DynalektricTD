package com.dynalektric.view;

import com.dynalektric.model.Model;
import com.dynalektric.view.workViews.AbstractWorkView;
import com.dynalektric.view.workViews.WelcomeWorkView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class View{
    private final static Logger LOGGER = LogManager.getLogger(View.class);
    private static View view;
    private MainFrame mainFrame;
    private MainPanel mainPanel;
    Model model = Model.getSingleton();
    private View(){

    }
    public static View getSingleton(){
        if(view == null){
            view = new View();
        }
        return view;
    }

    public void initView(){
        LOGGER.info("Initializing the UI");
        this.initializeUI();
    }

    public void setView (AbstractWorkView view){
        model.setLiveView(view);
        this.mainPanel.displayWorkView(view.getViewName());
    }

    public void chooseWorkView(){
        this.setView(new WelcomeWorkView(model));
    }

    public void startApp(){
        try{
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    mainFrame.setVisible(true);
                }
            });
        }catch(Exception e){
            System.exit(1);
        }
    }

    private void initializeUI(){
        try{
            mainFrame = new MainFrame();
            mainPanel = new MainPanel(new BorderLayout());
            mainPanel.loadWorkView(new WelcomeWorkView(model));
            mainFrame.setContentPane(new JPanel(new BorderLayout()));
            mainFrame.getContentPane().add(mainPanel , BorderLayout.CENTER);
            mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            mainFrame.setSize(400 , 400);
            mainFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
//                    HibernateHelper.closeSessionFactory();
                    System.out.println("Closing app");
                }
            });
        } catch (Exception e){
            System.exit(1);
        }
    }
}
