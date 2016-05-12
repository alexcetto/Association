package com.mycompany.biblio.controller;

import com.mycompany.biblio.business.StampEJB;
import com.mycompany.biblio.model.User;
import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;


@ManagedBean
@SessionScoped
public class LoginController {

    // ======================================
    // =             Attributes             =
    // ======================================

    @EJB
    private StampEJB stampEJB;

    private User user= new User();

    // ======================================
    // =           Public Methods           =
    // ======================================

    public String doLogin() {
        System.out.println("Path info: "+FacesContext.getCurrentInstance().getExternalContext().getRequestPathInfo());
        AuthenticationToken token= new UsernamePasswordToken(user.getUsername(), user.getPassword());
        Subject currentUser= SecurityUtils.getSubject(); 
        try {
            currentUser.login( token );
            //if no exception, that's it, we're done!

            return savedUrl();
        } catch (AuthenticationException e) {
            addWarnMessage("Connexion impossible : ", "vérifiez les paramètres saisis");
            return null;
        }
    }

    private String savedUrl() {
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        SavedRequest request = WebUtils.getSavedRequest(origRequest);
        
        if (request == null)
             return "/home.xhtml?faces-redirect=true";
        
        String uri = pathWithoutContextFor(request.getRequestURI());

        if (StringUtils.isEmpty(request.getQueryString())) {
            return facesRedirect(uri);
        } else {
            return facesRedirect(uri + "?" + request.getQueryString());
        }
    }
    
    private String facesRedirect(String url) {
        if (url.contains("?"))
            return url + "&faces-redirect=true";
        else 
            return url + "?faces-redirect=true";
    }
    
    private String pathWithoutContextFor(String request) {
        String context = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        return request.substring(context.length());
    }
    
    private void addWarnMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail));
    }

    public String doLogout() {
        SecurityUtils.getSubject().logout();
        return "/home.xhtml?faces-redirect=true";
    }
    
    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}