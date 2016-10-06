package shestakov.servlets;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * The type Role create servlet test.
 */
public class RoleCreateServletTest {

    /**
     * When add role should do it correct.
     *
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @Test
    public void whenAddRoleShouldDoItCorrect() throws ServletException, IOException {
        RoleCreateServlet controller = new RoleCreateServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("name")).thenReturn("User");

        controller.doPost(request, response);

        verify(request, atLeast(1)).getParameter("name");
    }

}