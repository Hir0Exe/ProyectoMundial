package proyectomundial.model;

/**
 *
 * @author Hiro
 */
public class Resultado {
    int id;
    char grupo;
    String local;
    String visitante;
    String continenteLocal;
    String continenteVisitante;
    int golesLocal;
    int golesVisitantes;
    int totalGol;

    public Resultado() {
    }

    public Resultado(int id, char grupo, String local, String visitante, String continenteLocal,
            String continenteVisitante, int golesLocal, int golesVisitantes) {
        this.id = id;
        this.grupo = grupo;
        this.local = local;
        this.visitante = visitante;
        this.continenteLocal = continenteLocal;
        this.continenteVisitante = continenteVisitante;
        this.golesLocal = golesLocal;
        this.golesVisitantes = golesVisitantes;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getGrupo() {
        return this.grupo;
    }

    public void setGrupo(char grupo) {
        this.grupo = grupo;
    }

    public String getLocal() {
        return this.local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getVisitante() {
        return this.visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public String getContinenteLocal() {
        return this.continenteLocal;
    }

    public void setContinenteLocal(String continenteLocal) {
        this.continenteLocal = continenteLocal;
    }

    public String getContinenteVisitante() {
        return this.continenteVisitante;
    }

    public void setContinenteVisitante(String continenteVisitante) {
        this.continenteVisitante = continenteVisitante;
    }

    public int getGolesLocal() {
        return this.golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public int getGolesVisitantes() {
        return this.golesVisitantes;
    }

    public void setGolesVisitantes(int golesVisitantes) {
        this.golesVisitantes = golesVisitantes;
    }

    public int getTotalGol() {
        return this.totalGol;
    }

    public void setTotalGol(int totalGol) {
        this.totalGol = totalGol;
    }

}
