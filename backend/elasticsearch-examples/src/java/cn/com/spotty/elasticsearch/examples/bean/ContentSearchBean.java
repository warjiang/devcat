package cn.com.spotty.elasticsearch.examples.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class ContentSearchBean implements Serializable {
    // 页数
    private Integer pageNumber;

    // 每页的条数
    private Integer pageSize;

    // 搜索内容
    private String searchContent;

    // 内容类型 1:文章 2:问题
    private Integer type;

    // 内容类别
    private String category;

    // 排序字段：id、read、support
    private String orderName;
}
