import com.example.atelier02vf.FactureServlet;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FactureServletTest {

    @Test
    public void testFactureServlet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(writer);

        FactureServlet servlet = new FactureServlet();
        servlet.doGet(request, response);

        verify(response).getWriter();
        writer.flush(); // ensure content is written

        String jsonResult = stringWriter.toString();
        System.out.println("JSON retourné : " + jsonResult);

        // Vérifier que le contenu JSON contient les bonnes données
        Gson gson = new Gson();
        Map data = gson.fromJson(jsonResult, Map.class);

        assertEquals("Imane Haffou", data.get("client"));
        assertEquals(2500.0, data.get("total")); // Gson converts numbers to Double
    }
}
