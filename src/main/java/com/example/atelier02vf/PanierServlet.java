package com.example.atelier02vf;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "panier", value = "/panier")
public class PanierServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        // Récupérer ou créer la liste des produits
        List<String> panier = (List<String>) session.getAttribute("panier");
        if (panier == null) {
            panier = new ArrayList<>();
            session.setAttribute("panier", panier);
        }

        // Ajouter un produit si présent en paramètre
        String produit = request.getParameter("produit");
        if (produit != null && !produit.isEmpty()) {
            panier.add(produit);
        }

        // Afficher le contenu du panier
        response.setContentType("text/plain");
        for (String p : panier) {
            response.getWriter().println("- " + p);
        }
    }
}
