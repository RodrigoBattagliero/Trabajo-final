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
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import resources.DateManager;

/**
 *
 * @author rodrigo
 */
public class DatosDocentesDAO implements Consultas<DatosDocentesDTO> {
     private final String SQL_INSERT = "INSERT INTO "
            + "docentes(nombre,apellido,dni,telefono,email,lugar_residencia,motivo_comision,fecha_inicio,fecha_finalizacion,id_departamento_academico,id_solicitud) "
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?) "
            + "RETURNING id";
    private final String SQL_UPDATE = "UPDATE docentes "
            + "SET nombre = ?, apellido = ?, dni = ?, telefono = ?, email = ?, lugar_residencia = ?, motivo_comision = ?, fecha_inicio = ?, fecha_finalizacion = ?, id_departamento_academico = ?, id_solicitud = ? "
            + "WHERE id = ?";
    private final String SQL_DELETE = "DELETE FROM docentes WHERE id = ?";
    private final String SQL_SELECT = "SELECT "
            + "id,nombre,apellido,dni,telefono,email,lugar_residencia,motivo_comision,fecha_inicio,fecha_finalizacion,id_departamento_academico,id_solicitud,observaciones "
            + "FROM docentes "
            + "WHERE id = ?";
    private final String SQL_SELECTALL = "SELECT "
            + "id,nombre,apellido,dni,telefono,email,lugar_residencia,motivo_comision,fecha_inicio,fecha_finalizacion,id_departamento_academico,id_solicitud,observaciones "
            + "FROM docentes";
    private final String SQL_SELECTRELATED = "SELECT "
            + "id,nombre,apellido,dni,telefono,email,lugar_residencia,motivo_comision,fecha_inicio,fecha_finalizacion,id_departamento_academico,id_solicitud,observaciones "
            + "FROM docentes "
            + "WHERE id_solicitud = ?";
    
    private static final Conexion conex = Conexion.estado();
    // int id, String nombre, String apellido, String dni, String telefono, String email, String lugar_residencia, int id_sede, String motivi_comision, Date fecha_hora_inicio, Date fecha_hora_finalizacion, int id_departamento_academico, int id_solicitud, String observaciones
    @Override
    public int create(DatosDocentesDTO c) {
        PreparedStatement ps;
        int id = 0;
        try {
            ps = conex.getCnn().prepareStatement(SQL_INSERT);
            ps.setString(1,c.getNombre());
            ps.setString(2,c.getApellido());
            ps.setString(3,c.getDni());
            ps.setString(4,c.getTelefono());
            ps.setString(5,c.getEmail());
            ps.setString(6,c.getLugar_residencia());
            ps.setString(7,c.getMotivi_comision());
            ps.setTimestamp(8,  new Timestamp(c.getFecha_hora_inicio().getFechaLong()));
            ps.setTimestamp(9, new Timestamp(c.getFecha_hora_finalizacion().getFechaLong()));
            ps.setInt(10,c.getId_departamento_academico());
            ps.setInt(11,c.getId_solicitud());
            ResultSet res = ps.executeQuery();
            res.next();
            id = res.getInt(1);
            if(id > 0)
                return id;
        } catch (SQLException ex) {
            Logger.getLogger(DatosDocentesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DatosDocentesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return false;
    }

    @Override
    public boolean update(DatosDocentesDTO c) {
        PreparedStatement ps;
        
        try {
            ps = conex.getCnn().prepareStatement(SQL_UPDATE);
            ps.setString(1,c.getNombre());
            ps.setString(2,c.getApellido());
            ps.setString(3,c.getDni());
            ps.setString(4,c.getTelefono());
            ps.setString(5,c.getEmail());
            ps.setString(6,c.getLugar_residencia());
            ps.setString(7,c.getMotivi_comision());
            ps.setDate(8, new Date(c.getFecha_hora_inicio().getFechaLong()));
            ps.setDate(9, new Date(c.getFecha_hora_finalizacion().getFechaLong()));
            ps.setInt(10,c.getId_departamento_academico());
            ps.setInt(11,c.getId_solicitud());
            ps.setInt(12, c.getId());
            if(ps.executeUpdate() > 0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatosDocentesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return false;
    }

    @Override
    public DatosDocentesDTO selectOne(Object key) {
        PreparedStatement ps;
        ResultSet res;
        DatosDocentesDTO a = null;
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECT);
            ps.setInt(1, (int) key);
           res = ps.executeQuery();
           while(res.next()){
               a = new DatosDocentesDTO(res.getInt(1),res.getString(2),
                       res.getString(3),res.getString(4),res.getString(5),res.getString(6),
                       res.getString(7),res.getString(8),new DateManager(res.getDate(9)),
                       new DateManager(res.getDate(10)),res.getInt(11),res.getInt(12),res.getString(13));
           }
           return a;
        } catch (SQLException ex) {
            Logger.getLogger(DatosDocentesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return a;
    }

    @Override
    public List<DatosDocentesDTO> selectAll() {
        PreparedStatement ps;
        ResultSet res;
        ArrayList datosDocentes = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECTALL);
            res = ps.executeQuery();
            while(res.next()){
                datosDocentes.add(new DatosDocentesDTO(res.getInt(1),res.getString(2),
                       res.getString(3),res.getString(4),res.getString(5),res.getString(6),
                       res.getString(7),res.getString(8),new DateManager(res.getDate(9)),
                       new DateManager(res.getDate(10)),res.getInt(11),res.getInt(12),res.getString(13)));
            }
            return datosDocentes;
        } catch (SQLException ex) {
            Logger.getLogger(DatosDocentesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return datosDocentes;
    }

    @Override
    public List<DatosDocentesDTO> selectRelated(Object key) {
        PreparedStatement ps;
        ResultSet res;
        ArrayList l = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECTRELATED);
            ps.setInt(1, (int) key);
            res = ps.executeQuery();
            while(res.next()){
                l.add(new DatosDocentesDTO(res.getInt(1),res.getString(2),
                       res.getString(3),res.getString(4),res.getString(5),res.getString(6),
                       res.getString(7),res.getString(8),new DateManager(res.getDate(9)),
                       new DateManager(res.getDate(10)),res.getInt(11),res.getInt(12),res.getString(13)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatosDocentesDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return l;
    }

}
