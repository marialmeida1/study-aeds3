package aeds3;

import java.io.*;
import java.lang.reflect.Constructor;

public class Arquivo<T extends EntidadeArquivo> {

    public String nomeEntidade;
    RandomAccessFile arquivo;
    Constructor<T> construtor;
    final int TAMANHO_CABECALHO = 13;

    public Arquivo(String ne, Constructor<T> c) throws Exception {
        this.nomeEntidade = ne;
        this.construtor = c;
        File f = new File(".//dados");
        if(!f.exists())
            f.mkdir();
        f = new File(".//dados//"+nomeEntidade);
        if(!f.exists())
            f.mkdir();
        arquivo = new RandomAccessFile(".//dados//"+nomeEntidade+"//"+nomeEntidade +".db", "rw");
        if(arquivo.length()<TAMANHO_CABECALHO) {
            arquivo.writeByte(2);  // versão do Arquivo
            arquivo.writeInt(0);   // último ID
            arquivo.writeLong(-1);   // ponteiro para primeiro registro excluído
        }
    }

    public int create(T entidade) throws Exception {
        // Obtem o ID da nova entidade
        arquivo.seek(1);
        int novoID = arquivo.readInt() + 1;
        entidade.setID(novoID);
        arquivo.seek(1);
        arquivo.writeInt(novoID);

        // Grava o novo registro
        arquivo.seek(arquivo.length());
        arquivo.writeByte(' ');
        byte[] vb = entidade.toByteArray();
        arquivo.writeShort(vb.length);
        arquivo.write(vb);

        return novoID;
    }

    public T read(int id) throws Exception {
        byte lapide;
        short tamanho;
        byte[] dados;
        arquivo.seek(TAMANHO_CABECALHO);
        while(arquivo.getFilePointer() < arquivo.length()) {
            lapide = arquivo.readByte();
            tamanho = arquivo.readShort();
            if(lapide == ' ') {
                dados = new byte[tamanho];
                arquivo.read(dados);
                T entidade = construtor.newInstance();
                entidade.fromByteArray(dados);
                if(entidade.getID() == id)
                    return entidade;
            } else {
                arquivo.skipBytes(tamanho);
            }
        }
        return null;
    }

    public boolean delete(int id) throws Exception {
        long endereco;
        byte lapide;
        short tamanho;
        byte[] dados;

        arquivo.seek(TAMANHO_CABECALHO);
        while(arquivo.getFilePointer() < arquivo.length()) {
            endereco = arquivo.getFilePointer();
            lapide = arquivo.readByte();
            tamanho = arquivo.readShort();
            if(lapide == ' ') {
                dados = new byte[tamanho];
                arquivo.read(dados);
                T entidade = construtor.newInstance();
                entidade.fromByteArray(dados);
                if(entidade.getID() == id)
                    arquivo.seek(endereco);
                    arquivo.writeByte('*');
                    return true;
            } else {
                arquivo.skipBytes(tamanho);
            }
        }
        return false;
    }

}
