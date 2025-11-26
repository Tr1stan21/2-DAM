package utils;

import dao.*;
import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UtilFicherosCSV {

    /**
     * Lee un archivo csv y devuelve una lista con cada linea
     *
     * @param nombrefichero ruta del fichero CSV
     * @return linesCsv lista con cada línea del fichero CSV
     */
    private ArrayList<String> readCsv(String nombrefichero) {
        ArrayList<String> linesCsv = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(nombrefichero))){
            String line;
            reader.readLine(); //Saltar cabecera
            while((line = reader.readLine()) != null) {
                linesCsv.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return linesCsv;
    }

    /**
     * Lee un archivo csv con objetos Producto y devuelve una lista con cada Producto
     *
     * @param nombrefichero fichero CSV con productos
     * @return productos lista de productos
     */
    public List<Producto> importarProductosCSV(String nombrefichero){
        List<Producto> productos = new ArrayList<>();
        for(String line : readCsv(nombrefichero)){
            String[] datosProductos = line.trim().split(";");
            productos.add(new Producto(Integer.parseInt(datosProductos[0]),datosProductos[1],Double.parseDouble(datosProductos[2]), Integer.parseInt(datosProductos[3])));
        }
        return productos;
    }

    /**
     * Metodo que exporta todos los pedidos de la base de datos, con información del cliente,
     * y a continuación sus líneas con la información de cada producto (y su categoría).
     *
     * @param nombrefichero nombre del fichero en el que exportar los datos
     * @throws Exception
     */
    public void exportarPedidosFull(String nombrefichero) throws Exception {
        PedidoDAOImpl pedidoDAO = new PedidoDAOImpl();
        ClienteDAOImpl clienteDAO = new ClienteDAOImpl();
        LineaPedidoDAOImpl lineaPedidoDAO = new LineaPedidoDAOImpl();
        ProductoDAOImpl productoDAO = new ProductoDAOImpl();
        CategoriaDAOImpl categoriaDAO = new CategoriaDAOImpl();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombrefichero))) {

            for(Pedido pedido : pedidoDAO.findAll()) {
            int idPedido = pedido.getIdPedido();
            Cliente cliente = clienteDAO.findById(pedido.getIdCliente());
            List<LineaPedido> lineasPedido = lineaPedidoDAO.findByPedido(idPedido);
            List<Producto> productosPedido = new ArrayList<>();
            for (LineaPedido lineas : lineasPedido){
                productosPedido.add(productoDAO.findById(lineas.getIdProducto()));
            }
            writer.write("PEDIDO ["+idPedido+"]");
            writer.newLine();
            writer.write(pedido.getFecha() +";"+cliente.getIdCliente()+";"+cliente.getNombre()+";"+cliente.getEmail());
            writer.newLine();
            writer.newLine();
            writer.write("LINEAS PEDIDO ["+idPedido+"]");
            writer.newLine();
            for (Producto producto : productosPedido) {
                writer.write(producto.getIdProducto()+";"+producto.getNombre()+";"+producto.getPrecio()+";"+producto.getIdCategoria()+";"+categoriaDAO.findById(producto.getIdCategoria()).getNombre());
                writer.newLine();
            }
            writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void importarPedidosFull(String nombrefichero){
        try (BufferedReader reader = new BufferedReader(new FileReader(nombrefichero))) {
            String linea;
            List<List> pedidos = new ArrayList<>();

            while((linea = reader.readLine()) != null) {
                if(linea.split(" ")[0].equals("PEDIDO")) {
                    List<String> lineas = new ArrayList<>();
                    lineas.add(linea);

                    String idPedido = linea.split(" ")[1].replace("[", "").replace("]","");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
