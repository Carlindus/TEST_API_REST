package org.symbolit.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"httpcode", "tag", "message"})
public class DevExcusesMessage implements IRestEntity {

    public DevExcusesMessage() {
    }

    public DevExcusesMessage(Integer pHttpcode, String pTag, String pMessage) {
        httpcode = pHttpcode;
        tag = pTag;
        message = pMessage;
    }

    @JsonProperty("http_code")
    private Integer httpcode;
    @JsonProperty("tag")
    private String tag;
    @JsonProperty("message")
    private String message;

    @JsonProperty("http_code")
    public Integer getHttpcode() {
        return httpcode;
    }

    @JsonProperty("http_code")
    public void setHttpcode(Integer httpcode) {
        this.httpcode = httpcode;
    }

    @JsonProperty("tag")
    public String getTag() {
        return tag;
    }

    @JsonProperty("tag")
    public void setTag(String tag) {
        this.tag = tag;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }
}
