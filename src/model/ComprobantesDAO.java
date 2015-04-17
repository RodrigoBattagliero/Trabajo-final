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
public class ComprobantesDAO implements Consultas<ComprobantesDTO> {
    private final String SQL_INSERT = "INSERT INTO "
            + "comprobantes(importe,id_solicitud,numero_comprobante,proveedor,observaciones) "
            + "VALUES(?,?,?,?,?) "
            + "RETURNING id";
    private final String SQL_UPDATE = "UPDATE "
            + "comprobantes SET importe = ?, id_solicitud = ?, numero_comprobante = ?, proveedor = ?, observaciones = ? "
            + "WHERE id = ?";
    private final String SQL_DELETE = "DELETE FROM comprobantes WHERE id = ?";
    private final String SQL_SELECT = "SELECT id,importe,id_solicitud,numero_comprobante,proveedor,obervaciones FROM comprobantes WHERE id = ?";
    private final String SQL_SELECTALL = "SELECT id,importe,id_solicitud,numero_comprobante,proveedor,obervaciones FROM comprobantes";
    private final String SQL_SELECTRELATED = "SELECT id,importe,id_solicitud,numero_comprobante,proveedor,obervaciones FROM comprobantes WHERE id_solicitud = ?";
    
    private static final Conexion conex = Conexion.estado();

    @Override
    public int create(ComprobantesDTO c) {
        PreparedStatement ps;
        int id = 0;
        try {
            ps = conex.getCnn().prepareStatement(SQL_INSERT);
            ps.setDouble(1,c.getImporte());
            ps.setInt(2,c.getId_solicitud());
            ps.setString(3, c.getNumero_comprobante());
            ps.setString(4, c.getProveedor());
            ps.setString(5, c.getObservaciones());
            ResultSet a = ps.executeQuery();
            a.next();
            id = a.getInt(1);
            if(id > 0)
                return id;
        } catch (SQLException ex) {
            Logger.getLogger(ComprobantesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ComprobantesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return false;
    }

    @Override
    public boolean update(ComprobantesDTO c) {
        PreparedStatement ps;
        
        try {
            ps = conex.getCnn().prepareStatement(SQL_UPDATE);
            ps.setDouble(1,c.getImporte());
            ps.setInt(2,c.getId_solicitud());
            ps.setString(3, c.getNumero_comprobante());
            ps.setString(4, c.getProveedor());
            ps.setString(5, c.getObservaciones());
            ps.setInt(6, c.getId());
            if(ps.executeUpdate() > 0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(ComprobantesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return false;
    }

    @Override
    public ComprobantesDTO selectOne(Object key) {
        PreparedStatement ps;
        ResultSet res;
        ComprobantesDTO a = null;
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECT);
            ps.setInt(1, (int) key);
           res = ps.executeQuery();
           while(res.next()){
               a = new ComprobantesDTO(res.getInt(1),res.getDouble(2),res.getInt(3),res.getString(4),res.getString(5),res.getString(6));
           }
           return a;
        } catch (SQLException ex) {
            Logger.getLogger(ComprobantesDTO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return a;
    }

    @Override
    public List<ComprobantesDTO> selectAll() {
        PreparedStatement ps;
        ResultSet res;
        ArrayList Comprobantes = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECTALL);
            res = ps.executeQuery();
            while(res.next()){
                Comprobantes.add(new ComprobantesDTO(res.getInt(1),res.getDouble(2),res.getInt(3),res.getString(4),res.getString(5),res.getString(6)));
            }
            return Comprobantes;
        } catch (SQLException ex) {
            Logger.getLogger(ComprobantesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return Comprobantes;
    }

    @Override
    public List<ComprobantesDTO> selectRelated(Object key) {
        PreparedStatement ps;
        ResultSet res;
        ArrayList l = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECTRELATED);
            ps.setInt(1, (int) key);
            res = ps.executeQuery();
            while(res.next()){
                l.add(new ComprobantesDTO(res.getInt(1),res.getDouble(2),res.getInt(3),res.getString(4),res.getString(5),res.getString(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComprobantesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

  
    
    
   
    
}
