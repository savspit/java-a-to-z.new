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
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

/**
 * The type User create servlet test.
 */
public class UserCreateServletTest {

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
     * When add user should do it correct.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenAddUserShouldDoItCorrect() throws Exception {
        UserCreateServlet controller = new UserCreateServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("login")).thenReturn("Petr");

        controller.doPost(request, response);

        verify(request, atLeast(1)).getParameter("login");

        User user = DBUtils.getInstance().getUserByLogin("Petr");
        assertThat(user.getLogin(), is("Petr"));
    }

}