package com.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class CalculatePriceServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String model = request.getParameter("model");
        Double basePrice = Double.parseDouble(request.getParameter("basePrice"));
        double totalPrice = basePrice;

        Map<String, Double> selectedOptions = new LinkedHashMap<>();

        //iterate over each parameter and split out model and basePrice
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {  //iterate over each parameter and split out model and basePrice
            String key = entry.getKey();
            String[] value = entry.getValue();
       
            if (!key.equals("model") && !key.equals("basePrice") && value != null && value.length > 0 && !value[0].isEmpty()) {
                String selectedOption = value[0];
                
                if (selectedOption.contains("~")) { // I made option value formatted to "optionName~optionPrice"
                    String[] optionParts = selectedOption.split("~");
                    if (optionParts.length > 1) {
                        double optionPrice = Double.parseDouble(optionParts[1]);
                        totalPrice += optionPrice;
                        selectedOptions.put(optionParts[0], optionPrice); //store option name and price for display
                    }
                }
            }
        }
      //send the info back to JSP
        request.setAttribute("model", model);
        request.setAttribute("basePrice", basePrice);
        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("selectedOptions", selectedOptions); //send the selected options back to JSP

        RequestDispatcher dispatcher = request.getRequestDispatcher("configureOptions.jsp");
        dispatcher.forward(request, response);
    }
}
