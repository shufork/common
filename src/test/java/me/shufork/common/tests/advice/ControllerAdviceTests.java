package me.shufork.common.tests.advice;

import me.shufork.common.dto.misc.ReplyBody;
import me.shufork.common.util.JsonUtil;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Configuration
@ComponentScan(basePackages = {"me.shufork.common.advice","me.shufork.biz.controller"})
//@PropertySource()
class SpringBootApp{

}


/**
 * 测试统一异常处理和返回类型二次包装
 */
@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes={SpringBootApp.class})
@TestPropertySource(
        //locations = {"classpath:no-security.yml"}
        properties = {
        "spring.security.enabled=false",
        "management.security.enabled=false",
        "security.basic.enabled=false"
        }
        )
public class ControllerAdviceTests {

    @Autowired
    private MockMvc mvc;

    //@MockBean
    //private UserVehicleService userVehicleService;

    @Test
    public void testResponseBodyAdvice()throws Exception{
        MvcResult result =this.mvc.perform(get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String json = result.getResponse().getContentAsString();
        System.out.println(json);
        ReplyBody parsed = JsonUtil.parseJson(json, ReplyBody.class);
        Assertions.assertThat(parsed).isNotNull();

        result =this.mvc.perform(get("/null").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        json = result.getResponse().getContentAsString();
        System.out.println(json);
        parsed = JsonUtil.parseJson(json, ReplyBody.class);
        Assertions.assertThat(parsed).isNotNull();

        result =this.mvc.perform(get("/throw-name?name=john").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        json = result.getResponse().getContentAsString();
        System.out.println(json);
        parsed = JsonUtil.parseJson(json, ReplyBody.class);
        Assertions.assertThat(parsed).isNotNull();
        Assertions.assertThat(parsed.getMoreInfo().contains("john"));
    }
    @Test
    public void testBadRequestHandling() throws Exception {
        MvcResult result =this.mvc.perform(get("/throw-name").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError()).andReturn();
        String json = result.getResponse().getContentAsString();
        System.out.println(json);
        ReplyBody parsed = JsonUtil.parseJson(json, ReplyBody.class);
        Assertions.assertThat(parsed).isNotNull();
    }
}
