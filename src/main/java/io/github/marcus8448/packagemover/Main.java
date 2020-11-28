package io.github.marcus8448.packagemover;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws URISyntaxException {
        File directory = new File(Main.class.getProtectionDomain().getCodeSource().getLocation()
                .toURI());
        if (args.length > 0) {
            directory = new File(args[0]);
        }
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (!file.isDirectory()) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String s;
                    while (true) {
                        try {
                            s = "/" + reader.readLine().split("//")[1].split(" ")[1].split(";")[0].replace(".", "/") + "/";
                        } catch (IndexOutOfBoundsException ex) {
                            continue;
                        }
                        break;
                    }
                    reader.close();
                    reader = new BufferedReader(new FileReader(file));
                    File file1 = new File(directory.toString() + s + file.getName());
                    file1.mkdirs();
                    file1.delete();
                    System.out.println(file1.getAbsolutePath());
                    file1.createNewFile();
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file1));
                    while (reader.ready()) {
                        writer.write(reader.readLine());
                        writer.write('\n');
                    }
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
