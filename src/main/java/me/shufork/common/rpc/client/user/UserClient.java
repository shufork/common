package me.shufork.common.rpc.client.user;

import me.shufork.common.dto.misc.ReplyBody;
import me.shufork.common.dto.user.UserAuthDto;
import me.shufork.common.dto.user.UserDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${rpc.service-names.user:user}",fallbackFactory = UserClientFallbackFactory.class)
public interface UserClient {

    @RequestMapping(value = "/users/name",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ReplyBody<UserDto> loadUserByLoginName(@RequestParam("login-name")String loginName);

    @RequestMapping(value = "/users/auth/name",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ReplyBody<UserAuthDto> loadUserAuthByLoginName(@RequestParam("login-name")String loginName);
}
