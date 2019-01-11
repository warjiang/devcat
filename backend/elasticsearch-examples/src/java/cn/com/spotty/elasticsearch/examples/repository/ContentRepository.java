package cn.com.spotty.elasticsearch.examples.repository;

import cn.com.spotty.elasticsearch.examples.entity.ContentEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends ElasticsearchRepository<ContentEntity, Long> {
}
