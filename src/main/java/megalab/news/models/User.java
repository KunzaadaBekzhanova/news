package megalab.news.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import megalab.news.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // image path
    private String image;

    private String firstName;

    private String lastName;

    private String nickname;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "writer", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<News> newsList;

    @OneToMany(mappedBy = "commenter")
    private List<Comment> commentList;

    @OneToMany
    private List<News> likedNews;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getUsername() {
        return nickname;
    }

    // we don't have user blocking, account expired, credentials expired features so i return true in all these methods below
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addNews(News news) {
        if (this.newsList == null) {
            this.newsList = new ArrayList<>();
        }
        this.newsList.add(news);
    }

    public void addLikedNews(News news) {
        if (this.likedNews == null) {
            this.likedNews = new ArrayList<>();
        }
        this.likedNews.add(news);
    }

    public void removeFromLikedNews(News news) {
        this.likedNews.remove(news);
    }
}
