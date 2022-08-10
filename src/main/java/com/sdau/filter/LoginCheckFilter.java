package com.sdau.filter;

import com.alibaba.fastjson.JSON;

import com.sdau.common.BaseContext;
import com.sdau.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否已经完成登录
 */
@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    //专门进行路径比较 ，路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        //1.获取本次请求的URI
        String requestURI = httpServletRequest.getRequestURI();

        //  {} 占位符
        log.info("拦截到请求：{}", httpServletRequest.getRequestURI());

        //2.定义不需要处理的请求路径
        String[] urls = new String[]{
                "/admin/login",
                "/admin/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/academy/**",
                "/teacher/**",
                "/student/**"
        };

        //3.判断本次请求是否需要处理
        Boolean check = check(urls, requestURI);

        //4.1不需要处理直接放行
        if (check) {
            log.info("本次请求{}不需要处理", requestURI);
            //放行
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        //4.21判断管理端登录状态，如果已登录，则直接放行
        if (httpServletRequest.getSession().getAttribute("admin") != null) {
            log.info("用户已登录，用户id为：{}", httpServletRequest.
                    getSession().getAttribute("admin"));

            Long admin = (Long) httpServletRequest.getSession().getAttribute("admin");
            BaseContext.setCurrentId(admin);

            //放行
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        //4.22判断移动端登录状态，如果已登录，则直接放行
        if (httpServletRequest.getSession().getAttribute("teacher") != null) {
            log.info("用户已登录，用户id为：{}", httpServletRequest.
                    getSession().getAttribute("teacher"));

            Long teacherId = (Long) httpServletRequest.getSession().getAttribute("teacher");
            BaseContext.setCurrentId(teacherId);

            //放行
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }


        log.info("用户未登录");
        //5.如果未登录则返回未登录结果
        httpServletResponse.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;


    }

    /**
     * 路径匹配，检查本次请求是否需要放行
     *
     * @param requestURI
     * @return
     */
    public Boolean check(String urls[], String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            //匹配上了
            if (match) return true;
        }
        return false;
    }
}
