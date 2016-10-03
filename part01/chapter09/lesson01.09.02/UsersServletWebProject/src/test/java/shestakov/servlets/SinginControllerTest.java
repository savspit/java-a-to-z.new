package shestakov.servlets;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

/**
 * The type Singin controller test.
 */
public class SinginControllerTest {

    /**
     * When login should do it.
     *
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @Test
    public void whenLoginShouldDoIt() throws ServletException, IOException {
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