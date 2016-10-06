package shestakov.servlets;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * The type Role select servlet test.
 */
public class RoleSelectServletTest {

    /**
     * When change role should do it correct.
     *
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @Test
    public void whenChangeRoleShouldDoItCorrect() throws ServletException, IOException {
        RoleSelectServlet controller = new RoleSelectServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getAttribute("login")).thenReturn("Petr");
        when(request.getAttribute("role")).thenReturn("User");

        controller.doPost(request, response);

        verify(request, atLeast(1)).getAttribute("login");
        verify(request, atLeast(1)).getAttribute("role");
    }
}