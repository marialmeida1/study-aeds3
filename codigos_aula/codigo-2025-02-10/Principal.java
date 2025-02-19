import java.io.*;

public class Principal {

    public static void main(String[] args) {

        FileOutputStream arquivo;
        DataOutputStream dos;
        try {
            arquivo = new FileOutputStream("dados.db");
            dos = new DataOutputStream(arquivo);
            dos.writeInt(1968);
            dos.writeBoolean(false);
            dos.writeFloat(3.14159F);
            dos.writeChar('A');
            dos.writeUTF("Conceição");
            byte[] cpf = "12345678901".getBytes();
            dos.write(cpf);
            dos.close();
            arquivo.close();
            
        } catch(IOException e) {
            e.printStackTrace();
        }

        FileInputStream arquivo2;
        DataInputStream dis;
        try {
            arquivo2 = new FileInputStream("dados.db");
            dis = new DataInputStream(arquivo2);
            int n = dis.readInt();
            boolean b = dis.readBoolean();
            float f = dis.readFloat();
            char c = dis.readChar();
            String nome = dis.readUTF();
            byte[] cpf0 = new byte[11];
            dis.read(cpf0);
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