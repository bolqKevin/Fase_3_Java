package Servlets;

import businessLogicLayer.BLSales;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entityLayer.Product;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SalesServlet")
public class SalesServlet extends HttpServlet {
  private static final long serialVersionUID = 1;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");

    try {
      // Obtener la fecha actual como un objeto java.sql.Date
      Date fechaActual = new Date(Calendar.getInstance().getTime().getTime());

      // Formatear la fecha en el formato deseado
      SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String saleDate = formatoFecha.format(fechaActual);


      // Obtener el JSON enviado desde JavaScript
      String jsonProductos = request.getParameter("productosSeleccionados");
      String clientID = request.getParameter("idCliente");
      String employeeID = request.getParameter("idEmpleado");
      String discount = request.getParameter("discount");

      // Comprobar si los parámetros son nulos antes de usarlos
      if (jsonProductos == null || clientID == null || employeeID == null || discount == null) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parámetros faltantes en la solicitud.");
        return;
      }

      // Crear un ObjectMapper de Jackson para analizar el JSON
      ObjectMapper objectMapper = new ObjectMapper();

      // Convertir el JSON en una lista de objetos Map
      List<Map<String, Object>> productosMapList = objectMapper.readValue(jsonProductos, new TypeReference<List<Map<String, Object>>>() {});

      // Crear una lista para almacenar los objetos Product
      List<Product> productosSeleccionados = new ArrayList<>();

      // Iterar a través de los objetos Map y crear objetos Product manualmente
      for (Map<String, Object> productoMap : productosMapList) {
        // Acceder a las propiedades del objeto como Object
        Object codigoObj = productoMap.get("codigo");
        Object descripcionObj = productoMap.get("descripcion");
        Object precioObj = productoMap.get("precio");
        Object cantidadObj = productoMap.get("cantidad");

        // Convertir los objetos a los tipos de datos adecuados
        int codigo = (codigoObj != null) ? Integer.parseInt(codigoObj.toString()) : 0;
        String descripcion = (descripcionObj != null) ? descripcionObj.toString() : "";
        double precio = (precioObj != null) ? Double.parseDouble(precioObj.toString()) : 0.0;
        int cantidad = (cantidadObj != null) ? Integer.parseInt(cantidadObj.toString()) : 0;

        // Crear un objeto Product y agregarlo a la lista
        Product producto = new Product(codigo, descripcion, precio, cantidad);
        productosSeleccionados.add(producto);
      }

      // Llamar al método para crear la venta en la base de datos
      int generatedSaleID = createSale(saleDate, clientID, employeeID, discount, productosSeleccionados);

      // Enviar una respuesta al cliente
      response.getWriter().println("Venta creada con éxito. ID de venta generado: " + generatedSaleID);
    } catch (IOException | SQLException e) {
    response.setStatus(HttpServletResponse.SC_OK); // Establece el estado de la respuesta a 200 OK
    }
  }

  // Método para crear la venta en la base de datos
  private int createSale(String saleDate, String clientID, String employeeID, String discount, List<Product> productosSeleccionados) throws SQLException {
    // Aquí debes implementar el método createSale adaptado que mencionamos anteriormente
    BLSales logic = new BLSales();
    // Llamar al método adecuado para crear la venta y retornar el ID generado
    return logic.createSale(saleDate, clientID, employeeID, discount, productosSeleccionados);
  }
}