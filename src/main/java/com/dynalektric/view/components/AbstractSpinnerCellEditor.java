package com.dynalektric.view.components;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractSpinnerCellEditor extends DefaultCellEditor {
    private final JSpinner sp = new JSpinner(new SpinnerNumberModel(10.0, 0.0 ,1000.0 , 0.5));
    Set<Point> rowsWithSpinner = new HashSet<>();
    public AbstractSpinnerCellEditor(){
        super(new JTextField());
        sp.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                handleSpinnerValueChange();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object
            value, boolean isSelected, int row, int column)
    {
        if(rowsWithSpinner.contains(new Point(row , column))){
            sp.setValue(value);
            return sp;
        }else{
            return super.getTableCellEditorComponent(table , value , isSelected , row , column);
        }

    }
    // Returns the current value of the spinners
    @Override
    public Object getCellEditorValue() {
        return sp.getValue();
    }

    public void addSpinnerToCell(int row ,int col){
        Point cell = new Point(row ,col);
        rowsWithSpinner.add(cell);
    }

    abstract protected void handleSpinnerValueChange();

}
