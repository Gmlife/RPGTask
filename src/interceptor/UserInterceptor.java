package interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {
        // 获取请求的URL
        String url = request.getRequestURI();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("USER_SESSION");
        // 判断Session中是否有用户数据，如果有，则返回true,继续向下执行
        if (user == null) {
            request.setAttribute("msg", "您还没有登录，请先登录！");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp")
                    .forward(request, response);
            return  false;
        }
        boolean type =user.getUserType();
        if(!type){
            if(url.contains("/Route/add.action")||
                    url.contains("/Route/delete.action")||
                    url.contains("/Route/update.action"))
                request.getRequestDispatcher("Route/list.action").forward(request, response);;
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}
