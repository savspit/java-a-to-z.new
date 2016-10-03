package shestakov.servlets;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import shestakov.models.Role;
import shestakov.postgresql.DBUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * The type Role update servlet test.
 */
public class RoleUpdateServletTest {

    /**
     * Create tables.
     */
    @BeforeClass
    public static void createTables() throws IOException {
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
     * When update role should do it correct.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenUpdateRoleShouldDoItCorrect() throws Exception {
        Role role = new Role("User");
        DBUtils.getInstance().addRole(role);

        RoleUpdateServlet controller = new RoleUpdateServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter("name")).thenReturn("UserRoleChanged");
        when(request.getParameter("update")).thenReturn("update");
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("role")).thenReturn(role.getName());

        controller.doPost(request, response);

        verify(request, atLeast(1)).getParameter("name");
        verify(request, atLeast(1)).getParameter("update");
        verify(request, atLeast(1)).getSession();
        verify(session, atLeast(1)).getAttribute("role");

        Role foundedRole = DBUtils.getInstance().getRoleByName("UserRoleChanged");
        assertThat(foundedRole.getName(), is("UserRoleChanged"));
    }

}