package shestakov.servlets;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * The type User delete servlet test.
 */
public class UserDeleteServletTest {

    /**
     * When delete user should do it correct.
     *
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @Test
    public void whenDeleteUserShouldDoItCorrect() throws ServletException, IOException {
        UserDeleteServlet controller = new UserDeleteServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("user")).thenReturn("Petr");

        controller.doPost(request, response);

        verify(request, atLeast(1)).getParameter("user");
    }

}