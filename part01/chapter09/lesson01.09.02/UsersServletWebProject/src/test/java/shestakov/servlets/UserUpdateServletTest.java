package shestakov.servlets;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import shestakov.models.User;
import shestakov.postgresql.DBUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * The type User update servlet test.
 */
public class UserUpdateServletTest {

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
     * When update user should do it correct.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenUpdateUserShouldDoItCorrect() throws Exception {
        User user = new User("Petr", "Petr", "Petr@Petr", System.currentTimeMillis());
        DBUtils.getInstance().addUser(user);

        UserUpdateServlet controller = new UserUpdateServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("login")).thenReturn(user.getLogin());
        when(request.getParameter("name")).thenReturn("Mike");
        when(request.getParameter("update")).thenReturn("update");

        controller.doPost(request, response);

        verify(request, atLeast(1)).getParameter("login");
        verify(request, atLeast(1)).getParameter("name");
        verify(request, atLeast(1)).getParameter("update");

        User foundedUser = DBUtils.getInstance().getUserByLogin("Petr");
        assertThat(foundedUser.getName(), is("Mike"));
    }

}