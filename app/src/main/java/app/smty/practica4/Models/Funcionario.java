package app.smty.practica4.Models;

public class Funcionario {
    String id, idevaluado, Nombres, genero, situacion, cargo, fechainicio, fechafin, imgJPG, imgjpg;

    public Funcionario() {
    }

    public Funcionario(String id, String idevaluado, String nombres, String genero, String situacion, String cargo, String fechainicio, String fechafin, String imgJPG, String imgjpg) {
        this.id = id;
        this.idevaluado = idevaluado;
        Nombres = nombres;
        this.genero = genero;
        this.situacion = situacion;
        this.cargo = cargo;
        this.fechainicio = fechainicio;
        this.fechafin = fechafin;
        this.imgJPG = imgJPG;
        this.imgjpg = imgjpg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdevaluado() {
        return idevaluado;
    }

    public void setIdevaluado(String idevaluado) {
        this.idevaluado = idevaluado;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(String fechainicio) {
        this.fechainicio = fechainicio;
    }

    public String getFechafin() {
        return fechafin;
    }

    public void setFechafin(String fechafin) {
        this.fechafin = fechafin;
    }

    public String getImgJPG() {
        return imgJPG;
    }

    public void setImgJPG(String imgJPG) {
        this.imgJPG = imgJPG;
    }

    public String getImgjpg() {
        return imgjpg;
    }

    public void setImgjpg(String imgjpg) {
        this.imgjpg = imgjpg;
    }
}
