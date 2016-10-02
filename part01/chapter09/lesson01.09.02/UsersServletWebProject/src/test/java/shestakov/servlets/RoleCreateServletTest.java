package shestakov.servlets;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import shestakov.models.Role;
import shestakov.postgresql.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * The type Role create servlet test.
 */
public class RoleCreateServletTest {

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

        Role role = DBUtils.getInstance().getRoleByName("User");
        assertThat(role.getName(), is("User"));
    }

}