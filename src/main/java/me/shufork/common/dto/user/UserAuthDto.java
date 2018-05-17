package me.shufork.common.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "login_name",
        "password",
        "status",
        "display_name",
        "email",
        "mobile"})
@Data
public class UserAuthDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("login_name")
    private String loginName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("status")
    private String status;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("mobile")
    private String mobile;

    @JsonProperty("roles")
    private List<RoleDto> roles;

}
