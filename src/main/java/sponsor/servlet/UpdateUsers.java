package sponsor.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");

        try {
            UsersDAO usersDAO = new UsersDAO();
            boolean isUpdated = usersDAO.updateUser(Integer.parseInt(userId), userName, userEmail, userPassword);

            if (isUpdated) {
                usersDAO.updateUserTimestamp(Integer.parseInt(userId)); // Update timestamp for the user
                request.setAttribute("message", "User updated successfully.");
            } else {
                request.setAttribute("error", "Failed to update user.");
            }
            request.getRequestDispatcher("updateUser.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("updateUser.jsp").forward(request, response);
        }
    }
}

