package utils;

import dao.*;
import model.*;

import java.io.*;
import java.time.LocalDate;
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

    /**
     * Metodo que lee un fichero con el formato de {@code exportarPedidosFull} y genera los pedidos y sus líneas en la BBDD.
     *
     * @param nombrefichero nombre del fichero del cual exporta los datos
     */
    public void importarPedidosFull(String nombrefichero){
        try (BufferedReader reader = new BufferedReader(new FileReader(nombrefichero))) {
            String linea;
            List<List<String>> pedidos = new ArrayList<>();

            while((linea = reader.readLine()) != null) {
                if(linea.split(" ")[0].equals("PEDIDO")) {
                    List<String> lineas = new ArrayList<>();
                    do {
                        lineas.add(linea);
                        linea = reader.readLine();
                    }while (!linea.isBlank() && !linea.split(" ")[0].equals(("PEDIDO")));
                    do {
                        lineas.add(linea);
                        linea = reader.readLine();
                    }while (!linea.isBlank() && !linea.split(" ")[0].equals(("PEDIDO")));
                    pedidos.add(lineas);
                }
            }

            PedidoDAOImpl pedidoDAO = new PedidoDAOImpl();
            LineaPedidoDAOImpl lineaPedidoDAO = new LineaPedidoDAOImpl();

            for(List<String> lista: pedidos) {
                String fechaPedido = "";
                String idCliente = "";
                String idProducto;
                String precio;
                for(int i = 0; i < lista.size(); i++) {
                    if(lista.get(i).split(" ")[0].equals("PEDIDO")){
                        fechaPedido = lista.get(i+1).split(";")[0];
                        idCliente = lista.get(i+1).split(";")[1];
                    }
                    int idPedido = pedidoDAO.insert(new Pedido(LocalDate.parse(fechaPedido), Integer.parseInt(idCliente)));

                    if(lista.get(i).split(" ")[0].equals("LINEAS")){
                        for(int j = i+1; j < lista.size(); j++){
                            idProducto = lista.get(j).split(";")[0];
                            precio = lista.get(j).split(";")[2];
                            lineaPedidoDAO.insert(new LineaPedido(idPedido, Integer.parseInt(idProducto), 1, Double.parseDouble(precio)));
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
