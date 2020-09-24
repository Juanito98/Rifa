/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.rifa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author jcsiglerp
 */
public class RifaManager {
    
    public static void appendIntoFile(int k) {
        try {
            FileWriter myWriter = new FileWriter("db.txt", true);
            myWriter.write(String.format("%d\n", k));
            myWriter.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void readFromFile(boolean[] used) {
        try {
            File file = new File("db.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextInt()) {
                int x = scanner.nextInt();
                used[x-1] = true;
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
