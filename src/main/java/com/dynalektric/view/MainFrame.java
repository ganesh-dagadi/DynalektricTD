package com.dynalektric.view;

import com.dynalektric.constants.DisplayConstant;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    JPanel contentPanel = new JPanel(new CardLayout());
    public void init(){
        setTitle(DisplayConstant.APP_TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
