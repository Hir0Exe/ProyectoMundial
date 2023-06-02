package proyectomundial.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import proyectomundial.model.*;
import proyectomundial.util.BasedeDatos;

/**
 *
 * @author Hiro & Camily
 */
public class ResultadoDAO {
    public static int totalInt;

    public double promedioGoles;
    // cantPartidosGanadorEmpate [0] cantidad partidos con ganador
    // cantPartidosGanadorEmpate [1] cantidad partidos empatados
    public int[] cantPartidosGanadorEmpate;
    // seleccionPuntos [0] seleccion con mayor cantidad de puntos
    // seleccionPuntos [1] seleccion con menor cantidad de puntos
    public String[] seleccionPuntos;
    public List<Seleccion> selecciones;

    public ResultadoDAO() {
        BasedeDatos.conectar();

    }

    public boolean registrarResultado(Resultado resultado) {

        String sql = "INSERT INTO c_bastos.partido (id, grupo, local, visitante, continente_local, continente_visitante, goles_local, goles_visitante) values("
                + "'" + resultado.getId() + "', "
                + "'" + resultado.getGrupo() + "', "
                + "'" + resultado.getLocal() + "', "
                + "'" + resultado.getVisitante() + "', "
                + "'" + resultado.getContinenteLocal() + "', "
                + "'" + resultado.getContinenteVisitante() + "', "
                + "'" + resultado.getGolesLocal() + "', "
                + "'" + resultado.getGolesVisitantes() + "')";

        boolean registro = BasedeDatos.ejecutarActualizacionSQL(sql);

        return registro;
    }

    public List<Resultado> getResultado() {

        String sql = "SELECT distinct * FROM c_bastos.partido ORDER BY id ASC";
        List<Resultado> resultados = new ArrayList<Resultado>();
        totalInt = 0;
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Resultado resultado = new Resultado(result.getInt("id"), result.getString("grupo").charAt(0),
                            result.getString("local"), result.getString("visitante"),
                            result.getString("continente_local"), result.getString("continente_visitante"),
                            result.getInt("goles_local"), result.getInt("goles_visitante"));
                    resultado.setTotalGol(resultado.getGolesLocal() + resultado.getGolesVisitantes());
                    resultados.add(resultado);
                    totalInt++;
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando resultados");
        }

