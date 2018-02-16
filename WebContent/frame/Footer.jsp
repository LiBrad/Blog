<%@ page language="java" import="java.util.*" import="cap.bean.*" import="cap.dao.impl.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>



    <div class="container">
      <hr>
      
      <footer>
        <div class="row">
          <div class="col-lg-12">
            <p >
            Copyright &copy; 2018 &middot; BradLee开发 
                                  &middot; 847788160@qq.com
                                  &middot;您是本站第：<%=(Integer)session.getAttribute("num") %> 位访客
            </p>
          </div>
        </div>
      </footer>

    </div>
  
    <script src="<%=basePath %>bootstrap/js/bootstrap.js"></script>
    <script src="<%=basePath %>bootstrap/js/bootstrap.min.js"></script>
    
  </body>
</html>
