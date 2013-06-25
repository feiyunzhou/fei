package com.rex.crm.offline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.rex.crm.ajax.DataProvider;
import com.rex.crm.ajax.FunctionClass;
import com.rex.crm.ajax.FunctionInvoker;
import com.rex.crm.db.DAOImpl;

/**
 * Servlet implementation class DataFeederServlet
 */
public class DataFeederServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataFeederServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        try {
            BufferedReader br = request.getReader();

            String jsonString = br.readLine();
            if ((jsonString == null) || jsonString.isEmpty()) {
                System.out.println(" no json found");
            } else {
                System.out.println(" json  is :" + jsonString);
            }
            FunctionClass method = new Gson().fromJson(jsonString, FunctionClass.class);

            if (!method.getF().equalsIgnoreCase("login")) {
                if (method.getS() == null) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    br.close();
                    out.print("session information not provided");
                    out.flush();
                    return;
                }

                String sessionId = method.getS().getId();
                String sessionKey = method.getS().getKey();
                if (sessionId == null || sessionKey == null || !DAOImpl.isSessionValid(sessionId, sessionKey)) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    br.close();
                    out.print("session is not valid");
                    out.flush();
                    return;
                }
            }
            
            
            FunctionInvoker invoker = new FunctionInvoker(DataProvider.class);
            System.out.println(" method:" + method);
            String repTxt = (String) invoker.invoke(method.getF(), method.getP());
            System.out.println(repTxt);
            br.close();

            out.print(repTxt);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 processRequest(request, response);
	}

}
