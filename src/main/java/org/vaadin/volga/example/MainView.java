package org.vaadin.volga.example;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import org.vaadin.viritin.label.Header;
import org.vaadin.viritin.label.RichText;
import org.vaadin.viritin.layouts.MVerticalLayout;

public class MainView extends MVerticalLayout implements View {

    public MainView() {
        add(
                // Header is a component from Viritin that uses real H1 tags
                new Header("Main View"),
                // RichText is a component with html validation, it also supports
                // Markdown for easy creation of well structured HTML, which is
                // relevant for SEO
                new RichText().withMarkDownResource("/mainview.md")
        );
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}
