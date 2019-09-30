/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wincomp;


public class AdaptiveHuffmanCoding {
    
    public void executar(String ops, String fileName, String encodingSize){
        if(ops.equals("-e")) {
            encode(fileName, encodingSize);
        } else if (ops.equals("-d")) {
            decode(fileName);
        } else if (ops.equals("-p")) {
            encodeAndDecode(fileName);
        }
    }
      
    
    public static void encode(String readFilename, String encodingSize){
        AdaptiveHuffmanEncoder encoder = new AdaptiveHuffmanEncoder(Integer.parseInt(encodingSize));
        System.out.println("Starting encoding " + readFilename + " with " + encodingSize + " bit tree node representation.");
        encoder.encode(readFilename);
        System.out.println("Finished encoding");
    }

    public static void decode(String fileName) {
        AdaptiveHuffmanDecoder decoder = new AdaptiveHuffmanDecoder();
        System.out.println("Starting decoding " + fileName);
        decoder.decode(fileName);
        System.out.println("Finished decoding");
    }

    public static void encodeAndDecode(String fileName) {
        String path = fileName.substring(0, fileName.lastIndexOf("/"));
        //retrieve the raw file name by removing everything before the absoloute filname
        String rawFileName = fileName.substring(fileName.lastIndexOf("/") + 1);
        //The original file name split into the parts before and after the first "." so that the "compressed" file name part
        //can be inserted
        String[] compressedFileNameComps = rawFileName.split("\\.", 2);

        //The compressed file name, made up of the file name components from the original file name and the compressed
        //file path keyword added
        String compressedFileName = path + "/" + compressedFileNameComps[0] + ".compressed8." + compressedFileNameComps[1];

        encode(fileName, "8");
        decode(compressedFileName);
    }
}
