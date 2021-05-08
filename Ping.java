/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ping;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Angel Chavez
 */
public class Ping {

    /**
     * @param args the command line arguments
     */
    public static void dividir(File archivo, List ip, List port) throws FileNotFoundException {
        Scanner sc = new Scanner(archivo);
        while (sc.hasNext()) {
            String texto = sc.next();
            String[] div = texto.split(":");
            int x = Integer.parseInt(div[1]);
            ip.add(div[0]);
            port.add(x);
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        int respuesta = 400; //ms
        File archivo = new File("src/ping/lista.txt");
        List<String> ip = new ArrayList<>();
        List<Integer> port = new ArrayList<>();
        dividir(archivo, ip, port);
        for (int i = 0; i < ip.size(); i++) {
            try ( Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress(ip.get(i), port.get(i)), respuesta);
                System.out.println("Server " + ip.get(i) + " Puerto " + port.get(i) + " -> OK");
            } catch (IOException e) {
                System.out.println("Server " + ip.get(i) + " Puerto " + port.get(i) + " -> Problem");

            }
        }
    }

}
