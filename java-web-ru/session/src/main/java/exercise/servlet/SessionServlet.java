package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import java.util.Map;
import java.util.Objects;

import static exercise.App.getUsers;
import exercise.Users;

public class SessionServlet extends HttpServlet {

    private Users users = getUsers();

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        if (request.getRequestURI().equals("/login")) {
            showLoginPage(request, response);
            return;
        }

        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {

        switch (request.getRequestURI()) {
            case "/login" -> login(request, response);
            case "/logout" -> logout(request, response);
            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showLoginPage(HttpServletRequest request,
                               HttpServletResponse response)
            throws IOException, ServletException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
        requestDispatcher.forward(request, response);
    }

    private void login(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {

        // BEGIN
        HttpSession session = request.getSession();
        session.removeAttribute("flash");
//        session.invalidate();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(">>>> ");
        System.out.println("getAttribute " + session.getAttribute("flash"));
        System.out.println("isNew " + session.isNew());
        System.out.println("getLastAccessedTime " + session.getLastAccessedTime());
        System.out.println("getId " + session.getId());
        System.out.println("getCreationTime " + session.getCreationTime());

        Map<String, String> user = users.findByEmail(email);
        if (user != null && Objects.equals(user.get("password"), password)) {
            session.setAttribute("userId", user.get("id"));
            if (session.getCreationTime() == session.getLastAccessedTime() ) {
                session.setAttribute("flash", "Вы успешно вошли");
            }
            response.sendRedirect("/");

        } else {
            response.setStatus(422);

            request.setAttribute("user", user);
            session.setAttribute("flash", "Неверные логин или пароль");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(request, response);
        }
        // END
    }

    private void logout(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException {

        // BEGIN
        HttpSession session = request.getSession();

        session.removeAttribute("userId");
        session.setAttribute("flash", "Вы успешно вышли");

        response.sendRedirect("/");
        // END
    }
}
