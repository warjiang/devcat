package cn.com.spotty.elasticsearch.examples.api;

import cn.com.spotty.elasticsearch.examples.bean.ContentBean;
import cn.com.spotty.elasticsearch.examples.bean.ContentSearchBean;
import cn.com.spotty.elasticsearch.examples.common.PageBean;
import cn.com.spotty.elasticsearch.examples.common.ResponseBean;
import cn.com.spotty.elasticsearch.examples.service.ContentService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Log4j2
public class ContentCommonApi {
    private final ContentService contentService;

    @RequestMapping(value = "/contents")
    public ResponseBean saveContents(@RequestBody List<ContentBean> contentBeanList) {
        boolean result = contentService.saveContents(contentBeanList);
        if (result) {
            return ResponseBean.success(result);
        }
        return ResponseBean.fail(result);
    }

    @PostMapping(value = "/content/search")
    public ResponseBean searchContent(@RequestBody ContentSearchBean contentSearchBean) {

        PageBean pageBean = contentService.searchContent(contentSearchBean);
        return ResponseBean.successPage(pageBean);
    }
}
