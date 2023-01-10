package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        ObjectMapper mapper = new ObjectMapper();
        Path filePath = Paths.get("src/main/resources/users.json").toAbsolutePath().normalize();
        byte[] jsonInBytes = Files.readAllBytes(filePath);
        List<Map> list = List.of(mapper.readValue(jsonInBytes, Map[].class));
        return list;
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        List<Map<String, String>> users = getUsers();

        StringBuilder table = new StringBuilder();
        table.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>Example application | Users</title>
                        <link rel=\"stylesheet\" href=\"mysite.css\">
                    </head>
                    <body>
                """);
        table.append("<table>");
        table.append("<th><td>id</td><td>fullName</td></th>");

        for(Map<String, String> user: users) {
            table.append("<tr>");
            table.append("<td>");
            table.append(user.get("id"));
            table.append("</td>");
            table.append("<td>");
            table.append("<a href=\"/users/" + user.get("id") + "\">");
            table.append(user.get("firstName"));
            table.append(" ");
            table.append(user.get("lastName"));
            table.append("</a>");
            table.append("</td>");
            table.append("</tr>");

        }

        table.append("""
                        </table>
                    </body>
                </html>
                """);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(table.toString());
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        List<Map<String, String>> users = getUsers();

        StringBuilder table = new StringBuilder();
        table.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>Example application | Users</title>
                        <link rel=\"stylesheet\" href=\"mysite.css\">
                    </head>
                    <body>
                """);
        table.append("<table>");
        table.append("<th><td>id</td><td>fullName</td><td>email</td></th>");

        boolean isEmpty = true;
        for(Map<String, String> user: users) {
            if (id.equals(user.get("id"))) {
                isEmpty = false;
                table.append("<tr>");
                table.append("<td>");
                table.append(user.get("id"));
                table.append("</td>");
                table.append("<td>");
                table.append("<a href=\"/users/" + user.get("id") + "\">");
                table.append(user.get("firstName"));
                table.append(" ");
                table.append(user.get("lastName"));
                table.append("</a>");
                table.append("</td>");
                table.append("<td>");
                table.append(user.get("email"));
                table.append("</td>");
                table.append("</tr>");
            }

        }
        if (isEmpty) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        table.append("""
                        </table>
                    </body>
                </html>
                """);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(table.toString());
        // END
    }
}
