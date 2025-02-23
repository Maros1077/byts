package cz.zelo.byts.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetrieveIdentityRequest {

    @JsonProperty(required = true)
    String application;

    @JsonProperty(required = true)
    IdentityTag identificationTag;

    @JsonProperty()
    boolean retrieveTags;

    @JsonProperty()
    boolean retrieveAuthPoints;
}