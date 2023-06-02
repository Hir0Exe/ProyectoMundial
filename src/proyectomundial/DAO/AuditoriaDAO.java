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
public class AuditoriaDAO {

    public AuditoriaDAO() {
        BasedeDatos.conectar();
    }
    
    public List<Auditoria> getAuditoria() {

        String sql = "SELECT distinct * FROM c_bastos.auditoria ORDER BY pagina ASC";
        List<Auditoria> auditorias = new ArrayList<Auditoria>();
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Auditoria resultado = new Auditoria(result.getString("pagina"), result.getInt("contador"));
                    auditorias.add(resultado);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando auditorias");
        }

        return auditorias;
    }
    
    public String[][] getAuditoriaMatriz() {

        String[][] matrizAuditorias = null;
        List<Auditoria> auditorias = getAuditoria();

        if (auditorias != null && auditorias.size() > 0) {

            matrizAuditorias = new String[auditorias.size()][3];

            int x = 0;
            for (Auditoria auditoria : auditorias) {

                matrizAuditorias[x][0] = String.valueOf(x+1);
                matrizAuditorias[x][1] = auditoria.getPagina();
                matrizAuditorias[x][2] = String.valueOf(auditoria.getContador());
                x++;
            }
        }

        return matrizAuditorias;
    }
    
    public void actualizarContador(String pagina){
        String sql = "update c_bastos.auditoria set contador = contador + 1 where pagina = '"+pagina+"';";
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando auditoria contador");
        }
        
    }
}
