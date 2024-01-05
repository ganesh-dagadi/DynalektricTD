package com.dynalektric.view;

import com.dynalektric.control.Control;
import com.dynalektric.control.WelcomeWorkViewController;
import com.dynalektric.model.Model;
import com.dynalektric.view.workViews.AbstractWorkView;
import com.dynalektric.view.workViews.InputWorkView;
import com.dynalektric.view.workViews.OutputOneWorkView;
import com.dynalektric.view.workViews.WelcomeWorkView;
import com.dynalektric.view.workViews.OutputTwoWorkView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;


public class View{
    private final static Logger LOGGER = LogManager.getLogger(View.class);
    private static View view;
    private final MainFrame mainFrame = new MainFrame();
    private final MainPanel mainPanel = new MainPanel(new BorderLayout());
    public Map<String , AbstractWorkView> loadedViews = new HashMap<>();

    public final static Dimension SCREEN_DIMENSION = new Dimension(
            (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
            (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()
        );
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
        view.loadWorkViews();
        view.initializeUI();
    }

    public void setView (AbstractWorkView view){
        model.setLiveView(view);
        View.getSingleton().mainPanel.displayWorkView(view.getViewName());
    }

    public void setView(String viewName){
        this.setView(loadedViews.get(viewName));
    }

    public void chooseWorkView(){
        String loadedProject = model.getGeneralRepo().getLoadedProjectName();
        if(loadedProject == null){
            view.setView(view.loadedViews.get(WelcomeWorkView.VIEW_NAME));
        }
        else{
           new WelcomeWorkViewController().openProjectWithName(loadedProject);
           view.setView(view.loadedViews.get(InputWorkView.VIEW_NAME));
        }
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

    private void loadWorkViews(){
        WelcomeWorkView welcomeView = new WelcomeWorkView(model);
        InputWorkView inputView = new InputWorkView(model);
        OutputOneWorkView outputOneWorkView = new OutputOneWorkView(model);
        OutputTwoWorkView outputTwoWorkView = new OutputTwoWorkView(model);
        view.loadedViews.put(outputOneWorkView.getViewName() , outputOneWorkView);
        view.loadedViews.put(welcomeView.getViewName() ,welcomeView);
        view.loadedViews.put(inputView.getViewName(),inputView);
        view.loadedViews.put(outputTwoWorkView.getViewName(),outputTwoWorkView);
        mainPanel.loadWorkView(welcomeView);
        mainPanel.loadWorkView(inputView);
        mainPanel.loadWorkView(outputOneWorkView);
        mainPanel.loadWorkView(outputTwoWorkView);
    }

    private void initializeUI(){
        try{
            mainFrame.setContentPane(new JPanel(new BorderLayout()));
            mainFrame.getContentPane().add(mainPanel , BorderLayout.CENTER);
            mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            mainFrame.setSize((int)(0.95 * View.SCREEN_DIMENSION.width) , (int)(0.95 * View.SCREEN_DIMENSION.height));
            mainFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    new Control().closeOpenedProject();
                }
            });
        } catch (Exception e){
            System.exit(1);
        }
    }
}
