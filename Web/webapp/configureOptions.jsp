<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %> 
<%@ page import="java.util.HashMap" %> 
<!DOCTYPE html>
<html>
<head>
    <title>BOB SINGH BLUE BOOK</title>
    <style>
        body {
            background-color: blue; 
            color: gold; 
        }
        label { 
            display: block; 
            margin-top: 20px; 
        }
        select { 
            width: 200px;
            background-color: white;
            color: black; 
        }
        .price-info { 
            margin-top: 20px; 
        }
        h1, h2 {
            color: gold; 
        }
    </style>
</head>
<body>
<h1>Jason Vu's AUTO: <%= request.getAttribute("model") %></h1>
<% if (request.getAttribute("basePrice") != null) { %>
    <h2>Base Price: $<%= request.getAttribute("basePrice") %></h2>
<% } %>

<% if (request.getAttribute("selectedOptions") == null) { %>
    <form action="CalculatePriceServlet" method="post">
        <input type="hidden" name="model" value="<%= request.getAttribute("model") %>">
        <input type="hidden" name="basePrice" value="<%= request.getAttribute("basePrice") %>">
        <% 
            Map<String, Map<String, Double>> optionSets = (Map<String, Map<String, Double>>) request.getAttribute("optionSets");
            if (optionSets != null) {
                for (java.util.Map.Entry<String, java.util.Map<String, Double>> entry : optionSets.entrySet()) {
                    String optionSetName = entry.getKey();
                    java.util.Map<String, Double> options = entry.getValue();
        %>
            <div>
                <label for="<%= optionSetName %>"><%= optionSetName %>:</label>
                <select id="<%= optionSetName %>" name="<%= optionSetName %>">
                    <option value="">----</option> <!-- default empty option -->
                    <% 
                    String selectedOption = request.getParameter(optionSetName);
                    for (java.util.Map.Entry<String, Double> option : options.entrySet()) {
                        String selected = (option.getKey().equals(selectedOption) ? " selected" : "");
                    %>
                        <option value="<%= option.getKey() %>~<%= option.getValue() %>"<%= selected %>><%= option.getKey() %> ($<%= option.getValue() %>)</option>
                    <% } %>
                </select>
            </div>
        <% 
                }
            }
        %>
        <input type="submit" value="Calculate Total Price">
    </form>
<% } %>

<% if (request.getAttribute("selectedOptions") != null) { %>
    <div class="price-info">
        <ul>
            <% 
            Map<String, Double> selectedOptions = (Map<String, Double>) request.getAttribute("selectedOptions");
            for (Map.Entry<String, Double> option : selectedOptions.entrySet()) { %>
                <li><%= option.getKey() %>: $<%= option.getValue() %></li>
            <% } %>
        </ul>
        <h2>Final Price: $<%= request.getAttribute("totalPrice") %></h2>
    </div>
<% } %>

</body>
</html>
