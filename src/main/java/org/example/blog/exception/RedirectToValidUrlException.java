package org.example.blog.exception;

public class RedirectToValidUrlException extends Exception {
    private final String url;

    public RedirectToValidUrlException(String url) {
        super("Should be redirect to " + url);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
