package org.vaadin;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import org.vaadin.addonhelpers.AbstractTest;

public class BasicUsageUI extends AbstractTest {

    @Override
    public Component getTestComponent() {
        return new Label("Hello there");
    }

}
