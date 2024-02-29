package org.javatask.searchengine.dao;

import org.javatask.searchengine.model.SearchResult;
import org.javatask.searchengine.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchDao {
    public static List<SearchResult> search(String keyword) {
        List<SearchResult> results = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT title, url, description FROM search_results WHERE keyword = ?")) {

            preparedStatement.setString(1, keyword);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Add logging statements to print or log the retrieved results
                    System.out.println("Title: " + resultSet.getString("title"));
                    System.out.println("URL: " + resultSet.getString("url"));
                    System.out.println("Description: " + resultSet.getString("description"));

                    SearchResult result = new SearchResult();
                    result.setTitle(resultSet.getString("title"));
                    result.setUrl(resultSet.getString("url"));
                    result.setDescription(resultSet.getString("description"));
                    results.add(result);
                }
            }
        } catch (SQLException e) {
            // Add logging statements to print or log the exception
            e.printStackTrace();
        }

        return results;
    }
}
