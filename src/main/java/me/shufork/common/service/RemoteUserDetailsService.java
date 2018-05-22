package me.shufork.common.service;

import me.shufork.common.dto.misc.ReplyBody;
import me.shufork.common.dto.user.UserAuthDto;
import me.shufork.common.enums.ErrorCodeEnums;
import me.shufork.common.enums.UserStatusEnums;
import me.shufork.common.rpc.client.user.UserClient;
import me.shufork.common.security.AuthDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class RemoteUserDetailsService implements UserDetailsService {

    private final UserClient userClient;

    public RemoteUserDetailsService(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final ReplyBody<UserAuthDto> replyBody = userClient.loadUserAuthByLoginName(username);
        if(!replyBody.getCode().equals(ErrorCodeEnums.OK.getValue())){
            throw new UsernameNotFoundException(username);
        }

        final UserAuthDto userAuthDto = replyBody.getData();
        final List<GrantedAuthority> grantedAuthorities = userAuthDto.getRoles().stream()
                .map( roleDto -> new SimpleGrantedAuthority(roleDto.getName()))
                .collect(Collectors.toList());
        final AuthDetails authDetails = new AuthDetails(
                userAuthDto.getId(),
                userAuthDto.getLoginName(),
                userAuthDto.getDisplayName(),
                userAuthDto.getPassword(),
                userAuthDto.getStatus().equals(UserStatusEnums.ACTIVE.toString()),
                true,
                true,
                !userAuthDto.getStatus().equals(UserStatusEnums.LOCKED.toString()),
                grantedAuthorities);
        return authDetails;
    }
}
