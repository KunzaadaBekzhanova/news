package megalab.news.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class TokenFilter extends OncePerRequestFilter {
    private final TokenUtilities tokenUtilities;
    private final UserDetailsService userDetailsService;
    public static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";

    @Override
    @SneakyThrows // this annotation can replace throws keyword
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(AUTHORIZATION_HEADER_PREFIX)) {
            token = authorizationHeader.substring(AUTHORIZATION_HEADER_PREFIX.length());
            if (StringUtils.hasText(token)) {
                String nickname = tokenUtilities.validateToken(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(nickname);
                UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(upat);
            }
        }
        filterChain.doFilter(request, response);
    }
}

