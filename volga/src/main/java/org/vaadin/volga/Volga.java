package org.vaadin.volga;

import com.vaadin.server.BootstrapPageResponse;
import javax.servlet.ServletContext;
import java.util.*;

public class Volga {

    private static final String VOLGA_CONTEXT_KEY = "org.vaadin.volga.Volga";
    private static final String PATH_SEPARATOR = "/";

    private final Map<String, VolgaDetails> mappings = new HashMap<>();

    private VolgaDetails defaultDetails;

    public static Volga getCurrent(ServletContext servletContext) {
        Object attribute = servletContext.getAttribute(VOLGA_CONTEXT_KEY);
        if (attribute != null && Volga.class.isAssignableFrom(attribute.getClass())) {
            return (Volga) attribute;
        }
        Volga volga = new Volga();
        servletContext.setAttribute(VOLGA_CONTEXT_KEY, volga);
        return volga;
    }
    public void setDefaultDetails(VolgaDetails details) {
        defaultDetails = details;
    }

    void addViewByPath(VolgaDetails view, String path) {
        if (view == null) {
            throw new NullPointerException("view cannot be null");
        }
        if (path == null) {
            throw new NullPointerException("path cannot be null");
        }
        mappings.put(path, view);
    }

    public VolgaDetails getVolgaDetails(BootstrapPageResponse response) {
        String path = response.getRequest().getPathInfo();
        
        // TODO figure out a way to allow dynamically set details, starts with matching and passing details for VolgaDetails
        if (path == null) {
            throw new NullPointerException("path cannot be null");
        }
        if (path.startsWith(PATH_SEPARATOR)) {
            path = path.replaceFirst("/", "");
        }
        if (path.contains("/")) {
            path = path.substring(0, path.indexOf("/"));
        }
        String strippedPath = path;
        return mappings.entrySet().stream().filter(e -> strippedPath.equals(e.getKey())).map(Map.Entry::getValue).findAny().orElse(defaultDetails);
    }
}