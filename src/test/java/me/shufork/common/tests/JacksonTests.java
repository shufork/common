package me.shufork.common.tests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import me.shufork.common.dto.user.RoleDto;
import me.shufork.common.util.JsonUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JacksonTests{

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "id",
            "login_name",
            "display_name",
            "cell_phone_number",
            "email"
            })
    @Data
    static class MyDto {
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

        @JsonProperty("roles")
        private List<RoleDto> roles;

    }

    @Test
    public void testJsonMapper() throws IOException {
        final MyDto dto = new MyDto();
        dto.setId("id-0");
        dto.setLoginName("bobo123");
        dto.setDisplayName("bobo-li");
        dto.setPassword("xxxxxxxxxx");
        dto.setCellPhoneNumber("18012345678");
        dto.setEmail("bobo@shufork.com");

        final String json = JsonUtil.toJsonOrNull(dto);
        System.out.println(json);

        assertEquals(json.indexOf("roles"),-1); //  NonNull
        assertEquals(json.indexOf("cell_phone_number") < json.indexOf("email"),true);// PropertyOrder
        final MyDto dto2 = JsonUtil.parseJson(json,MyDto.class);
        assertEquals(dto2.getLoginName(),"bobo123"); //  NonNull
    }
}