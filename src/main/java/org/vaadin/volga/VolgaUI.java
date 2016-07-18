package org.vaadin.volga;

import com.github.wolfie.history.HistoryExtension;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

public abstract class VolgaUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        HistoryExtension.configurePushStateEnabledNavigator(this, getContainer());
        doInit(request);
    }

    protected abstract void doInit(VaadinRequest request);

    protected abstract ComponentContainer getContainer();
}