        return resultados;
    }

    public List<Resultado> getResultado(String dato) {

        String sql = "SELECT distinct * FROM c_bastos.partido where "
                + "grupo iLIKE '%" + dato + "%' "
                + "OR local iLIKE '%" + dato + "%' "
                + "OR visitante iLIKE '%" + dato + "%' "
                + "OR continente_local iLIKE '%" + dato + "%' "
                + "OR continente_visitante iLIKE '%" + dato + "%' "
                + "OR CAST(goles_local AS VARCHAR(30)) iLIKE '%" + dato + "%' "
                + "OR CAST(goles_visitante AS VARCHAR(30)) iLIKE '%" + dato + "%' "
                + "ORDER BY id ASC";

        List<Resultado> resultados = new ArrayList<Resultado>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Resultado resultado = new Resultado(result.getInt("id"), result.getString("grupo").charAt(0),
                            result.getString("local"), result.getString("visitante"),
                            result.getString("continente_local"), result.getString("continente_visitante"),
                            result.getInt("goles_local"), result.getInt("goles_visitante"));
                    resultado.setTotalGol(resultado.getGolesLocal() + resultado.getGolesVisitantes());
                    resultados.add(resultado);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando resultados");
        }

        return resultados;
    }

    public String[][] getResultadoMatriz() {

        String[][] matrizResultados = null;
        List<Resultado> resultados = getResultado();

        setInfoDashboard(resultados);

        if (resultados != null && resultados.size() > 0) {

            matrizResultados = new String[resultados.size()][8];

            int x = 0;
            for (Resultado resultado : resultados) {

                matrizResultados[x][0] = String.valueOf(resultado.getId());
                matrizResultados[x][1] = String.valueOf(resultado.getGrupo());
                matrizResultados[x][2] = resultado.getLocal();
                matrizResultados[x][3] = resultado.getVisitante();
                matrizResultados[x][4] = resultado.getContinenteLocal();
                matrizResultados[x][5] = resultado.getContinenteVisitante();
                matrizResultados[x][6] = String.valueOf(resultado.getGolesLocal());
                matrizResultados[x][7] = String.valueOf(resultado.getGolesVisitantes());
                x++;
            }
        }

        return matrizResultados;
    }

    public String[][] getResultadoMatriz(String dato) {

        String[][] matrizResultados = null;
        List<Resultado> resultados = getResultado(dato);

        if (resultados != null && resultados.size() > 0) {

            matrizResultados = new String[resultados.size()][8];

            int x = 0;
            for (Resultado resultado : resultados) {

                matrizResultados[x][0] = String.valueOf(resultado.getId());
                matrizResultados[x][1] = String.valueOf(resultado.getGrupo());
                matrizResultados[x][2] = resultado.getLocal();
                matrizResultados[x][3] = resultado.getVisitante();
                matrizResultados[x][4] = resultado.getContinenteLocal();
                matrizResultados[x][5] = resultado.getContinenteVisitante();
                matrizResultados[x][6] = String.valueOf(resultado.getGolesLocal());
                matrizResultados[x][7] = String.valueOf(resultado.getGolesVisitantes());
                x++;
            }
        }

        return matrizResultados;
    }

    public String[][] getResultadoOrdenMayorGolesMatriz() {

        String[][] matrizResultados = null;
        List<Resultado> resultados = getMetodoBurbujaResultado(getResultado());

        if (resultados != null && resultados.size() > 0) {

            matrizResultados = new String[resultados.size()][8];

            int x = 0;
            for (Resultado resultado : resultados) {

                matrizResultados[x][0] = String.valueOf(x + 1);
                matrizResultados[x][1] = String.valueOf(resultado.getGrupo());
                matrizResultados[x][2] = resultado.getLocal();
                matrizResultados[x][3] = resultado.getVisitante();
                matrizResultados[x][4] = resultado.getContinenteLocal();
                matrizResultados[x][5] = resultado.getContinenteVisitante();
                matrizResultados[x][6] = String.valueOf(resultado.getGolesLocal());
                matrizResultados[x][7] = String.valueOf(resultado.getGolesVisitantes());
                x++;
            }
        }

        return matrizResultados;
    }

    public String[][] getSeleccionOrdenMayorGolesMatriz() {

        String[][] matrizSelecciones = null;

        List<Seleccion> tempSelecciones = getMetodoBurbujaSeleccion(selecciones);

        if (tempSelecciones != null && tempSelecciones.size() > 0) {

            matrizSelecciones = new String[tempSelecciones.size()][6];

            int x = 0;
            for (Seleccion seleccion : tempSelecciones) {

                matrizSelecciones[x][0] = String.valueOf(x + 1);
                matrizSelecciones[x][1] = seleccion.getNombre();
                matrizSelecciones[x][2] = seleccion.getContinente();
                matrizSelecciones[x][3] = seleccion.getDt();
                matrizSelecciones[x][4] = seleccion.getNacionalidad();
                matrizSelecciones[x][5] = String.valueOf(seleccion.getGol());
                x++;
            }
        }

        return matrizSelecciones;
    }

    public String[][] getContinenteGoles() {

        String[][] matrizContinente = null;

        List<Seleccion> continentes = new SeleccionDAO().getContinenteDiferente();
        List<Resultado> resultados = getResultado();

        if (resultados != null && resultados.size() > 0) {

            matrizContinente = new String[resultados.size()][8];

            for (Resultado resultado : resultados) {
                for (Seleccion continente : continentes) {
                    if (continente.getContinente().equalsIgnoreCase(resultado.getContinenteLocal())) {
                        continente.setGol(continente.getGol() + resultado.getGolesLocal());
                    }
                    if (continente.getContinente().equalsIgnoreCase(resultado.getContinenteVisitante())) {
                        continente.setGol(continente.getGol() + resultado.getGolesVisitantes());
                    }
                }
            }

            continentes = getMetodoBurbujaSeleccion(continentes);

            int x = 0;
            for (Seleccion continente : continentes) {
                matrizContinente[x][0] = String.valueOf(x + 1);
                matrizContinente[x][1] = continente.getContinente();
                matrizContinente[x][2] = String.valueOf(continente.getGol());
                x++;
            }
        }

        return matrizContinente;
    }

    public List<Resultado> getMetodoBurbujaResultado(List<Resultado> resultados) {
        int n = resultados.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Resultado resultadoActual = resultados.get(j);
                Resultado resultadoSiguiente = resultados.get(j + 1);
                if (resultadoActual.getTotalGol() < resultadoSiguiente.getTotalGol()) {
                    // Intercambiar los elementos si están en el orden incorrecto
                    resultados.set(j, resultadoSiguiente);
                    resultados.set(j + 1, resultadoActual);
                }
            }
        }
        return resultados;
    }

    public List<Seleccion> getMetodoBurbujaSeleccion(List<Seleccion> selecciones) {
        int n = selecciones.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Seleccion resultadoActual = selecciones.get(j);
                Seleccion resultadoSiguiente = selecciones.get(j + 1);
                if (resultadoActual.getGol() < resultadoSiguiente.getGol()) {
                    // Intercambiar los elementos si están en el orden incorrecto
                    selecciones.set(j, resultadoSiguiente);
                    selecciones.set(j + 1, resultadoActual);
                }
            }
        }
        return selecciones;
    }

    public void setInfoDashboard(List<Resultado> resultados) {
        if (resultados != null && resultados.size() > 0) {
            // Limpio variables globales
            promedioGoles = 0;
            cantPartidosGanadorEmpate = new int[2];
            seleccionPuntos = new String[2];
            // Variables locales necesarias
            int cantGoles = 0;
            selecciones = new SeleccionDAO().getSelecciones();

            for (Resultado resultado : resultados) {
                cantGoles += resultado.getGolesLocal() + resultado.getGolesVisitantes();
                if (resultado.getGolesLocal() == resultado.getGolesVisitantes()) {
                    cantPartidosGanadorEmpate[1]++;
                } else {
                    cantPartidosGanadorEmpate[0]++;
                }
                selecciones = getSeleccionesActGoles(selecciones, resultado);
            }

            int cantPuntosMayor = 0;
            int cantPuntosMenor = 0;
            for (Seleccion seleccion : selecciones) {
                if (seleccion.getPunto() > cantPuntosMayor) {
                    cantPuntosMayor = seleccion.getPunto();
                    seleccionPuntos[0] = seleccion.getNombre() + " (Puntos: " + seleccion.getPunto() + ")";
                } else if (seleccion.getPunto() < cantPuntosMenor) {
                    cantPuntosMenor = seleccion.getPunto();
                    seleccionPuntos[1] = seleccion.getNombre() + " (Puntos: " + seleccion.getPunto() + ")";
                }
            }
            promedioGoles = cantGoles / resultados.size();
        }
    }

    public List<Seleccion> getSeleccionesActGoles(List<Seleccion> selecciones, Resultado resultado) {
        for (int i = 0; i < selecciones.size(); i++) {
            if (selecciones.get(i).getNombre().equalsIgnoreCase(resultado.getLocal())) {
                selecciones.get(i).setGol(selecciones.get(i).getGol() + resultado.getGolesLocal());
                selecciones.get(i).setPunto(
                        selecciones.get(i).getPunto() + (resultado.getGolesLocal() - resultado.getGolesVisitantes()));
            } else if (selecciones.get(i).getNombre().equalsIgnoreCase(resultado.getVisitante())) {
                selecciones.get(i).setGol(selecciones.get(i).getGol() + resultado.getGolesVisitantes());
                selecciones.get(i).setPunto(
                        selecciones.get(i).getPunto() + (resultado.getGolesVisitantes() - resultado.getGolesLocal()));
            }
        }
        return selecciones;
    }
}