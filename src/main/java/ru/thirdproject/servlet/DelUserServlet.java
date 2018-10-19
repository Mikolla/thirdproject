package ru.thirdproject.servlet;


import ru.thirdproject.service.abstraction.user.UserService;
import ru.thirdproject.service.impl.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/deluser")
public class DelUserServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    public DelUserServlet()  {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long userIdToDel = Long.parseLong(request.getParameter("id"));
        userService.deleteUser(userIdToDel);
        response.sendRedirect("/admin/allusers");
    }

}
