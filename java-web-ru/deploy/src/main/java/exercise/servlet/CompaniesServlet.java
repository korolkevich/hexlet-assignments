package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        // BEGIN
        String search = request.getParameter("search");
        List<String> companies = getCompanies();
        PrintWriter out = response.getWriter();


        List<String> filteredCompanies = companies.stream()
                .filter(comp -> filterCompany(comp, search))
                .collect(Collectors.toList());

        if (filteredCompanies.size() > 0) {
            for (String comp : filteredCompanies) {
                out.println(comp);
            }
        } else {
            out.println("Companies not found");
        }

        // END
    }

    private static boolean filterCompany(String company, String search) {
        if (search == null || search.equals("")) {
            return true;
        } else {
            return company.contains(search);
        }
    }
}
