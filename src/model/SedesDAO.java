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

/**
 *
 * @author rodrigo
 */
public class SedesDAO implements Consultas<SedesDTO> {
    
    private final String SQL_INSERT = "INSERT INTO "
            + "sedes(nombre) "
            + "VALUES(?)";
    private final String SQL_UPDATE = "UPDATE "
            + "sedes SET nombre = ? "
            + "WHERE id = ?";
    private final String SQL_DELETE = "DELETE FROM sedes WHERE id = ?";
    private final String SQL_SELECT = "SELECT "
            + "id,nombre "
            + "FROM sedes"
            + " WHERE id = ?";
    private final String SQL_SELECTALL = "SELECT "
            + "id,nombre "
            + "FROM sedes";

    
    private static final Conexion conex = Conexion.estado();
    
    @Override
    public int create(SedesDTO c) {
        PreparedStatement ps;
        int id = 0;
        try {
            ps = conex.getCnn().prepareStatement(SQL_INSERT);
            ps.setString(1, c.getNombre());
            id = ps.executeUpdate();
            if(id > 0)
                return id;
        } catch (SQLException ex) {
            Logger.getLogger(SedesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SedesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return false;
    }

    @Override
    public boolean update(SedesDTO c) {
         PreparedStatement ps;
        
        try {
            ps = conex.getCnn().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getNombre());
            ps.setInt(2, c.getId());
            if(ps.executeUpdate() > 0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(SedesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return false;
    }

    @Override
    public SedesDTO selectOne(Object key) {
        PreparedStatement ps;
        ResultSet res;
        SedesDTO a = null;
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECT);
            ps.setInt(1, (int) key);
           res = ps.executeQuery();
           while(res.next()){
               a = new SedesDTO(res.getInt(1),res.getString(2));
           }
           return a;
        } catch (SQLException ex) {
            Logger.getLogger(SedesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return a;
    }

    @Override
    public List<SedesDTO> selectAll() {
        PreparedStatement ps;
        ResultSet res;
        ArrayList areas = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECTALL);
            res = ps.executeQuery();
            while(res.next()){
                areas.add(new SedesDTO(res.getInt(1),res.getString(2)));
            }
            return areas;
        } catch (SQLException ex) {
            Logger.getLogger(SedesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return areas;
    }

    @Override
    public List<SedesDTO> selectRelated(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
