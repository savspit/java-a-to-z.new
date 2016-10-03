package shestakov.servlets;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

/**
 * The type User update servlet test.
 */
public class UserUpdateServletTest {

    /**
     * When update user should do it correct.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenUpdateUserShouldDoItCorrect() throws Exception {
        UserUpdateServlet controller = new UserUpdateServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("login")).thenReturn("Petr");
        when(request.getParameter("name")).thenReturn("Mike");
        when(request.getParameter("update")).thenReturn("update");

        controller.doPost(request, response);

        verify(request, atLeast(1)).getParameter("login");
        verify(request, atLeast(1)).getParameter("name");
        verify(request, atLeast(1)).getParameter("update");
    }

}