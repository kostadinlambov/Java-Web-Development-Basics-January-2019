package http;

import java.util.Map;

public interface HttpResponse {
    Map<String, String> getHeaders();

    int getStatusCode();

    void setStatusCode(int statusCode);

    byte[] getContent();

    void setContent(byte[] content);

    byte[] getResponseBytes();

    Map<String, HttpCookie> getCookies();

    void setCookies(Map<String, HttpCookie> cookies);

    void addHeader(String header, String value);
}
