/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fiap.dao;

import br.com.fiap.connection.ConnectionSingleton;
import br.com.fiap.entity.Reserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class ReservaDAO {
    private Connection con;
    private PreparedStatement p;
    private String sql;
    private ResultSet rs;
    private String msg;
    
        public void insert(Reserva reserva){
        try {
            con = ConnectionSingleton.getConnection();
            sql = "INSERT INTO POO_RESERVA VALUES(?,?,?,?)";
            p = con.prepareStatement(sql);
            p.setString(1, reserva.getCpf());
            p.setString(2, reserva.getNome());
            p.setInt(3, reserva.getNumeroPassagem());
            p.setString(4, reserva.getDestino());
            if (p.executeUpdate() > 0) {
                msg = "Parabéns! sua passagem já foi reservada :)";
            } else {
                msg = " Falha ao reservar passagem ";
            }

            JOptionPane.showMessageDialog(null, msg);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao reservar passagem.\n Detalhes: " + e.getMessage());
        }
    }
    
    

    public void delete(int numeroPassagem) {
        con = ConnectionSingleton.getConnection();
        sql = "DELETE FROM POO_RESERVA WHERE NUMEROPASSAGEM = ?";
        try {
            p = con.prepareStatement(sql);
            p.setInt(1, numeroPassagem);
             if (!p.execute()) {
                msg = "Que pena! Sua reserva foi excluída, sentiremos sua falta :(";
            } else {
                msg = "Falha ao excluir reserva";
            }
             JOptionPane.showMessageDialog(null, msg);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir reserva\n" + e);
        }
    }

    public Reserva getByName(String nomePassageiro) {
        Reserva reserva = null;
        try {

            con = ConnectionSingleton.getConnection();
            sql = "SELECT * FROM POO_RESERVA WHERE nome LIKE ? ";
            p = con.prepareStatement(sql);
            p.setString(1, nomePassageiro);
            rs = p.executeQuery();
            String nome, cpf, destino, caminho;
            int numeroPassagem;    
            
            while (rs.next()) {
                cpf = rs.getString("cpf");
                nome = rs.getString("nome");
                numeroPassagem = rs.getInt("numeropassagem");
                destino = rs.getString("destino");
                reserva = new Reserva(cpf, nome, numeroPassagem, destino);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar reserva.\n  Detalhes: " + e.getMessage());
        }
        
        if (reserva == null) {
            JOptionPane.showMessageDialog(null, "Nenhuma reserva encontrada com o nome: " + nomePassageiro);
        }

        return reserva;
    }
    
    public Reserva getByID(int numeroPassagem) {
        Reserva reserva = null;
        try {

            con = ConnectionSingleton.getConnection();
            sql = "SELECT * FROM POO_RESERVA WHERE numeroPassagem = ?";
            p = con.prepareStatement(sql);
            p.setInt(1, numeroPassagem);
            rs = p.executeQuery();
            String nome, cpf, destino, caminho;
            
            while (rs.next()) {
                cpf = rs.getString("cpf");
                nome = rs.getString("nome");
                numeroPassagem = rs.getInt("numeropassagem");
                destino = rs.getString("destino");
                reserva = new Reserva(cpf, nome, numeroPassagem, destino);
            }
            

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar reserva.\n  Detalhes: " + e.getMessage());
        }

        if (reserva == null) {
            JOptionPane.showMessageDialog(null, "Nenhuma reserva encontrada com o codigo: " + numeroPassagem);
        }
        
        return reserva;
    }

    
    public List<Reserva> getAll(){
        List<Reserva> reservas = null;
        con = ConnectionSingleton.getConnection();
        sql = "SELECT * FROM POO_CLIENTE";
        try{
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            reservas = gerarLista();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar reservas");
        }
        return reservas;
    }
    
    private List<Reserva> gerarLista() throws SQLException {
        List<Reserva> lista = new ArrayList();
        String nome, cpf, destino, caminho;
        int numeroPassagem;
        
        while(rs.next()){
            cpf = rs.getString("cpf");
            nome = rs.getString("nome");
            numeroPassagem = rs.getInt("numeropassagem");
            destino = rs.getString("destino");
            lista.add(new Reserva(cpf, nome, numeroPassagem, destino));
        }
        
        return lista;
    }
    
    
    public int getLastId(){
         int lastId = 0;
        try {

            con = ConnectionSingleton.getConnection();
            sql = "SELECT NVL(MAX(NUMEROPASSAGEM),1000) AS MAX_ID FROM POO_RESERVA";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            String nome, cpf, destino, caminho;
            int numeroPassagem;    
            
            while (rs.next()) {
                lastId = rs.getInt("MAX_ID");
            }
            
            lastId = lastId > 999 ? lastId : 1000;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar ultimo id.\n  Detalhes: " + e.getMessage());
        }

        return lastId;
    }
   
}
