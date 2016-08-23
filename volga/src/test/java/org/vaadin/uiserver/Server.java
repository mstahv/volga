package org.vaadin.uiserver;

import com.vaadin.server.VaadinServlet;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.vaadin.addonhelpers.TServer;
import org.vaadin.volga.BasicVolgaDetails;
import org.vaadin.volga.Volga;
import org.vaadin.volga.VolgaDetails;
import org.vaadin.volga.VolgaServlet;

/**
 * The main method in this helper class opens a web server to
 * http://localhost:9998/ to serve all your test UIs for development and
 * integration testing.
 */
public class Server extends TServer {

    public static void main(String... args) throws Exception {
        new Server().startServer();
    }

    @Override
    protected VaadinServlet createServlet() {

        return new VolgaServlet() {
            @Override
            protected void servletInitialized() throws ServletException {
                super.servletInitialized();
                // Set the defaults for meta headers
                ServletContext context = getServletContext();
                String path = context.getContextPath();
                Volga.getCurrent(context).setDefaultDetails(new BasicVolgaDetails(path,
                        "Default SEO title",
                        "http://v4.tahvonen.fi/boat.png",
                        "This is the default SEO description that is used."));
                
                // configure addon-test-helpers uiprovider etc
                configureVaadinService(getService());
            }

            @Override
            protected Map<VolgaDetails, String> getViewMappings() {
                Map<VolgaDetails, String> mappings = new HashMap<>();
                ServletContext context = getServletContext();
                String path = context.getContextPath();
                String subpath = "second";
                mappings.put(new BasicVolgaDetails(path + '/' + subpath,
                        "Seo title for second view",
                        "http://v4.tahvonen.fi/boat.png",
                        "This is the description for view with id second."), subpath);
                return mappings;
            }
        };
    }

}
