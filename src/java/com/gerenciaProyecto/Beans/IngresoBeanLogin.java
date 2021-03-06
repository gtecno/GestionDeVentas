/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//verificar el applicationContext y verificar el bean Usuario para verifcar la
// la inyeccipon de dependecia del bean
package com.gerenciaProyecto.Beans;

import com.gerenciaProyecto.Entites.Usuario;
import com.gerenciaProyecto.Servicio.UsuarioService;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Agandio
 */
@Named("ingresoBeanLogin")
@RequestScoped
@Controller
public class IngresoBeanLogin implements Serializable {

    /**
     * Creates a new instance of IngresoBen
     */
    private String user;
    private String password;
    private String redireccion;
    private Usuario usuario;

    @Autowired
    //@Qualifier("usuarioBean")
    private UsuarioBean usuarioBean;

    @Autowired
    //@Qualifier("usuarioService")
    private UsuarioService usuarioService;

    public IngresoBeanLogin() {
    }

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        usuarioBean = new UsuarioBean();
    }

//<editor-fold defaultstate="collapsed" desc="action">
    public void onLogin(ActionEvent ev) {
//        redireccion = "vistaInicial.xhtml";
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            usuario = (Usuario) usuarioService.usuarioLogin(user);
        } catch (Exception tr) {
            tr.printStackTrace();
        }
        if (usuario.getId() != null) {
            if (usuario.getUsername().equals(user)) {
                if (usuario.getPassword().equals(password)) {
//                    redireccion = "../view/inicio.xhtml";

                    try {
                        context.getExternalContext().redirect("./../view/inicio.xhtml");
                    } catch (IOException ex) {
                        Logger.getLogger(IngresoBeanLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                fake();
                try {
                    context.getExternalContext().redirect("./../login/vistaInicial.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(IngresoBeanLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        fake();
        try {
            context.getExternalContext().redirect("./../login/vistaInicial.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(IngresoBeanLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onLogout(ActionEvent ev) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("./../login/vistaInicial.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(IngresoBeanLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fake() {
        FacesMessage msg = new FacesMessage("Usuario o Contraseña ", "No existe ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getter and setter">
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRedireccion() {
        return redireccion;
    }

    public void setRedireccion(String redireccion) {
        this.redireccion = redireccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    public void setUsuarioBean(UsuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
//</editor-fold>

}
