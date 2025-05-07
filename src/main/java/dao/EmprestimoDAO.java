package dao;

import model.Emprestimo;
import util.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmprestimoDAO {
    public void cadastrarEmprestimo(Emprestimo emprestimo) {
        String sql = "INSERT INTO Emprestimos (id_aluno, id_livro, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, emprestimo.getIdAluno());
            stmt.setInt(2, emprestimo.getIdLivro());
            stmt.setString(3, emprestimo.getDataEmprestimo()); // formato: yyyy-MM-dd
            stmt.setString(4, emprestimo.getDataDevolucao());  // formato: yyyy-MM-dd
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar empréstimo: " + e.getMessage());
        }
    }
}
