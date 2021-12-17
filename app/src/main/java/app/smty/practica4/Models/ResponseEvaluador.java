package app.smty.practica4.Models;

import java.util.List;

public class ResponseEvaluador {
    List<Evaluador> listaaevaluador;

    public ResponseEvaluador() {
    }

    public ResponseEvaluador(List<Evaluador> listaaevaluador) {
        this.listaaevaluador = listaaevaluador;
    }

    public List<Evaluador> getListaaevaluador() {
        return listaaevaluador;
    }

    public void setListaaevaluador(List<Evaluador> listaaevaluador) {
        this.listaaevaluador = listaaevaluador;
    }
}
