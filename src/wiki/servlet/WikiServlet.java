package wiki.servlet;

import java.io.IOException;
import java.util.Date;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import cs320.model.Revisions;
import cs320.model.UserAccount;
import cs320.model.WikiPage;

@WebServlet(urlPatterns = "/wiki/*", loadOnStartup = 1)
public class WikiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String path;

	public WikiServlet() {
		super();

	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}

	}


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		UserAccount account = (UserAccount) request.getSession().getAttribute(
				"user");

		Revisions revwiki = null;

		Integer id = 0;
		Integer i = 0;

		String p = request.getPathInfo();
		path = p.substring(1);
		String query = request.getQueryString();
		boolean exists = false;
	

		try {
			String url = "";
			String username = "";
			String password = "";
			

			Connection c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select id, path from wikipages");

			while (rs.next())
			{

				id = rs.getInt("id");
			
				
				String check = rs.getString("path");
				

				if (check.equals(path)) 
				{

					exists = true;

					if (query != null)
					{
					
						int id2 = Integer.parseInt(query.substring(12));
						revwiki = getEntryFromDB(id2);
					}
					else 
					{

						String sqls = "select i.revision_id,i.wiki_id from wikis_revisions_id i, wikipages w where i.wiki_id = w.id and w.path =  ? ";
						PreparedStatement pstmt1 = c.prepareStatement(sqls);
						pstmt1.setString(1, path);
						ResultSet rsr = pstmt1.executeQuery();
						int i2 = 0;

						if (rsr.next()) 
						{
							i = rsr.getInt("revision_id");
							i2 = rsr.getInt("wiki_id");
						}

						revwiki = getEntryFromDB(i);

					}
					
					break;
					
				}

			}

			c.close();
		} 
		catch (SQLException e) 
		{
			throw new ServletException(e);
		}

		if (exists) 
		{

			request.setAttribute("account", account);
		
			request.setAttribute("revwiki", revwiki);
			request.setAttribute("path", path);
			request.getRequestDispatcher("/WEB-INF/db/Wikipages.jsp").forward(
					request, response);
		} 
		else
		{
			getServletContext().setAttribute("path", path);
			request.getRequestDispatcher("/WEB-INF/db/NoPage.jsp").forward(
					request, response);
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
				doGet(request, response);
	}

	Revisions getEntryFromDB(int id) throws ServletException 
	{
	
		Revisions rev = null;
		Integer rvId = 0;
		Integer wId = 0;
		String content = null;
		Integer uId = 0;

		UserAccount useracc = null;
		Date time = null;
		WikiPage wiki = null;

		Integer uI = 0;
		String usernameU = null;
		String passwordU = null;
		String firstName = null;
		String lastName = null;
		String email = null;

	
		try 
		{
			String url = "";
			String username = "";
			String password = "";

			Connection c = DriverManager.getConnection(url, username, password);
			String sql = "select * from revisions where id = ?";
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			String sql2 = "select * from users where id = ?";
			PreparedStatement pstmt2 = c.prepareStatement(sql2);

			while (rs.next()) 
			{
				rvId = rs.getInt("id");
				wId = rs.getInt("wikipage");
				content = rs.getString("content");
				uId = rs.getInt("author");
				time = rs.getTimestamp("stamptime");

			}
			wiki = new WikiPage(wId, path);
			pstmt2.setInt(1, uId);
			ResultSet rsa = pstmt2.executeQuery();

			if (rsa.next()) 
			{
				uI = rsa.getInt("id");
				usernameU = rsa.getString("username");
				passwordU = rsa.getString("password");
				firstName = rsa.getString("first_name");
				lastName = rsa.getString("last_name");
				email = rsa.getString("email");
			}

			useracc = new UserAccount(uI, usernameU, passwordU, firstName,
					lastName, email);

			rev = new Revisions(rvId, wiki, content, useracc, time);

			c.close();
		}
		catch (SQLException e) 
		{
			throw new ServletException(e);
		}
		return rev;
	}
}
