package com.dynalektric.view.modals;

import com.dynalektric.constants.ViewMessages;
import com.dynalektric.view.ChildFrameListener;
import com.dynalektric.view.ViewMessage;
import com.dynalektric.view.components.AbstractPopUpMenu;
import com.dynalektric.view.components.MenuItem;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProjectPopUpMenu extends AbstractPopUpMenu {
    private String projectName;
    public ProjectPopUpMenu(String projectName , ChildFrameListener parent) {
        super(parent);
        this.projectName = projectName;
        this.initMenuItem();
    }

    private void initMenuItem() {
        JMenuItem openItem = new MenuItem("Open");
        openItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                parent.captureEventFromChildSubFrame(new ViewMessage(ViewMessages.OPEN_SELECTED_PROJECT , projectName));
            }
        });

        JMenuItem deleteItem = new MenuItem("Delete");
        deleteItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int result = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to delete the project?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION
                );
                if (result == JOptionPane.YES_OPTION) {
                    parent.captureEventFromChildSubFrame(new ViewMessage(ViewMessages.DELETE_SELECTED_PROJECT , projectName));
                }
            }
        });
        this.add(openItem);
        this.add(deleteItem);
    }
}
