package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.HexColor;

/**
 * Servlet implementation class getSameServlet
 */
@WebServlet("/getSameServlet")
public class getSameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static HexColorHelper help = new HexColorHelper();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getSameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inputRandom = request.getParameter("inputSame").toUpperCase();
		HexColor hexColor = new HexColor();
		try {
			hexColor.setHexColor(inputRandom);
			List<HexColor> sameColors = help.searchForItemByHexGray(hexColor.getHexGray());
			String output = "";
			for (HexColor color : sameColors) {
				output += color.getHexColor() + " ";
			}
			request.setAttribute("result", output);
			getServletContext().getRequestDispatcher("/sameResult.jsp").forward(request, response);
		} catch (IllegalArgumentException ex){
			request.setAttribute("result", "Error: " + ex.getMessage());
			getServletContext().getRequestDispatcher("/sameResult.jsp").forward(request, response);
		}
	}

}
