package lab;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthServlet extends HttpServlet {

	private static final long serialVersionUID = -8810955163797221871L;
	private static final long MAX_TRIES = 5;
	private static final long BLOCK_MINS = 1;

	/**
	 * Default constructor.
	 */
	public AuthServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Integer tries = getTries(request.getSession(true));
		if (tries >= MAX_TRIES) {
			blockPage(request, response); // перенаправляем на blocked.jsp, если все попытки израсходованы
		} else {
			request.setAttribute("message", "");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		HttpSession session = request.getSession(true);

		String login = request.getParameter("login");
		String password = request.getParameter("password");

		Integer tries = getTries(session);
		setTries(session, tries + 1);

		if (tries >= MAX_TRIES) {
			blockPage(request, response);
		} else {

			if (login.equals("login") && password.equals("password")) {
				setTries(session, 0);
				welcomePage(request, response);
			} else {
				withMessage("Неверный логин или пароль.<br>Осталось " + (MAX_TRIES - tries) + " попыток входа.",
						request, response);
			}

		}

	}
	

	private void withMessage(String message, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("message", message);
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	private void blockPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("mins", BLOCK_MINS);
		request.getRequestDispatcher("blocked.jsp").forward(request, response);
	}

	private void welcomePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("welcome.jsp").forward(request, response);
	}

	private Integer getTries(HttpSession session) {

		long diff = (new Date()).getTime() - session.getLastAccessedTime();

		if (diff > BLOCK_MINS * 60 * 1000) {
			setTries(session, 0);
			
			return new Integer(0);
		}
		
		Integer tries = (Integer) session.getAttribute("tries"); // проверка на null вместо isNew

		return tries == null ? new Integer(0) : tries;

	}

	private void setTries(HttpSession session, Integer tries) {
		session.setAttribute("tries", tries);
	}

}
