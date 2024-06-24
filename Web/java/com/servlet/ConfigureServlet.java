package com.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConfigureServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectedModel = request.getParameter("model");
        
        Double basePrice = 0.0;  
        
        Map<String, Map<String, Double>> optionSets = new LinkedHashMap<>();
        try (Socket socket = new Socket("localhost", 5555);
             ObjectOutputStream serverOut = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream serverIn = new ObjectInputStream(socket.getInputStream())) {

            serverOut.writeObject(2);  // request configuration options for the selected model
            serverOut.writeObject(selectedModel);
            serverOut.flush();

            
            String menu = (String) serverIn.readObject(); //read menu first and discard
            String ignore = (String) serverIn.readObject(); //read and discard the string the server sends over
            String autoDetails = (String) serverIn.readObject();
            String[] lines = autoDetails.split("\n");
            for (String line : lines) {
                if (line.startsWith("Base Price:")) {
                	String priceStr = line.substring(line.indexOf("$") + 1).trim();
                	basePrice=Double.parseDouble(priceStr); 
                	
                }
                else if (!line.startsWith("Automotive Name:") && !line.startsWith("Base Price:")) {
                    String[] parts = line.split(":");
                    if (parts.length > 1) {
                        String optionSet = parts[0].trim();
                        Map<String, Double> options = new LinkedHashMap<>();
                        
                        String optionsData = line.substring(line.indexOf(':') + 1).trim();
                        String[] optionsParts = optionsData.split(",");

                        

                        for (String optionPart : optionsParts) {
                            String[] optionDetail = optionPart.split("=");
                            if (optionDetail.length == 2) {
                                String optionName = optionDetail[0].trim();
                                Double optionPrice = Double.parseDouble(optionDetail[1].trim());
                             
                                options.put(optionName, optionPrice);

                                
                            }
                        }
                        optionSets.put(optionSet, options);
                        System.out.println("Processed option set: " + optionSet + " with options: " + options);
                    }
                }

        }}catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Server communication error: " + e.getMessage());
        }

        request.setAttribute("model", selectedModel);
        request.setAttribute("basePrice", basePrice);  
        request.setAttribute("optionSets", optionSets);
        
        //debugging purposes
        
//        System.out.println("Model Selected: " + selectedModel);
//        System.out.println("Base Price: " + basePrice ));
       // System.out.println("Total Price:" + totalPrice);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("configureOptions.jsp");
        dispatcher.forward(request, response);
    }
}
