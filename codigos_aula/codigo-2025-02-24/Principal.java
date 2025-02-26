import java.time.LocalDate;

import aeds3.*;

public class Principal {
    
    public static void main(String[] args) {
        try {
            Arquivo<Livro> arqLivros = new Arquivo<>("livros", Livro.class.getConstructor());
            // povoar(arqLivros);
            Livro l = arqLivros.read(4);
            l.setAutor("Paul Deitel");
            arqLivros.update(l);


        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void povoar(Arquivo<Livro> arquivo) throws Exception {
        arquivo.create(new Livro("9788575225639", "Entendendo Algoritmos", "Aditya Y. Bhargava", 1, LocalDate.of(2017, 4, 24),  51.30F));
        arquivo.create(new Livro("9788595159907", "Algoritmos - Teoria e Prática", "Thomas H. Cormen", 4, LocalDate.of(2024,2,6), 416.52F));
        arquivo.create(new Livro("9788573933758", "Estruturas de Dados e Algoritmos em Java", "Lafore", 2, LocalDate.of(2005,1,1), 119.42F));
        arquivo.create(new Livro("9788543004792", "Java - Como programar", "Paul Deitel", 10, LocalDate.of(2016,6,24), 365.25F));
    }
}
