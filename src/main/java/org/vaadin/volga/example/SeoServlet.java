package org.vaadin.volga.example;

import com.vaadin.annotations.VaadinServletConfiguration;
import org.vaadin.volga.VolgaServlet;

import javax.servlet.annotation.WebServlet;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import org.vaadin.volga.BasicVolgaDetails;
import org.vaadin.volga.Volga;
import org.vaadin.volga.VolgaDetails;

@WebServlet(urlPatterns = "/*", name = "ExampleSEOServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = SeoUI.class, productionMode = false)
public class SeoServlet extends VolgaServlet {

    @Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized(); //To change body of generated methods, choose Tools | Templates.
        Volga.getCurrent(getServletContext()).setDefaultDetails(new BasicVolgaDetails("Default SEO title", "http://v4.tahvonen.fi/boat.png", "This is the default SEO description that is used."));
    }

    @Override
    protected Map<VolgaDetails, String> getViewMappings() {
        Map<VolgaDetails, String> mappings = new HashMap<>();
        mappings.put(new BasicVolgaDetails("Seo title for second view", "http://v4.tahvonen.fi/boat.png", "This is the description for view with id second."), "second");
        return mappings;
    }
}
