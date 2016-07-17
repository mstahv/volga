package org.vaadin.volga.example;

import com.vaadin.navigator.ViewChangeListener;
import org.vaadin.viritin.label.RichText;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.volga.VolgaView;

public class SecondView extends MVerticalLayout implements VolgaView {

    public SecondView() {
        add(new RichText().withMarkDown("# Second view \n \n This is a second view that Google Bot will hopefully index as well. We are using link with 'hashbang' style to let google bot figure out it crawl to this page. "));
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
        return "http://v4.tahvonen.fi/boat2.png";
    }

    @Override
    public String getSeoDescription() {
        return "SEO Description: " + getClass().getSimpleName();
    }
}
