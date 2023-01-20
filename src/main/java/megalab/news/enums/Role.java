package megalab.news.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER;
    // ...

    @Override
    public String getAuthority() {
        return this.name();
    }
}

