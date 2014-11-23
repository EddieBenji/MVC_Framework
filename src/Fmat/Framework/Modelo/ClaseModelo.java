package Fmat.Framework.Modelo;

import java.util.ArrayList;

/**
 *
 * @author Lalo
 */
public abstract class ClaseModelo {

    protected ArrayList<ClaseEvento> eventos;
    protected Object datos;

    public ClaseModelo() {
        eventos = new ArrayList();
    }

    /**
     * Agrega un nuevo observador, a la lista de observadores y eventos.
     *
     * @param observer
     * @param idEvento
     */
    public void registrarObservador(InterfazObserver observer, int idEvento) {
        for (ClaseEvento evento : eventos) {
            if (evento.getIdEvento() == idEvento) {
                evento.agregarMiembro(observer);
                return;
            }
        }
    }//fin método registrarObservador.

    /**
     * Eliminar de la lista de observadores, dicho observador y se notifica a
     * los eventos, que ya no será avisado ese observador.
     *
     * @param observer
     * @param idEvento
     */
    public void eliminarObservador(InterfazObserver observer, int idEvento) {
        for (ClaseEvento evento : eventos) {
            if (evento.getIdEvento() == idEvento) {
                evento.eliminarMiembro(observer);
                return;
            }
        }
    }

    /**
     * Agrega un nuevo evento a la lista de eventos, por ejemplo: el evento 1
 notifica a controladores, el evento 2 a las vistas, para crear un nuevo
 evento, se tendrá que extender de la clase ClaseEvento.
     *
     * @param evento
     */
    public void agregarEvento(ClaseEvento evento) {
        for (ClaseEvento evt : this.eventos) {
            if (evt.getIdEvento() == evento.getIdEvento()) {
                break;
            } else {
                eventos.add(evento);
            }
        }
    }

    /**
     * Este método es el encargado de notificar a todos los observadores que
     * estén asociado a dicho evento.
     *
     * @param idEvento
     */
    public void notificarObservadoresEvento(int idEvento) {

        for (ClaseEvento evento : eventos) {
            if (evento.getIdEvento() == idEvento) {
                for (InterfazObserver observer : evento.getMiembros()) {

                    observer.actualizar(getDatos());
                }
                break;
            }
        }
    }
    
    

    /**
     * Este método debe ser implementado por el proyecto debido a que la
     * información deberá ser tomada de la caché.
     *
     * @return the datos
     */
    public abstract Object getDatos();

    /**
     * @param datos the datos to set
     */
    public void setDatos(Object datos) {
        this.datos = datos;
    }

}
