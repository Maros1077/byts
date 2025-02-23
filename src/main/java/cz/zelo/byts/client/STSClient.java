package cz.zelo.byts.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.exodus.jsend.network.client.BaseJSendClient;
import cz.exodus.jsend.network.exception.JSendClientException;
import cz.exodus.jsend.network.model.Result;
import cz.zelo.byts.client.model.ValidateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class STSClient extends BaseJSendClient {

    private static String STS_URL = "http://localhost:8081";
    private static final String VALIDATE_ENDPOINT = "/sts/v1/int/validate";
    private static final String SERVICE_NAME = "sts-service";

    @Autowired
    public STSClient(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        super(webClientBuilder.baseUrl(STS_URL).build(), SERVICE_NAME, objectMapper);
    }

    protected STSClient(WebClient webClient, String serviceName, ObjectMapper objectMapper) {
        super(webClient, serviceName, objectMapper);
    }

    public Result<ValidateResponse, JSendClientException> validateToken(String token) {
        return executePostRequestSync(VALIDATE_ENDPOINT, token, ValidateResponse.class);
    }
}
