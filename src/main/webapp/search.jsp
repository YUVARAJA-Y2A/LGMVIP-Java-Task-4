<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Results</title>
</head>
<body>
<h1>Search Results</h1>
<% List<SearchResult> searchResults = (List<SearchResult>) request.getAttribute("searchResults");
    if (searchResults != null && !searchResults.isEmpty()) {
        for (SearchResult result : searchResults) { %>
<div>
    <h2><%= result.getTitle() %></h2>
    <p><%= result.getDescription() %></p>
    <p><%= result.getUrl() %></p>
</div>
<% }
} else { %>
<p>No results found.</p>
<% } %>
</body>
</html>