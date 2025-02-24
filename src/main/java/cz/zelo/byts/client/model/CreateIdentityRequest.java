package cz.zelo.byts.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateIdentityRequest {

    @JsonProperty(required = true)
    String application;

    @JsonProperty(required = true)
    List<IdentityTag> tags;

    @JsonProperty()
    List<AuthPoint> authPoints;

}