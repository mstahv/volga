package org.vaadin.volga.example;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import org.vaadin.viritin.label.RichText;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.volga.VolgaView;

public class SecondView extends MVerticalLayout implements VolgaView {
    
    Label pathParam = new Label();

    public SecondView() {
        add(new RichText().withMarkDownResource("/secondview.md"), pathParam);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        pathParam.setValue(event.getParameters());
    }


    @Override
    public String getSeoTitle() {
        return "SEO Title: " + getClass().getSimpleName();
    }

    @Override
    public String getSeoImage() {
        return "http://v4.tahvonen.fi/boat2.png";
    }

    @Override
    public String getSeoDescription() {
        return "SEO Description: " + getClass().getSimpleName();
    }
}
