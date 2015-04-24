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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import resources.DateManager;

/**
 *
 * @author rodrigo
 */
public class DatosTrasladoDAO implements Consultas<DatosTrasladoDTO>  {
     private final String SQL_INSERT = "INSERT INTO "
             + "comprobantes_traslados(desde,hasta,fecha_hora_salida,fecha_hora_regreso,id_comprobante) "
             + "VALUES(?,?,?,?,?) "
             + "RETURNING id";
    private final String SQL_UPDATE = "UPDATE comprobantes_traslados SET "
            + "desde = ?, hasta = ?, fecha_hora_salida = ?, fecha_hora_regreso = ?, id_comprobante = ? "
            + "WHERE id = ?";
    private final String SQL_DELETE = "DELETE FROM comprobantes_traslados WHERE id = ?";
    private final String SQL_SELECT = "SELECT "
            + "id,desde,hasta,fecha_hora_salida,fecha_hora_regreso,id_comprobante "
            + "FROM comprobantes_traslados "
            + "WHERE id = ?";
    private final String SQL_SELECTALL = "SELECT "
            + "id,desde,hasta,fecha_hora_salida,fecha_hora_regreso,id_comprobante "
            + "FROM comprobantes_traslados";
    private final String SQL_SELECTRELATED = "SELECT "
            + "id,desde,hasta,fecha_hora_salida,fecha_hora_regreso,id_comprobante "
            + "FROM comprobantes_traslados "
            + "WHERE id_comprobante = ?";
    
    private static final Conexion conex = Conexion.estado();

    @Override
    public int create(DatosTrasladoDTO c) {
        PreparedStatement ps;
        int id  = 0;
        try {
            ps = conex.getCnn().prepareStatement(SQL_INSERT);
            // desde,hasta,fecha_hora_salida,fecha_hora_regreso,id_comprobante
            ps.setString(1,c.getDesde());
            ps.setString(2,c.getHasta());
            ps.setTimestamp(3, new Timestamp(c.getFecha_hora_salida().getFechaLong()));
            ps.setTimestamp(4, new Timestamp(c.getFecha_hora_regreso().getFechaLong()));
            //ps.setString(3,(String)c.getFecha_hora_salida().getFechaString());
            //ps.setString(4, (String)c.getFecha_hora_regreso().getFechaString());
            ps.setInt(5, c.getId_comprobante());
            System.out.println(ps);
            String a = "";
            ResultSet res = ps.executeQuery();
            res.next();
            id = res.getInt(1);
            if(id > 0)
                return id;
        } catch (SQLException ex) {
            Logger.getLogger(DatosTrasladoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DatosTrasladoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return false;
    }

    @Override
    public boolean update(DatosTrasladoDTO c) {
         PreparedStatement ps;
        
        try {
            ps = conex.getCnn().prepareStatement(SQL_UPDATE);
            ps.setString(1,c.getDesde());
            ps.setString(2,c.getHasta());
            ps.setDate(3, new Date(c.getFecha_hora_salida().getFechaLong()));
            ps.setDate(4, new Date(c.getFecha_hora_regreso().getFechaLong()));
            ps.setInt(5, c.getId_comprobante());
            ps.setInt(6, c.getId());
            if(ps.executeUpdate() > 0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatosTrasladoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return false;
    }

    @Override
    public DatosTrasladoDTO selectOne(Object key) {
          PreparedStatement ps;
        ResultSet res;
        DatosTrasladoDTO a = null;
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECT);
            ps.setInt(1, (int) key);
           res = ps.executeQuery();
           while(res.next()){
               //int id, String desde, String hasta, java.util.Date fecha_hora_salida, java.util.Date fecha_hora_regreso, int id_comprobante
               a = new DatosTrasladoDTO(res.getInt(1),res.getString(2),res.getString(3),new DateManager(res.getDate(4)),new DateManager(res.getDate(5)),res.getInt(6));
           }
           return a;
        } catch (SQLException ex) {
            Logger.getLogger(DatosTrasladoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return a;
    }

    @Override
    public List<DatosTrasladoDTO> selectAll() {
        PreparedStatement ps;
        ResultSet res;
        ArrayList Comprobantes = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECTALL);
            res = ps.executeQuery();
            while(res.next()){
                Comprobantes.add(new DatosTrasladoDTO(res.getInt(1),res.getString(2),res.getString(3),new DateManager(res.getDate(4)),new DateManager(res.getDate(5)),res.getInt(6)));
            }
            return Comprobantes;
        } catch (SQLException ex) {
            Logger.getLogger(DatosTrasladoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return Comprobantes;
    }

    @Override
    public List<DatosTrasladoDTO> selectRelated(Object key) {
        PreparedStatement ps;
        ResultSet res;
        ArrayList l = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECTRELATED);
            ps.setInt(1, (int) key);
            res = ps.executeQuery();
            while(res.next()){
                l.add(new DatosTrasladoDTO(res.getInt(1),res.getString(2),res.getString(3),new DateManager(res.getDate(4)),new DateManager(res.getDate(5)),res.getInt(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatosTrasladoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
  
}
