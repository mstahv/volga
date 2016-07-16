package org.vaadin.seo;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import org.vaadin.viritin.label.RichText;
import org.vaadin.viritin.layouts.MVerticalLayout;

public class SecondView extends MVerticalLayout implements View {

    public SecondView() {
        add(new RichText().withMarkDown("# Second view \n \n This is a second view that Google Bot will hopefully index as well. We are using link with 'hashbang' style to let google bot figure out it crawl to this page. "));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
