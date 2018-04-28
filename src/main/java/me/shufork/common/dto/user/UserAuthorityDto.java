package me.shufork.common.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserAuthorityDto {
    @NotNull(message = "'authority' is required")
    private String authority;
}
