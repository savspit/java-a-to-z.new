package shestakov.servlets;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import shestakov.models.Role;
import shestakov.models.User;
import shestakov.postgresql.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * The type User delete servlet test.
 */
public class UserDeleteServletTest {

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
     * When delete user should do it correct.
     *
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @Test
    public void whenDeleteUserShouldDoItCorrect() throws ServletException, IOException {
        User user = new User("Petr", "Petr", "Petr@Petr", System.currentTimeMillis());
        DBUtils.getInstance().addUser(user);

        UserDeleteServlet controller = new UserDeleteServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("user")).thenReturn(user.getLogin());

        controller.doPost(request, response);

        verify(request, atLeast(1)).getParameter("user");

        User foundedUser = DBUtils.getInstance().getUserByLogin(user.getLogin());
        Assert.assertNull(foundedUser);
    }

}