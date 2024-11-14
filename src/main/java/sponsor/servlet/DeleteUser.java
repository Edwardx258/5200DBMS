package sponsor.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sponsor.dal.UsersDAO;

@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");

        try {
            UsersDAO usersDAO = new UsersDAO();
            int parsedUserId = Integer.parseInt(userId);

            usersDAO.deleteUser(parsedUserId);

            request.setAttribute("message", "User deleted successfully.");
        } catch (NumberFormatException e) {

            e.printStackTrace();
            request.setAttribute("error", "Invalid User ID format.");
        } catch (Exception e) {

            e.printStackTrace();
            request.setAttribute("error", "An error occurred: " + e.getMessage());
        }

        request.getRequestDispatcher("DeleteUser.jsp").forward(request, response);
    }
}
