package shestakov.servlets;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import shestakov.models.Role;
import shestakov.models.User;
import shestakov.postgresql.DBUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

/**
 * The type Role get servlet test.
 */
public class RoleGetServletTest {

    /**
     * Create tables.
     */
    @BeforeClass
    public static void createTables() {
        DBUtils.getInstance().createTables();
        DBUtils.getInstance().deleteAllUsersAndRoles();
    }

    /**
     * Delete data in tables.
     */
    @AfterClass
    public static void deleteDataInTables() {
        DBUtils.getInstance().deleteAllUsersAndRoles();
    }

    /**
     * When going select role should do it correct.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGoingSelectRoleShouldDoItCorrect() throws Exception {
        Role role = new Role("User");
        DBUtils.getInstance().addRole(role);

        RoleGetServlet controller = new RoleGetServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("role")).thenReturn(role.getName());
        when(request.getParameter("select")).thenReturn("select");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("login")).thenReturn(role.getName());
        when(request.getRequestDispatcher("/roleSelect")).thenReturn(dispatcher);

        controller.doPost(request, response);

        verify(request, atLeast(1)).getParameter("role");
        verify(request, atLeast(1)).getParameter("select");
        verify(request, atLeast(1)).getSession();
        verify(session, atLeast(1)).getAttribute("login");
        verify(request, atLeast(1)).getRequestDispatcher("/roleSelect");
    }

    /**
     * When going edit role should do it correct.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGoingEditRoleShouldDoItCorrect() throws Exception {
        Role role = new Role("User");
        DBUtils.getInstance().addRole(role);

        RoleGetServlet controller = new RoleGetServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter("role")).thenReturn(role.getName());
        when(request.getParameter("edit")).thenReturn("edit");
        when(request.getSession()).thenReturn(session);
        when(request.getContextPath()).thenReturn("/roleUpdate/");

        controller.doPost(request, response);

        verify(request, atLeast(1)).getParameter("role");
        verify(request, atLeast(1)).getParameter("select");
        verify(request, atLeast(1)).getSession();
        verify(request, atLeast(1)).getContextPath();

        assertThat(request.getContextPath(), is("/roleUpdate/"));
    }

    /**
     * When going delete role should do it correct.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGoingDeleteRoleShouldDoItCorrect() throws Exception {
        Role role = new Role("User");
        DBUtils.getInstance().addRole(role);

        RoleGetServlet controller = new RoleGetServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("role")).thenReturn(role.getName());
        when(request.getParameter("delete")).thenReturn("delete");
        when(request.getRequestDispatcher("/roleDelete")).thenReturn(dispatcher);

        controller.doPost(request, response);

        verify(request, atLeast(1)).getParameter("role");
        verify(request, atLeast(1)).getParameter("delete");
        verify(request, atLeast(1)).getRequestDispatcher("/roleDelete");
    }
}