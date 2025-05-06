package main;

import dao.AlunoDAO;
import model.Aluno;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AlunoDAO alunoDAO = new AlunoDAO();

        System.out.println("📚 Sistema da Biblioteca 📚");
        System.out.println("1. Cadastrar Aluno");
        System.out.println("2. Sair");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha

        if (opcao == 1) {
            String nome = "", matricula = "", dataNascimento = "";
            boolean nomeValido = false, matriculaValida = false, dataValida = false;

            // Nome
            while (!nomeValido) {
                System.out.println("Nome do aluno: ");
                nome = scanner.nextLine();
                if (!nome.trim().isEmpty()) {
                    nomeValido = true;
                } else {
                    System.out.println("❌ Nome inválido. Tente novamente.");
                }
            }

            // Matrícula
            while (!matriculaValida) {
                System.out.println("Matrícula: ");
                matricula = scanner.nextLine();
                if (!matricula.trim().isEmpty()) {
                    matriculaValida = true;
                } else {
                    System.out.println("❌ Matrícula inválida. Tente novamente.");
                }
            }

            // Data de nascimento (formato brasileiro, mas convertida para o formato do banco)
            DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while (!dataValida) {
                System.out.println("Data de nascimento (ex: dd/MM/yyyy): ");
                String input = scanner.nextLine();
                try {
                    LocalDate data = LocalDate.parse(input, formatterInput);
                    dataNascimento = data.format(formatterOutput); // Conversão para formato SQL
                    dataValida = true;
                } catch (DateTimeParseException e) {
                    System.out.println("❌ Data inválida. Use o formato dd/MM/yyyy.");
                }
            }

            Aluno aluno = new Aluno(0, nome, matricula, dataNascimento);

            try {
                alunoDAO.cadastrarAluno(aluno);
                System.out.println("Aluno cadastrado com sucesso! ✅");
            } catch (Exception e) {
                System.out.println("❌ Erro ao cadastrar aluno: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
