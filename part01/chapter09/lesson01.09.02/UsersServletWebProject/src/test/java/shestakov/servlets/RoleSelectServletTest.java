package shestakov.servlets;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import shestakov.models.Role;
import shestakov.models.User;
import shestakov.postgresql.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * The type Role select servlet test.
 */
public class RoleSelectServletTest {

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
     * When change role should do it correct.
     *
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @Test
    public void whenChangeRoleShouldDoItCorrect() throws ServletException, IOException {
        User user = new User("Petr", "Petr", "Petr@Petr", System.currentTimeMillis());
        Role role = new Role("User");
        Role newRole = new Role("ChangedUsersRole");
        DBUtils.getInstance().addUser(user);
        DBUtils.getInstance().addRole(role);
        DBUtils.getInstance().addRole(newRole);
        DBUtils.getInstance().changeUsersRole(user, role);

        RoleSelectServlet controller = new RoleSelectServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getAttribute("login")).thenReturn(user.getLogin());
        when(request.getAttribute("role")).thenReturn(newRole.getName());

        controller.doPost(request, response);

        verify(request, atLeast(1)).getAttribute("login");
        verify(request, atLeast(1)).getAttribute("role");

        Role foundedRole = DBUtils.getInstance().getRoleByUserLogin(user.getLogin());
        assertThat(foundedRole.getName(), is("ChangedUsersRole"));
    }
}