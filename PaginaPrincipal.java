package TtrabajoIndividialProcesadorDeTexto;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;



import static TtrabajoIndividialProcesadorDeTexto.PanelPrincipal.guardarDatos;


public class PaginaPrincipal {
    public static void main(String[] args) {
        FramePrincipal pag1 = new FramePrincipal();
        pag1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class FramePrincipal extends JFrame {

    public FramePrincipal(){
        PanelPrincipal panel1 = new PanelPrincipal();
        add(panel1);
        setTitle("Procesador de Texto by Juan Burich");
        setBounds(225,100,1000,700);
        setVisible(true);
        addWindowListener(new VentanaEventos());
    }



   private static class VentanaEventos implements WindowListener{


        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
            guardarDatos();
        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }


    }






}



