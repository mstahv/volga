/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vaadin.volga;

import com.vaadin.server.BootstrapFragmentResponse;
import com.vaadin.server.BootstrapListener;
import com.vaadin.server.BootstrapPageResponse;
import com.vaadin.ui.UI;
import java.util.Optional;
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
        Optional<VolgaDetails> currentView = getCurrentView(response);
        if(currentView.isPresent()) {
            addHeaders(response, currentView.get());
        }
    }

    private void addHeaders(BootstrapPageResponse response, VolgaDetails view) {
        Class<? extends UI> uiClass = response.getUiClass();
        
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
    
}
