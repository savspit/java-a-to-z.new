package shestakov.servlets;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import shestakov.models.Role;
import shestakov.postgresql.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * The type Role delete servlet test.
 */
public class RoleDeleteServletTest {

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
     * When delete role should do it correct.
     *
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @Test
    public void whenDeleteRoleShouldDoItCorrect() throws ServletException, IOException {
        Role role = new Role("User");
        DBUtils.getInstance().addRole(role);

        RoleDeleteServlet controller = new RoleDeleteServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("role")).thenReturn(role.getName());

        controller.doPost(request, response);

        verify(request, atLeast(1)).getParameter("role");

        Role foundedRole = DBUtils.getInstance().getRoleByName(role.getName());
        Assert.assertNull(foundedRole);
    }

}