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
public class DatosAlojamientoComidaDAO implements Consultas<DatosAlojamientoComidaDTO> {
    
    private final String SQL_INSERT = "INSERT INTO "
             + "comprobantes_comida_alojamientos(tipo,descripcion,id_comprobante) "
             + "VALUES(?,?,?) "
            + "RETURNING id";
    private final String SQL_UPDATE = "UPDATE comprobantes_comida_alojamientos SET "
            + "tipo = ?, descripcion = ?, id_comprobante = ? "
            + "WHERE id = ?";
    private final String SQL_DELETE = "DELETE FROM comprobantes_comida_alojamientos WHERE id = ?";
    private final String SQL_SELECT = "SELECT "
            + "id,tipo,descripcion,id_comprobante "
            + "FROM comprobantes_comida_alojamientos "
            + "WHERE id = ?";
    private final String SQL_SELECTALL = "SELECT "
            + "id,tipo,descripcion,id_comprobante "
            + "FROM comprobantes_comida_alojamientos";
    private final String SQL_SELECTRELATED = "SELECT "
            + "id,tipo,descripcion,id_comprobante "
            + "FROM comprobantes_comida_alojamientos "
            + "WHERE id_comprobante = ?";
    
    private static final Conexion conex = Conexion.estado();
    
    @Override
    public int create(DatosAlojamientoComidaDTO c) {
        PreparedStatement ps;
        int id  = 0;
        try {
            ps = conex.getCnn().prepareStatement(SQL_INSERT);
            ps.setInt(1,c.getTipo());
            ps.setString(2,c.getDescripcion());
            ps.setInt(3, c.getId_comprobante());
            ResultSet res = ps.executeQuery();
            res.next();
            id = res.getInt(1);
            if(id > 0)
                return id;
        } catch (SQLException ex) {
            Logger.getLogger(DatosAlojamientoComidaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DatosAlojamientoComidaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return false;
    }

    @Override
    public boolean update(DatosAlojamientoComidaDTO c) {
        PreparedStatement ps;
        
        try {
            ps = conex.getCnn().prepareStatement(SQL_UPDATE);
           ps.setInt(1,c.getTipo());
            ps.setString(2,c.getDescripcion());
            ps.setInt(3, c.getId_comprobante());
            ps.setInt(5, c.getId());
            if(ps.executeUpdate() > 0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatosAlojamientoComidaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return false;
    }

    @Override
    public DatosAlojamientoComidaDTO selectOne(Object key) {
        PreparedStatement ps;
        ResultSet res;
        DatosAlojamientoComidaDTO a = null;
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECT);
            ps.setInt(1, (int) key);
           res = ps.executeQuery();
           while(res.next()){
               a = new DatosAlojamientoComidaDTO(
                       res.getInt(1),
                       res.getInt(2),
                       res.getString(3),
                       res.getInt(4)
                );
           }
           return a;
        } catch (SQLException ex) {
            Logger.getLogger(DatosAlojamientoComidaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return a;
    }

    @Override
    public List<DatosAlojamientoComidaDTO> selectAll() {
        PreparedStatement ps;
        ResultSet res;
        ArrayList Comprobantes = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECTALL);
            res = ps.executeQuery();
            while(res.next()){
                Comprobantes.add(
                        new DatosAlojamientoComidaDTO(
                            res.getInt(1),
                            res.getInt(2),
                            res.getString(3),
                            res.getInt(4)
                        )
                );
            }
            return Comprobantes;
        } catch (SQLException ex) {
            Logger.getLogger(DatosAlojamientoComidaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return Comprobantes;
    }

    @Override
    public List<DatosAlojamientoComidaDTO> selectRelated(Object key) {
        PreparedStatement ps;
        ResultSet res;
        ArrayList l = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECTRELATED);
            ps.setInt(1, (int) key);
            res = ps.executeQuery();
            while(res.next()){
                l.add(
                        new DatosAlojamientoComidaDTO(
                            res.getInt(1),
                            res.getInt(2),
                            res.getString(3),
                            res.getInt(4)
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatosAlojamientoComidaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    
}
