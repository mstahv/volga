/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vaadin.seo;

import com.vaadin.server.Resource;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Link;
import java.util.UUID;

/**
 *
 * @author mattitahvonenitmill
 */
public class PushStateLink extends Link {
    
    private final String id = UUID.randomUUID().toString();

    public PushStateLink(String caption, Resource resource) {
        super(caption, resource);
        setId(id);
        JavaScript.eval("var el = document.getElementById(\""+id+"\").children.item(0);\n" +
"el.addEventListener(\"click\", function(e) {\n" +
"   var href = event.currentTarget.href;\n" +
"   var name = event.currentTarget.innerText;\n" +
"   window.history.pushState('object', name, href);\n" +
"   e.preventDefault();\n" +
"});");
    }

    
}
