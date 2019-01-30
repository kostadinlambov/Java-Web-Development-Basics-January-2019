package http;

import java.util.LinkedHashMap;
import java.util.Map;

public class HttpRequestImpl implements HttpRequest {
    private String requestContent;
    private String method;
    private String requestUrl;
    private Map<String, String> headersMap;
    private Map<String, String> requestBodyParametersMap;
    private Map<String, HttpCookie> cookieMap;


    public HttpRequestImpl(String  requestContent){
        this.requestContent = requestContent;
        this.setMethod(this.requestContent);
        this.setRequestUrl(this.requestContent);
        this.initHeaders();
        this.initBodyParameters();
        this.initCookies();
    }

    private void initCookies() {
        this.cookieMap = new LinkedHashMap<>();

        if(!this.headersMap.containsKey("Cookie")){
            return;
        }

        String cookiesHeader = this.headersMap.get("Cookie");
        String[] cookiesArr = cookiesHeader.split("\\;\\s");

        for (int i = 0; i < cookiesArr.length; i++) {
            String[] cookiesKvp = cookiesArr[i].split("=");
            this.cookieMap
                    .putIfAbsent(cookiesKvp[0], new HttpCookieImpl(cookiesKvp[0], cookiesKvp[1]));
        }
    }

    private void initHeaders() {
        this.headersMap = new LinkedHashMap<>();
        String[] tokens = this.requestContent.split("\\r\\n\\r\\n");
        String[] inputTokens = tokens[0].split("\\r\\n");
        for (int i = 1; i < inputTokens.length; i++) {
            if ("".equals(inputTokens[i])) {
                break;
            }
            String[] headerTokens = inputTokens[i].split(":\\s");
            this.headersMap.putIfAbsent(headerTokens[0], headerTokens[1]);
        }
    }

    private void initBodyParameters() {
        this.requestBodyParametersMap =  new LinkedHashMap<>();
        String[] bodyTokens = this.requestContent.split("\\r\\n\\r\\n");
        if(bodyTokens.length > 1){
            String requestBody = this.requestContent.split("\\r\\n\\r\\n")[1];
            String[] requestBodyParameters = requestBody.split("&");

            for (String requestBodyParameter : requestBodyParameters) {
                String[] parameterKvp = requestBodyParameter.split("=");
                this.requestBodyParametersMap.putIfAbsent(parameterKvp[0], parameterKvp[1]);
            }
        }
    }

    @Override
    public Map<String, HttpCookie> getCookieMap() {
        return this.cookieMap;
    }

    @Override
    public Map<String, String> getHeaders() {
        return this.headersMap;
    }

    @Override
    public Map<String, String> getBodyParameters() {
        return this.requestBodyParametersMap;
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public void setMethod(String inputContent) {
        String[] inputTokens = inputContent.split("\\r\\n");
        this.method = inputTokens[0].split("\\s+")[0];
    }

    @Override
    public String getRequestUrl() {
        return this.requestUrl;
    }

    @Override
    public void setRequestUrl(String inputContent) {
        String[] inputTokens = inputContent.split("\\r\\n");
        this.requestUrl = inputTokens[0].split("\\s+")[1];
    }

    @Override
    public void addHeader(String header, String value) {
        this.headersMap.putIfAbsent(header, value);
    }

    @Override
    public void addBodyParameter(String parameter, String value) {
            this.requestBodyParametersMap.putIfAbsent(parameter, value);
    }

    @Override
    public boolean isResource() {
        return this.getRequestUrl().contains(".");
    }
}
