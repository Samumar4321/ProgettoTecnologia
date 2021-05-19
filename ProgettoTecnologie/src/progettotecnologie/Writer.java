/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettotecnologie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Writer {

    String filename;

    public Writer(String path) {
        filename = path;
    }

    public void WriteToFile(Presets ps) {
        // Write the content in file 
        if (ps != null) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
                String fileContent = "";
                for (int i = 0; i < ps.size(); i++) {
                    fileContent += ps.get(i).toCSV() + "\n";
                }
                bufferedWriter.write(fileContent);
            } catch (IOException e) {
                // Exception handling
                System.out.println(e);
            }
        }

    }

}
