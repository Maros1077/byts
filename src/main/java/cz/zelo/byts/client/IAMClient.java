package cz.zelo.byts.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.exodus.jsend.network.client.BaseJSendClient;
import cz.exodus.jsend.network.exception.JSendClientException;
import cz.exodus.jsend.network.model.Result;
import cz.zelo.byts.client.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class IAMClient extends BaseJSendClient {

    private static String IAM_URL = "http://localhost:8080";
    private static final String CREATE_IDENTITY_ENDPOINT = "/iam/v1/int/create";
    private static final String UPDATE_IDENTITY_PATH = "/iam/v1/int/update";
    private static final String RETRIEVE_IDENTITY_PATH = "/iam/v1/int/retrieve";

    private static final String SERVICE_NAME = "iam-service";

    @Autowired
    public IAMClient(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        super(webClientBuilder.baseUrl(IAM_URL).build(), SERVICE_NAME, objectMapper);
    }

    protected IAMClient(WebClient webClient, String serviceName, ObjectMapper objectMapper) {
        super(webClient, serviceName, objectMapper);
    }

    public Result<CreateIdentityResponse, JSendClientException> createIdentity(CreateIdentityRequest request) {
        return executePostRequestSync(CREATE_IDENTITY_ENDPOINT, request, CreateIdentityResponse.class);
    }

    public Result<RetrieveIdentityResponse, JSendClientException> retrieveIdentity(RetrieveIdentityRequest request) {
        return executePostRequestSync(RETRIEVE_IDENTITY_PATH, request, RetrieveIdentityResponse.class);
    }

    public Result<UpdateIdentityResponse, JSendClientException> updateIdentity(UpdateIdentityRequest request) {
        return executePostRequestSync(UPDATE_IDENTITY_PATH, request, UpdateIdentityResponse.class);
    }
}
