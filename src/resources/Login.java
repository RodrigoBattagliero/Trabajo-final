/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import controller.bedel.BedelController;
import controller.ogagtd.OgagtdController;
import controller.rendicionDeCuentas.RendicionDeCuentasController;
import controller.secretariaAdministrativoFinanciera.SecretariaAdministrativaFinancieraController;
import controller.sedeinterior.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.BedelDAO;
import model.LoginDAO;
import model.LoginDTO;
import model.OgagtdDAO;
import model.RendicionDeCuentasDAO;
import model.SecretariaAdministrativoFinancieraDAO;
import model.SedeInteriorDAO;
import view.FrameLogin;
import view.VentanaBedel;
import view.VentanaOgagtd;
import view.VentanaRendicionDeCuentas;
import view.VentanaSecretariaAdministrativoFinanciera;
import view.VentanaSedeInterior;

/**
 *
 * @author rodrigo
 */
public class Login implements ActionListener {
    
    private FrameLogin LoginView;
    private LoginDAO LoginDAO;
    
    public Login(FrameLogin v, LoginDAO l){
        this.LoginView = v;
        this.LoginDAO = l;
    }
    
    public void init(){
        this.LoginView.setVisible(true);
        this.LoginView.setLocationRelativeTo(null);
        this.LoginView.btnIngresar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        LoginDTO l = LoginDAO.comprobarLogin(this.LoginView.txtUsuario.getText(), String.valueOf(this.LoginView.txtPassword.getText()));
        if(l != null){
            switch(l.getIdArea()){
                case 1:
                    this.LoginView.dispose();
                    OgagtdController ventana1 = new OgagtdController(
                            new VentanaOgagtd(),
                            new OgagtdDAO()
                    );
                    ventana1.init();
                    break;
                case 2:
                    
                    break;
                case 3:
                    this.LoginView.dispose();
                    SedeInteriorController ventana3 = new SedeInteriorController(
                            new VentanaSedeInterior(), 
                            new SedeInteriorDAO()
                    );
                    ventana3.init();
                    break;
                case 4:
                    this.LoginView.dispose();
                    BedelController ventana4 = new BedelController(
                            new VentanaBedel(),
                            new BedelDAO()
                    );
                    ventana4.init();
                    break;
                case 5:
                    this.LoginView.dispose();
                    RendicionDeCuentasController ventana5 = new RendicionDeCuentasController(
                            new VentanaRendicionDeCuentas(),
                            new RendicionDeCuentasDAO()
                    );
                    ventana5.init();
                    break;
                case 6:
                    this.LoginView.dispose();
                    SecretariaAdministrativaFinancieraController ventana6 = new SecretariaAdministrativaFinancieraController(
                            new VentanaSecretariaAdministrativoFinanciera(),
                            new SecretariaAdministrativoFinancieraDAO()
                    );
                    ventana6.init();
                    break;
                default:
                    JOptionPane.showMessageDialog(LoginView, "Error!.");
                    break;
            }
        }else{
            JOptionPane.showMessageDialog(LoginView, "Datos incorrectos\n"
           +this.LoginView.txtUsuario.getText()+"\n"
           +String.valueOf(this.LoginView.txtPassword.getText()));
        }
    }
    
    
}
