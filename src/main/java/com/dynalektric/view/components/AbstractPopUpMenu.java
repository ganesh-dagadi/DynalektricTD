package com.dynalektric.view.components;

import com.dynalektric.constants.StyleConstants;
import com.dynalektric.view.ChildFrameListener;

import javax.swing.*;

public abstract class AbstractPopUpMenu extends JPopupMenu {
    protected ChildFrameListener parent;
    protected AbstractPopUpMenu(ChildFrameListener parent) {
        super();
        this.parent = parent;
        this.initPopUpMenu();
    }

    private void initPopUpMenu() {
//        this.setBackground(StyleConstants.BACKGROUND_PRIMARY);
//        this.setForeground(StyleConstants.FOREGROUND_PRIMARY);
        this.setFont(StyleConstants.NORMAL_TEXT);
        this.setBorder(BorderFactory.createEmptyBorder(5 , 10 , 5 ,10));
        this.setBorder(BorderFactory.createEmptyBorder());

    }
}
