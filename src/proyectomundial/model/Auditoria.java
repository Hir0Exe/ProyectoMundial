package proyectomundial.model;
/**
 *
 * @author Hiro & Camily
 */
public class Auditoria {
    String pagina;
    int contador;

    public Auditoria(String pagina, int contador) {
        this.pagina = pagina;
        this.contador = contador;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
    
}
