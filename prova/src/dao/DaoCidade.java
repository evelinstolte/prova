/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import modelo.Cidade;
import tela.manutencao.ManutencaoCidade;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Administrador
 */
public class DaoCidade {
    
     public static boolean inserir(Cidade objeto) {
        String sql = "INSERT INTO cidade (nome, sigla, hab, datae, area, dist) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            
            ps.setString(1, objeto.getNome());
            ps.setString(2, objeto.getSigla());
            ps.setInt(3, objeto.getHab());
            ps.setDate(4, Date.valueOf(objeto.getDatae())); //fazer a seguinte importação: java.sql.Date 
            ps.setDouble(5, objeto.getArea());
            ps.setInt(6, objeto.getDist());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
     
      public static void main(String[] args) {
        Cidade objeto = new Cidade();
        
        objeto.setNome("Brasil");
        objeto.setSigla("BR");
        objeto.setHab(15);
        objeto.setDatae(LocalDate.parse("11/01/1988", DateTimeFormatter.ofPattern("dd/MM/yyyy"))); //fazer as seguintes importações: java.time.format.DateTimeFormatter e java.time.LocalDate
        objeto.setArea(123.5);
        objeto.setDist(15);
        boolean resultado = inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
     
     public static boolean alterar(Cidade objeto) {
        String sql = "UPDATE cidade SET nome = ?, sigla = ?, hab = ?, datae = ?, area = ?, dist = ? WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getNome()); 
            ps.setString(2, objeto.getSigla());
            ps.setInt(3, objeto.getHab());
            ps.setDate(4, Date.valueOf(objeto.getDatae())); //fazer a seguinte importação: java.sql.Date 
            ps.setDouble(5, objeto.getArea());
            ps.setInt(6, objeto.getDist());
            ps.setInt(7, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public static boolean excluir(Cidade objeto) {
        String sql = "DELETE FROM cidade WHERE codigo = ?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public static List<Cidade> consultar() {
        List<Cidade> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, nome, sigla, hab, datae, area, dist FROM cidade";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cidade objeto = new Cidade();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setNome(rs.getString("nome"));
                objeto.setSigla(rs.getString("sigla"));
                objeto.setHab(rs.getInt("hab"));
                objeto.setDatae(rs.getDate("datae").toLocalDate());
                objeto.setArea(rs.getDouble("area"));
                objeto.setDist(rs.getInt("dist"));
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
}
   public static Cidade consultar(int primaryKey) {
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, nome, sigla, hab, datae, area , dist FROM cidade WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, primaryKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cidade objeto = new Cidade();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setNome(rs.getString("nome"));
                objeto.setSigla(rs.getString("sigla"));
                objeto.setHab(rs.getInt("hab"));
                objeto.setDatae(rs.getDate("datae").toLocalDate());
                objeto.setArea(rs.getDouble("area"));
                objeto.setDist(rs.getInt("dist"));
                return objeto;//não mexa nesse, ele adiciona o objeto na lista
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
}
