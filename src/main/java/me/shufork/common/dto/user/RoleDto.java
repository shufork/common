package me.shufork.common.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "role_name",
        "type",
        "create_by"})
@Data
public class RoleDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("role_name")
    private String name;

    @JsonProperty("create_by")
    private String createBy;

    @JsonProperty("type")
    private String type;
}
