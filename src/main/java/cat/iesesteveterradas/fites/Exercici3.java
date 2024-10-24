package cat.iesesteveterradas.fites;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import cat.iesesteveterradas.fites.objectes.Exercici3nau;

/**
 * Implementa el codi que realitzi el següent:
 * 
 * - Serialitza aquesta llista en un fitxer binari anomenat 'Exercici3.dat' al directori de treball especificat.
 * - Després d'una pausa d'1 segon, deserialitza la llista del fitxer binari i la imprimeix per pantalla amb el format "Nom, País Any".
 * - Gestió d'errors: si hi ha algun problema en escriure o llegir el fitxer (ex. fitxer no trobat), mostra l'excepció a la consola amb e.printStackTrace()
 */
public class Exercici3 {
    private String filePath;

    public static void main(String[] args) {
        String basePath = System.getProperty("user.dir") + "/data/exercici3/";
        String filePath = basePath + "Exercici3.dat";

        Exercici3 exercici = new Exercici3();
        exercici.configurarRutaFitxerSortida(filePath);
        exercici.executa();
    }

    // Mètode que executa la lògica de la classe
    public void executa() {
        ArrayList<Exercici3nau> llista0 = new ArrayList<>();
        llista0.add(new Exercici3nau("Vostok", "USSR", 1961));
        llista0.add(new Exercici3nau("Mercury", "US", 1961));
        llista0.add(new Exercici3nau("Gemini", "US", 1965));
        llista0.add(new Exercici3nau("Soyuz", "Russia", 1967));
        llista0.add(new Exercici3nau("Apollo", "US", 1968));
        llista0.add(new Exercici3nau("Shuttle", "US", 1981));
        llista0.add(new Exercici3nau("Shenzhou", "China", 2003));
        llista0.add(new Exercici3nau("Crew Dragon", "US", 2020));

        serialitzaLlista(filePath, llista0);

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) { 
            e.printStackTrace(); 
        }

        ArrayList<Exercici3nau> llista1 = deserialitzaLlista(filePath);
        imprimeixLlista(llista1);
    }

    // Mètode per serialitzar la llista a un fitxer
    public void serialitzaLlista(String filePath, ArrayList<Exercici3nau> llista) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(llista);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    // Mètode per deserialitzar la llista del fitxer
    public ArrayList<Exercici3nau> deserialitzaLlista(String filePath) {
        ArrayList<Exercici3nau> llista = null;

        try (FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);) {
        
          llista = (ArrayList) ois.readObject();
        
        } catch (IOException ioe) {
          ioe.printStackTrace();
        } catch (ClassNotFoundException c) {
          System.out.println("Class not found");
          c.printStackTrace();
        }

        return llista;
    }

    // Mètode per imprimir la llista
    public void imprimeixLlista(ArrayList<Exercici3nau> llista) {
        for (Exercici3nau x : llista) {
            System.out.println(x);
        }
    }

    /****************************************************************************/
    /*                          NO CAL MODIFICAR                                */
    /****************************************************************************/       
    // Setter per definir el path
    public void configurarRutaFitxerSortida(String filePath) {
        this.filePath = filePath;
    }

    // Getter per obtenir el path
    public String obtenirRutaFitxerSortida() {
        return this.filePath;
    }
}
