package shestakov.servlets;

import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * The type Auth filter test.
 */
public class AuthFilterTest {

    /**
     * When do filter should do it correct.
     *
     * @throws IOException      the io exception
     * @throws ServletException the servlet exception
     */
    @Test
    public void whenDoFilterShouldDoItCorrect() throws IOException, ServletException {
        AuthFilter controller = new AuthFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);

        when(request.getRequestURI()).thenReturn("/singin");

        controller.doFilter(request, response, chain);

        verify(chain, atLeast(1)).doFilter(request, response);
    }

    /**
     * When null login should redirect.
     *
     * @throws IOException      the io exception
     * @throws ServletException the servlet exception
     */
    @Test
    public void whenNullLoginShouldRedirect() throws IOException, ServletException {
        AuthFilter controller = new AuthFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("login")).thenReturn(null);
        when(request.getRequestURI()).thenReturn("/");

        controller.doFilter(request, response, chain);

        verify(request, atLeast(1)).getSession();
        verify(response).sendRedirect("null/singin");
    }

}