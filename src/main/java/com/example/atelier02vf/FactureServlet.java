package com.example.atelier02vf;

import java.io.IOException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "facture", value = "/facture")
public class FactureServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        // Sample facture data
        Map<String, Object> facture = new HashMap<>();
        facture.put("datefacture", "2025-05-01");
        facture.put("client", "Imane Haffou");
        facture.put("total", 2500);

        // Convert to JSON using Gson
        Gson gson = new Gson();
        String json = gson.toJson(facture);

        response.getWriter().write(json);
    }
}
