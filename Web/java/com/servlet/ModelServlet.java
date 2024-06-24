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
import java.util.ArrayList;
import java.util.List;

public class ModelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> models = new ArrayList<>();
        
        try (Socket socket = new Socket("localhost", 5555);
             ObjectOutputStream serverOut = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream serverIn = new ObjectInputStream(socket.getInputStream())) {
            
            serverOut.writeObject(3);  //request all automobile models in BuildCarModelOptions.java in server package, 3=case
            serverOut.flush();

            // Read the menu first and discard it
            String menu = (String) serverIn.readObject();  //read the menu first and discard it

            // Now read the actual car models
            String carModels = (String) serverIn.readObject();
            String[] cars = carModels.split("\n");  //split by newline to get individual models

            for (String car : cars) {
                if (!car.trim().isEmpty()) {
                    models.add(car.trim());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        request.setAttribute("models", models);
        RequestDispatcher dispatcher = request.getRequestDispatcher("selectModel.jsp");
        dispatcher.forward(request, response);
    }
}
