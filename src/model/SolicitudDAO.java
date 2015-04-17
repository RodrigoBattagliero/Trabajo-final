    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import conexion.Conexion;
import interfaces.Consultas;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.T;
import static jdk.nashorn.internal.objects.NativeJava.type;
import resources.DateManager;

/**
 *
 * @author rodrigo
 */
public class SolicitudDAO implements Consultas<SolicitudDTO> {
    
    private final String SQL_INSERT = "INSERT INTO "
            + "solicitudes(numero_solicitud,tipo,fecha_alta,observaciones) "
            + "VALUES(?,?,?,?) "
            + "RETURNING id";
    private final String SQL_UPDATE = "UPDATE "
            + "solicitudes SET numero_solicitud = ?, tipo = ?, fecha_alta = ?, observaciones = ? "
            + "WHERE id = ?";
    private final String SQL_DELETE = "DELETE FROM solicitudes WHERE id = ? ";
    private final String SQL_SELECT = "SELECT "
            + "id,numero_solicitud,tipo,fecha_alta,observaciones "
            + "FROM solicitudes "
            + "WHERE id = ?";
    private final String SQL_SELECTALL = "SELECT "
            + "id,numero_solicitud,tipo,fecha_alta,observaciones "
            + "FROM solicitudes";
    private final String SQL_SELECTLAST = "SELECT "
            + "id,numero_solicitud,tipo,fecha_alta,observaciones "
            + "FROM solicitudes "
            + "ORDER BY numero_solicitud DESC "
            + "LIMIT 1 "
            + "OFFSET 0";
    private final String SQL_SELECTRELATED = "SELECT "
            + "id,numero_solicitud,tipo,fecha_alta,observaciones "
            + "FROM solicitudes "
            + "WHERE numero_solicitud = ?";
    //private final String SQL_LASTID = "SELECT lastval() FROM solicitudes";
    
    private static final Conexion conex = Conexion.estado();
    
    @Override
    public int create(SolicitudDTO c) {
        PreparedStatement ps;
        int id = 0;
        try {
            ps = conex.getCnn().prepareStatement(SQL_INSERT);
            ps.setInt(1,c.getNumero_solicutd());
            ps.setInt(2, c.getTipo());
            ps.setDate(3, new Date(c.getFecha_alta().getFechaLong()));
            //ps.setString(3,(String)c.getFecha_alta().getFechaString());
            ps.setString(4, c.getObservaciones());
            ResultSet a = ps.executeQuery();
            a.next();
            id = a.getInt(1);
            //System.out.println(id);
            if(id > 0)
                return id;
        } catch (SQLException ex) {
            Logger.getLogger(SolicitudDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SolicitudDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return false;
    }

    @Override
    public boolean update(SolicitudDTO c) {
        PreparedStatement ps;
        
        try {
            ps = conex.getCnn().prepareStatement(SQL_UPDATE);
            ps.setInt(1,c.getNumero_solicutd());
            ps.setInt(2, c.getTipo());
            ps.setDate(3, (Date) c.getFecha_alta().getFechaDate());
            ps.setString(4, c.getObservaciones());
            ps.setInt(5, c.getId());
            if(ps.executeUpdate() > 0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(SolicitudDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return false;
    }

    @Override
    public SolicitudDTO selectOne(Object key) {
        PreparedStatement ps;
        ResultSet res;
        SolicitudDTO a = null;
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECT);
            ps.setInt(1, (int) key);
           res = ps.executeQuery();
           while(res.next()){
               
               a = new SolicitudDTO(res.getInt(1),res.getInt(2),res.getInt(3),new DateManager(res.getDate(4)),res.getString(5));
           }
           return a;
        } catch (SQLException ex) {
            Logger.getLogger(SolicitudDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return a;
    }

    @Override
    public List<SolicitudDTO> selectAll() {
        PreparedStatement ps;
        ResultSet res;
        ArrayList solicitudes = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECTALL);
            res = ps.executeQuery();
            while(res.next()){
                solicitudes.add(new SolicitudDTO(res.getInt(1),res.getInt(2),res.getInt(3),new DateManager(res.getDate(4)),res.getString(5)));
            }
            return solicitudes;
        } catch (SQLException ex) {
            Logger.getLogger(SolicitudDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return solicitudes;
    }

    @Override
    public List<SolicitudDTO> selectRelated(Object key) {
        PreparedStatement ps;
        ResultSet res;
        ArrayList l = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECTRELATED);
            ps.setInt(1, (int) key);
            res = ps.executeQuery();
            while(res.next()){
                l.add(new SolicitudDTO(res.getInt(1),res.getInt(2),res.getInt(3),new DateManager(res.getDate(4)),res.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SolicitudDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    
    public SolicitudDTO selectLast(){
        PreparedStatement ps;
        ResultSet res;
        SolicitudDTO a = null;
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECTLAST);
           res = ps.executeQuery();
           while(res.next()){
               
               a = new SolicitudDTO(res.getInt(1),res.getInt(2),res.getInt(3),new DateManager(res.getDate(4)),res.getString(5));
           }
           return a;
        } catch (SQLException ex) {
            Logger.getLogger(SolicitudDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return a;
    }
    
}
