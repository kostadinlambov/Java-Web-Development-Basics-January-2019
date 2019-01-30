package http;

public class HttpCookieImpl implements HttpCookie {
    private String key;
    private String value;

    public HttpCookieImpl(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
