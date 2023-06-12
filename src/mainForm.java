import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainForm extends JFrame {

    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTextField textIngresoNombre;
    private JButton ingresarPlatoButton;
    private JTextArea textAIngresoPlatos;
    private JButton QuemarDatosButton;
    private JTextField textIngresoPrecio;
    private JTextField textIngresoCalorias;
    private JTextField textIngresoPreparacion;
    private JButton buscarModifButton;
    private JButton modificarModifButton;
    private JTextField textoModifNombre;
    private JTextField textoModifPrecio;
    private JTextField textoModifCalorias;
    private JTextField textoModifPreparacion;
    private JTextArea textAModif;
    private JButton ButtonBuscarEliminar;
    private JTextField textNombreEliminar;
    private JTextArea textAEliminar;
    private JButton eliminarButton;
    private JComboBox comboBoxOrder;
    private JButton mostrarButton;
    private JTextArea textAMostrar;
    private JButton buscarButton;
    private JTextField textBuscarPlatoOrden;

    private Menu menu = new Menu();

    public mainForm() {

        ingresarPlatoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ingresarPlato();
            }
        });
        QuemarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        buscarModifButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPlatoModificar();
            }
        });
        modificarModifButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarPlato();
            }
        });
        ButtonBuscarEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPlatoEliminar();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPlato();
            }
        });
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTodos();
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPlatoOrdenamiento();
            }
        });
    }

    //Get mainPanel
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void ingresarPlato() {

        if (!textIngresoNombre.getText().isEmpty()) {

            if (!textIngresoPrecio.getText().isEmpty()) {

                if (!textIngresoCalorias.getText().isEmpty()) {

                    if (!textIngresoPreparacion.getText().isEmpty()) {

                        if (menu.ingresarPlato(textIngresoNombre.getText(), Double.parseDouble(textIngresoPrecio.getText()), Integer.parseInt(textIngresoCalorias.getText()), Integer.parseInt(textIngresoPreparacion.getText())) != null) {
                            textAIngresoPlatos.setText(menu.imprimirMenu());
                        } else {
                            JOptionPane.showMessageDialog(null, "Error. El plato ya ha sido agregado");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Error. El campo tiempo de preparacion esta vacio");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Error. El campo calorias esta vacio");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Error. El campo precio esta vacio");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Error. El campo nombre esta vacio");
        }
    }

    public void buscarPlatoModificar(){
        if (!textoModifNombre.getText().isEmpty()) {

            if(Ordenamiento.busquedaSecuencialNombre(textoModifNombre.getText(), menu.getPlatos()) != -1){
                textoModifPrecio.setEditable(true);
                textoModifCalorias.setEditable(true);
                textoModifPreparacion.setEditable(true);
            }else{
                JOptionPane.showMessageDialog(null, "Error. No se ha encontrado el plato.");
                textoModifPrecio.setEditable(false);
                textoModifCalorias.setEditable(false);
                textoModifPreparacion.setEditable(false);
            }

        }else{
            JOptionPane.showMessageDialog(null, "Error. El campo nombre esta vacio");
        }

    }

    public void modificarPlato(){
        int posPlato = Ordenamiento.busquedaSecuencialNombre(textoModifNombre.getText(), menu.getPlatos());
        Plato plato = menu.modificarPlato(posPlato, Double.parseDouble(textoModifPrecio.getText()), Integer.parseInt(textoModifCalorias.getText()), Integer.parseInt(textoModifPreparacion.getText()));
        textAModif.setText(plato.toString());

        textoModifPrecio.setEditable(false);
        textoModifCalorias.setEditable(false);
        textoModifPreparacion.setEditable(false);

        textoModifPrecio.setText("");
        textoModifCalorias.setText("");
        textoModifPreparacion.setText("");
        textoModifNombre.setText("");
    }

    public void buscarPlatoEliminar(){
        if (!textNombreEliminar.getText().isEmpty()) {
            int posPlato = Ordenamiento.busquedaSecuencialNombre(textNombreEliminar.getText(), menu.getPlatos());
            if(posPlato != -1){
                textAEliminar.setText(menu.getPlatos().get(posPlato).toString());
                eliminarButton.setEnabled(true);
            }else{
                JOptionPane.showMessageDialog(null, "Error. No se ha encontrado el plato");
                eliminarButton.setEnabled(false);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Error. El campo nombre esta vacio");
        }
    }

    public void eliminarPlato(){
        int posPlato = Ordenamiento.busquedaSecuencialNombre(textNombreEliminar.getText(), menu.getPlatos());
        menu.eliminarPlato(posPlato);
        textAEliminar.setText("El plato se ha eliminado correctamente");
        eliminarButton.setEnabled(false);
    }

    public void mostrarTodos(){
        if(comboBoxOrder.getSelectedItem().equals("ordenarPorNombre")){
            Ordenamiento.ordenarPorNombre(menu.getPlatos());
            textAMostrar.setText(menu.imprimirMenu());
        }else if(comboBoxOrder.getSelectedItem().equals("ordenarPorPrecio")){
            Ordenamiento.ordenarPorPrecio(menu.getPlatos());
            textAMostrar.setText(menu.imprimirMenu());
        }else if((comboBoxOrder.getSelectedItem().equals("ordenarPorCalorias"))){
            Ordenamiento.ordenarPorCalorias(menu.getPlatos());
            textAMostrar.setText(menu.imprimirMenu());
        }else if((comboBoxOrder.getSelectedItem().equals("ordenarPorTiempoPreparacion"))){
            Ordenamiento.ordenarPorTiempoPreparacion(menu.getPlatos());
            textAMostrar.setText(menu.imprimirMenu());
        }
    }

    public void buscarPlatoOrdenamiento(){
        if(comboBoxOrder.getSelectedItem().equals("ordenarPorNombre")){
            int pos = Ordenamiento.busquedaBinariaPorNombre(textBuscarPlatoOrden.getText(), menu.getPlatos());
            textAMostrar.setText(menu.getPlatos().get(pos).toString());
        }else if(comboBoxOrder.getSelectedItem().equals("ordenarPorPrecio")){
            int pos = Ordenamiento.busquedaBinariaPorPrecio(Double.parseDouble(textBuscarPlatoOrden.getText()), menu.getPlatos());
            textAMostrar.setText(menu.getPlatos().get(pos).toString());
        }else if((comboBoxOrder.getSelectedItem().equals("ordenarPorCalorias"))){
            int pos = Ordenamiento.busquedaBinariaPorCalorias(Double.parseDouble(textBuscarPlatoOrden.getText()), menu.getPlatos());
            textAMostrar.setText(menu.getPlatos().get(pos).toString());
        }else if((comboBoxOrder.getSelectedItem().equals("ordenarPorTiempoPreparacion"))){
            int pos = Ordenamiento.busquedaBinariaPorTiempoPreparacion(Integer.parseInt(textBuscarPlatoOrden.getText()), menu.getPlatos());
            textAMostrar.setText(menu.getPlatos().get(pos).toString());
        }
    }
}
