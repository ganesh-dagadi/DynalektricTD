package com.dynalektric.view.components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class SpinnerCellRenderer extends DefaultTableCellRenderer {
    private final JSpinner sp =new JSpinner();
    Set<Point> rowsWithSpinner = new HashSet<>();
    public SpinnerCellRenderer() {
        sp.setBorder(null);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column){
        if(rowsWithSpinner.contains(new Point(row , column))){
            sp.setValue(value);
            return sp;
        }else{
            return super.getTableCellRendererComponent(table , value , isSelected , hasFocus , row , column);
        }
    }

    public void addSpinnerToCell(int row , int col){
        rowsWithSpinner.add(new Point(row , col));
    }

}
