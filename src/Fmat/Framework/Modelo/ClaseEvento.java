package Fmat.Framework.Modelo;

import java.util.ArrayList;

/**
 *
 * @author Lalo
 */
public class ClaseEvento {

    protected ArrayList<InterfazObserver> miembros;
    protected int idEvento;
    protected String nombreEvento;

    public ClaseEvento(int idEvento) {
        this.miembros = new ArrayList();
        this.idEvento = idEvento;
    }

    /**
     *Agrega un elemento a la lista de eventos.
     * @param miembro
     */
    public void agregarMiembro(InterfazObserver miembro) {
        miembros.add(miembro);
    }

    /**
     *Eliminar un miembro en específico de la lista
     * de los eventos.
     * @param miembro
     */
    public void eliminarMiembro(InterfazObserver miembro) {
        miembros.remove(miembro);
    }

    /**
     * @return the miembros
     */
    public ArrayList<InterfazObserver> getMiembros() {
        return miembros;
    }

    /**
     * @return the idEvento
     */
    public int getIdEvento() {
        return idEvento;
    }

    /**
     * @return the nombreEvento
     */
    public String getNombreEvento() {
        return nombreEvento;
    }

    /**
     * @param miembros the miembros to set
     */
    public void setMiembros(ArrayList<InterfazObserver> miembros) {
        this.miembros = miembros;
    }

    /**
     * @param idEvento the idEvento to set
     */
    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    /**
     * @param nombreEvento the nombreEvento to set
     */
    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

}
