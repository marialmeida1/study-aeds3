import java.time.LocalDate;

import aeds3.*;

public class Principal {
    
    public static void main(String[] args) {
        try {
            Arquivo<Livro> arqLivros = new Arquivo<>("livros", Livro.class.getConstructor());
            // povoar(arqLivros);
            Livro l = arqLivros.read(2);
            if(l != null) {
                System.out.println(l.getTitulo());
            }
            l = arqLivros.read(1);
            if(l != null) {
                System.out.println(l.getTitulo());
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void povoar(Arquivo arquivo) throws Exception {
        Livro l1 = new Livro("9788575225639", "Entendendo Algoritmos", "Aditya Y. Bhargava", 1, LocalDate.of(2017, 4, 24),  51.30F);
        Livro l2 = new Livro("9788595159907", "Algoritmos - Teoria e Prática", "Thomas H. Cormen", 4, LocalDate.of(2024,2,6), 416.52F);
        arquivo.create(l1);
        arquivo.create(l2);
    }
}
