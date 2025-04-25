import com.example.atelier02vf.HelloServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class HelloServletTest {
    @Test
    public void testHelloServlet() throws Exception {
        // Simulation des objets HttpServletRequest/Response
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simulation du PrintWriter
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        // Simulation de la méthode getWriter() pour renvoyer notre PrintWriter simulé
        when(response.getWriter()).thenReturn(writer);

        // Création et appel du servlet
        HelloServlet servlet = new HelloServlet();
        servlet.doGet(request, response);

        // Vérification que getWriter() a été appelé
        verify(response).getWriter();

        // Récupération du contenu écrit dans le PrintWriter
        String result = stringWriter.toString();
        System.out.println("Contenu retourné par le servlet : " + result);

        // Vérification du contenu de la réponse
        assertTrue(result.contains("Hello, World"), "La sortie ne contient pas 'Hello, World'");
    }
}
