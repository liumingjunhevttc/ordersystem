package com.baidu.filter;

import com.baidu.entity.Account;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "*",filterName = "loginfilter")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        Account account = (Account) request.getSession().getAttribute("account");
        if(!uri.contains("user")&&!uri.contains("admin")){
            filterChain.doFilter(request,response);
            return;
        } else {
            if(account == null){
                response.sendRedirect("/login");
            } else {
                if(uri.contains("html")){
                    if("user".equals(account.getType())){
                        if(!uri.contains("user")){
                            response.sendRedirect("/user/index.html");
                            return;
                        }
                    } if("admin".equals(account.getType())){
                        if(!uri.contains("admin")){
                            response.sendRedirect("/admin/index.html");
                            return;
                        }
                    }
                }
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
