package cn.com.spotty.elasticsearch.examples.service;

import cn.com.spotty.elasticsearch.examples.bean.ContentBean;
import cn.com.spotty.elasticsearch.examples.bean.ContentSearchBean;
import cn.com.spotty.elasticsearch.examples.common.PageBean;

import java.util.List;

public interface ContentService {

    /**
     * 批量向 ES 保存内容
     */
    boolean saveContents(List<ContentBean> contentBeanList);

    /**
     * 搜索
     */
    PageBean searchContent(ContentSearchBean contentSearchBean);
}
