package cz.zelo.byts.service;

import cz.exodus.jsend.network.exception.JSendClientException;
import cz.exodus.jsend.network.model.Result;
import cz.zelo.byts.client.IAMClient;
import cz.zelo.byts.client.STSClient;
import cz.zelo.byts.client.model.AuthPoint;
import cz.zelo.byts.client.model.CreateIdentityRequest;
import cz.zelo.byts.client.model.CreateIdentityResponse;
import cz.zelo.byts.client.model.IdentityTag;
import cz.zelo.byts.db.entity.UserEntity;
import cz.zelo.byts.db.repository.UserRepository;
import cz.zelo.byts.exception.EmailIsNotAvailableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
            log.info("IAM createIdentity request failed");
            log.info(Arrays.toString(iamResponse.getFailure().getStackTrace()));
            if (iamResponse.getFailure().getErrorDetails().getCode() == 1003) // Identity already exists
                throw new EmailIsNotAvailableException(email);
            else throw iamResponse.getFailure();
        }
        userRepository.save(new UserEntity(null, iamResponse.getSuccess().getIdid(), new Date(), null));
    }

}
