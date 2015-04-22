/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import conexion.Conexion;
import interfaces.Consultas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import resources.DateManager;

/**
 *
 * @author rodrigo
 */
public class LoginDAO implements Consultas<LoginDTO> {
    
     private final String SQL_INSERT = "INSERT INTO "
            + "usuarios(id_area,nombre,password,user_name) "
            + "VALUES(?,?,?,?)";
    private final String SQL_UPDATE = "UPDATE "
            + "usuarios SET id_area = ?, nombre = ?, password = ?,user_name = ? "
            + "WHERE id = ?";
    private final String SQL_DELETE = "DELETE FROM usuarios WHERE id = ?";
    private final String SQL_SELECT = "SELECT "
            + "id,id_area,nombre,password,user_name "
            + "FROM usuarios"
            + " WHERE id = ?";
    private final String SQL_SELECTALL = "SELECT "
            + "id,id_area,nombre,password,user_name "
            + "FROM usuarios";
    private final String SQL_SELECTRELATED = "SELECT "
            + "id,id_area,nombre,password,user_name "
            + "FROM usuarios "
            + "WHERE id_area = ?";
    
    private final String SQL_LOGIN = "SELECT "
            + "id,id_area,nombre,password,user_name "
            + "FROM usuarios "
            + "WHERE user_name = ? AND password = ?";
    
    private static final Conexion conex = Conexion.estado();
    
    @Override
    public int create(LoginDTO c) {
        PreparedStatement ps;
        int id = 0;
        try {
            ps = conex.getCnn().prepareStatement(SQL_INSERT);
            ps.setInt(1, c.getIdArea());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getPassword());
            ps.setString(4, c.getUser());
            id = ps.executeUpdate();
            if(id > 0)
                return id;
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return id;
    }

    @Override
    public boolean delete(Object key) {
         PreparedStatement ps;
        try {
            ps = conex.getCnn().prepareStatement(SQL_DELETE);
            ps.setInt(1, (int) key);
            if(ps.executeUpdate() > 0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return false;
    }

    @Override
    public boolean update(LoginDTO c) {
        PreparedStatement ps;
        
        try {
            ps = conex.getCnn().prepareStatement(SQL_UPDATE);
            ps.setInt(1, c.getIdArea());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getPassword());
            ps.setString(4, c.getUser());
            ps.setInt(5, c.getId());
            if(ps.executeUpdate() > 0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return false;
    }

    @Override
    public LoginDTO selectOne(Object key) {
        PreparedStatement ps;
        ResultSet res;
        LoginDTO a = null;
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECT);
            ps.setInt(1, (int) key);
           res = ps.executeQuery();
           while(res.next()){
               a = new LoginDTO(
                       res.getInt(1),
                       res.getInt(2),
                       res.getString(3),
                       res.getString(4),
                       res.getString(5)
               );
           }
           return a;
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return a;
    }

    @Override
    public List<LoginDTO> selectAll() {
        PreparedStatement ps;
        ResultSet res;
        ArrayList areas = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECTALL);
            res = ps.executeQuery();
            while(res.next()){
                areas.add(new LoginDTO(
                       res.getInt(1),
                       res.getInt(2),
                       res.getString(3),
                       res.getString(4),
                       res.getString(5)
                ));
            }
            return areas;
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return areas;
    }

    @Override
    public List<LoginDTO> selectRelated(Object key) {
        PreparedStatement ps;
        ResultSet res;
        ArrayList l = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECTRELATED);
            ps.setInt(1, (int) key);
            res = ps.executeQuery();
            while(res.next()){
                l.add(new LoginDTO(
                       res.getInt(1),
                       res.getInt(2),
                       res.getString(3),
                       res.getString(4),
                       res.getString(5)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    
    public LoginDTO comprobarLogin(String user, String pass){
        PreparedStatement ps;
        ResultSet res;
        LoginDTO a = null;
        try {
            ps = conex.getCnn().prepareStatement(SQL_LOGIN);
            ps.setString(1, user);
            ps.setString(2, pass);
           res = ps.executeQuery();
           while(res.next()){
               a = new LoginDTO(
                       res.getInt(1),
                       res.getInt(2),
                       res.getString(3),
                       res.getString(4),
                       res.getString(5)
               );
           }
           return a;
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return a;
    }
    
   
}

