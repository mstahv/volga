package org.vaadin.mavenproject1;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.BootstrapFragmentResponse;
import com.vaadin.server.BootstrapListener;
import com.vaadin.server.BootstrapPageResponse;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Link;
import com.vaadin.ui.UI;
import javax.servlet.ServletException;
import org.jsoup.nodes.Element;
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

    final MVerticalLayout main = new MVerticalLayout();
    final MVerticalLayout layout = new MVerticalLayout();
    Navigator navigator = new Navigator(this, layout);

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        
        main.add(new Menu());
        main.add(layout);

        layout.add(new Header("Test apps for something related to SEO"));

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

        setContent(main);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {

        @Override
        protected void servletInitialized() throws ServletException {
            getService().addSessionInitListener(new SessionInitListener() {
                @Override
                public void sessionInit(SessionInitEvent event) throws ServiceException {
                    event.getSession().addBootstrapListener(new BootstrapListener() {
                        @Override
                        public void modifyBootstrapFragment(BootstrapFragmentResponse response) {

                        }

                        @Override
                        public void modifyBootstrapPage(BootstrapPageResponse response) {

                            Element html = response.getDocument().getElementsByTag("html").get(0);
                            html.attr("prefix", "og: http://ogp.me/ns# article: http://ogp.me/ns/article#");

                            Element head = response.getDocument().getElementsByTag("head").get(0);

                            // This works for Twittter
                            meta(response, head, "twitter:card", "summary");
                            meta(response, head, "twitter:site", "@MattiTahvonen");
                            meta(response, head, "twitter:title", "Vaadin SEO examle");
                            meta(response, head, "twitter:image", "http://v4.tahvonen.fi/boat.png");

                            meta(response, head, "og:title", "This is OG title for this page");

                        }

                        private void meta(BootstrapPageResponse response, Element head, String name, String content) {
                            Element t = response.getDocument().createElement("meta");
                            t.attr("name", name);
                            t.attr("content", content);
                            head.appendChild(t);
                        }
                    });
                }
            });
        }
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
