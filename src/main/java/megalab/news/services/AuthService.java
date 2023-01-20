package megalab.news.services;


import lombok.RequiredArgsConstructor;
import megalab.news.dtos.UserDTO;
import megalab.news.dtos.requests.AuthRequest;
import megalab.news.dtos.responses.AuthResponse;
import megalab.news.repositories.UserRepo;
import megalab.news.security.TokenUtilities;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final AuthenticationManager authManager;
    private final TokenUtilities tokenUtilities;

    public AuthResponse authenticate(AuthRequest authRequest) {

        Authentication authenticate = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.nickname(), authRequest.password())
        );

        String token = tokenUtilities.generateToken(authenticate.getName());

        UserDTO user = userRepo.findByNicknameAndCast(authenticate.getName());

        return AuthResponse.builder()
                .token(token)
                .user(user)
                .build();
    }
}