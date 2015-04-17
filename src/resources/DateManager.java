/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author rodrigo
 */
public class DateManager {
    
    private SimpleDateFormat formato;
    private Date fechaDate;
    private String fechaString;
    private long fechaLong;

    public DateManager(long fechaLong) {
        this.fechaLong = fechaLong;
        this.fechaDate = new Date(fechaLong);
        this.fechaString = this.DateTimeToString(fechaDate);
    }
    
    
    public DateManager(Date fechaDate) {
        this.fechaDate = fechaDate;
        this.fechaString =this.DateTimeToString(fechaDate);
        this.fechaLong = this.fechaDate.getTime();
    }

    public DateManager(String fechaString) {
        this.fechaString = fechaString;
        this.fechaDate = null; // this.StringToDate(fechaString, "dd-MM-YYYY HH:mm:ss");
        try {
            formato = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
            this.fechaDate = formato.parse(fechaString);
        } catch (ParseException ex) {
            //Logger.getLogger(DateManager.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Ex");
            this.fechaDate = null;
        }
        this.fechaLong = this.fechaDate.getTime();
    }
    
    public long getFechaLong() {
        return fechaLong;
    }

    public void setFechaLong(long fechaLong) {
        this.fechaLong = fechaLong;
        
    }

    public Date getFechaDate() {
        return fechaDate;
    }

    public void setFechaDate(Date fechaDate) {
        this.fechaDate = fechaDate;
    }

    public String getFechaString() {
        return fechaString;
    }

    public void setFechaString(String fechaString) {
        this.fechaString = fechaString;
    }
    
    
    
    private String dateToString(Date fechaDate){
        formato = new SimpleDateFormat("dd/MM/yyyy");    
        //System.out.println("Fecha: "+formato.format(fechaDate));
        return formato.format(fechaDate);
    }
    
    private Date StringToDate(String date,String format){
        try {
            formato = new SimpleDateFormat(format);
            return formato.parse(date);
        } catch (ParseException ex) {
            //Logger.getLogger(DateManager.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Ex");
            return null;
        }
    }
    
    private String DateTimeToString(Date fecha){
        formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");    
        //System.out.println("Hora y fecha: "+formato.format(fecha));
        return formato.format(fecha);
    }
    
    public String toString(){
        if(this.fechaString != null)
            return this.fechaString;
        else
            return String.format("00-00-0000 00:00:00");
    }

    
}
