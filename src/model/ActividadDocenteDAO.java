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
import resources.DateManager;

/**
 *
 * @author rodrigo
 */
public class ActividadDocenteDAO implements Consultas<ActividadDocenteDTO> {
    
    private final String SQL_INSERT = "INSERT INTO "
            + "actividad_docentes(id_solicitud,asignatura,fecha,observaciones) "
            + "VALUES(?,?,?,?) "
            + "RETURNING id";
    private final String SQL_UPDATE = "UPDATE actividad_docentes"
            + "id_solicitud = ?, asignatura = ?, fecha = ?, observaciones = ? "
            + "WHERE id = ?";
    private final String SQL_DELETE = "DELETE FROM actividad_docentes WHERE id = ? ";
    private final String SQL_SELECT = "SELECT "
            + "id,id_solicitud,asignatura,fecha,observaciones "
            + "FROM actividad_docentes "
            + "WHERE id = ?";
    private final String SQL_SELECTALL = "SELECT "
            + "id,id_solicitud,asignatura,fecha,observaciones "
            + "FROM actividad_docentes";
    private final String SQL_SELECTRELATED = ""
            + "SELECT "
            + "id,id_solicitud,asignatura,fecha,observaciones "
            + "FROM actividad_docentes "
            + "WHERE id_solicitud = ?";
            
    
    private static final Conexion conex = Conexion.estado();

    @Override
    public int create(ActividadDocenteDTO c) {
        PreparedStatement ps;
        int id = 0;
        try {
            ps = conex.getCnn().prepareStatement(SQL_INSERT);
            // id_solicitud,asignatura,fecha,observaciones
            ps.setInt(1, c.getId_solicitud());
            ps.setString(2, c.getAsignatura());
            ps.setDate(3, new Date(c.getFecha().getFechaLong()));
            ps.setString(4, c.getObservaciones());
            
            ResultSet a = ps.executeQuery();
            a.next();
            id = a.getInt(1);
            if(id > 0)
                return id;
        } catch (SQLException ex) {
            Logger.getLogger(ActividadDocenteDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DesignacionDocenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return false;
    }

    @Override
    public boolean update(ActividadDocenteDTO c) {
        PreparedStatement ps;
        
        try {
            ps = conex.getCnn().prepareStatement(SQL_UPDATE);
            ps.setInt(1, c.getId());
            ps.setString(2, c.getAsignatura());
            ps.setDate(3, new Date(c.getFecha().getFechaLong()));
            ps.setString(4, c.getObservaciones());
            ps.setInt(5, c.getId());
            if(ps.executeUpdate() > 0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(ActividadDocenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return false;
    }

    @Override
    public ActividadDocenteDTO selectOne(Object key) {
        PreparedStatement ps;
        ResultSet res;
        ActividadDocenteDTO a = null;
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECT);
            ps.setInt(1, (int) key);
           res = ps.executeQuery();
           while(res.next()){
               a = new ActividadDocenteDTO(
                       res.getInt(1),
                       res.getInt(2),
                       res.getString(3),
                       new DateManager(res.getDate(4)),
                       res.getString(5)
                       );
           }
           return a;
        } catch (SQLException ex) {
            Logger.getLogger(ActividadDocenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return a;
    }

    @Override
    public List<ActividadDocenteDTO> selectAll() {
        PreparedStatement ps;
        ResultSet res;
        ArrayList actividades = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECTALL);
            res = ps.executeQuery();
            while(res.next()){
                actividades.add(new ActividadDocenteDTO(
                       res.getInt(1),
                       res.getInt(2),
                       res.getString(3),
                       new DateManager(res.getDate(4)),
                       res.getString(5)
                ));
            }
            return actividades;
        } catch (SQLException ex) {
            Logger.getLogger(ActividadDocenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return actividades;
    }

    @Override
    public List<ActividadDocenteDTO> selectRelated(Object key) {
        PreparedStatement ps;
        ResultSet res;
        ArrayList l = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECTRELATED);
            ps.setInt(1, (int) key);
            res = ps.executeQuery();
            while(res.next()){
                l.add(new ActividadDocenteDTO(
                       res.getInt(1),
                       res.getInt(2),
                       res.getString(3),
                       new DateManager(res.getDate(4)),
                       res.getString(5)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActividadDocenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    
    
}
