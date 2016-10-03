package shestakov.servlets;

import org.junit.Test;
import shestakov.models.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

/**
 * The type Role update servlet test.
 */
public class RoleUpdateServletTest {

    /**
     * When update role should do it correct.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenUpdateRoleShouldDoItCorrect() throws Exception {
        RoleUpdateServlet controller = new RoleUpdateServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter("name")).thenReturn("UserRoleChanged");
        when(request.getParameter("update")).thenReturn("update");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn(new Role("User"));

        controller.doPost(request, response);

        verify(request, atLeast(1)).getParameter("name");
        verify(request, atLeast(1)).getParameter("update");
        verify(request, atLeast(1)).getSession();
        verify(session, atLeast(1)).getAttribute("role");
    }

}