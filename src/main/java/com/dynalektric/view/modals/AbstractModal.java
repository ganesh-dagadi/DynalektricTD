package com.dynalektric.view.modals;

import com.dynalektric.view.workViews.AbstractWorkView;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractModal extends JDialog {
        AbstractWorkView parentView;
        protected AbstractModal(AbstractWorkView parentView,String title){
            super(SwingUtilities.windowForComponent(parentView),title, ModalityType.DOCUMENT_MODAL);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }

        public abstract void init();
}
