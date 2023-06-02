/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomundial.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import proyectomundial.model.Seleccion;
import proyectomundial.util.BasedeDatos;

/**
 *
 * @author miguelropero
 */
public class SeleccionDAO {
    public static int totalInt;

    public SeleccionDAO() {
        BasedeDatos.conectar();
        getSelecciones();
    }

    public boolean registrarSeleccion(Seleccion seleccion) {

        String sql = "INSERT INTO c_bastos.seleccion (equipo, continente, director, nacionalidad) values("
                + "'" + seleccion.getNombre() + "', "
                + "'" + seleccion.getContinente() + "', "
                + "'" + seleccion.getDt() + "', "
                + "'" + seleccion.getNacionalidad() + "')";

        // BasedeDatos.conectar();
        boolean registro = BasedeDatos.ejecutarActualizacionSQL(sql);
        // BasedeDatos.desconectar();
        return registro;
    }

    public List<Seleccion> getSelecciones() {

        String sql = "SELECT distinct equipo, continente, director, nacionalidad FROM c_bastos.seleccion ORDER BY equipo ASC";
        List<Seleccion> selecciones = new ArrayList<Seleccion>();
        totalInt = 0;
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Seleccion seleccion = new Seleccion(result.getString("equipo"), result.getString("continente"),
                            result.getString("director"), result.getString("nacionalidad"));
                    selecciones.add(seleccion);
                    totalInt++;
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return selecciones;
    }

    public List<Seleccion> getSelecciones(String dato) {

        String sql = "SELECT distinct equipo, continente, director, nacionalidad FROM c_bastos.seleccion where "
                + "equipo iLIKE '%" + dato + "%' "
                + "OR continente iLIKE '%" + dato + "%' "
                + "OR director iLIKE '%" + dato + "%' "
                + "OR nacionalidad iLIKE '%" + dato + "%' "
                + "ORDER BY equipo ASC";
        List<Seleccion> selecciones = new ArrayList<Seleccion>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Seleccion seleccion = new Seleccion(result.getString("equipo"), result.getString("continente"),
                            result.getString("director"), result.getString("nacionalidad"));
                    selecciones.add(seleccion);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return selecciones;
    }

    public String[][] getSeleccionesMatriz() {

        String[][] matrizSelecciones = null;
        List<Seleccion> selecciones = getSelecciones();

        if (selecciones != null && selecciones.size() > 0) {

            matrizSelecciones = new String[selecciones.size()][5];

            int x = 0;
            for (Seleccion seleccion : selecciones) {

                matrizSelecciones[x][0] = String.valueOf(x + 1);
                matrizSelecciones[x][1] = seleccion.getNombre();
                matrizSelecciones[x][2] = seleccion.getContinente();
                matrizSelecciones[x][3] = seleccion.getDt();
                matrizSelecciones[x][4] = seleccion.getNacionalidad();
                x++;
            }
        }

        return matrizSelecciones;
    }

    public String[][] getSeleccionesMatriz(String dato) {

        String[][] matrizSelecciones = null;
        List<Seleccion> selecciones = getSelecciones(dato);

        if (selecciones != null && selecciones.size() > 0) {

            matrizSelecciones = new String[selecciones.size()][5];

            int x = 0;
            for (Seleccion seleccion : selecciones) {

                matrizSelecciones[x][0] = String.valueOf(x + 1);
                matrizSelecciones[x][1] = seleccion.getNombre();
                matrizSelecciones[x][2] = seleccion.getContinente();
                matrizSelecciones[x][3] = seleccion.getDt();
                matrizSelecciones[x][4] = seleccion.getNacionalidad();
                x++;
            }
        } else {
            return null;
        }

        return matrizSelecciones;
    }

    public List<Seleccion> getContinenteDiferente() {
        String sql = "SELECT distinct continente FROM c_bastos.seleccion ORDER BY continente ASC";
        List<Seleccion> selecciones = new ArrayList<Seleccion>();
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Seleccion seleccion = new Seleccion();
                    seleccion.setContinente(result.getString("continente"));
                    selecciones.add(seleccion);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando getContinenteDiferente");
        }
        return selecciones;
    }

    public List<Seleccion> getNacionalidadDiferente() {
        String sql = "SELECT distinct nacionalidad FROM c_bastos.seleccion ORDER BY nacionalidad ASC";
        List<Seleccion> selecciones = new ArrayList<Seleccion>();
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Seleccion seleccion = new Seleccion();
                    seleccion.setNacionalidad(result.getString("nacionalidad"));
                    selecciones.add(seleccion);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando getNacionalidadDiferente");
        }
        return selecciones;
    }

    public String[][] getRankingNacionalidadesDTMatriz() {
        String[][] matrizSelecciones = null;
        List<Seleccion> selecciones = getSelecciones();
        List<Seleccion> nacionalidades = getNacionalidadDiferente();
        int x = 0, aux = 0;
        if (selecciones != null && selecciones.size() > 0) {

            matrizSelecciones = new String[nacionalidades.size()][3];

            for (Seleccion nacionalidad : nacionalidades) {

                matrizSelecciones[x][1] = nacionalidad.getNacionalidad();

                for (Seleccion seleccion : selecciones) {
                    if (seleccion.getNacionalidad().equals(nacionalidad.getNacionalidad())) {
                        aux++;
                    }
                }

                matrizSelecciones[x][2] = String.valueOf(aux);
                x++;
                aux = 0;
            }
            int n = matrizSelecciones.length;
            String[] temp;
            for (int i = 0; i < n; i++) {
                for (int j = 1; j < (n - i); j++) {
                    if (Integer.parseInt(matrizSelecciones[j - 1][2]) < Integer.parseInt(matrizSelecciones[j][2])) {
                        temp = matrizSelecciones[j - 1];
                        matrizSelecciones[j - 1] = matrizSelecciones[j];
                        matrizSelecciones[j] = temp;
                    }
                }
            }
            for (int i = 0; i < matrizSelecciones.length; i++) {
                matrizSelecciones[i][0] = String.valueOf(i + 1);
            }
        }
        return matrizSelecciones;
    }
}
