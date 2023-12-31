package com.dynalektric.view.workViews;

import com.dynalektric.constants.ViewMessages;
import com.dynalektric.control.Control;
import com.dynalektric.model.Model;
import com.dynalektric.view.ViewMessage;
import com.dynalektric.view.components.MenuBar;
import com.dynalektric.view.components.MenuItem;

import javax.swing.*;
import java.awt.*;

public class InputWorkView extends AbstractWorkView{
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
        this.add(new MenuBar(this) , BorderLayout.NORTH);
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
