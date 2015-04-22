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
public class LiquidacionDAO implements Consultas<LiquidacionDTO> {
    
    private final String SQL_INSERT = "INSERT INTO "
            + "liquidaciones(id_solicitud,reconocimiento_gasto_comida,reconocimiento_gasto_alojamiento,reconocimiento_gasto_combustible,importe_declarado,reconocimiento_importe_total,observaciones) "
            + "VALUES(?,?,?,?,?,?,?) "
            + "RETURNING id";
    private final String SQL_UPDATE = "UPDATE liquidaciones"
            + "id_solicitud = ?,reconocimiento_gasto_comida = ?,reconocimiento_gasto_alojamiento = ?,reconocimiento_gasto_combustible = ?,importe_declarado = ?,reconocimiento_importe_total = ?,observaciones = ? "
            + "WHERE id = ?";
    private final String SQL_DELETE = "DELETE FROM liquidaciones WHERE id = ? ";
    private final String SQL_SELECT = "SELECT "
            + "id,id_solicitud,reconocimiento_gasto_comida,reconocimiento_gasto_alojamiento,reconocimiento_gasto_combustible,importe_declarado,reconocimiento_importe_total,observaciones "
            + "FROM liquidaciones "
            + "WHERE id = ?";
    private final String SQL_SELECTALL = "SELECT "
            + "id,id_solicitud,reconocimiento_gasto_comida,reconocimiento_gasto_alojamiento,reconocimiento_gasto_combustible,importe_declarado,reconocimiento_importe_total,observaciones "
            + "FROM liquidaciones";
    private final String SQL_SELECTRELATED = ""
            + "SELECT "
            + "id,id_solicitud,reconocimiento_gasto_comida,reconocimiento_gasto_alojamiento,reconocimiento_gasto_combustible,importe_declarado,reconocimiento_importe_total,observaciones "
            + "FROM liquidaciones "
            + "WHERE id_solicitud = ?";
            
    
    private static final Conexion conex = Conexion.estado();
    
    @Override
    public int create(LiquidacionDTO c) {
         PreparedStatement ps;
        int id = 0;
        try {
            ps = conex.getCnn().prepareStatement(SQL_INSERT);
            ps.setInt(1,c.getId_solicitud());
            ps.setDouble(2, c.getReconocimientoGastoComida());
            ps.setDouble(3, c.getReconocimientoGastoAlojamiento());
            ps.setDouble(4, c.getReconocimientoGastoCombustible());
            ps.setDouble(5, c.getImporteDeclarado());
            ps.setDouble(6, c.getReconocimientoImporteTotal());
            ps.setString(7, c.getObservaciones());
            
            ResultSet a = ps.executeQuery();
            a.next();
            id = a.getInt(1);
            if(id > 0)
                return id;
        } catch (SQLException ex) {
            Logger.getLogger(LiquidacionDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LiquidacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return false;
    }

    @Override
    public boolean update(LiquidacionDTO c) {
         PreparedStatement ps;
        
        try {
            ps = conex.getCnn().prepareStatement(SQL_UPDATE);
            ps.setInt(1,c.getId_solicitud());
            ps.setDouble(2, c.getReconocimientoGastoComida());
            ps.setDouble(3, c.getReconocimientoGastoAlojamiento());
            ps.setDouble(4, c.getReconocimientoGastoCombustible());
            ps.setDouble(5, c.getImporteDeclarado());
            ps.setDouble(6, c.getReconocimientoImporteTotal());
            ps.setString(7, c.getObservaciones());
            ps.setInt(8, c.getId());
            if(ps.executeUpdate() > 0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(LiquidacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return false;
    }

    @Override
    public LiquidacionDTO selectOne(Object key) {
        PreparedStatement ps;
        ResultSet res;
        LiquidacionDTO a = null;
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECT);
            ps.setInt(1, (int) key);
           res = ps.executeQuery();
           while(res.next()){
               a = new LiquidacionDTO(
                       res.getInt(1),
                       res.getInt(2),
                       res.getDouble(3),
                       res.getDouble(4),
                       res.getDouble(5),
                       res.getDouble(6),
                       res.getDouble(7),
                       res.getString(8)
                       );
           }
           return a;
        } catch (SQLException ex) {
            Logger.getLogger(LiquidacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return a;
    }

    @Override
    public List<LiquidacionDTO> selectAll() {
        PreparedStatement ps;
        ResultSet res;
        ArrayList liquidacion = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECTALL);
            res = ps.executeQuery();
            while(res.next()){
                liquidacion.add(new LiquidacionDTO(
                       res.getInt(1),
                       res.getInt(2),
                       res.getDouble(3),
                       res.getDouble(4),
                       res.getDouble(5),
                       res.getDouble(6),
                       res.getDouble(7),
                       res.getString(8)
                ));
            }
            return liquidacion;
        } catch (SQLException ex) {
            Logger.getLogger(LiquidacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return liquidacion;
    }

    @Override
    public List<LiquidacionDTO> selectRelated(Object key) {
         PreparedStatement ps;
        ResultSet res;
        ArrayList l = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECTRELATED);
            ps.setInt(1, (int) key);
            res = ps.executeQuery();
            while(res.next()){
                l.add(new LiquidacionDTO(
                       res.getInt(1),
                       res.getInt(2),
                       res.getDouble(3),
                       res.getDouble(4),
                       res.getDouble(5),
                       res.getDouble(6),
                       res.getDouble(7),
                       res.getString(8)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LiquidacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    
}
