/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author rodrigo
 */
public class LoginDAO {
    
    private String user;
    private String password;
    private int tipo;
    
    public LoginDAO(){
        this.user = "rodrigo";
        this.password = "123";
        this.tipo = 1;
        System.out.printf("%s\n%s",
             this.user,this.password);
    }
    
    public LoginDTO selectOne(){
        return new LoginDTO(this.user,this.password,this.tipo);
    }
    
    public boolean comprobarLogin(String user, String pass){
        System.out.printf("%s\n%s\n%d",
            user + " " + this.user,pass+" "+this.password,this.tipo);
        return user.equals(this.user) && pass.equals(this.password);
        
    }
}
