package ca.softwarespace.qiyanna.dataaggregator.Utils;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 * Author: Steve Mbiele
 * Date: 5/15/2019
 */

public class RestClient {
    private RestTemplate restTemplate;
    private HttpHeaders httpHeaders;
    private HttpStatus httpStatus;

    public RestClient() {
        this.restTemplate = new RestTemplate();
        this.httpHeaders = new HttpHeaders();
    }

    public void addHeader(String headerKey, String headerValue){
        this.httpHeaders.add(headerKey, headerValue);
    }

    public String get (String uri) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
        this.setHttpStatus(responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    public String post (String uri, String json) {
        HttpEntity<String> requestEntity = new HttpEntity<>(json, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.PUT, requestEntity, String.class);
        this.setHttpStatus(responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    public void put(String uri, String json) {
        HttpEntity<String> requestEntity = new HttpEntity<String>(json, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.PUT, requestEntity, (Class<String>) null);
        this.setHttpStatus(responseEntity.getStatusCode());
    }

    public void delete(String uri) {
        HttpEntity<String> requestEntity = new HttpEntity<String>("", httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.DELETE, requestEntity, (Class<String>)null);
        this.setHttpStatus(responseEntity.getStatusCode());
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
