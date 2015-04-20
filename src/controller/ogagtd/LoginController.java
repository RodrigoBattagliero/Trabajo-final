/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ogagtd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.LoginDAO;
import model.LoginDTO;
import model.OgagtdDAO;
import view.FrameLogin;
import view.VentanaOgagtd;

/**
 *
 * @author rodrigo
 */
public class LoginController implements ActionListener {
    
    private FrameLogin LoginView;
    private LoginDAO LoginDAO;
    
    public LoginController(FrameLogin v, LoginDAO l){
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
        if(this.LoginDAO.comprobarLogin(this.LoginView.txtUsuario.getText(), String.valueOf(this.LoginView.txtPassword.getText()))){
            LoginDTO l = this.LoginDAO.selectOne();
            switch(l.getTipo()){
                case 1:
                    this.LoginView.dispose();
                    OgagtdController OgagtdC = new OgagtdController(new VentanaOgagtd(), new OgagtdDAO());
                    OgagtdC.init();
                    break;
                default:
                    JOptionPane.showMessageDialog(LoginView, "Erro!.");
                    break;
            }
        }else{
            JOptionPane.showMessageDialog(LoginView, "Datos incorrectos\n"
           +this.LoginView.txtUsuario.getText()+"\n"
           +String.valueOf(this.LoginView.txtPassword.getText()));
        }
    }
    
    
}
