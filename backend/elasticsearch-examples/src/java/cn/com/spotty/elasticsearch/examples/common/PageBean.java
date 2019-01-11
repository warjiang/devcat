package cn.com.spotty.elasticsearch.examples.common;

import lombok.Data;

import java.util.List;

@Data
public class PageBean<T> {
    // 当前页码
    private Integer pageNumber;

    // 每页数量
    private Integer pageSize;

    // 总页数
    private Integer totalPage;

    // 总数量
    private Integer totalCount;

    private List<T> result;

}
