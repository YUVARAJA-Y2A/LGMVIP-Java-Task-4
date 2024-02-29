package org.javatask.searchengine;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.javatask.searchengine.dao.SearchDao;
import org.javatask.searchengine.model.SearchResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Your search logic here
        String keyword = request.getParameter("keyword");

        // Perform search and get search results
        List<SearchResult> searchResults = SearchDao.search(keyword);

        // Forward the results to a JSP page
        request.setAttribute("searchResults", searchResults);
        request.getRequestDispatcher("/search.jsp").forward(request, response);
    }

    // Rest of your code...

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setResourceBase("src/main/webapp");
        webAppContext.setContextPath("/");
        webAppContext.setParentLoaderPriority(true);

        server.setHandler(webAppContext);

        server.start();
        server.join();
    }
}
