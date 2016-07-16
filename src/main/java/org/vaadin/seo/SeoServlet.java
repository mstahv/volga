package org.vaadin.seo;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.View;
import com.vaadin.server.*;
import org.jsoup.nodes.Element;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet(urlPatterns = "/*", name = "SeoServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = SeoUI.class, productionMode = false)
public class SeoServlet extends VaadinServlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        Map<Class<? extends View>, String> viewClassesToId = new HashMap<>();
        viewClassesToId.put(MainView.class, "");
        viewClassesToId.put(SecondView.class, "second");
        getServletContext().setAttribute("viewClassesToId", viewClassesToId);
    }

    @Override
    protected void servletInitialized() throws ServletException {

        getService().addSessionInitListener((SessionInitListener) event -> event.getSession().addBootstrapListener(new BootstrapListener() {
            @Override
            public void modifyBootstrapFragment(BootstrapFragmentResponse response) {

            }

            @Override
            public void modifyBootstrapPage(BootstrapPageResponse response) {

                HttpServletRequest request = (HttpServletRequest) response.getRequest();
                String path = request.getPathInfo();
                String strippedPath = path.replaceFirst("/", "");

                Map<Class<? extends View>, String> viewClassesToId = (Map<Class<? extends View>, String>) getServletContext().getAttribute("viewClassesToId");
                Optional<? extends Class<? extends View>> entry = viewClassesToId.entrySet().stream().filter(e -> strippedPath.equals(e.getValue())).map(e -> e.getKey()).findAny();

                Class<? extends View> clazz = entry.get();

                Element html = response.getDocument().getElementsByTag("html").get(0);
                html.attr("prefix", "og: http://ogp.me/ns# article: http://ogp.me/ns/article#");

                Element head = response.getDocument().getElementsByTag("head").get(0);

                // This works for Twittter
                meta(response, head, "twitter:card", "summary");
                meta(response, head, "twitter:site", "@MattiTahvonen");
                meta(response, head, "twitter:title", "Through Servlet - " + clazz.getCanonicalName());
                meta(response, head, "twitter:image", "http://v4.tahvonen.fi/boat.png");

                meta(response, head, "og:title", "Through Servlet - " + clazz.getCanonicalName());
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
