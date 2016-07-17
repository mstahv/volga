package org.vaadin.volga;

import com.vaadin.annotations.VaadinServletConfiguration;
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

    static final String VIEWS_MAPPINGS_KEY = "SeoServlet.classesMappings";

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        BidiMap classesMappings = new DualHashBidiMap();
        classesMappings.put(new MainView(), "");
        classesMappings.put(new SecondView(), "second");
        getServletContext().setAttribute(VIEWS_MAPPINGS_KEY, classesMappings);
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
            VolgaView view = getCurrentView(response);
            addHeaders(response, view);
        }

        private void addHeaders(BootstrapPageResponse response, VolgaView view) {

            // For Twitter
            meta(response, "twitter:card", "summary");
            meta(response, "twitter:site", "http://v4.tahvonen.fi");
            meta(response, "twitter:title", view.getSeoTitle());
            meta(response, "twitter:description", view.getSeoDescription());
            meta(response, "twitter:image", view.getSeoImage());

            // For Facebook
            Element html = response.getDocument().getElementsByTag("html").get(0);
            html.attr("prefix", "og: http://ogp.me/ns# article: http://ogp.me/ns/article#");

            meta(response, "og:title", view.getSeoTitle());
            meta(response, "og:description", view.getSeoDescription());
            meta(response, "og:image", view.getSeoImage());
        }

        private VolgaView getCurrentView(BootstrapPageResponse response) {
            HttpServletRequest request = (HttpServletRequest) response.getRequest();
            String path = request.getPathInfo();
            String strippedPath = path.replaceFirst("/", "");

            BidiMap viewsMappings = (BidiMap) request.getServletContext().getAttribute(VIEWS_MAPPINGS_KEY);
            return (VolgaView) viewsMappings.inverseBidiMap().getOrDefault(strippedPath, new MainView());
        }

        private void meta(BootstrapPageResponse response, String name, String content) {
            Element head = response.getDocument().getElementsByTag("head").get(0);
            Element meta = response.getDocument().createElement("meta");
            meta.attr("name", name);
            meta.attr("content", content);
            head.appendChild(meta);
        }
    }
}
