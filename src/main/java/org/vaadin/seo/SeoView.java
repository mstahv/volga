package org.vaadin.seo;

import com.vaadin.navigator.View;

public interface SeoView extends View {

    String getSeoTitle();
    String getSeoImage();
    String getSeoDescription();
}
