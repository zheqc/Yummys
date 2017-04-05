package api;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import db.DBConnection;
import db.MySQLDBConnection;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final DBConnection connection = new MySQLDBConnection();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignupServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			JSONObject msg = new JSONObject();
			//if (request.getParameterMap().containsKey("user_id") && request.getParameterMap().containsKey("password")
				//	&& request.getParameterMap().containsKey("last_name")
				//	&& request.getParameterMap().containsKey("first_name")) 
		/*	if (input.has("user_id") && input.has("password") 
					&& input.has("first_name") && input.has("last_name")) */
			if (request.getParameterMap().containsKey("user_id") && request.getParameterMap().containsKey("password")
				&& request.getParameterMap().containsKey("last_name")
				&& request.getParameterMap().containsKey("first_name")) {
				String userId = request.getParameter("user_id");
				if (!connection.isNewUser(userId)) {
					RpcParser.writeOutput(response, new JSONObject().put("status", "Already Exists"));
				}
				String password = request.getParameter("password");
				String first_name = request.getParameter("first_name");
				String last_name = request.getParameter("last_name");
				connection.insertNewUser(userId, password, first_name, last_name);
				msg.put("status", "OK");
			//	msg.put("user_id", userId);
			//	msg.put("name", first_name + " " + last_name);
				RpcParser.writeOutput(response, msg);
			} else {
				RpcParser.writeOutput(response, new JSONObject().put("status", "InvalidParameter"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
