package http;

import java.util.LinkedHashMap;
import java.util.Map;

public class HttpResponseImpl implements HttpResponse {
    private static final String PROTOCOL_VERSION = "HTTP/1.1";

    private int statusCode;
    private Map<String, String> headers;
    private Map<String, HttpCookie> cookies;
    private byte[] content;

    public HttpResponseImpl() {
        this.headers = new LinkedHashMap<>();
        this.cookies = new LinkedHashMap<>();
        this.content = new byte[0];
    }

    @Override
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public byte[] getContent() {
        return this.content;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public byte[] getResponseBytes() {
        String statusLine = this.getStatusLine();
        String headersString = this.createHeaders();

        int responseMessageLength = statusLine.length() + headersString.length() + this.content.length;

        byte[] responseBytes = new byte[responseMessageLength];

        System.arraycopy(
                statusLine.getBytes(),
                0,
                responseBytes,
                0, statusLine.length());

        System.arraycopy(
                headersString.getBytes(),
                0, responseBytes,
                statusLine.length(),
                headersString.length());

        System.arraycopy(this.content,
                0,
                responseBytes,
                statusLine.length() + headersString.length(), this.content.length);

        return responseBytes;
    }

    private String createHeaders() {
       StringBuilder headersSb = new StringBuilder();
        for (Map.Entry<String, String> header : this.headers.entrySet()) {
            String headerName = header.getKey();
            if ("Date".equals(headerName) || "Host".equals(headerName) ||
                    "Content-Type".equals(headerName) || "Cookie".equals(headerName)) {
                headersSb
                        .append(headerName)
                        .append(": ")
                        .append(header.getValue())
                        .append(System.lineSeparator());
            }
        }

//        if(!this.cookies.isEmpty()) {
//            headersSb.append("Set-Cookie: ");
//
//            for (HttpCookie cookie : this.cookies.values()) {
//                headersSb.append(cookie.toString()).append("; ");
//            }
//
//            headersSb.replace(headersSb.length() - 2, headersSb.length(), "");
//            headersSb.append(System.lineSeparator());
//        }

        headersSb.append("\r\n");

        return headersSb.toString();
    }

    @Override
    public Map<String, HttpCookie> getCookies() {
        return this.cookies;
    }

    @Override
    public void setCookies(Map<String, HttpCookie> cookies) {
        this.cookies = cookies;
    }

    @Override
    public void addHeader(String header, String value) {
        if ("Date".equals(header) || "Host".equals(header) ||
                "Content-Type".equals(header) || "Cookie".equals(header)) {
            this.headers.putIfAbsent(header, value);
        }
    }

    private String getStatusLine() {
        switch (this.statusCode) {
            case 200:
                return PROTOCOL_VERSION + " " + HttpStatus.OK.getStatusPhrase() + "\r\n";
            case 201:
                return PROTOCOL_VERSION + " " + HttpStatus.CREATED.getStatusPhrase() + "\r\n";
            case 204:
                return PROTOCOL_VERSION + " " + HttpStatus.NO_CONTENT.getStatusPhrase() + "\r\n";
            case 301:
                return PROTOCOL_VERSION + " " + HttpStatus.MOVED_PERMANENTLY.getStatusPhrase() + "\r\n";
            case 303:
                return PROTOCOL_VERSION + " " + HttpStatus.SEE_OTHER.getStatusPhrase() + "\r\n";
            case 400:
                return PROTOCOL_VERSION + " " + HttpStatus.BAD_REQUEST.getStatusPhrase() + "\r\n";
            case 401:
                return PROTOCOL_VERSION + " " + HttpStatus.UNAUTHORIZED.getStatusPhrase() + "\r\n";
            case 403:
                return PROTOCOL_VERSION + " " + HttpStatus.FORBIDDEN.getStatusPhrase() + "\r\n";
            case 500:
                return PROTOCOL_VERSION + " " + HttpStatus.INTERNAL_SERVER_ERROR.getStatusPhrase() + "\r\n";
        }

        return PROTOCOL_VERSION + " " + HttpStatus.NOT_FOUND.getStatusPhrase() + "\r\n";
    }
}
