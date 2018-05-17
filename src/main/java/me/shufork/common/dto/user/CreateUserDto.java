package me.shufork.common.dto.user;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "CreateUserDto", description = "新用户信息")
public class CreateUserDto {

    @JsonProperty("login_name")
    @NotNull(message = "'login_name' is required")
    @ApiModelProperty(value = "登录名", position = 1, required = true)
    private String loginName;

    @JsonProperty("password")
    @NotNull(message = "'password' is required")
    @ApiModelProperty(value = "登录口令", position = 2, required = true)
    private String password;

    @JsonProperty("display_name")
    @NotNull(message = "'display_name' is required")
    @ApiModelProperty(value = "昵称", position = 3, required = true)
    private String displayName;

    @JsonProperty("email")
    @NotNull(message = "'email' is required")
    @ApiModelProperty(value = "邮箱", position = 4, required = true)
    private String email;

    @JsonProperty("mobile")
    @ApiModelProperty(value = "手机号码", position = 5, required = true)
    private String mobile;
}
