package megalab.news.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import megalab.news.dtos.requests.AuthRequest;
import megalab.news.dtos.requests.CreateUserRequest;
import megalab.news.dtos.responses.AuthResponse;
import megalab.news.services.AuthService;
import megalab.news.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Authentication API", description = "Any users can authenticate")
@PermitAll
public class AuthAPI {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/authenticate")
    @Operation(summary = "Login", description = "Only registered users can login")
    AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        return authService.authenticate(authRequest);
    }

    @PostMapping("/register")
    @Operation(summary = "Registration ", description = "Any user can do registration")
    AuthResponse register(@RequestBody CreateUserRequest createUserRequest) {
        return userService.save(createUserRequest);
    }
}
