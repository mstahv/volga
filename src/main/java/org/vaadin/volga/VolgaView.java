package org.vaadin.volga;

import com.vaadin.navigator.View;

public interface VolgaView extends View {

    String getSeoTitle();
    String getSeoImage();
    String getSeoDescription();
}
