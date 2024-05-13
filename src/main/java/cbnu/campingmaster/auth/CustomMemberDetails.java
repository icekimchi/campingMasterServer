package cbnu.campingmaster.auth;

import cbnu.campingmaster.member.dto.CustomMemberDto;
import cbnu.campingmaster.member.dto.RoleList;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomMemberDetails implements UserDetails {
    private CustomMemberDto member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        RoleList role = member.getRole();
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }


    @Override
    public String getPassword() {
        return member.getMemberId();
    }

    @Override
    public String getUsername() {
        return member.getMemberId();
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
