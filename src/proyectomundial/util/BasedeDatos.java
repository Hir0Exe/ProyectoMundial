/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomundial.util;

import java.sql.ResultSet;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miguelropero
 */
public class BasedeDatos {
    
    private static String bd = "unisimon";
    private static String user = "unisimon_user";
    private static String password = "11k1WiZg5ekiFQYHx9Bog6W7cTArSZea";
    private static String url = "jdbc:postgresql://dpg-cfpuu1qrrk0fd9ounopg-a.oregon-postgres.render.com:5432/" + bd;
    private static Connection conexion = null;

    public static boolean hayConexion() {
        return (conexion != null);
    }  

    public static void conectar() {
        
        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager
                    .getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BasedeDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BasedeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void desconectar() {
        try {
            conexion.close();
        } catch (Exception e) {
            System.err.println("SQL Error:" + e.getMessage());
        }
    }

    public static boolean ejecutarActualizacionSQL(String comandoSQL) {

        boolean ok;
        if (hayConexion()) {
            PreparedStatement sql;
            try {
                sql = conexion.prepareStatement(comandoSQL);

                ok = sql.executeUpdate() != 0;
                //importante cerrar la conexion
                sql.close();
                sql = null;
                return ok;
            } catch (SQLException ex) {
                Logger.getLogger(BasedeDatos.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            return false;
        }
    }


    public static ResultSet ejecutarSQL(String consultaSQL) throws Exception {
        if (hayConexion()) {
            Statement sql = conexion.createStatement();
            return sql.executeQuery(consultaSQL);
        } else {
            return null;
        }
    }

    public static java.util.ArrayList<String> getConsultaSQL(String sql) {
        StringBuffer html = new StringBuffer();
        ArrayList<String> v = new ArrayList<String>();

        if (hayConexion()) {
            ResultSet rs;
            try {
                rs = ejecutarSQL(sql);
                ResultSetMetaData rsm = rs.getMetaData();
                while (rs.next()) {
                    String r = "";
                    for (int i = 1; i <= rsm.getColumnCount(); i++) {
                        r += rs.getString(i) + "-";
                    }
                    v.add(r);
                }
                //desconectar();
            } catch (Exception ex) {
                Logger.getLogger(BasedeDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return v;
    }

    public static void main(String args[]) throws Exception {
        conectar();
    
        if(hayConexion()) {
            System.out.println("Hay conexion");
            String sql = "Select equipo, director from poo.equipo";
            
            ArrayList<String> lista = getConsultaSQL(sql);
            for(String cadena : lista) {
                System.out.println(cadena);
            }
            
            System.out.println("------------------------");
            ResultSet y = ejecutarSQL(sql);
                
            while (y.next()) {  
                System.out.println(y.getString("equipo") + " - " + y.getString("director")); 
            }
        
        } else {
            System.out.println("No Hay conexion");
            conectar();
        }
        
        //desconectar();
    }
    
}
