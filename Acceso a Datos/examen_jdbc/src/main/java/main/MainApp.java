package main;

import model.Producto;
import utils.UtilFicherosCSV;

public class MainApp {
    public static void main(String[] args) throws Exception {
        UtilFicherosCSV u = new UtilFicherosCSV();
        //Prueba importar productos
        for(Producto p: u.importarProductosCSV("src/main/resources/productos_import_ejemplo.csv")){
            System.out.println(p.toString());
        }

        //Prueba exportar productos
        u.exportarPedidosFull("src/main/resources/pruebaExport.txt");

        //Prueba importar pedidos
        u.importarPedidosFull("src/main/resources/pruebaExport.txt");
    }
}
