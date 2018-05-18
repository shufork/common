package me.shufork.common.dto.session;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserSessionDto implements Serializable {
    private String id;
    private String loginName;
    private String status;
    private String displayName;
}
