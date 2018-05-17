package me.shufork.common.rpc.client.user;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import me.shufork.common.dto.misc.ReplyBody;
import me.shufork.common.dto.user.UserAuthDto;
import me.shufork.common.dto.user.UserDto;
import me.shufork.common.enums.ErrorCodeEnums;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserClientFallbackFactory implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable throwable) {
        return new UserClient(){
            @Override
            public ReplyBody<UserDto> loadUserByLoginName(String loginName) {
                log.error("loadUserByLoginName:"+loginName+","+throwable.getMessage());
                ReplyBody<UserDto> replyBody = new ReplyBody<>();
                replyBody.setCode(ErrorCodeEnums.RPC_FAILED.getValue());
                replyBody.setMessage(throwable.getMessage());
                replyBody.setMoreInfo(loginName);
                return replyBody;
            }

            @Override
            public ReplyBody<UserAuthDto> loadUserAuthByLoginName(String loginName) {
                log.error("loadUserAuthByLoginName:"+loginName+","+throwable.getMessage());
                ReplyBody<UserAuthDto> replyBody = new ReplyBody<>();
                replyBody.setCode(ErrorCodeEnums.RPC_FAILED.getValue());
                replyBody.setMessage(throwable.getMessage());
                replyBody.setMoreInfo(loginName);
                return replyBody;
            }
        };
    }
}
