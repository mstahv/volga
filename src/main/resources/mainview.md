This is a demo application to show how to build SEO optimized Vaadin apps. It 
is also used to develop a helper library called Volga to improve SEO of a Vaadin
application.

This demo application:

 * Uses sematical HTML elements, like H1 elements for headers instead of just styling them
 * Uses Links for navigation
 * Uses HTML5 pushState to modify browsers URL, instead of less optimal "hashbang style" which is default for curent version of Vaadin. With HistoryExtension add-on, you can use pushState today. This app uses it with Navigator, but it can be used with the lower level API as well. 
 * Writes [Open Graph](http://ogp.me) and Twitter meta tags for better social media integration. The meta tags are not changed dynamically, but correct meta tags are loaded when a page (with certain) is directly requested. At least twitter don't support dynamically added meta tags, so this is all that matters, but good to take into account when testing.
