package org.vaadin.volga;

import javax.servlet.ServletContext;
import java.util.*;

public class Volga {

    private static final String VOLGA_CONTEXT_KEY = "org.vaadin.volga.Volga";

    private final Map<String, VolgaView> mappings = new HashMap<>();

    public static Volga getCurrent(ServletContext servletContext) {
        Object attribute = servletContext.getAttribute(VOLGA_CONTEXT_KEY);
        if (attribute != null && Volga.class.isAssignableFrom(attribute.getClass())) {
            return (Volga) attribute;
        }
        Volga volga = new Volga();
        servletContext.setAttribute(VOLGA_CONTEXT_KEY, volga);
        return volga;
    }

    void addViewByPath(VolgaView view, String path) {
        if (view == null) {
            throw new NullPointerException("view cannot be null");
        }
        if (path == null) {
            throw new NullPointerException("path cannot be null");
        }
        mappings.put(path, view);
    }

    public Optional<VolgaView> getViewByPath(String path) {
        if (path == null) {
            throw new NullPointerException("path cannot be null");
        }
        String strippedPath = path.replaceFirst("/", "");
        return mappings.entrySet().stream().filter(e -> strippedPath.equals(e.getKey())).map(Map.Entry::getValue).findAny();
    }
}
