package me.shufork.common.dto.misc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "code",
        "message",
        "moreInfo",
        "data",
        "dataList"})
@Data
@NoArgsConstructor
public class ReplyBody <T>{
    private String code;
    private String message;
    private String moreInfo;

    private T data;
    private List<T> dataList;
    private PageResult<T> pageResult;

    public ReplyBody(String code) {
        this(code,null,null);
    }

    public ReplyBody(String code, String message, String moreInfo) {
        this.code = code;
        this.message = message;
        this.moreInfo = moreInfo;
    }
}
