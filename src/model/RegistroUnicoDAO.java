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
public class RegistroUnicoDAO implements Consultas<RegistroUnicoDTO> {

      private final String SQL_INSERT = "INSERT INTO "
            + "registros_unicos(fecha_entrada,fecha_salida,confirmado,observaciones,id_solicitud,id_area,id_estado) "
            + "VALUES(?,?,?,?,?,?,?) "
            + "RETURNING id";
    private final String SQL_UPDATE = "UPDATE "
            + "registros_unicos SET fecha_entrada = ?, fecha_salida = ?, confirmado = ?, observaciones = ?, id_solicitud = ?, id_area = ?, id_estado = ? "
            + "WHERE id = ?";
    private final String SQL_DELETE = "DELETE FROM registros_unicos WHERE id = ? ";
    private final String SQL_SELECT = "SELECT "
            + "id,fecha_entrada,fecha_salida,confirmado,observaciones,id_solicitud,id_area,id_estado "
            + "FROM registros_unicos "
            + "WHERE id = ?";
    private final String SQL_SELECT_ALL = "SELECT "
            + "id,fecha_entrada,fecha_salida,confirmado,observaciones,id_solicitud,id_area,id_estado "
            + "FROM registros_unicos";
    private final String SQL_SELECT_RELATED = "SELECT "
            + "id,fecha_entrada,fecha_salida,confirmado,observaciones,id_solicitud,id_area,id_estado "
            + "FROM registros_unicos "
            + "WHERE id_solicitud = ?";
    private final String SQL_SELECT_REGISTRO_UNICO = "SELECT "
            + "id,fecha_entrada,fecha_salida,confirmado,observaciones,id_solicitud,id_area,id_estado "
            + "FROM registros_unicos "
            + "WHERE id_area = ? AND id_estado = ? "
            + "ORDER BY fecha_entrada DESC";
    private final String SQL_SELECT_LOTE_CONFIRMAR = "SELECT "
            + "id,fecha_entrada,fecha_salida,confirmado,observaciones,id_solicitud,id_area,id_estado "
            + "FROM registros_unicos "
            + "WHERE id_area = ? AND id_estado = ? AND confirmado = false "
            + "ORDER BY fecha_entrada DESC";
    
    private static final Conexion conex = Conexion.estado();
    
    @Override
    public int create(RegistroUnicoDTO c) {
        PreparedStatement ps;
        int id = 0;
        try {
            ps = conex.getCnn().prepareStatement(SQL_INSERT);
            // fecha_entrada,fecha_salida,confirmado,observaciones,id_solicitud,id_area,id_estado
            ps.setDate(1, new Date(c.getFecha_entrada().getFechaLong()));
            ps.setDate(2, new Date(c.getFecha_salida().getFechaLong()));
            ps.setBoolean(3, c.isConfirmado());
            ps.setString(4, c.getObservaciones());
            ps.setInt(5, c.getId_solicitud());
            ps.setInt(6, c.getId_area());
            ps.setInt(7, c.getId_estado());
            ResultSet res = ps.executeQuery();
            System.out.println(ps);
            res.next();
            id = res.getInt(1);
            if(id > 0)
                return id;
        } catch (SQLException ex) {
            Logger.getLogger(RegistroUnicoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistroUnicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return false;
    }

    @Override
    public boolean update(RegistroUnicoDTO c) {
        PreparedStatement ps;
        
        try {
            ps = conex.getCnn().prepareStatement(SQL_UPDATE);
            
            ps.setDate(1, new Date(c.getFecha_entrada().getFechaLong()));
            ps.setDate(2, new Date(c.getFecha_salida().getFechaLong()));
            ps.setBoolean(3, c.isConfirmado());
            ps.setString(4, c.getObservaciones());
            ps.setInt(5, c.getId_solicitud());
            ps.setInt(6, c.getId_area());
            ps.setInt(7, c.getId_estado());
            ps.setInt(8, c.getId());
            if(ps.executeUpdate() > 0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(RegistroUnicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return false;
    }

    @Override
    public RegistroUnicoDTO selectOne(Object key) {
        PreparedStatement ps;
        ResultSet res;
        RegistroUnicoDTO a = null;
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECT);
            ps.setInt(1, (int) key);
           res = ps.executeQuery();
           while(res.next()){
               // int id, DateManager fecha_entrada, DateManager fecha_salida, boolean confirmado, String observaciones, int id_solicitud, int id_area, int id_estado
               a = new RegistroUnicoDTO(res.getInt(1),new DateManager(res.getDate(2)),new DateManager(res.getDate(3)),res.getBoolean(4),res.getString(5),res.getInt(6),res.getInt(7),res.getInt(8));
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
    public List<RegistroUnicoDTO> selectAll() {
        PreparedStatement ps;
        ResultSet res;
        ArrayList registros = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECT_ALL);
            res = ps.executeQuery();
            while(res.next()){
                registros.add(new RegistroUnicoDTO(res.getInt(1),new DateManager(res.getDate(2)),new DateManager(res.getDate(3)),res.getBoolean(4),res.getString(5),res.getInt(6),res.getInt(7),res.getInt(8)));
            }
            return registros;
        } catch (SQLException ex) {
            Logger.getLogger(SolicitudDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return registros;
    }

    @Override
    public List<RegistroUnicoDTO> selectRelated(Object key) {
        PreparedStatement ps;
        ResultSet res;
        ArrayList l = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECT_RELATED);
            ps.setInt(1, (int) key);
            res = ps.executeQuery();
            while(res.next()){
                l.add(new RegistroUnicoDTO(res.getInt(1),new DateManager(res.getDate(2)),new DateManager(res.getDate(3)),res.getBoolean(4),res.getString(5),res.getInt(6),res.getInt(7),res.getInt(8)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SolicitudDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    
    public List<RegistroUnicoDTO> selectRegistroUnico(Object idArea,Object idEstado) {
        PreparedStatement ps;
        ResultSet res;
        ArrayList l = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECT_REGISTRO_UNICO);
            ps.setInt(1, (int) idArea);
            ps.setInt(2, (int) idEstado);
            res = ps.executeQuery();
            while(res.next()){
                l.add(new RegistroUnicoDTO(res.getInt(1),new DateManager(res.getDate(2)),new DateManager(res.getDate(3)),res.getBoolean(4),res.getString(5),res.getInt(6),res.getInt(7),res.getInt(8)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SolicitudDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    
    public List<RegistroUnicoDTO> selectLoteConfirmar(Object idArea,Object idEstado) {
        PreparedStatement ps;
        ResultSet res;
        ArrayList l = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECT_LOTE_CONFIRMAR);
            ps.setInt(1, (int) idArea);
            ps.setInt(2, (int) idEstado);
            res = ps.executeQuery();
            while(res.next()){
                l.add(new RegistroUnicoDTO(res.getInt(1),new DateManager(res.getDate(2)),new DateManager(res.getDate(3)),res.getBoolean(4),res.getString(5),res.getInt(6),res.getInt(7),res.getInt(8)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SolicitudDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    
    
    
    
}

