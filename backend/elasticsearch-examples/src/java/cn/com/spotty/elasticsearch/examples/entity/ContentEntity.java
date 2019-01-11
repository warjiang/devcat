package cn.com.spotty.elasticsearch.examples.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Document(indexName = "content", type = "content")
@Data
public class ContentEntity implements Serializable {
    // 内容 ID
    @Id
    private Long id;

    // 内容标题
    private String title;

    // 内容
    private String content;

    // 内容类型 1:文章 2:问题
    private Integer type;

    // 内容类别
    private String category;

    // 文章阅读数
    private Integer read;

    // 问题支持数
    private Integer support;
}
