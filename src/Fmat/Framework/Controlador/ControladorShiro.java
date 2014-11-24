package Fmat.Framework.Controlador;

import javax.swing.JOptionPane;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.Ini;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author Lalo
 */
public class ControladorShiro {

    /**
     * Son 3 cosas lo que necesitamos:
     *
     * - Un módulo para inicio de sesión 
     * - Un módulo para encriptar/descrncriptar las contraseñas 
     * - Otro para cargar los usuario en caché.
     *
     *
     */

    /**
     * El SecurityUtils, que es el que maneja todo en ControladorShiro, necesita
     * un SecurityManager, en este caso usaré el de Default para no tener que
     * configurarlo manualmente
     *
     * El SecurityManager (sm) necesita un Realm y un Realm necesita el ini. En
     * este caso pasaremos un nuevo realm al sm de forma directa y con el ini
     * que estamos confugurando actualmente, pues no necesitamos un realm
     * especial.
     *
     */
    private DefaultSecurityManager sm = new DefaultSecurityManager();
    private Ini ini = new Ini();

    //Esta es la sección de nuestro ini donde se almacenan los usuarios y contraseñas
    private Ini.Section usuarios = ini.addSection(IniRealm.USERS_SECTION_NAME);

    public ControladorShiro() {
        inicializar();
    }

    private void inicializar() {

        //Se setea el relm, con el ini inicializado
        sm.setRealm(new IniRealm(ini));

        //Se setea el securityManayer que estamos configurando
        SecurityUtils.setSecurityManager(sm);
    }

    /**
     * Aquí es donde actualizamos el sm y el SecurityUtils con el ini modificado.
     *
     */
    private void actualizar() {
        sm.setRealm(new IniRealm(ini));
        SecurityUtils.setSecurityManager(sm);
    }

    /**
     * Se agregan cuentas nuevas a nuestro ini y se mandan a actualizar los
     * otros componentes con la ini modificada.
     *
     * @param usuario, es el nombre del usuario que estará asociada a la nueva
     * cuenta.
     * @param clave, es la contraseña del usuario que estará asociada a la nueva
     * cuenta.
     *
     */
    public void agregarCuenta(String usuario, String clave) {
        String claveEncriptada = encriptar(clave);
        usuarios.put(usuario, claveEncriptada);
        actualizar();
    }

    /**
     * Método encriptador.
     *
     * @param aEncriptar, es la contraseña del usuario sin encriptar.
     * @return , la contraseña del usuario encriptada.
     *
     */
    public String encriptar(String aEncriptar) {
        Hash hash = new Md5Hash(aEncriptar);
//        Si se necesita saber el resultado de la encriptación:
//        System.out.println( hash.toBase64());        
        return hash.toBase64();
    }

    /**
     *Este método es el que se utiliza para que un usuario pueda
     * loggearse en el sistema.
     * @param usuario, es el nombre del usuario a entrar.
     * @param clave, es la contraseña del usuario que quiere entrar.
     * @return, la sesión del usuario que va a entrar.
     */
    public boolean logIn(String usuario, String clave) {
        //Un Subject es cualquier cosa que esté usando el sistema,
        //En este caso es una persona, pero es anónima y no está identificada.
        Subject currentUser = SecurityUtils.getSubject();

        String claveEncriptada = encriptar(clave);
        UsernamePasswordToken token = new UsernamePasswordToken(usuario, claveEncriptada);
        try {
            currentUser.login(token);
        } catch (UnknownAccountException uae) {
            JOptionPane.showMessageDialog(null, "No hay usuario con el nombre " + token.getPrincipal());
        } catch (IncorrectCredentialsException ice) {
            JOptionPane.showMessageDialog(null, "Password para la cuenta " + token.getPrincipal() + " es incorrecto");
        }

        return currentUser.isAuthenticated();
    }

    /**
     * Por si se requiere obtener el usuario que está logueado.
     *
     * @return String
     *
     */
    public String getUsuario() {
        Subject currentUser = SecurityUtils.getSubject();
        return currentUser.getPrincipal().toString();

    }

    /**
     *Método utilizado por el usuario, cuando desee abandonar
     * el sistema y salir de su cuenta.
     */
    public void logOut() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
    }

}
