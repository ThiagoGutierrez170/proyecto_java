/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lp2_proyecto1_thiago;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ASUS
 */
public class alumnos {
    int id;
    String nombre;
    String apellido;
    String edad;
    String direccion;
    String fecha_nac;
    String ciudad;
    String genero;
    String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void InsertarAlumno(JTextField paramnombre, 
            JTextField paramapellido, JTextField paramedad,
            JTextField paramdireccion,JTextField paramfecha_nac,
            JTextField paramciudad,JTextField paramgenero,JTextField paramemail){
        
            setNombre(paramnombre.getText());
            setApellido(paramapellido.getText());
            setEdad(paramedad.getText());
            setDireccion(paramdireccion.getText());
            setFecha_nac(paramfecha_nac.getText());
            setCiudad(paramciudad.getText());
            setGenero(paramgenero.getText());
            setEmail(paramemail.getText());
            conexion objetoConexion = new conexion();
            String Consulta ="INSERT INTO alumnos(nombre,apellido,edad,direccion,fecha_nac,ciudad,genero,email) VALUES(?,?,?,?,?,?,?,?);";
            try{
               CallableStatement cs = objetoConexion.establecerConexcion().prepareCall(Consulta);
               cs.setString(1,getNombre()); 
               cs.setString(2,getApellido());
               cs.setString(3,getEdad());
               cs.setString(4,getDireccion());
               cs.setString(5,getFecha_nac());
               cs.setString(6,getCiudad());
               cs.setString(7,getGenero());
               cs.setString(8,getEmail());
               cs.execute();
               JOptionPane.showMessageDialog(null,"Se creo correctamente!");
            }catch(Exception e){
                 JOptionPane.showMessageDialog(null,"Hubo un error al intentar guardar en la base de datos!,error:"+e.toString());
            }
    }
      public void MostrarAlumnos(JTable paramtabla){
        conexion  objetoConexion = new conexion(); 
        DefaultTableModel model = new DefaultTableModel();
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(model);
        paramtabla.setRowSorter(OrdenarTabla);
        String sql="";
        model.addColumn("Id");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Edad");
        model.addColumn("Direccion");
        model.addColumn("Fecha nacimiento");
        model.addColumn("Ciudad");
        model.addColumn("Genero");
        model.addColumn("Email");
        
        paramtabla.setModel(model);
        sql="select * from alumnos";
        String []  datos = new String[9];
        Statement st;
         try{
             st=objetoConexion.establecerConexcion().createStatement();
             ResultSet rs =st.executeQuery(sql);
             while(rs.next()){
             datos[0]=rs.getString(1);
             datos[1]=rs.getString(2);
             datos[2]=rs.getString(3);
             datos[3]=rs.getString(4); 
             datos[4]=rs.getString(5);
             datos[5]=rs.getString(6);
             datos[6]=rs.getString(7);
             datos[7]=rs.getString(8);
             datos[8]=rs.getString(9);
             
             model.addRow(datos);
         }
             paramtabla.setModel(model);
            
        } catch(Exception e ){
            JOptionPane.showMessageDialog(null,"Error al mostrar los registros"+e.toString());
        }
        
    }
      public void SelecionarAlumno(JTable paramtabla,JTextField paramid ,JTextField paramnombre, 
            JTextField paramapellido, JTextField paramedad,
            JTextField paramdireccion,JTextField paramfecha_nac,
            JTextField paramciudad,JTextField paramgenero,
            JTextField paramemail){
          try{
              int fila=paramtabla.getSelectedRow();
              if (fila>=0){
                  paramid.setText(paramtabla.getValueAt(fila, 0).toString());
                  paramnombre.setText(paramtabla.getValueAt(fila, 1).toString());
                  paramapellido.setText(paramtabla.getValueAt(fila, 2).toString());
                  paramedad.setText(paramtabla.getValueAt(fila, 3).toString());
                  paramdireccion.setText(paramtabla.getValueAt(fila, 4).toString());
                  paramfecha_nac.setText(paramtabla.getValueAt(fila, 5).toString());
                  paramciudad.setText(paramtabla.getValueAt(fila, 6).toString());
                  paramgenero.setText(paramtabla.getValueAt(fila, 7).toString());
                  paramemail.setText(paramtabla.getValueAt(fila, 8).toString());
                  
              }
              else{
                  JOptionPane.showMessageDialog(null,"Error al selecionar");
              }
          }
          catch(Exception e){
              JOptionPane.showMessageDialog(null,"Error al selecionar, error: "+e.toString());
          }
          
      }
      public void Editar(JTextField paramid ,JTextField paramnombre, 
            JTextField paramapellido, JTextField paramedad,
            JTextField paramdireccion,JTextField paramfecha_nac,
            JTextField paramciudad,JTextField paramgenero,JTextField paramemail){
              setId(Integer.parseInt(paramid.getText()));
              setNombre(paramnombre.getText());
              setApellido(paramapellido.getText());
              setEdad(paramedad.getText());
              setDireccion(paramdireccion.getText());
              setFecha_nac(paramfecha_nac.getText());
              setCiudad(paramciudad.getText());
              setGenero(paramgenero.getText());
              setEmail(paramemail.getText());
              conexion objetoConexion=new conexion();
              String editar="UPDATE alumnos set alumnos.nombre=?,alumnos.apellido=?,alumnos.edad=?,alumnos.direccion=?,alumnos.fecha_nac=?,alumnos.ciudad=?,alumnos.genero=?,alumnos.email=? where alumnos.id=?";
              try{
                  CallableStatement cs = objetoConexion.establecerConexcion().prepareCall(editar);
                  cs.setString(1, getNombre());
                  cs.setString(2, getApellido());
                  cs.setString(3, getEdad());
                  cs.setString(4, getDireccion());
                  cs.setString(5, getFecha_nac());
                  cs.setString(6, getCiudad());
                  cs.setString(7, getGenero());
                  cs.setString(8, getEmail());
                  cs.setInt(9, getId());
                  cs.execute();
                  JOptionPane.showMessageDialog(null,"Se creo edito correctamente!");
                  
              }
              catch(Exception e){
                  JOptionPane.showMessageDialog(null,"Error al editar, error: "+e.toString());
                  
              }
        }
      public void Eliminar(JTextField paramid){
          conexion objetoConexion=new conexion();
          setId(Integer.parseInt(paramid.getText()));
          String eliminar="DELETE from alumnos where alumnos.id=?";
          try{
              CallableStatement cs = objetoConexion.establecerConexcion().prepareCall(eliminar);
              cs.setInt(1, getId());
              cs.execute();
              JOptionPane.showMessageDialog(null,"Se creo elimino correctamente!");
          }
          catch(Exception e){
          JOptionPane.showMessageDialog(null,"Error al eliminar, error: "+e.toString());
      }
      }
}
