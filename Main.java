import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.ArrayList;

public class Main extends JFrame implements ActionListener {

    private JLabel texto;           
    private JButton boton;

    public static void main(String[] args) {
        /* Generamos la ventana y la hacemos visible */
        Main v = new Main();
        v.setVisible(true);
    }

    public Main() {
        /* Definimos las caracteristicas de la ventana */
        super();                    
        this.setTitle("Puzzle");
        this.setSize(350, 350);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /* creamos los componentes */
        texto = new JLabel();
        boton = new JButton();

        /* configuramos los componentes */
        texto.setText("Aritmetica verbal");
        texto.setBounds(70, 20, 250, 100);
        boton.setText("Resolver");
        boton.setBounds(70, 200, 200, 30);
        boton.addActionListener(this);

        /* agregamos los componentes a la ventana ¨*/
        this.add(texto);
        this.add(boton);           
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // leemos los datos del xml
        ArrayList<String> text  = CrearXml.read();
        //si este nos devuelve algun dato, los enviamos para que se pueda comienzar a ejecutar el codigo de backtracking con los datos leidos
        if(text.size()!=0){
            Solucion s = new Solucion(text);
            JOptionPane.showMessageDialog(this, "Se ha leido puzzle.xml y generado solucion.xml, con exito");
        }else{
            JOptionPane.showMessageDialog(this, "No se ha encontrado el archivo puzzle.xml en la carpeta");
        }
    }
}