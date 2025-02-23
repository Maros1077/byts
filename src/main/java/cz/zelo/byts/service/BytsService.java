package cz.zelo.byts.service;

import cz.exodus.jsend.network.exception.JSendClientException;
import cz.exodus.jsend.network.model.Result;
import cz.zelo.byts.client.IAMClient;
import cz.zelo.byts.client.STSClient;
import cz.zelo.byts.client.model.*;
import cz.zelo.byts.db.entity.UserEntity;
import cz.zelo.byts.db.repository.UserRepository;
import cz.zelo.byts.exception.EmailIsNotAvailableException;
import cz.zelo.byts.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class BytsService {

    private static String APP_NAME = "byts";

    private final UserRepository userRepository;

    private final STSClient stsClient;
    private final IAMClient iamClient;

    public void createIamIdentity(String email, String password) throws Exception {
        List<IdentityTag> identityTags = new ArrayList<>();
        identityTags.add(new IdentityTag("EMAIL", email));
        List<AuthPoint> authPoints = new ArrayList<>();
        authPoints.add(new AuthPoint("PASSWORD", password));
        Result<CreateIdentityResponse, JSendClientException> iamResponse = iamClient.createIdentity(new CreateIdentityRequest(APP_NAME, identityTags, authPoints));
        if (iamResponse.isFailure()) {
            if (iamResponse.getFailure().getErrorDetails().getCode() == 1003) // Identity already exists
                throw new EmailIsNotAvailableException(email);
            else throw iamResponse.getFailure();
        }
        userRepository.save(new UserEntity(null, iamResponse.getSuccess().getIdid(), new Date(), null));
    }

    public void init(String token) throws Exception {
        if (token == null || token.isEmpty()) {
            throw new UnauthorizedException();
        }
        String extractedToken = extractToken(token);
        Result<ValidateResponse, JSendClientException> tokenResponse = stsClient.validateToken(extractedToken);
        if (tokenResponse.isFailure()) {
            if (tokenResponse.getFailure().getHttpStatus().value() == 401 || tokenResponse.getFailure().getHttpStatus().value() == 403)
                throw new UnauthorizedException();
            else throw tokenResponse.getFailure();
        }
        if (!tokenResponse.getSuccess().isActive() || !Objects.equals(tokenResponse.getSuccess().getClientId(), APP_NAME)) {
            throw new UnauthorizedException();
        }
        UserEntity userEntity = userRepository.findByIdid(tokenResponse.getSuccess().getMetadata().get("idid").asText());
        if (userEntity == null) {
            throw new UnauthorizedException();
        }
        userEntity.setLastAccess(new Date());
        userRepository.save(userEntity);
    }

    private String extractToken(String token) {
        if (token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }

}
