
package com.thesis.collectorservice.model.UserDataModels;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "code",
    "label",
    "value"
})
@Generated("jsonschema2pojo")
public class IdDocumentReleaseEntity {

    @JsonProperty("code")
    private String code;
    @JsonProperty("label")
    private String label;
    @JsonProperty("value")
    private boolean value;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    public IdDocumentReleaseEntity withCode(String code) {
        this.code = code;
        return this;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    public IdDocumentReleaseEntity withLabel(String label) {
        this.label = label;
        return this;
    }

    @JsonProperty("value")
    public boolean isValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(boolean value) {
        this.value = value;
    }

    public IdDocumentReleaseEntity withValue(boolean value) {
        this.value = value;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public IdDocumentReleaseEntity withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
