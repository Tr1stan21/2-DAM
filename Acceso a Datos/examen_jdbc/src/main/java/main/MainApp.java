package main;

import dao.LineaPedidoDAOImpl;
import dao.PedidoDAOImpl;
import model.Cliente;
import model.LineaPedido;
import model.Producto;
import utils.UtilFicherosCSV;

public class MainApp {
    public static void main(String[] args) throws Exception {
        //PRUEBAS CRUD
        PedidoDAOImpl pedidoDAO = new PedidoDAOImpl();
        LineaPedidoDAOImpl lineaPedidoDAO = new LineaPedidoDAOImpl();

        //Prueba findByPedido
        System.out.println("Prueba findByPedido");
        for(LineaPedido lineaPedido : lineaPedidoDAO.findByPedido(1)) {
            System.out.println(lineaPedido.toString());
        }

        System.out.println("\nPrueba creaPedidoCompleto");
        //pedidoDAO.crearPedidoCompleto(new Cliente("maria", "maria@gmail.com"), lineaPedidoDAO.findByPedido(2)); //HAY QUE CAMBIAR MODELO Y REVISAR DAO


        //PRUEBAS FICHEROS
        UtilFicherosCSV u = new UtilFicherosCSV();
        //Prueba importar productos
        System.out.println("\nPrueba importarProductosCSV");
        for(Producto p: u.importarProductosCSV("src/main/resources/productos_import_ejemplo.csv")){
            System.out.println(p.toString());
        }

        //Prueba exportar productos
        //u.exportarPedidosFull("src/main/resources/pruebaExport.txt");

        //Prueba importar pedidos
        u.importarPedidosFull("src/main/resources/pruebaExport.txt");
    }
}
