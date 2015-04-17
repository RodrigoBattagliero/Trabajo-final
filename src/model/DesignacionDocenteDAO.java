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
public class DesignacionDocenteDAO implements Consultas<DesignacionDocenteDTO> {
    
    private final String SQL_INSERT = "INSERT INTO "
            + "designaciones(numero_resolucion,categoria,desde,hasta,dedicacion,observaciones,id_solicitud) "
            + "VALUES(?,?,?,?,?,?,?) "
            + "RETURNING id";
    private final String SQL_UPDATE = "UPDATE designaciones"
            + "numero_resolucion = ?, categoria = ?, desde = ?, hasta = ?, dedicacion = ?, observaciones = ?, id_solicitud = ? "
            + "WHERE id = ?";
    private final String SQL_DELETE = "DELETE FROM designaciones WHERE id = ? ";
    private final String SQL_SELECT = "SELECT "
            + "id,numero_resolucion,categoria,desde,hasta,dedicacion,observaciones,id_solicitud "
            + "FROM designaciones "
            + "WHERE id = ?";
    private final String SQL_SELECTALL = "SELECT "
            + "id,numero_resolucion,categoria,desde,hasta,dedicacion,observaciones,id_solicitud "
            + "FROM designaciones";
    private final String SQL_SELECTRELATED = ""
            + "SELECT "
            + "id,numero_resolucion,categoria,desde,hasta,dedicacion,observaciones,id_solicitud "
            + "FROM designaciones "
            + "WHERE id_solicitud = ?";
            
    
    private static final Conexion conex = Conexion.estado();

    @Override
    public int create(DesignacionDocenteDTO c) {
        PreparedStatement ps;
        int id = 0;
        try {
            ps = conex.getCnn().prepareStatement(SQL_INSERT);
            //numero_designacion,categoria,desde,hasta,dedicacion,observaciones,id_solicitud
            ps.setString(1, c.getNumero_resolucion());
            ps.setString(2, c.getCategoria());
            ps.setDate(3, new Date(c.getDesde().getFechaLong()));
            ps.setDate(4, new Date(c.getHasta().getFechaLong()));
            ps.setString(5, c.getDedicacion());
            ps.setString(6, c.getObservaciones());
            ps.setInt(7, c.getId_solicitud());
            
            ResultSet a = ps.executeQuery();
            a.next();
            id = a.getInt(1);
            if(id > 0)
                return id;
        } catch (SQLException ex) {
            Logger.getLogger(DesignacionDocenteDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean update(DesignacionDocenteDTO c) {
        PreparedStatement ps;
        
        try {
            ps = conex.getCnn().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getNumero_resolucion());
            ps.setString(2, c.getCategoria());
            ps.setDate(3, new Date(c.getDesde().getFechaLong()));
            ps.setDate(4, new Date(c.getHasta().getFechaLong()));
            ps.setString(5, c.getDedicacion());
            ps.setString(6, c.getObservaciones());
            ps.setInt(7, c.getId_solicitud());
            ps.setInt(8, c.getId());
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
    public DesignacionDocenteDTO selectOne(Object key) {
        PreparedStatement ps;
        ResultSet res;
        DesignacionDocenteDTO a = null;
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECT);
            ps.setInt(1, (int) key);
           res = ps.executeQuery();
           while(res.next()){
               //int id, String numero_resolucion, String categoria, DateManager desde, DateManager hasta, String dedicacion, String observaciones, int id_solicitud
               a = new DesignacionDocenteDTO(
                       res.getInt(1),
                       res.getString(2),
                       res.getString(3),
                       new DateManager(res.getDate(4)),
                       new DateManager(res.getDate(5)),
                       res.getString(6),
                       res.getString(7),
                       res.getInt(8)
                       );
           }
           return a;
        } catch (SQLException ex) {
            Logger.getLogger(DesignacionDocenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return a;
    }

    @Override
    public List<DesignacionDocenteDTO> selectAll() {
        PreparedStatement ps;
        ResultSet res;
        ArrayList designaciones = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECTALL);
            res = ps.executeQuery();
            while(res.next()){
                designaciones.add(new DesignacionDocenteDTO(
                       res.getInt(1),
                       res.getString(2),
                       res.getString(3),
                       new DateManager(res.getDate(4)),
                       new DateManager(res.getDate(5)),
                       res.getString(6),
                       res.getString(7),
                       res.getInt(8)
                ));
            }
            return designaciones;
        } catch (SQLException ex) {
            Logger.getLogger(DesignacionDocenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return designaciones;
    }

    @Override
    public List<DesignacionDocenteDTO> selectRelated(Object key) {
        PreparedStatement ps;
        ResultSet res;
        ArrayList l = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECTRELATED);
            ps.setInt(1, (int) key);
            res = ps.executeQuery();
            while(res.next()){
                l.add(new DesignacionDocenteDTO(
                       res.getInt(1),
                       res.getString(2),
                       res.getString(3),
                       new DateManager(res.getDate(4)),
                       new DateManager(res.getDate(5)),
                       res.getString(6),
                       res.getString(7),
                       res.getInt(8)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DesignacionDocenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    
}
