package megalab.news.repositories;


import megalab.news.dtos.UserDTO;
import megalab.news.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    // It's Appendix, the JpaRepository can write query itself
    Optional<User> findByNickname(String username);

    @Query(""" 
            select new megalab.news.dtos.UserDTO(
            u.image,
            u.firstName,
            u.lastName,
            u.nickname
            ) from User u where u.nickname = ?1
            """)
    UserDTO findByNicknameAndCast(String nickname);

    @Query("""
            select case when count(u) > 0 then true else false end
            from User u
            where u.nickname = ?1
            """)
    boolean existsByNickname(String nickname);

    User getByNickname(String name);
}
