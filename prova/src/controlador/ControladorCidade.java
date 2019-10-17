/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import dao.DaoCidade;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import modelo.Cidade;
import tela.manutencao.ManutencaoCidade;
import java.util.List;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Administrador
 */
public class ControladorCidade {

   
  public static void inserir(ManutencaoCidade man){
        Cidade objeto = new Cidade();
        objeto.setNome(man.jtfNome.getText());
        objeto.setSigla(man.jtfSigla.getText());
        objeto.setHab(Integer.parseInt(man.jtfHab.getText()));
        objeto.setDatae(LocalDate.parse(man.jtfDatae.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));//é necessário fazer as seguintes importações: java.time.LocalDate; e java.time.format.DateTimeFormatter
        objeto.setArea(Double.parseDouble(man.jtfArea.getText()));
        objeto.setDist(Integer.parseInt(man.jtfDist.getText()));
        boolean resultado = DaoCidade.inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
} 
   public static void alterar(ManutencaoCidade man){
        Cidade objeto = new Cidade();
        //definir todos os atributos
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText()));
        objeto.setNome(man.jtfNome.getText());
        objeto.setSigla(man.jtfSigla.getText());
        objeto.setHab(Integer.parseInt(man.jtfHab.getText()));
        objeto.setDatae(LocalDate.parse(man.jtfDatae.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));//é necessário fazer as seguintes importações: java.time.LocalDate; e java.time.format.DateTimeFormatter
        objeto.setArea(Double.parseDouble(man.jtfArea.getText()));
        objeto.setDist(Integer.parseInt(man.jtfDist.getText()));
        boolean resultado = DaoCidade.alterar(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
     public static void excluir(ManutencaoCidade man){
        Cidade objeto = new Cidade();
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoCidade.excluir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
     public static void atualizarTabela(JTable tabela) {
        DefaultTableModel modelo = new DefaultTableModel();
        //definindo o cabeçalho da tabela
        modelo.addColumn("Codigo");
        modelo.addColumn("Nome");
        modelo.addColumn("Sigla");
        modelo.addColumn("Habitantes");
        modelo.addColumn("Data");
        modelo.addColumn("Área");
        modelo.addColumn("Distância"); 
        
        List<Cidade> resultados = DaoCidade.consultar();
        for (Cidade objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getNome());
            linha.add(objeto.getSigla());
            linha.add(objeto.getHab());
            linha.add(objeto.getDatae().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            linha.add(objeto.getArea());
            linha.add(objeto.getDist());
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
     public static void atualizaCampos(ManutencaoCidade man, int pk){ 
        Cidade objeto = DaoCidade.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfCodigo.setText(objeto.getCodigo().toString());
        man.jtfNome.setText(objeto.getNome());
        man.jtfSigla.setText(objeto.getSigla());
        man.jtfHab.setText(objeto.getCodigo().toString());
        man.jtfDatae.setText(objeto.getDatae().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        man.jtfArea.setText(objeto.getArea().toString());
        man.jtfDist.setText(objeto.getCodigo().toString());
        
        man.jtfCodigo.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }
}
