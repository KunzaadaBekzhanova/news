package megalab.news.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import megalab.news.models.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String image;

    private String firstName;

    private String lastName;

    private String nickname;

    public UserDTO(User user) {
        this.image = user.getImage();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.nickname = user.getNickname();
    }
}

