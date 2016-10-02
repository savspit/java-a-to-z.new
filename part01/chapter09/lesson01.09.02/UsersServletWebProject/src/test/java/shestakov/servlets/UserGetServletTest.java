package shestakov.servlets;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import shestakov.models.Role;
import shestakov.models.User;
import shestakov.postgresql.DBUtils;

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
     * When going edit user should do it correct.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGoingEditUserShouldDoItCorrect() throws Exception {
        User user = new User("Petr", "Petr", "Petr@Petr", System.currentTimeMillis());
        DBUtils.getInstance().addUser(user);

        UserGetServlet controller = new UserGetServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter("user")).thenReturn(user.getLogin());
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
        User user = new User("Petr", "Petr", "Petr@Petr", System.currentTimeMillis());
        DBUtils.getInstance().addUser(user);

        UserGetServlet controller = new UserGetServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter("user")).thenReturn(user.getLogin());
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
        User user = new User("Petr", "Petr", "Petr@Petr", System.currentTimeMillis());
        DBUtils.getInstance().addUser(user);

        UserGetServlet controller = new UserGetServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter("user")).thenReturn(user.getLogin());
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