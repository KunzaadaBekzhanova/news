package megalab.news.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import megalab.news.dtos.UserDTO;
import megalab.news.dtos.responses.GetProfileResponse;
import megalab.news.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "User API", description = "Users can  update profile")
public class UserAPI {

    private final UserService userService;

    @GetMapping("/profile")
    @Operation(summary = "Get user profile ", description = "User can see own profile")
    GetProfileResponse getUserProfile(Authentication auth) {
        return userService.getProfile(auth);
    }

    @PutMapping
    @Operation(summary = "Update user profile information ", description = "User can update profile information")
    UserDTO updateUser(Authentication auth, @RequestBody UserDTO userRequest) {
        return userService.updateUser(auth, userRequest);
    }
}
