package cn.com.spotty.elasticsearch.examples.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBean {
    private int code;

    private String message;

    private Object data;

    /**
     * 正常返回
     */
    public static ResponseBean success(Object object) {
        return new ResponseBean(0, "success", object);
    }

    /**
     * 错误返回
     */
    public static ResponseBean error(Exception exception) {
        return new ResponseBean(-1, "error", exception.getMessage());
    }

    /**
     * 操作失败
     */
    public static ResponseBean fail(Object object) {
        return new ResponseBean(-2, "fail", object);
    }

    /**
     * 正常返回，分页对象
     */
    public static ResponseBean successPage(PageBean page) {
        return new ResponseBean(0, "success", page);
    }
}
