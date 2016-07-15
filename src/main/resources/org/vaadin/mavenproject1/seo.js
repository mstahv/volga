var head = document.getElementsByTagName("head")[0];
// Twitter CANNOT parse dynamically added meta tags :-(

var el = document.createElement("meta");
el.name = "twitter:description";
el.content = "This is test description";
head.appendChild(el);

el = document.createElement("meta");
el.name = "twitter:card";
el.content = "summary";
head.appendChild(el);

el = document.createElement("meta");
el.name = "twitter:site";
el.content = "@nicolas_frankel";
head.appendChild(el);

el = document.createElement("meta");
el.name = "twitter:title";
el.content = "A Java geek";
head.appendChild(el);

el = document.createElement("meta");
el.name = "twitter:image";
el.content = "http://v4.tahvonen.fi/boat.png";
head.appendChild(el);

el = document.createElement("meta");
// Facebook CAN parse dynamically added stuff
el.name = "og:image";
el.content = "http://v4.tahvonen.fi/boat.png";
head.appendChild(el);

el = document.createElement("meta");
el.name = "og:url";
el.content = "http://v4.tahvonen.fi/seo-test/";
head.appendChild(el);

el = document.createElement("meta");
el.name = "og:title";
el.content = "Vaadin OG test";
head.appendChild(el);