package org.vaadin.seo;

import com.github.wolfie.history.HistoryExtension;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.*;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Component;
import com.vaadin.ui.Link;
import com.vaadin.ui.UI;
import org.vaadin.viritin.label.Header;
import org.vaadin.viritin.label.RichText;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.HashMap;
import java.util.Map;

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
@JavaScript("seo.js")
public class SeoUI extends UI implements ViewDisplay {

    private static final Map<Class<? extends View>, String> VIEW_CLASSES_TO_ID = new HashMap<>();

    MVerticalLayout layout = new MVerticalLayout();

    static {
        VIEW_CLASSES_TO_ID.put(MainView.class, "");
        VIEW_CLASSES_TO_ID.put(SecondView.class, "/second");
    }

    private Navigator navigator;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        setupNavigation();
        layout();
    }

    private void setupNavigation() {
        HistoryExtension history = new HistoryExtension();
        history.extend(this);
        String contextPath = VaadinServlet.getCurrent().getServletContext().getContextPath();
        NavigationStateManager pushStateManager = history.createNavigationStateManager(contextPath);
        navigator = new Navigator(this, pushStateManager, this);
    }

    private void layout() {
        MVerticalLayout main = new MVerticalLayout(new Menu(), layout);
        setContent(main);
    }

    @Override
    public void showView(View view) {
        layout.removeAllComponents();
        layout.addComponent((Component) view);
    }

    class Menu extends MHorizontalLayout {

        public Menu() {
            addView("Main view", MainView.class);
            addView("Second view", SecondView.class);
        }

        private void addView(String name, Class<? extends View> clazz) {
            String id = VIEW_CLASSES_TO_ID.get(clazz);
            Link link = new Link(name, new ExternalResource(id));
            add(link);
            navigator.addView(id, clazz);
        }

    }

    public static class MainView extends MVerticalLayout implements View {

        @Override
        public void enter(ViewChangeListener.ViewChangeEvent event) {
        }

        public MainView() {
            add(new RichText().withMarkDown("# Main View"));
        }

    }

    public static class SecondView extends MVerticalLayout implements View {

        @Override
        public void enter(ViewChangeListener.ViewChangeEvent event) {
        }

        public SecondView() {
            add(new RichText().withMarkDown("# Second view \n \n This is a second view that Google Bot will hopefully index as well. We are using link with 'hashbang' style to let google bot figure out it crawl to this page. "));
        }

    }

}
