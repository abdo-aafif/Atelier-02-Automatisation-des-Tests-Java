import com.example.atelier02vf.PanierServlet;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class PanierServletTest {

    @Test
    public void testAjoutProduitAuPanier() throws Exception {
        // Mocks
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        // Simuler la session et un panier existant
        List<String> panier = new ArrayList<>();
        when(session.getAttribute("panier")).thenReturn(panier);
        when(request.getSession()).thenReturn(session);

        // Simuler le paramètre produit
        when(request.getParameter("produit")).thenReturn("Livre");

        // Capture de la réponse
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Exécution
        PanierServlet servlet = new PanierServlet();
        servlet.doGet(request, response);

        writer.flush();
        String result = stringWriter.toString();
        System.out.println("Contenu du panier :\n" + result);

        // Vérifier le contenu affiché
        assertTrue(result.contains("- Livre"));
        assertTrue(panier.contains("Livre")); // en mémoire
    }
}