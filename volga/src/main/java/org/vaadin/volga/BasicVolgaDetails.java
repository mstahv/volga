package org.vaadin.volga;

public class BasicVolgaDetails implements VolgaDetails {

    private final String seoUrl;
    private final String seoTitle;
    private final String seoImage;
    private final String seoDescription;

    public BasicVolgaDetails(String seoUrl, String seoTitle, String seoImage, String seoDescription) {
        this.seoUrl = seoUrl;
        this.seoTitle = seoTitle;
        this.seoImage = seoImage;
        this.seoDescription = seoDescription;
    }

    @Override
    public String getSeoTitle() {
        return seoTitle;
    }

    @Override
    public String getSeoImage() {
        return seoImage;
    }

    @Override
    public String getSeoDescription() {
        return seoDescription;
    }

    @Override
    public String getSeoUrl() {
        return seoUrl;
    }
}
