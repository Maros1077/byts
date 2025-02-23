package cz.zelo.byts.controller;

import cz.zelo.byts.rest.RegisterRequest;
import cz.zelo.byts.service.BytsService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static cz.zelo.byts.controller.BytsEndpoints.*;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(MOBILE_CONTROLLER)
public class BytsController {

    private final BytsService bytsService;

    @PostMapping(REGISTER_PATH)
    public void register(@RequestBody RegisterRequest request, HttpServletResponse response) throws Exception {
        log.debug("REGISTER START");
        try {
            bytsService.createIamIdentity(request.getEmail(), request.getPassword());
            log.info("REGISTER SUCCESS for: {}", request.getEmail());
        } catch (Exception e) {
            log.error(e.getMessage());
            log.info("REGISTER FAILED for {}", request.getEmail());
            throw e;
        }
    }

    @GetMapping(INIT_PATH)
    public void init(@RequestHeader("Authorization") String authHeader, HttpServletResponse response) throws Exception {
        log.debug("INIT START");
        try {
            bytsService.init(authHeader);
            log.info("INIT SUCCESS");
        } catch (Exception e) {
            log.error(e.getMessage());
            log.info("INIT FAILED");
            throw e;
        }
    }

}
