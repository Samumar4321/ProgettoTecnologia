/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettotecnologie;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author User
 */
public class Reader{
    String filename;

    public Reader(String filename) {
        this.filename = filename;
    }
    
    public Presets ReadFromFile(String _filename) {
        // Write the content in file 
        Presets ps = new Presets();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(_filename))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] temp = line.split(";");
                Preset p = new Preset(temp[0], temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3]), Integer.parseInt(temp[4]),Integer.parseInt(temp[5]),Integer.parseInt(temp[6]));
                ps.add(p);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please specify the right file...");
        } catch (IOException e) {
            System.out.println(e);
        }
        return ps;
    }

    public Presets ReadFromFile() {
        // Write the content in file 
        Presets ps = new Presets();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] temp = line.split(";");
                Preset p = new Preset(temp[0], temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3]), Integer.parseInt(temp[4]),Integer.parseInt(temp[5]),Integer.parseInt(temp[6]));
                ps.add(p);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            //System.out.println("File not found. Please specify the right file...");
            return null;
        } catch (IOException e) {
            System.out.println(e);
        }
        return ps;
    }
}
