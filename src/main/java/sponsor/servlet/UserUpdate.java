package sponsor.servlet;

import sponsor.dal.*;
import sponsor.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/userupdate")
public class UserUpdate extends HttpServlet {

    protected UserDao usersDao;

    @Override
    public void init() throws ServletException {
        usersDao = UserDao.getInstance();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        String userIdStr = req.getParameter("userid");
        String newEmail = req.getParameter("email");

        try {
            int userId = Integer.parseInt(userIdStr);
            Users user = usersDao.getUserByUserId(userId);
            if (user != null) {
                usersDao.updateEmail(user, newEmail);
                messages.put("success", "Successfully updated email for user ID " + userId);
            } else {
                messages.put("success", "User not found.");
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            messages.put("success", "Error updating user.");
        }
        req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
    }
}
