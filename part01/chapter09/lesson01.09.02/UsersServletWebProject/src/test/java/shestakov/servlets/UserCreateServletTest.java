package shestakov.servlets;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

/**
 * The type User create servlet test.
 */
public class UserCreateServletTest {

    /**
     * When add user should do it correct.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenAddUserShouldDoItCorrect() throws Exception {
        UserCreateServlet controller = new UserCreateServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("login")).thenReturn("Petr");

        controller.doPost(request, response);

        verify(request, atLeast(1)).getParameter("login");
    }

}