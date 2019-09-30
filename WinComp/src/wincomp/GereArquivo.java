/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wincomp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class GereArquivo {

    FileNameExtensionFilter txt = new FileNameExtensionFilter("Arquivos de texto", "txt");
    FileNameExtensionFilter bin = new FileNameExtensionFilter("Arquivos binários", "bin");
    protected File file;

    public GereArquivo() {
        this.file = null;
    }

    public String abrirArquivoTexto() {
        String retorno = "";
        BufferedReader in = null;
        JFileChooser fc = new JFileChooser();
        try {
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                FileReader fr;
                this.file = fc.getSelectedFile();
                fr = new FileReader(fc.getSelectedFile());
                in = new BufferedReader(fr);
                String line;
                line = in.readLine();
                while (line != null) {
                    retorno += line + "\r\n";
                    line = in.readLine();
                }

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GereArquivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GereArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    public static byte[] getBytes(File fin) {
        byte[] data = null;
        try {
            data = Files.readAllBytes(fin.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void writeBytes(byte[] input, File fou) {
        try {
            Files.write(fou.toPath(), input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(File fin) {
        String output = "";
        int input = 0;
        try {
            FileReader fr = new FileReader(fin);
            BufferedReader br = new BufferedReader(fr);
            while (input != -1) {
                input = br.read();
                output = output + (char) input;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public static void writeFile(String input, File fou) {
        try {
            FileWriter fw = new FileWriter(fou);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(input);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void writeFileCRC(String input, File fou) {
        try {
            FileWriter fw = new FileWriter(fou);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(input);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File selecionarLocalGravacao() {
        File retorno = null;
        JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(retorno);
        fc.setDialogTitle("Salvar arquivo comprimido");
        fc.setFileFilter(bin);
        int j = fc.showSaveDialog(null);
        if (j == JFileChooser.APPROVE_OPTION) {
            retorno = fc.getSelectedFile();
            return retorno;
        }
        return retorno;
    }
    
    public File selecionarLocalGravacaoTXT(){
        File retorno = null;
        JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(retorno);
        fc.setDialogTitle("Salvar arquivo aberto");
        fc.setFileFilter(txt);
        int j = fc.showSaveDialog(null);
        if (j == JFileChooser.APPROVE_OPTION) {
            retorno = fc.getSelectedFile();
            return retorno;
        }
        return retorno;
    }
}
