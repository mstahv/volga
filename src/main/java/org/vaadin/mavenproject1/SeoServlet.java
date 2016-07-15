package org.vaadin.mavenproject1;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.*;
import org.jsoup.nodes.Element;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/*", name = "SeoServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
public class SeoServlet extends VaadinServlet {

    @Override
    protected void servletInitialized() throws ServletException {
        getService().addSessionInitListener((SessionInitListener) event -> event.getSession().addBootstrapListener(new BootstrapListener() {
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
                meta(response, head, "twitter:title", "Through Servlet - Vaadin SEO examle");
                meta(response, head, "twitter:image", "http://v4.tahvonen.fi/boat.png");

                meta(response, head, "og:title", "Through Servlet - This is OG title for this page");

            }

            private void meta(BootstrapPageResponse response, Element head, String name, String content) {
                Element t = response.getDocument().createElement("meta");
                t.attr("name", name);
                t.attr("content", content);
                head.appendChild(t);
            }
        }));
    }
}
