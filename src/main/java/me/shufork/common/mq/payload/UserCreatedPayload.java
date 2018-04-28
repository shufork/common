package me.shufork.common.mq.payload;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class UserCreatedPayload {

    @JsonProperty("user_id")
    private String userId;

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
}
