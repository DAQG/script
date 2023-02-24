import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Vehiculos {
    private JTextField tfmarca;
    private JTextField tfcolor;
    private JTextField tfmodelo;
    private JTextField tfpuertas;
    private JComboBox Tipo;
    private JComboBox anio;
    private JLabel lbmarca;
    private JLabel lbcolor;
    private JLabel lbanio;
    private JLabel lbmodelo;
    private JLabel lbpuertas;
    private JLabel lbtipo;
    private JPanel Vehiculoss;
    private JButton crear;
    private JButton buscar;
    private JButton actualizar;
    private JButton borrar;
    public Vehiculos(){

        tfcolor.setEnabled(false);
        tfmodelo.setEnabled(false);
        tfpuertas.setEnabled(false);
        anio.setEnabled(false);
        Tipo.setEnabled(false);
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con;
                try{
                    String marca;
                    marca = tfmarca.getText();
                    System.out.println(marca);
                    con = getConection();
                    String query = "select * from infoautos where Marca = " + tfmarca.getText();
                    Statement s = con.createStatement();
                    ResultSet rs = s.executeQuery(query);
                    System.out.println(rs);

                    //while(rs.next()){
                        if(marca.equals(rs.getString(1))){
                            tfcolor.setText(rs.getString(2));
                            tfmodelo.setText(rs.getString(3));
                            tfpuertas.setText(rs.getString(4));


                            System.out.println(rs.getString(1) + " " +
                                    rs.getString(2) + " " +
                                    rs.getString(3) + " " +
                                    rs.getString(4));

                            tfcolor.setEnabled(true);
                            tfmodelo.setEnabled(true);
                            tfpuertas.setEnabled(true);
                            actualizar.setEnabled(true);
                            //break;
                        }else{
                            System.out.println("Vehiculo no encontrado");
                            //break;
                        }
                    //}
                    con.close();
                }catch(HeadlessException | SQLException f){
                    System.err.println(f);
                }
            }
        });
        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con;

                try{
                    con = getConection();
                    PreparedStatement ps = con.prepareStatement("UPDATE infoautos SET Marca= ?, Color = ?, Modelo = ?, Npuertas = ?, Anio = ?, Tipo = ? WHERE id =" + tfmarca);
                    ps.setString(1, tfmarca.getText());
                    ps.setString(2, tfcolor.getText());
                    ps.setString(3, tfmodelo.getText());
                    ps.setString(4, tfpuertas.getText());
                    System.out.println(ps);//Imprime para ver los datos ingresados por consola

                    int res = ps.executeUpdate();
                    if(res > 0){
                        JOptionPane.showMessageDialog(null, "Actualizacion exitosa ");
                    }else{
                        JOptionPane.showMessageDialog(null,"Error al actualizar");
                    }
                    con.close();
                }catch(HeadlessException | SQLException f){
                    System.err.println(f);
                }
            }
        });
    }

    public static Connection getConection(){
        Connection con = null;
        String base= "vehiculos";
        String url="jdbc:mysql://localhost/" + base;
        String user ="root";
        String password = "12345";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user,password);
        }catch(ClassNotFoundException | SQLException es){
            System.err.println(es);
        }
        return con;

    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Vehiculos");
        frame.setContentPane(new Vehiculos().Vehiculoss);
        frame.setSize(1000,1000);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
