package controller;

import database.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.mAutores;
import model.mEditoras;

public class cAutores {

    public List<mEditoras> pesquisar(String texto) {

        Connection conn = mysql.conexão();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<mEditoras> lista = new ArrayList<>();

        try {
            stmt = conn.prepareStatement("SELECT nome, endereco, numero, bairro, cidade, cpf \n"
                    + "FROM editoras \n" + "WHERE nome LIKE ?");
            stmt.setString(1, "%" + texto + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                mEditoras modelE = new mEditoras();
                modelE.setId_editora(rs.getInt("id_editora"));
                modelE.setNome(rs.getString("nome"));
                modelE.setEndereco(rs.getString("endereco"));
                modelE.setNumero(rs.getString("numero"));
                modelE.setBairro(rs.getString("bairro"));
                modelE.setCidade(rs.getString("cidade"));
                modelE.setCpf(rs.getString("cpf"));
                lista.add(modelE);

            }

        } catch (SQLException ex) {
            Logger.getLogger(cEditoras.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public void cadastrar(mAutores autor) {
        Connection conn = mysql.conexão();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("INSERT INTO autores (nome, endereco, numero, bairro, cidade, cpf) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getEndereco());
            stmt.setString(3, autor.getNumero());
            stmt.setString(4, autor.getBairro());
            stmt.setString(5, autor.getCidade());
            stmt.setString(6, autor.getCpf());

            stmt.executeUpdate();

         JOptionPane.showMessageDialog(null, "Autor cadastrada com sucesso !");

        } catch (SQLException ex) {
            Logger.getLogger(cEditoras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<mAutores> listar() {

        Connection conn = mysql.conexão();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<mAutores> lista = new ArrayList<>();

        try {
            stmt = conn.prepareStatement("SELECT * FROM autores");
            rs = stmt.executeQuery();

            while (rs.next()) {
                mAutores modelE = new mAutores();
                modelE.setId_autor(rs.getInt("id_autor"));
                modelE.setNome(rs.getString("nome"));
                modelE.setEndereco(rs.getString("endereco"));
                modelE.setNumero(rs.getString("numero"));
                modelE.setBairro(rs.getString("bairro"));
                modelE.setCidade(rs.getString("cidade"));
                modelE.setCpf(rs.getString("cpf"));
                lista.add(modelE);

            }

        } catch (SQLException ex) {
            Logger.getLogger(cEditoras.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }
    
    public void excluir(mAutores modelE) {
        Connection conn = mysql.conexão();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("DELETE FROM autores WHERE id_autor = ? ");
            stmt.setInt(1, modelE.getId_autor());

            stmt.executeUpdate();

            JOptionPane.showConfirmDialog(null, "Autor excluida com sucesso !");

        } catch (SQLException ex) {
            Logger.getLogger(cEditoras.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
