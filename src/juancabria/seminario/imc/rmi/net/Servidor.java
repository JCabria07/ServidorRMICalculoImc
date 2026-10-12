package juancabria.seminario.imc.rmi.net;

import java.io.IOException;
import juancabria.seminario.imc.rmi.lib.IRemotaCalculoImc;
import net.sf.lipermi.exception.LipeRMIException;
import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Server;

/**
 * Servidor LipeRMI para calculo remoto de IMC
 * @author JuanCabria
 */
public class Servidor {
    private int puerto = 9007;
    private CallHandler invocador;
    private Server servidor;
    private CalculoRmiImcImplem calculoImc;
    private IRemotaCalculoImc calculoImcRemoto;

    public Servidor() {
        invocador = new CallHandler();
        servidor = new Server();
        calculoImc = new CalculoRmiImcImplem();
    }

    public void iniciar() throws Exception {
        try {
            invocador.registerGlobal(IRemotaCalculoImc.class, calculoImc);
            servidor.bind(puerto, invocador);
            System.out.println("Servidor LipeRMI (IMC) iniciado exitosamente en puerto: " + puerto);
        } catch (LipeRMIException ex) {
            throw new Exception("Error: No es posible invocar metodos remotos - " + ex.getMessage());
        } catch (IOException ex) {
            throw new Exception("Error: I/O - " + ex.getMessage());
        }
    }

    public void detener() {
        if (servidor != null) {
            servidor.close();
        }
    }
}
