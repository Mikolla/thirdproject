package ru.thirdproject.filter;

import ru.thirdproject.model.User;
import ru.thirdproject.service.abstraction.user.UserService;
import ru.thirdproject.service.impl.user.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    private final UserService userService = new UserServiceImpl();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)
            throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest)request).getSession();
        User user = (User) session.getAttribute("user");
        String role = user.getRole();

        if (role.equals("admin")) {
            filterChain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("/accessDenied");
        }

    }

    @Override
    public void destroy() {

    }


}
