package me.shufork.common.service;

import me.shufork.common.dto.misc.ReplyBody;
import me.shufork.common.dto.user.UserDto;
import me.shufork.common.enums.ErrorCodeEnums;
import me.shufork.common.rpc.client.user.UserClient;
import me.shufork.common.security.AuthDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserClient userClient;

    public UserDetailsServiceImpl(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final ReplyBody<UserDto> replyBody = userClient.loadUserByLoginName(username);
        if(!replyBody.getCode().equals(ErrorCodeEnums.OK.getValue())){
            throw new UsernameNotFoundException(username);
        }

        final UserDto userDto = replyBody.getData();
        final List<GrantedAuthority> grantedAuthorities = userDto.getAuthorities().stream()
                .map( userAuthorityDto -> new SimpleGrantedAuthority(userAuthorityDto.getAuthority()))
                .collect(Collectors.toList());
        final AuthDetails authDetails = new AuthDetails(
                userDto.getId(),
                userDto.getLoginName(),
                userDto.getDisplayName(),
                userDto.getPassword(),
                true,
                true,
                true,
                true,
                grantedAuthorities);
        return authDetails;
    }
}
