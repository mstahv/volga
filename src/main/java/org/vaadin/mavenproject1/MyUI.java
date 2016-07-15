package org.vaadin.mavenproject1;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Link;
import com.vaadin.ui.UI;
import org.vaadin.viritin.label.Header;
import org.vaadin.viritin.label.RichText;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

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
public class MyUI extends UI {

    private Navigator navigator;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        MVerticalLayout layout = new MVerticalLayout(new Header("Test apps for something related to SEO"));
        navigator = new Navigator(this, layout);

        JavaScript.eval("var head = document.getElementsByTagName(\"head\")[0];\n"
                + // Twitter CANNOT parse dynamically added meta tags :-(
                "var el = document.createElement(\"meta\");\n"
                + "el.name = \"twitter:description\";\n"
                + "el.content = \"This is test description\";\n"
                + "head.appendChild(el);\n"
                + "\n"
                + "el = document.createElement(\"meta\");\n"
                + "el.name = \"twitter:card\";\n"
                + "el.content = \"summary\";\n"
                + "head.appendChild(el);\n"
                + "\n"
                + "el = document.createElement(\"meta\");\n"
                + "el.name = \"twitter:site\";\n"
                + "el.content = \"@nicolas_frankel\";\n"
                + "head.appendChild(el);\n"
                + "\n"
                + "el = document.createElement(\"meta\");\n"
                + "el.name = \"twitter:title\";\n"
                + "el.content = \"A Java geek\";\n"
                + "head.appendChild(el);\n"
                + "\n"
                + "el = document.createElement(\"meta\");\n"
                + "el.name = \"twitter:image\";\n"
                + "el.content = \"http://v4.tahvonen.fi/boat.png\";\n"
                + "head.appendChild(el);"
                + "el = document.createElement(\"meta\");\n"
                + // Facebook CAN parse dynamically added stuff
                "el.name = \"og:image\";\n"
                + "el.content = \"http://v4.tahvonen.fi/boat.png\";\n"
                + "head.appendChild(el);\n"
                + "\n"
                + "el = document.createElement(\"meta\");\n"
                + "el.name = \"og:url\";\n"
                + "el.content = \"http://v4.tahvonen.fi/seo-test/\";\n"
                + "head.appendChild(el);\n"
                + "\n"
                + "el = document.createElement(\"meta\");\n"
                + "el.name = \"og:title\";\n"
                + "el.content = \"Vaadin OG test\";\n"
                + "head.appendChild(el);");

        MVerticalLayout main = new MVerticalLayout(new Menu(), layout);
        setContent(main);
    }

    class Menu extends MHorizontalLayout {

        public Menu() {
            addView("Main view", "", MainView.class);
            addView("Second view", "second", SecondView.class);
        }

        private void addView(String name, String id, Class clazz) {
            Link link = new Link(name, new ExternalResource("#!" + id));
            add(link);
            navigator.addView(id, clazz);
        }

    }

    public  static class MainView extends MVerticalLayout implements View {

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
