package projet.blog.blog_authms.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static projet.blog.blog_authms.entities.Permission.*;

@RequiredArgsConstructor

public enum Role {
    USER(Collections.emptySet()),
    ADMIN(
            Set.of(Permission.USER_CREATE,
                    USER_DELETE,
                    ADMIN_CREATE
                    ,ADMIN_DELETE
                    ,ADMIN_UPDATE,
                    USER_UPDATE,
                    USER_READ,
                    ADMIN_READ
            )
    ),
    MEMBER(
            Set.of(USER_CREATE,USER_DELETE,USER_UPDATE,USER_READ)
    )

    ;

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getUserpermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
