package cz.zelo.byts.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateResponse {

    @JsonProperty(required = true)
    private boolean active;

    @JsonProperty()
    private String clientId;

    @JsonProperty()
    private String type;

    @JsonProperty()
    private String sub;

    @JsonProperty()
    private String scopes;

    @JsonProperty()
    private Date expiration;

    @JsonProperty()
    Long expiresIn;

    @JsonProperty()
    private JsonNode metadata;

}