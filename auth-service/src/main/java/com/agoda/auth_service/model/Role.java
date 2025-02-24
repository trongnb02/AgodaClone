package com.agoda.auth_service.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.agoda.auth_service.model.Permission.*;

@RequiredArgsConstructor
public enum Role {
    CUSTOMER(
            Collections.emptySet()
    ),
    VENDOR(
            Set.of(
                    VENDOR_READ,
                    VENDOR_UPDATE,
                    VENDOR_CREATE,
                    VENDOR_DELETE
            )
    ),
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_CREATE,
                    ADMIN_DELETE,
                    VENDOR_READ,
                    VENDOR_UPDATE,
                    VENDOR_CREATE,
                    VENDOR_DELETE
            )
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
