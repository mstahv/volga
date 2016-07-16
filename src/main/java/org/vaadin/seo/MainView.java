package org.vaadin.seo;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import org.vaadin.viritin.label.RichText;
import org.vaadin.viritin.layouts.MVerticalLayout;

public class MainView extends MVerticalLayout implements View {

    public MainView() {
        add(new RichText().withMarkDown("# Main View"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {  }
}