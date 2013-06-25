/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rex.crm.ajax;

import com.google.gson.Gson;
import com.rex.crm.db.DAOImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Feiyun
 */
public class DataProviderServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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
            if(method.getS() == null){ 
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                out.write("session information not provided");
                return;
             }
            
             String sessionId = method.getS().getId();
             String sessionKey = method.getS().getKey();
             if(sessionId == null || sessionKey == null || !DAOImpl.isSessionValid(sessionId,sessionKey)){
                 response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                 out.write("session is not valid");
                 return;
             }
            

            FunctionInvoker invoker = new FunctionInvoker(DataProvider.class);
            System.out.println(" method:" + method);
            String repTxt = (String) invoker.invoke(method.getF(), method.getP());

            br.close();


            out.write(repTxt);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
