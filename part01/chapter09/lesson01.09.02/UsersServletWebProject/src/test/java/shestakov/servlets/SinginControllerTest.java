package shestakov.servlets;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import shestakov.models.User;
import shestakov.postgresql.DBUtils;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

/**
 * The type Singin controller test.
 */
public class SinginControllerTest {

    /**
     * Create tables.
     *
     * @throws Exception the exception
     */
    @BeforeClass
    public static void createTables() throws Exception {
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
     * When correct login should redirect.
     *
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @Test
    public void whenCorrectLoginShouldRedirect() throws ServletException, IOException {
        User user = new User("Petr", "Petr", "Petr@Petr", System.currentTimeMillis());
        DBUtils.getInstance().addUser(user);

        SinginController controller = new SinginController();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("login")).thenReturn("Petr");
        when(request.getRequestURI()).thenReturn("/");

        controller.doPost(request, response);

        verify(request, atLeast(1)).getSession();
        verify(response).sendRedirect("null/");
    }

    /**
     * When incorrect login should redirect.
     *
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @Test
    public void whenIncorrectLoginShouldRedirect() throws ServletException, IOException {
        SinginController controller = new SinginController();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("login")).thenReturn("Mike");
        when(request.getRequestURI()).thenReturn("/");
        when(request.getRequestDispatcher("/WEB-INF/views/LoginView.jsp")).thenReturn(dispatcher);

        controller.doPost(request, response);

        verify(request, atLeast(1)).setAttribute("error", "Credentional invalid");
        verify(request, atLeast(1)).getRequestDispatcher("/WEB-INF/views/LoginView.jsp");
    }

}