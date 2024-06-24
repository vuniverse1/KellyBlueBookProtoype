<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <title>BOB SINGH CAR SITE</title>
    <style>
        body {
            background-color: blue; 
            color: gold; 
        }
        select, input[type="submit"] {
            background-color: white; 
            color: black; 
            margin-top: 10px;
        }
        input[type="submit"] {
            cursor: pointer; 
        }
    </style>
</head>
<body>
    <h2>Select an Automobile to Configure</h2>
    <form action="ConfigureServlet" method="post">
        <select name="model">
            <% 
                List<String> models = (List<String>) request.getAttribute("models");
                for (String model : models) {
                    out.println("<option value=\"" + model + "\">" + model + "</option>");
                }
            %>
        </select>
        <input type="submit" value="Configure">
    </form>
</body>
</html>
