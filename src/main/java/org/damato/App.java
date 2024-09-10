package org.damato;

import java.io.*;
import java.util.Arrays;

public class App {
   private  File file;
   private String ruta;


    public File getFile() {
        return file;
    }

    public String getRuta() {
        return ruta;
    }

    public File getArchivo(){
        return new File(ruta+file);
    }

    public App() {
        this.file = new File("file.txt");
        this.ruta ="./src/main/resources/" ;
    }



    public static void main(String[] args) {
        App app = new App();
        System.out.println(existeFichero(app.getRuta(), app.getFile())?"El fichero existe.":"El fichero NO existe");
        try {
            escribirArchivo(app.getArchivo());
           // mostrarDirectoriosRaiz();
            leerArchivo(app.getArchivo());




        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void leerArchivo(File archivo) throws IOException {
        System.out.println("Lectura archivo");
        try (FileReader fr = new FileReader(archivo)) {
            int texto;
            while ((texto=fr.read())!=-1){
                System.out.print((char) texto);
            }
        }
    }

    private static void mostrarDirectoriosRaiz() {
        String directorioRaiz = File.listRoots()[0].toString();
        System.out.println(directorioRaiz);


        System.out.println("   -----  STREAM   ----- ");
        File[] paths = File.listRoots();
        Arrays.stream(paths).forEach(p -> System.out.println(p.toString()));

        File dirD = new File("D:/");

        System.out.println("   -- stream ---  D");
        Arrays.stream(dirD.listFiles()).forEach(f -> System.out.println(f.isFile()? f.getName()+ " Es un archivo ": f.getName()+" Es un directorio"));

        System.out.println(" Filtramos ficheros de D");
        Arrays.stream(dirD.list()).filter(f -> f.contains(".")).forEach(System.out::println);

        //InputStreamReader
        //FileInputStream

        //OutputStreamWriter
        //FileOutputStream


        }

    private static void escribirArchivo(File archivo) throws IOException {
        try (FileWriter fw = new FileWriter(archivo,true)) {
            System.out.println("El archivo existe "+archivo.getName());
            System.out.println("El archivo parent "+archivo.getParent());
            System.out.println("El archivo longitud "+archivo.length());

            fw.write("Hola soy el nuevo archivo\n");
        }
        System.out.println("Archivo escrito correctamente");
        System.out.println("El archivo longitud "+archivo.length());

    }
    static boolean existeFichero(String ruta, File file) {

        File busca = new File(ruta+file);

        return busca.exists();
    }

}