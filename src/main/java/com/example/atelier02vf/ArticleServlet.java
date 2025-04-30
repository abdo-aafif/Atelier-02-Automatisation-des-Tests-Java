package com.example.atelier02vf;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "articleServlet", value = "/articles")
public class ArticleServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        // Récupérer ou créer la liste des articles
        List<String> articles = (List<String>) session.getAttribute("articles");
        if (articles == null) {
            articles = new ArrayList<>();
            session.setAttribute("articles", articles);
        }

        // Récupérer les paramètres d'article (référence, libellé, quantité)
        String reference = request.getParameter("reference");
        String libelle = request.getParameter("libelle");
        String quantity = request.getParameter("quantity");

        // Ajouter un article si tous les champs sont fournis
        if (reference != null && libelle != null && quantity != null &&
                !reference.isEmpty() && !libelle.isEmpty() && !quantity.isEmpty()) {
            String articleStr = "Ref: " + reference + ", Libelle: " + libelle + ", Quantité: " + quantity;
            articles.add(articleStr);
        }

        // Afficher la liste des articles
        response.setContentType("text/plain");
        response.getWriter().println("Liste des articles dans la session :");
        for (String art : articles) {
            response.getWriter().println("- " + art);
        }
    }
}
