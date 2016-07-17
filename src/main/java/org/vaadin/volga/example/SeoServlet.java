package org.vaadin.volga.example;

import com.vaadin.annotations.VaadinServletConfiguration;
import org.vaadin.volga.VolgaServlet;
import org.vaadin.volga.VolgaView;

import javax.servlet.annotation.WebServlet;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/*", name = "ExampleSEOServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = SeoUI.class, productionMode = false)
public class SeoServlet extends VolgaServlet {

    @Override
    protected Map<VolgaView, String> getViewMappings() {
        Map<VolgaView, String> mappings = new HashMap<>();
        mappings.put(new MainView(), "");
        mappings.put(new SecondView(), "second");
        return mappings;
    }
}
