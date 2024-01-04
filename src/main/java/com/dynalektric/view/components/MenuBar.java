package com.dynalektric.view.components;

import com.dynalektric.constants.DisplayConstant;
import com.dynalektric.constants.ViewMessages;
import com.dynalektric.view.ChildFrameListener;
import com.dynalektric.view.ViewMessage;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuBar extends JMenuBar{
    private ChildFrameListener parent;
    public MenuBar(ChildFrameListener parent) {
        super();
        this.parent = parent;
        this.createMenus();
        this.setStyling();
    }

    private void createMenus() {
        JMenu projectMenu = new JMenu(DisplayConstant.MENU_BAR_PROJECT);
        JMenu viewMenu = new JMenu(DisplayConstant.MENU_BAR_VIEW);
        JMenu windowMenu = new JMenu(DisplayConstant.MENU_BAR_WINDOW);
        JMenu helpMenu = new JMenu(DisplayConstant.MENU_BAR_HELP);

        //project Menu
        MenuItem closeProject = new MenuItem(DisplayConstant.MENU_ITEM_CLOSE_PROJECT);
        MenuItem saveProject =new MenuItem(DisplayConstant.MENU_ITEM_SAVE_PROJECT);
        closeProject.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                parent.captureEventFromChildSubFrame(new ViewMessage(ViewMessages.CLOSE_OPENED_PROJECT , null));
            }
        });
        saveProject.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                parent.captureEventFromChildSubFrame(new ViewMessage(ViewMessages.SAVE_PROJECT , null));
            }
        });
        projectMenu.add(closeProject);
        projectMenu.add(saveProject);
        this.add(projectMenu);
        this.add(viewMenu);
        this.add(windowMenu);
        this.add(helpMenu);
    }

    private void setStyling() {
//        this.setBackground(StyleConstants.BACKGROUND_TERTIARY);
//        this.setForeground(StyleConstants.FOREGROUND_SECONDARY);
        this.setBorder(BorderFactory.createEmptyBorder(3 , 10 , 3 , 10));
    }
}
