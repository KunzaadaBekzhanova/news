package megalab.news.dtos.requests;

public record CreateUserRequest(String firstName, String lastName, String nickname, String password) {
}
