package Fmat.Framework.Controlador;

import Fmat.Framework.Modelo.ClaseModelo;
import Fmat.Framework.Modelo.InterfazObserver;

/**
 *
 * @author Lalo
 */
public abstract class ClaseControlador implements InterfazObserver {

    protected ClaseModelo modelo;

    public ClaseControlador(ClaseModelo modelo, int idEvento) {
        this.modelo = modelo;
        this.modelo.registrarObservador(this, idEvento);
    }

    /**
     *Devuelve el atributo, de la clase ClaseModelo.
     * @return
     */
    public ClaseModelo getModelo() {
        return modelo;
    }

    /**
     *Establece el atributo, de la clase ClaseModelo.
     * @param modelo
     */
    public void setModelo(ClaseModelo modelo) {
        this.modelo = modelo;
    }

}
