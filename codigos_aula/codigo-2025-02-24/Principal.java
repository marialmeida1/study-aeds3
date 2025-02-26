import java.time.LocalDate;

import aeds3.*;

public class Principal {
    
    public static void main(String[] args) {
        try {
            // Significa que é um arquivo do tipo Livros
            Arquivo<Livro> arqLivros = new Arquivo<>("livros", Livro.class.getConstructor());
            
            // Insere no banco de dados
            // povoar(arqLivros);

            // Lê o que existe no banco de dados com o id
            Livro l = arqLivros.read(4);
            l.setAutor("Paul Deitel"); // No ID encontrado insere um novo nome
            arqLivros.update(l); // Realiza o update dessa informação

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void povoar(Arquivo<Livro> arquivo) throws Exception {
        // Cria novos quatro valores dentro do banco de dados  
        arquivo.create(new Livro("9788575225639", "Entendendo Algoritmos", "Aditya Y. Bhargava", 1, LocalDate.of(2017, 4, 24),  51.30F));
        arquivo.create(new Livro("9788595159907", "Algoritmos - Teoria e Prática", "Thomas H. Cormen", 4, LocalDate.of(2024,2,6), 416.52F));
        arquivo.create(new Livro("9788573933758", "Estruturas de Dados e Algoritmos em Java", "Lafore", 2, LocalDate.of(2005,1,1), 119.42F));
        arquivo.create(new Livro("9788543004792", "Java - Como programar", "Paul Deitel", 10, LocalDate.of(2016,6,24), 365.25F));
    }
}
