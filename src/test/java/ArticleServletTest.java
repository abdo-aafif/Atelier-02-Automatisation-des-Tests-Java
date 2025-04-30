import com.example.atelier02vf.ArticleServlet;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ArticleServletTest {

    @Test
    public void testAjoutArticleAvecTousLesParametres() throws Exception {
        // Mocks
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        // Simuler la liste des articles dans la session
        List<String> articles = new ArrayList<>();
        when(session.getAttribute("articles")).thenReturn(articles);
        when(request.getSession()).thenReturn(session);

        // Simuler les paramètres d'article
        when(request.getParameter("reference")).thenReturn("HP-123");
        when(request.getParameter("libelle")).thenReturn("HP EliteBook");
        when(request.getParameter("quantity")).thenReturn("5");

        // Simuler la réponse HTTP
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Exécuter la servlet
        ArticleServlet servlet = new ArticleServlet();
        servlet.doGet(request, response);

        writer.flush();
        String result = stringWriter.toString();

        // Affichage console
        System.out.println("Contenu des articles :\n" + result);

        // Vérifications
        assertTrue(result.contains("Ref: HP-123"));
        assertTrue(result.contains("Libelle: HP EliteBook"));
        assertTrue(result.contains("Quantite: 5"));
        assertTrue(articles.contains("Ref: HP-123, Libellé: HP EliteBook, Qté: 5"));
    }
}
