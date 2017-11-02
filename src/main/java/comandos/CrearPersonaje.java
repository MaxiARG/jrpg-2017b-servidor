package comandos;

import java.io.IOException;

import mensajeria.PaquetePersonaje;
import servidor.Servidor;
/**
 *Clase CrearPersonaje.
 */
public class CrearPersonaje extends ComandosServer {

    @Override
    public final void ejecutar() {
        // Casteo el paquete personaje
        getEscuchaCliente().setPaquetePersonaje((PaquetePersonaje) (gson
                .fromJson(cadenaLeida, PaquetePersonaje.class)));
        // Guardo el personaje en ese usuario
        Servidor.getConector().registrarPersonaje(getEscuchaCliente()
                .getPaquetePersonaje(), getEscuchaCliente().getPaqueteUsuario());
        try {
            PaquetePersonaje paquetePersonaje;
            paquetePersonaje = new PaquetePersonaje();
            paquetePersonaje = Servidor.getConector().getPersonaje(getEscuchaCliente().getPaqueteUsuario());
            getEscuchaCliente().setIdPersonaje(paquetePersonaje.getId());
            getEscuchaCliente().getSalida().writeObject(gson.toJson(getEscuchaCliente()
                    .getPaquetePersonaje(), getEscuchaCliente().getPaquetePersonaje().getClass()));
        } catch (IOException e1) {
            Servidor.getLog().append("Falló al intentar enviar personaje creado \n");
        }

    }

}
