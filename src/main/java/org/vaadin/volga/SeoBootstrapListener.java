/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vaadin.volga;

import com.vaadin.server.BootstrapFragmentResponse;
import com.vaadin.server.BootstrapListener;
import com.vaadin.server.BootstrapPageResponse;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import org.jsoup.nodes.Element;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 *
 * @author mattitahvonenitmill
 */
public class SeoBootstrapListener implements BootstrapListener {
    
    @Override
    public void modifyBootstrapFragment(BootstrapFragmentResponse response) {
    }

    @Override
    public void modifyBootstrapPage(BootstrapPageResponse response) {
        Optional<VolgaDetails> currentView = getCurrentView(response);
        String path = computePath(response);
        if(currentView.isPresent()) {
            addHeaders(path, response, currentView.get());
        }
    }

    private void addHeaders(String path, BootstrapPageResponse response, VolgaDetails details) {
        Class<? extends UI> uiClass = response.getUiClass();
        
        // For Twitter
        meta(response, "twitter:card", "summary");
        meta(response, "twitter:site", path + details.getSeoUrl());
        meta(response, "twitter:title", details.getSeoTitle());
        meta(response, "twitter:description", details.getSeoDescription());
        meta(response, "twitter:image", details.getSeoImage());
        
        // For Facebook
        Element html = response.getDocument().getElementsByTag("html").get(0);
        html.attr("prefix", "og: http://ogp.me/ns# article: http://ogp.me/ns/article#");
        meta(response, "og:type", "website");
        meta(response, "og:url", path + details.getSeoUrl());
        meta(response, "og:title", details.getSeoTitle());
        meta(response, "og:description", details.getSeoDescription());
        meta(response, "og:image", details.getSeoImage());
    }

    private Optional<VolgaDetails> getCurrentView(BootstrapPageResponse response) {
        HttpServletRequest request = (HttpServletRequest) response.getRequest();
        final Volga current = Volga.getCurrent(request.getServletContext());
        return current.getVolgaView(response);
    }

    private void meta(BootstrapPageResponse response, String name, String content) {
        Element head = response.getDocument().getElementsByTag("head").get(0);
        Element meta = response.getDocument().createElement("meta");
        meta.attr("name", name);
        meta.attr("content", content);
        head.appendChild(meta);
    }

    private String computePath(BootstrapPageResponse response) {
        VaadinRequest vaadinRequest = response.getRequest();
        HttpServletRequest request = (HttpServletRequest) vaadinRequest;
        String scheme = request.getScheme();
        String server = request.getServerName();
        int port = request.getServerPort();
        StringBuilder builder = new StringBuilder(scheme)
                .append("://")
                .append(server);
        if (port != 80) {
            builder.append(':').append(port);
        }
        return builder.toString();
    }
}
