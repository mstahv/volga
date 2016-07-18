package org.vaadin.volga;

import com.vaadin.server.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.util.Map;

public abstract class VolgaServlet extends VaadinServlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        Volga volga = Volga.getCurrent(servletConfig.getServletContext());
        getViewMappings().entrySet().stream().forEach(e -> volga.addViewByPath(e.getKey(), e.getValue()));
    }

    protected abstract Map<VolgaView, String> getViewMappings();

    @Override
    protected void servletInitialized() throws ServletException {

        getService().addSessionInitListener((SessionInitListener) event -> event.getSession().addBootstrapListener(new SeoBootstrapListener()));
    }

}
