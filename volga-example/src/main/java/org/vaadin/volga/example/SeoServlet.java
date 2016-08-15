package org.vaadin.volga.example;

import com.vaadin.annotations.VaadinServletConfiguration;
import org.vaadin.volga.Volga;
import org.vaadin.volga.VolgaDetails;
import org.vaadin.volga.BasicVolgaDetails;
import org.vaadin.volga.VolgaServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/*", name = "ExampleSEOServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = SeoUI.class, productionMode = false)
public class SeoServlet extends VolgaServlet {

    @Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized();
        ServletContext context = getServletContext();
        String path =  context.getContextPath();
        Volga.getCurrent(context).setDefaultDetails(new BasicVolgaDetails(path,
                "Default SEO title",
                "http://v4.tahvonen.fi/boat.png",
                "This is the default SEO description that is used."));
    }

    @Override
    protected Map<VolgaDetails, String> getViewMappings() {
        Map<VolgaDetails, String> mappings = new HashMap<>();
        ServletContext context = getServletContext();
        String path =  context.getContextPath();
        String subpath = "second";
        mappings.put(new BasicVolgaDetails(path + '/' + subpath,
                "Seo title for second view",
                "http://v4.tahvonen.fi/boat.png",
                "This is the description for view with id second."), subpath);
        return mappings;
    }
}
