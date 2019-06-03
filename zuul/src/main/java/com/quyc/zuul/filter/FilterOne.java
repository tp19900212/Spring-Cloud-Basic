package com.quyc.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: andy
 * @create: 2019/6/3 19:40
 * @description: filter one
 */
@Component
public class FilterOne extends ZuulFilter {

    /**
     * 下游服务之前进行处理
     *
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤器顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断哪些链接需要过滤
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }


    /**
     * 处理逻辑
     *
     * @return
     */
    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        HttpServletResponse response = requestContext.getResponse();
        StringBuffer requestURL = request.getRequestURL();
        System.out.println("requestURL = " + requestURL);
        return null;
    }


}
