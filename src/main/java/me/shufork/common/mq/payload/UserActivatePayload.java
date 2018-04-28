package me.shufork.common.mq.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserActivatePayload {

    @JsonProperty("user_id")
    private String userId;
}
