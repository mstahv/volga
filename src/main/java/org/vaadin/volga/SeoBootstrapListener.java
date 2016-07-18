/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vaadin.volga;

import com.vaadin.server.BootstrapFragmentResponse;
import com.vaadin.server.BootstrapListener;
import com.vaadin.server.BootstrapPageResponse;
import javax.servlet.http.HttpServletRequest;
import org.jsoup.nodes.Element;

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
        return Volga.getCurrent(request.getServletContext()).getViewByPath(path).get();
    }

    private void meta(BootstrapPageResponse response, String name, String content) {
        Element head = response.getDocument().getElementsByTag("head").get(0);
        Element meta = response.getDocument().createElement("meta");
        meta.attr("name", name);
        meta.attr("content", content);
        head.appendChild(meta);
    }
    
}
