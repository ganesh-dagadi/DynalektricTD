package com.dynalektric.view.components;

import com.dynalektric.constants.StyleConstants;

import javax.swing.*;

public class MenuItem extends JMenuItem {
    public MenuItem(String text) {
        super(text);
        this.applyStyle();
    }
    private void applyStyle() {
//        this.setBackground(StyleConstants.BACKGROUND_PRIMARY);
//        this.setForeground(StyleConstants.FOREGROUND_PRIMARY);
        this.setFont(StyleConstants.SMALL_TEXT);
        this.setBorder(BorderFactory.createEmptyBorder(3 , 20 , 3 , 20));
    }
}
