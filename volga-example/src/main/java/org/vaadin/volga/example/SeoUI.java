package org.vaadin.volga.example;

import com.github.wolfie.history.PushStateLink;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.ComponentContainer;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.volga.VolgaUI;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("valo")
@Title("SEO test: basic title")
@Widgetset("AppWidgetset")
public class SeoUI extends VolgaUI {

    private MVerticalLayout layout = new MVerticalLayout();

    @Override
    protected void doInit(VaadinRequest vaadinRequest) {
        MVerticalLayout main = new MVerticalLayout(new Menu(), layout);
        setContent(main);
    }

    @Override
    protected ComponentContainer getContainer() {
        return layout;
    }

    class Menu extends MHorizontalLayout {

        public Menu() {
            addView(MainView.class, "");
            addView(SecondView.class, "second");
        }

        private void addView(Class<? extends View> view, String path) {
            PushStateLink link = new PushStateLink(view.getSimpleName(), path);
            add(link);
            getNavigator().addView(path, view);
        }
    }
}
