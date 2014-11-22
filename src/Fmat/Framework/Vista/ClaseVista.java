/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fmat.Framework.Vista;

import Fmat.Framework.Modelo.ClaseModelo;
import Fmat.Framework.Modelo.InterfazObserver;

/**
 *
 * @author Lalo
 */
public abstract class ClaseVista implements InterfazObserver {

    private ClaseModelo modelo;

    public ClaseVista(ClaseModelo modelo, int idEvento) {
        this.modelo = modelo;
        this.modelo.registrarObservador(this, idEvento);
    }

    /**
     *Este método será el encargado de pintar la información
     * en la pantalla.
     */
    public abstract void mostrarInformacion();

}
