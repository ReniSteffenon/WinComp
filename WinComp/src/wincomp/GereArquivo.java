/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wincomp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Steffenon
 */
public class GereArquivo {

    public String abrirArquivoTexto() {
        String retorno = "";
        BufferedReader in = null;
        JFileChooser fc = new JFileChooser();
        try {
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                FileReader fr;

                fr = new FileReader(fc.getSelectedFile());
                in = new BufferedReader(fr);
                String line;
                line = in.readLine();
                while (line != null) {
                    retorno+=line+"\r\n";
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
}
