package org.vaadin.volga.example;

import com.vaadin.navigator.ViewChangeListener;
import org.vaadin.viritin.label.Header;
import org.vaadin.viritin.label.RichText;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.volga.VolgaView;

public class MainView extends MVerticalLayout implements VolgaView {

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

    @Override
    public String getSeoTitle() {
        return "SEO Title: " + getClass().getSimpleName();
    }

    @Override
    public String getSeoImage() {
        return "http://v4.tahvonen.fi/boat.png";
    }

    @Override
    public String getSeoDescription() {
        return "SEO Description: " + getClass().getSimpleName();
    }
}
