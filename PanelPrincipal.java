package TtrabajoIndividialProcesadorDeTexto;

import javax.swing.*;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;

public class PanelPrincipal extends JPanel {
    JMenu archivo;
    JMenu estilo;
    JMenu fuente;
    JMenu tamanio;
    private static JTextPane textArea;
    private static String nombre="";
    private static String directorio="";





    public PanelPrincipal(){

        setLayout(new BorderLayout());
        JMenuBar barraConfig= new JMenuBar();
        ButtonGroup tamanio_letra=new ButtonGroup();
        archivo = new JMenu("Archivo");
        estilo = new JMenu("Estilos");
        fuente=new JMenu("Fuente");
        tamanio = new JMenu("Tamaño");


        addMenuItems("Guardar",KeyEvent.VK_G,InputEvent.CTRL_DOWN_MASK,new listenerGuardar(),archivo);
        addMenuItems("Negrita",KeyEvent.VK_N,InputEvent.CTRL_DOWN_MASK,new StyledEditorKit.BoldAction(),estilo);
        addMenuItems("Cursiva",KeyEvent.VK_K,InputEvent.CTRL_DOWN_MASK,new StyledEditorKit.ItalicAction(),estilo);
        addMenuItems("Arial",new StyledEditorKit.FontFamilyAction("cambio_fuente","Arial"),fuente);
        addMenuItems("Times New Roman",new StyledEditorKit.FontFamilyAction("cambio_fuente","Times New Roman"),fuente);
        addMenuItems("Verdana",new StyledEditorKit.FontFamilyAction("cambio_fuente","Verdana"),fuente);
        addMenuItems("Tahoma",new StyledEditorKit.FontFamilyAction("cambio_fuente","Tahoma"),fuente);
        barraConfig.add(archivo);
        barraConfig.add(estilo);
        barraConfig.add(fuente);
        barraConfig.add(tamanio);
        addElements(8,30,tamanio_letra,tamanio);





        textArea = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(barraConfig,BorderLayout.NORTH);
        add(scrollPane,BorderLayout.CENTER);

    }


    public void addElements(int min, int max, ButtonGroup group,JMenu menu){
        for (int i =min;i<=max;i=i+3){
            JRadioButtonMenuItem btn = new JRadioButtonMenuItem(String.valueOf(i));
            group.add(btn);
            menu.add(btn);
            btn.addActionListener(new StyledEditorKit.FontSizeAction("cambio_tamanio",Integer.parseInt(btn.getText())));
        }
    }

    public void addMenuItems(String nombre, ActionListener listener,JMenu menu){
        JMenuItem item = new JMenuItem(nombre);
        item.addActionListener(listener);
        menu.add(item);
    }

    public void addMenuItems(String nombre, int ctrl, int letra, ActionListener listener,JMenu menu){
        JMenuItem item = new JMenuItem(nombre);
        item.setAccelerator(KeyStroke.getKeyStroke(ctrl,letra));
        item.addActionListener(listener);
        menu.add(item);
    }


    public static void cargaDatos(){
        try{
            JFrame dialog = new JFrame();
            FileDialog fd = new FileDialog(dialog,"Abrir archivo",FileDialog.LOAD);
            fd.setVisible(true);

            if (fd.getFile()!=null){
                nombre = fd.getFile();
                directorio = fd.getDirectory();
            }
            String path =directorio+nombre;

            ObjectInputStream leerArchivos = new ObjectInputStream(new FileInputStream(path));
            textArea.setText((String) leerArchivos.readObject());
            leerArchivos.close();
        } catch (Exception e){
            JOptionPane.showConfirmDialog(null, "No se encontro el archivo", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void guardarDatos(){
        try {
            JFrame dialog = new JFrame();
            FileDialog fd = new FileDialog(dialog,"Guardar archivo",FileDialog.SAVE);
            fd.setVisible(true);

            if (fd.getFile() != null){
                nombre = fd.getFile();
                directorio = fd.getDirectory();
            }
            ObjectOutputStream guardarArchivos = new ObjectOutputStream(new FileOutputStream(directorio+nombre));
            guardarArchivos.writeObject(textArea.getText());
            guardarArchivos.close();
        }catch (IOException e){
            JOptionPane.showConfirmDialog(null, "El archivo no se guardó", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        }
    }


    private class listenerAbrir implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
           cargaDatos();
        }
    }

    private class listenerGuardar implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            guardarDatos();
        }
    }



}
