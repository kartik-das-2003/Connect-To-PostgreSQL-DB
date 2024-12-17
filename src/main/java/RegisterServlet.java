
import java.sql.Connection;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        Connection conn = null;
        PreparedStatement ps = null;

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Get the database connection
            conn = PostgresDBConnection.getConnection();

            // Insert data into the database
            String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                out.println("<h3>Registration Successful!</h3>");
                out.println("<a herf='Login.html'>Back To Registration Page AgainClick Here</a>");
            } 
            else {
                out.println("<h3>Registration Failed!</h3>");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error occurred: " + e.getMessage() + "</h3>");
        } 
        finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
