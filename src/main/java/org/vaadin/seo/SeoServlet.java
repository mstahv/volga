package org.vaadin.seo;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.View;
import com.vaadin.server.*;
import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.jsoup.nodes.Element;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet(urlPatterns = "/*", name = "SeoServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = SeoUI.class, productionMode = false)
public class SeoServlet extends VaadinServlet {

    static final String CLASSES_MAPPINGS_KEY = "SeoServlet.classesMappings";

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        BidiMap classesMappings = new DualHashBidiMap();
        classesMappings.put(MainView.class, "");
        classesMappings.put(SecondView.class, "second");
        getServletContext().setAttribute(CLASSES_MAPPINGS_KEY, classesMappings);
    }

    @Override
    protected void servletInitialized() throws ServletException {

        getService().addSessionInitListener((SessionInitListener) event -> event.getSession().addBootstrapListener(new SeoBootstrapListener()));
    }

    private static class SeoBootstrapListener implements BootstrapListener {

        @Override
        public void modifyBootstrapFragment(BootstrapFragmentResponse response) { }

        @Override
        public void modifyBootstrapPage(BootstrapPageResponse response) {
            Class<? extends View> clazz = getCurrentViewClass(response);
            addHeaders(response, clazz);
        }

        private void addHeaders(BootstrapPageResponse response, Class<? extends View> clazz) {
            Element html = response.getDocument().getElementsByTag("html").get(0);
            html.attr("prefix", "og: http://ogp.me/ns# article: http://ogp.me/ns/article#");

            Element head = response.getDocument().getElementsByTag("head").get(0);

            // For Twitter
            meta(response, head, "twitter:card", "summary");
            meta(response, head, "twitter:site", "@MattiTahvonen");
            meta(response, head, "twitter:title", "Through Servlet - " + clazz.getCanonicalName());
            meta(response, head, "twitter:image", "http://v4.tahvonen.fi/boat.png");

            // For Facebook
            meta(response, head, "og:title", "Through Servlet - " + clazz.getCanonicalName());
        }

        private Class<? extends View> getCurrentViewClass(BootstrapPageResponse response) {
            HttpServletRequest request = (HttpServletRequest) response.getRequest();
            String path = request.getPathInfo();
            String strippedPath = path.replaceFirst("/", "");

            BidiMap classesMappings = (BidiMap) request.getServletContext().getAttribute(CLASSES_MAPPINGS_KEY);
            return (Class<? extends View>) classesMappings.inverseBidiMap().getOrDefault(strippedPath, MainView.class);
        }

        private void meta(BootstrapPageResponse response, Element head, String name, String content) {
            Element t = response.getDocument().createElement("meta");
            t.attr("name", name);
            t.attr("content", content);
            head.appendChild(t);
        }
    }
}
