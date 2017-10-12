   public class LoggerListener implements HttpSessionAttributeListener {  
       private HttpSessionBindingEvent evt;  
         
       private void logarEntrada() {  
            HttpSession session = evt.getSession();  
            try {  
                Logger logBEAN = new Logger();  
                LoggerDAO logDAO = new LoggerDAO();       
                logBEAN.setUserid(this.evt.getValue().toString());  
               logBEAN.setDtentrada(new Timestamp(System.currentTimeMillis()));  
              logBEAN.setIp("teste");  
               logDAO.login(logBEAN);  
              session.setAttribute("_LOGGER", logBEAN);  
          } catch (Exception e) {  
               System.out.println("------------------------------------------------");  
              System.out.println("ERRO ao fazer o logger (index.jsp): "+ e.getMessage());  
               System.out.println("------------------------------------------------");  
           }     
       }  
      private void logarSaida() {  
          try {  
               Logger bean = (Logger) this.evt.getValue();  
               bean.setDtsaida(new Timestamp(System.currentTimeMillis()));  
               LoggerDAO dao = new LoggerDAO();  
               dao.logout(bean);  
           } catch (Exception e) {  
              System.out.println("------------------------------------------------");  
              System.out.println("ERRO (LoggerListner.java): "+ e.getMessage() );  
              System.out.println("------------------------------------------------");  
          }         
       }  
       public void attributeAdded(HttpSessionBindingEvent evt) {  
           this.evt = evt;  
           if ("j_username".equals(evt.getName())) {  
               this.logarEntrada();  
          }         
     }  
     
     public void attributeRemoved(HttpSessionBindingEvent evt) {  
           this.evt = evt;  
           if ("_LOGGER".equals(evt.getName())) {  
               this.logarSaida();  
          }  
       }  
  
       public void attributeReplaced(HttpSessionBindingEvent evt) {  
           this.evt = evt;  
          if ("j_username".equals(evt.getName())) {  
            this,logarSaida();   
          	this.logarEntrada();  
           }         
       }  
   }  