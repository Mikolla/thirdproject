package ru.thirdproject.servlet;


import ru.thirdproject.model.User;
import ru.thirdproject.service.abstraction.user.UserService;
import ru.thirdproject.service.impl.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    public LoginServlet()  {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       request.getRequestDispatcher("/index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userLogin = (request.getParameter("login"));
        String userPassword = (request.getParameter("password"));

        if (  (userLogin == null || userPassword == null) || ( (userLogin.isEmpty() || userPassword.isEmpty())  )  ) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("empty username or password");
            response.sendRedirect("login");
            return;
        }

        User user = userService.getUserByLogin(userLogin);
        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        if (userPassword.equals(user.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.setContentType("text/html");
            if (user.getRole().equals("admin")) {
                response.sendRedirect("admin/allusers");
            } else {
                response.sendRedirect("user");
            }

        } else {
            response.sendRedirect("login");
        }



    }

}
