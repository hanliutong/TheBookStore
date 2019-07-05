/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MySvlt;


import Entity.Booktable;
import MyBeans.BooktableFacadeLocal;
import MyBeans.CartBean;
import MyBeans.CartBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rmi_server_bookstore.BookStore;

/**
 *
 * @author Hanliutong
 */
public class CtrlServlet extends HttpServlet {
    CartBeanLocal cartBean;
    @EJB
    private BooktableFacadeLocal booktableFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            response.resetBuffer();

             
            if(request.getSession().getAttribute(request.getRemoteAddr() )==null){//创建有状态会话Bean
                    cartBean = lookupCartBeanLocal();
                    request.getSession(). setAttribute(request. getRemoteAddr(),  this.cartBean);

                }else{
                    cartBean = (CartBeanLocal) request.getSession().getAttribute(request.getRemoteAddr());
                } 
            if (request.getParameter("page").equals("welcome")){//welcome.jsp收到请求
                Enumeration<String> attributeNames = request.getSession().getAttributeNames();
                String UID = (String)request.getSession().getAttribute("UID");
                while(attributeNames.hasMoreElements()){
                    request.getSession().removeAttribute(attributeNames.nextElement());
                }//清空Session中的属性
                Enumeration<String> parameterNames = request.getParameterNames();
                while(parameterNames.hasMoreElements()){
                    request.removeAttribute(parameterNames.nextElement());
                }//清空request中的属性
                if (!UID.isEmpty()){
                    request.getSession().setAttribute("UID", UID);
                }
                if(request.getParameter("submit").equals("Search")){//Search
                    request.getRequestDispatcher("Search.jsp").forward(request, response);
                }
                else{//Release
                    request.getRequestDispatcher("Release.jsp").forward(request, response);
                }
            }
            if (request.getParameter("page").equals("Login")){//Login.jsp收到请求
                String usr_string = request.getParameter("USR");
                String pws_string = request.getParameter("PWD");
                Registry Reg  = LocateRegistry.getRegistry("47.101.150.13", 2099);
                BookStore obj;
                boolean check = false;
                try {
                    obj = (BookStore) Reg.lookup("rmi//47.101.150.13:2099/BOOKSTORE");
                    check = obj.Check(usr_string, pws_string);
                } catch (RemoteException | NotBoundException ex) {
                    Logger.getLogger(CtrlServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (check){
                    request.getSession().setAttribute("UID", usr_string);
                    request.getRequestDispatcher("welcome.jsp").forward(request, response);
                }
                else
                    response.sendRedirect("Login.jsp");
            }
            
            if (request.getParameter("page").equals("Search")){//从Search.jsp收到请求
                String str = request.getParameter("bookname");
                str = new String(str.getBytes("ISO-8859-1"),"utf-8").toUpperCase();//忽略大小写
                str = "%"+str+"%";//模糊查询
                List<Booktable> findByTitle = booktableFacade.findByTitle(str);
                request.getSession().setAttribute("booktable", findByTitle);
                request.getRequestDispatcher("Directory.jsp").forward(request, response);
            }//Search.jsp
            
            if (request.getParameter("page").equals("Directory")){//从Directory.jsp收到请求
                if(request.getParameter("submit").equals("Search")){
                    response.sendRedirect("Search.jsp");
                }
                else{
                    Enumeration it = request.getParameterNames();
                    it.nextElement();//跳过page属性
                    while (it.hasMoreElements()){
                        String isbn = (String) it.nextElement();
                        if("0".equals(request.getParameter(isbn)) || !(isbn.matches("[0-9]+")))
                            continue;
                        String Num = request.getParameter(isbn);
                        cartBean.setISBNandNum(isbn, Integer.parseInt(Num));
                    }
                    ArrayList<Booktable> tmpList = new ArrayList<>();
                    for (int i = 0;i < cartBean.size();i++){
                        tmpList.add(booktableFacade.findByISBN(cartBean.getISBN().get(i)).get(0));
                    }
                    request.getSession().setAttribute("carttable", tmpList);
                    if(cartBean.size() != 0){
                        request.getSession().setAttribute("numtable", cartBean.getNumber());
                    }
                    request.getRequestDispatcher("Cart.jsp").forward(request, response);
                }
            }//Directory.jsp
            
            if (request.getParameter("page").equals("Cart")){//从Cart.jsp收到请求
                Enumeration it = request.getParameterNames();
                it.nextElement();//跳过page属性
                while (it.hasMoreElements()){
                    String isbn = (String) it.nextElement();
                    if(!(isbn.matches("[0-9]+")))
                            continue;
                    else{
                        cartBean.updateNum(isbn, Integer.parseInt(request.getParameter(isbn)));
                    }
                }//保存Cart页上数量更改,如果数量修改为0，updateNum函数会删除该组数据
                switch(request.getParameter("submit")){
                    case("Directory"):
                        request.getRequestDispatcher("Directory.jsp").forward(request, response);
                        break;
                    case("Order"):
                        if (cartBean.getNumber().isEmpty()){
                            request.getRequestDispatcher("Order.jsp").forward(request, response);
                        }//下空订单，直接转发，Order会做异常处理
                        else{
                            request.getSession().setAttribute("numtable", cartBean.getNumber());
                            List L = cartBean.getISBN();
                            ArrayList<Booktable> tmpList = new ArrayList<>();
                            Iterator It = L.iterator();
                            Entity.Booktable bk = new Booktable();
                            double totalPrice = 0;
                            int point = 0;
                            for(int i =0;It.hasNext();i++){
                                bk = booktableFacade.findByISBN(It.next()).get(0) ;
                                double price = bk.getPrice();
                                Integer num = cartBean.getNumber().get(i);
                                totalPrice += price*num;
                            }
                            Registry Reg  = LocateRegistry.getRegistry("47.101.150.13", 2099);
                            BookStore obj = null;
                            try {
                                obj = (BookStore) Reg.lookup("rmi//47.101.150.13:2099/BOOKSTORE");
                                point = obj.getPoint((String) request.getSession().getAttribute("UID"));
                            } catch (RemoteException | NotBoundException ex) {
                                Logger.getLogger(CtrlServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (point < totalPrice){
                                request.getSession().setAttribute("point", String.valueOf(point));
                                request.getSession().setAttribute("totalPrice", String.valueOf(totalPrice));
                                request.getRequestDispatcher("ErrPage.jsp").forward(request, response);
                              }
                            else{
                            It = L.iterator();
                            boolean check = true;//库存检查
                            for(int i =0;It.hasNext();i++){
                                bk = booktableFacade.findByISBN(It.next()).get(0) ;
                                if((bk.getStock()-cartBean.getNumber().get(i))<0){//不够了
                                    check = false;
                                    break;
                                }
                                else{
                                bk.setStock(bk.getStock()-cartBean.getNumber().get(i));//够，去库存，
                                tmpList.add(bk);
                                }
                            }
                            if(check){
                                It = tmpList.iterator();
                                while(It.hasNext()){
                                    booktableFacade.edit((Booktable) It.next());
                                }//修改数据库
                                obj.setPoint((String) request.getSession().getAttribute("UID"), point - (int)totalPrice);
                                request.getSession().setAttribute("carttable", tmpList);
                                request.getRequestDispatcher("Order.jsp").forward(request, response);
                            }
                            else{
                                request.getRequestDispatcher("StockErr.jsp").forward(request, response);
                            }
                            }

                        }
                        break;
                    default:
                    case("Search"):
                        response.sendRedirect("Search.jsp");
                        break;
                }
            }//Cart.jsp
            
            if (request.getParameter("page").equals("Order")){
                //order还不会发请求，不过万一我要增量代码呢
            }
            
            //以下是扩展功能了，原题目中不要求
            if (request.getParameter("page").equals("Release")){
                String ISBN = request.getParameter("ISBN");
                ISBN = new String(ISBN.getBytes("ISO-8859-1"),"utf-8");
                List<Booktable> findByISBN = booktableFacade.findByISBN(ISBN);
                if(!findByISBN.isEmpty()){
                    request.getSession().setAttribute("bookinfo", findByISBN.get(0));
                    request.getRequestDispatcher("NeedMoreInf.jsp").forward(request, response);
                }
                else{//没查到，放个null，jsp会去处理的
                    request.getSession().setAttribute("bookinfo", null);
                    request.getRequestDispatcher("NeedMoreInf.jsp").forward(request, response);
                }
            }
            if (request.getParameter("page").equals("signin")){
                Registry Reg  = LocateRegistry.getRegistry("47.101.150.13", 2099);
                BookStore obj = null;
                String PWD = request.getParameter("PWD");
                PWD = new String(PWD.getBytes("ISO-8859-1"),"utf-8");
                String UserName = request.getParameter("UserName");
                UserName = new String(UserName.getBytes("ISO-8859-1"),"utf-8");
                String phoneNum = request.getParameter("phoneNum");
                phoneNum = new String(phoneNum.getBytes("ISO-8859-1"),"utf-8");
                    try {
                        obj = (BookStore) Reg.lookup("rmi//47.101.150.13:2099/BOOKSTORE");
                        String UID = obj.addUser(PWD, UserName, phoneNum);
                        request.getSession().setAttribute("newUserId", UID);
                    } catch (RemoteException | NotBoundException ex) {
                        Logger.getLogger(CtrlServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
               request.getRequestDispatcher("SignInSuccful.jsp").forward(request, response);
            }
            
            if (request.getParameter("page").equals("NeedMoreInf")){
                if(request.getParameter("submit").equals("submit")){
                    double Price_D = 0;
                    if(request.getSession().getAttribute("bookinfo") == null){//新书
                        String ISBN = request.getParameter("ISBN");
                        ISBN = new String(ISBN.getBytes("ISO-8859-1"),"utf-8");
                        String Title = request.getParameter("Title");
                        Title = new String(Title.getBytes("ISO-8859-1"),"utf-8");
                        String Author = request.getParameter("Author");
                        Author = new String(Author.getBytes("ISO-8859-1"),"utf-8");
                        String Stock  = request.getParameter("Stock");
                        Stock = new String(Stock.getBytes("ISO-8859-1"),"utf-8");
                        int Stock_I = Integer.parseInt(Stock);
                        String Price = request.getParameter("Price");
                        Price = new String(Price.getBytes("ISO-8859-1"),"utf-8");
                        Price_D = Double.parseDouble(Price);
                        Booktable newBook = new Booktable(ISBN,Title,Author,Stock_I,Price_D);
                        booktableFacade.create(newBook);
                    }
                    else{//原来就有的书，加库存
                        Booktable bk = (Booktable) request.getSession().getAttribute("bookinfo");
                        Price_D = bk.getPrice();
                        bk.setStock(bk.getStock()+Integer.parseInt(request.getParameter("Stock")));
                        booktableFacade.edit(bk);
                    }
                    Registry Reg  = LocateRegistry.getRegistry("47.101.150.13", 2099);
                    BookStore obj;
                    String UID =(String) request.getSession().getAttribute("UID");
                    int point = 0;
                    try {
                        obj = (BookStore) Reg.lookup("rmi//47.101.150.13:2099/BOOKSTORE");
                        point = obj.getPoint(UID);
                        obj.setPoint(UID, (point+(int)Price_D));
                    } catch (RemoteException | NotBoundException ex) {
                        Logger.getLogger(CtrlServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    response.sendRedirect("RelSuccess.jsp");
                }
                else{//没加
                    response.sendRedirect("welcome.jsp");
                }
            }
            out.close();
                
        }

        catch(NullPointerException | NumberFormatException| ArrayIndexOutOfBoundsException | IOException E){
            //System.out.printf(E.getLocalizedMessage());
            Enumeration<String> attributeNames = request.getSession().getAttributeNames();
            while(attributeNames.hasMoreElements()){
                request.getSession().removeAttribute(attributeNames.nextElement());
            }
            Enumeration<String> parameterNames = request.getParameterNames();
            while(parameterNames.hasMoreElements()){
                request.removeAttribute(parameterNames.nextElement());
            }
            try {
                response.sendRedirect("welcome.jsp");
                return;
            } catch (IOException ex) {
                Logger.getLogger(CtrlServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private CartBeanLocal lookupCartBeanLocal() {
        try {
            
            Context c = new InitialContext();
            return (CartBeanLocal) c.lookup("java:global/TheBookStore/TheBookStore-ejb/CartBean!MyBeans.CartBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
