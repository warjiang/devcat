package cn.com.spotty.elasticsearch.examples.service.impl;

import cn.com.spotty.elasticsearch.examples.bean.ContentBean;
import cn.com.spotty.elasticsearch.examples.bean.ContentSearchBean;
import cn.com.spotty.elasticsearch.examples.common.PageBean;
import cn.com.spotty.elasticsearch.examples.constant.SearchConstant;
import cn.com.spotty.elasticsearch.examples.entity.ContentEntity;
import cn.com.spotty.elasticsearch.examples.repository.ContentRepository;
import cn.com.spotty.elasticsearch.examples.service.ContentService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


@Service
@Primary
@AllArgsConstructor
@Log4j2
public class ContentServiceImpl implements ContentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentServiceImpl.class);

    private final ContentRepository contentRepository;

    @Override
    public boolean saveContents(List<ContentBean> contentBeanList) {
        List<ContentEntity> contentEntityList = transferToContentEntityList(contentBeanList);
        contentRepository.saveAll(contentEntityList);//saveAll
        return true;
    }

    @Override
    public PageBean searchContent(ContentSearchBean contentSearchBean) {
        Integer pageNumber = contentSearchBean.getPageNumber();
        Integer pageSize = contentSearchBean.getPageSize();

        PageBean<ContentEntity> resultPageBean = new PageBean<>();
        resultPageBean.setPageNumber(pageNumber);
        resultPageBean.setPageSize(pageSize);

        // 构建查询条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        // 搜索内容
        String searchContent = contentSearchBean.getSearchContent();
        if (!StringUtils.isEmpty(searchContent)) {
            boolQueryBuilder.should(QueryBuilders.matchPhraseQuery(SearchConstant.CONTENT_ES_NAME, searchContent));
            boolQueryBuilder.minimumShouldMatch(SearchConstant.MINIMUM_SHOULD_MATCH);
        }

        // 内容类型筛选
        Integer type = contentSearchBean.getType();
        if (type != null) {
            boolQueryBuilder.must(QueryBuilders.matchQuery(SearchConstant.TYPE_ES_NAME, type).lenient(true));
        }

        // 内容类别筛选
        String category = contentSearchBean.getCategory();
        if (!StringUtils.isEmpty(category)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery(SearchConstant.CATEGORY_ES_NAME, category).lenient(true));
        }

        // 构建分页、排序条件
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        if (!StringUtils.isEmpty(contentSearchBean.getOrderName())) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, contentSearchBean.getOrderName());
        }
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                                        .withPageable(pageable)
                                        .withQuery(boolQueryBuilder)
                                        .build();
        // 搜索
        LOGGER.info("\n ContentServiceImpl.searchContent() DSL  = \n " + searchQuery.getQuery().toString());
        Page<ContentEntity> contentPage = contentRepository.search(searchQuery);

        resultPageBean.setResult(contentPage.getContent());
        resultPageBean.setTotalCount((int) contentPage.getTotalElements());
        resultPageBean.setTotalPage((int) contentPage.getTotalElements() / resultPageBean.getPageSize() + 1);
        return resultPageBean;

    }


    /**
     * 转换
     */
    private List<ContentEntity> transferToContentEntityList(final List<ContentBean> contentBeanList) {
        List<ContentEntity> contentEntityList = new ArrayList<>();

        contentBeanList.forEach(contentBean -> {
            ContentEntity contentEntity = transferToContentEntity(contentBean);
            contentEntityList.add(contentEntity);
        });

        return contentEntityList;
    }


    /**
     * 转换
     */
    private ContentEntity transferToContentEntity(final ContentBean contentBean) {
        if (contentBean == null)
            return null;

        ContentEntity contentEntity = new ContentEntity();
        contentEntity.setId(contentBean.getId());
        contentEntity.setTitle(contentBean.getTitle());
        contentEntity.setContent(contentBean.getContent());
        contentEntity.setType(contentBean.getType());
        contentEntity.setCategory(contentBean.getCategory());
        contentEntity.setRead(contentBean.getRead());
        contentEntity.setSupport(contentBean.getSupport());

        return contentEntity;
    }
}
