package megalab.news.dtos.responses;

import lombok.Builder;
import lombok.Data;
import megalab.news.dtos.UserDTO;

@Data
@Builder
public class AuthResponse {
    private String token;
    private UserDTO user;
}

