package com.microservices.gateway.auth.user;

import com.microservices.core.abstractEntity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends AbstractEntity implements UserDetails {

    String nome;

    @Column(unique = true)
    String username;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.role != null ? AuthorityUtils.createAuthorityList(this.role.getName()) : AuthorityUtils.NO_AUTHORITIES;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

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

}


