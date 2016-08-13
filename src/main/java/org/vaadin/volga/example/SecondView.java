package org.vaadin.volga.example;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import org.vaadin.viritin.label.RichText;
import org.vaadin.viritin.layouts.MVerticalLayout;

class SecondView extends MVerticalLayout implements View {

    private Label pathParam = new Label();

    public SecondView() {
        add(new RichText().withMarkDownResource("/secondview.md"), pathParam);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        pathParam.setValue(event.getParameters());
    }
}
