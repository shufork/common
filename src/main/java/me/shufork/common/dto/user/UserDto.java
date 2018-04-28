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
        "display_name",
        "email",
        "cell_phone_number"})
@Data
public class UserDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("login_name")
    private String loginName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("cell_phone_number")
    private String cellPhoneNumber;

    @JsonProperty("authorities")
    private List<UserAuthorityDto> authorities;


}
