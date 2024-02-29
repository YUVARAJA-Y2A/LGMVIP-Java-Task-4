package org.javatask.searchengine.controller;

import org.javatask.searchengine.model.SearchResult;
import org.javatask.searchengine.dao.SearchDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchController", urlPatterns = "/search-controller")
public class SearchController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<SearchResult> results = SearchDao.search(keyword);
        request.setAttribute("searchResults", results);
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}