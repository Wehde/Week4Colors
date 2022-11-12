package controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.HexColor;

/**
 * Servlet implementation class getRandomServlet
 */
@WebServlet("/getRandomServlet")
public class getRandomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static HexColorHelper help = new HexColorHelper();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getRandomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inputRandom = request.getParameter("inputRandom").toUpperCase();
		HexColor hexColor = new HexColor();
		try {
			hexColor.setHexColor(inputRandom);
			List<HexColor> grayColors = help.searchForItemByHexGray(hexColor.getHexGray());
			Random rand = new Random();
			HexColor randomColor = grayColors.get(rand.nextInt(grayColors.size()));
			request.setAttribute("result", "Random Color: " + randomColor.getHexColor());
			getServletContext().getRequestDispatcher("/randomResult.jsp").forward(request, response);
		} catch (IllegalArgumentException ex){
			request.setAttribute("result", "Error: " + ex.getMessage());
			getServletContext().getRequestDispatcher("/randomResult.jsp").forward(request, response);
		}
		
	}

}
