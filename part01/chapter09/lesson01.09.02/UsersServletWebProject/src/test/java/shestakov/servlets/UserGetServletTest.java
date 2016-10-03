package shestakov.servlets;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * The type User get servlet test.
 */
public class UserGetServletTest {

    /**
     * When going edit user should do it correct.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGoingEditUserShouldDoItCorrect() throws Exception {
        UserGetServlet controller = new UserGetServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter("user")).thenReturn("Petr");
        when(request.getParameter("edit")).thenReturn("edit");
        when(request.getSession()).thenReturn(session);
        when(request.getContextPath()).thenReturn("/update/");

        controller.doPost(request, response);

        verify(request, atLeast(1)).getParameter("user");
        verify(request, atLeast(1)).getParameter("edit");
        verify(request, atLeast(1)).getSession();
        verify(request, atLeast(1)).getContextPath();

        assertThat(request.getContextPath(), is("/update/"));
    }

    /**
     * When going delete user should do it correct.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGoingDeleteUserShouldDoItCorrect() throws Exception {
        UserGetServlet controller = new UserGetServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter("user")).thenReturn("Petr");
        when(request.getParameter("edit")).thenReturn("edit");
        when(request.getSession()).thenReturn(session);
        when(request.getContextPath()).thenReturn("/delete/");

        controller.doPost(request, response);

        verify(request, atLeast(1)).getParameter("user");
        verify(request, atLeast(1)).getParameter("edit");
        verify(request, atLeast(1)).getSession();
        verify(request, atLeast(1)).getContextPath();

        assertThat(request.getContextPath(), is("/delete/"));
    }

    /**
     * When going edit role user should do it correct.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGoingEditRoleUserShouldDoItCorrect() throws Exception {
        UserGetServlet controller = new UserGetServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter("user")).thenReturn("Petr");
        when(request.getParameter("edit")).thenReturn("edit");
        when(request.getSession()).thenReturn(session);
        when(request.getContextPath()).thenReturn("/editRole/");

        controller.doPost(request, response);

        verify(request, atLeast(1)).getParameter("user");
        verify(request, atLeast(1)).getParameter("edit");
        verify(request, atLeast(1)).getSession();
        verify(request, atLeast(1)).getContextPath();

        assertThat(request.getContextPath(), is("/editRole/"));
    }

}