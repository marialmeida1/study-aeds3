import java.io.*;

public class Principal2 {
    
    public static void main(String[] args) {

        RandomAccessFile arquivo;
        try {
            
            arquivo = new RandomAccessFile("dados.db", "rw");
            arquivo.seek(arquivo.length());
            arquivo.writeInt(1968);
            arquivo.writeBoolean(false);
            arquivo.writeFloat(3.14159F);
            arquivo.writeChar('A');
            arquivo.writeUTF("Conceição");
            byte[] cpf1 = "12345678901".getBytes();
            arquivo.write(cpf1);

            arquivo.seek(0);
             int n = arquivo.readInt();
            boolean b = arquivo.readBoolean();
            float f = arquivo.readFloat();
            char c = arquivo.readChar();
            String nome = arquivo.readUTF();
            byte[] cpf0 = new byte[11];
            arquivo.read(cpf0);
            String cpf = new String(cpf0);
            System.out.println(n);
            System.out.println(b);
            System.out.println(f);
            System.out.println(c);
            System.out.println(nome);
            System.out.println(cpf);

        } catch(IOException e) {
            e.printStackTrace();
        }

    }


}
