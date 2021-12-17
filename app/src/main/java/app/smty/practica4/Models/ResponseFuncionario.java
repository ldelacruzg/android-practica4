package app.smty.practica4.Models;

import java.util.List;

public class ResponseFuncionario {
    List<Funcionario> listaaevaluar;

    public ResponseFuncionario() {
    }

    public ResponseFuncionario(List<Funcionario> listaaevaluar) {
        this.listaaevaluar = listaaevaluar;
    }

    public List<Funcionario> getListaaevaluar() {
        return listaaevaluar;
    }

    public void setListaaevaluar(List<Funcionario> listaaevaluar) {
        this.listaaevaluar = listaaevaluar;
    }
}
