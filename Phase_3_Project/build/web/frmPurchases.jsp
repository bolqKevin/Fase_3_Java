<%@page import="entityLayer.Employee"%>
<%@page import="businessLogicLayer.BLEmployees"%>
<%@ page import="entityLayer.Product" %>
<%@ page import="businessLogicLayer.BLProducts" %>
<%@ page import="java.util.List" %>
<%@ page import="entityLayer.Client" %>
<%@ page import="businessLogicLayer.BLClients" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Factura de cliente</title>
    <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="lib/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.min.css" rel="stylesheet" type="text/css" />
    <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css" />
    <link href="lib/DataTables/datatables.min.css" rel="stylesheet" type="text/css" />
    <style>
        /* Agregar estilos personalizados aquí */
    </style>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-sm navbar-toggleable-sm navbar-light bg-white border-bottom box-shadow mb-3">
        <div class="container">
            <a class="navbar-brand" href="index.html">Veterinaria PetCare <i class="fas fa-paw"></i></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".navbar-collapse" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="navbar-collapse collapse d-sm-inline-flex flex-sm-row-reverse">
                <ul class="navbar-nav flex-grow-1">
                    <li class="nav-item">
                        <a class="nav-link" href="index.html">Inicio</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Servicios</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="frmClients.jsp">Clientes</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="frmListPets.jsp">Mascotas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Citas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="frmPurchases.jsp">Facturación</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
<div class="container">
    <div class="row">
        <div class="col-10">
            <h1>Facturación de Clientes</h1>
        </div>
    </div>
    <br>
    <form action="SalesServlet" method="post">

        <div class="form-group">
            <!-- Campos de cliente -->
            <div class="input-group">
                <input type="text" id="txtNombreCliente" name="txtNombreCliente" value="" readonly class="form-control" placeholder="Seleccionar un Cliente" />
                <input type="text" id="txtIdCliente" name="txtIdCliente" value="" disabled class="form-control" />
                <a id="btnBuscarCliente" class="btn btn-primary" data-toggle="modal" data-target="#buscarCliente">
                    <i class="fas fa-search"></i>
                </a>
            </div>
        </div>
        <hr/>
        <div class="form-group">
    <!-- Campos de empleado -->
    <div class="input-group">
        <input type="text" id="txtNombreEmpleado" name="txtNombreEmpleado" value="" readonly class="form-control" placeholder="Seleccionar un Empleado" />
        <input type="text" id="txtIdEmpleado" name="txtIdEmpleado" value="" disabled class="form-control" />
        <a id="btnBuscarEmpleado" class="btn btn-primary" data-toggle="modal" data-target="#buscarEmpleado">
            <i class="fas fa-search"></i>
        </a>
    </div>
</div>
        <div class="form-group">
            <!-- Campos de producto -->
            <input type="hidden" id="txtIdProducto" name="txtIdProducto" value="" readonly class="form-control" />
            <input type="text" id="txtDescripcion" name="txtDescripcion" value="" class="form-control" readonly placeholder="Seleccione un producto" />
            <a id="btnBuscarProducto" class="btn btn-success" data-toggle="modal" data-target="#buscarProducto">
                <i class="fas fa-search"></i>
            </a>
            <input type="number" id="txtCantidad" name="txtCantidad" value="" class="form-control" placeholder="Cantidad" />
            <input type="number" id="txtPrecio" readonly="true" name="txtPrecio" value="" class="form-control" placeholder="Precio" />
            <input type="number" id="txtExistencia" readonly name="txtExistencia" value="" class="form-control" placeholder="Existencia" />
            <a id="btnAgregarProducto" class="btn btn-primary">
                Agregar
            </a>
        </div>
        <br/>
        <div class="form-group">
            <!-- Botón para agregar producto -->
            <input type="submit" name="Guardar" id="btnGuardar" value="Guardar" class="btn btn-primary">
        </div>

<div class="modal" id="buscarCliente" tabindex="1" role="dialog" aria-labelledby="Titulo ventana">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 id="tituloVenta">Buscar Cliente</h5>
                <button class="close" data-dismiss="modal" aria-label="Cerrar" aria-hidden="true" onclick="LimpiarCliente()">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <table id="tablaClientes" class="table">
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Nombre</th>
                            <th>Seleccionar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            BLClients clientLogic = new BLClients();
                            List<Client> clientsData;
                            clientsData = clientLogic.listClients("", "");
                            for(Client clientRecord : clientsData){
                        %>
                        <tr>
                           <% String clientId = clientRecord.getId_client();
                              String clientName = clientRecord.getFirstName();
                           %>
                           <td><%= clientId %></td>
                           <td><%= clientName %></td>
                           <td >
                               <a href="#" data-dismiss="modal"
                                  onclick="SeleccionarCliente('<%= clientId %>','<%= clientName %>');">
                                  Seleccionar
                               </a>
                           </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-warning" data-dismiss="modal" onclick="LimpiarCliente()">Cerrar</button>
            </div>
        </div>
    </div>
</div>
<!-- Modal para buscar empleados -->
<div class="modal" id="buscarEmpleado" tabindex="1" role="dialog" aria-labelledby="Titulo ventana">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 id="tituloVenta">Buscar Empleado</h5>
                <button class="close" data-dismiss="modal" aria-label="Cerrar" aria-hidden="true" onclick="LimpiarEmpleado()">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <table id="tablaEmpleados" class="table">
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Nombre</th>
                            <th>Seleccionar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Aquí se mostrarán los empleados -->
                        <%
                            BLEmployees employeeLogic = new BLEmployees();
                            List<Employee> employeesData;
                            employeesData = employeeLogic.listEmployee("", "");
                            for (Employee employeeRecord : employeesData) {
                                String employeeId = employeeRecord.getId_employee();
                                String employeeName = employeeRecord.getFirstName() + " " + employeeRecord.getLastName();
                        %>
                        <tr>
                            <td><%= employeeId %></td>
                            <td><%= employeeName %></td>
                            <td>
                                <a href="#" data-dismiss="modal" onclick="SeleccionarEmpleado('<%= employeeId %>','<%= employeeName %>');">
                                    Seleccionar
                                </a>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-warning" data-dismiss="modal" onclick="LimpiarEmpleado()">Cerrar</button>
            </div>
        </div>
    </div>
</div>                    
<div class="modal" id="buscarProducto" tabindex="1" role="dialog" aria-labelledby="Titulo ventana">
    <div class="modal-dialog" style="max-width: 800px;" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 id="tituloVenta">Buscar Producto</h5>
                <button class="close" data-dismiss="modal" aria-label="Cerrar" aria-hidden="true" onclick="LimpiarProducto()">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <table id="tablaProductos" class="table">
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Descripción</th>
                            <th>Precio</th>
                            <th>Existencia</th>
                            <th>Seleccionar</th>
                        </tr>
                    </thead>
                    <tbody>
                      <%
                        BLProducts logic = new BLProducts();
                        List<Product> listaProductos = logic.listProducts("", "");
                        
                        for (Product producto : listaProductos) {
                    %>
                    <tr>
                        <td><%= producto.getProductId()%></td>
                        <td><%= producto.getDescription()%></td>
                        <td><%= producto.getPrice()%></td>
                        <td><%= producto.getCurrentStock()%></td>
                        <td>
                            <a href="#" onclick="SeleccionarProducto('<%= producto.getProductId() %>', '<%= producto.getDescription() %>', '<%= producto.getPrice() %>', '<%= producto.getCurrentStock() %>')">Seleccionar</a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
           </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-warning" data-dismiss="modal" onclick="LimpiarProducto()">Cerrar</button>
            </div>
        </div>
    </div>
</div>
    <!-- Tabla de productos seleccionados -->
    <div class="table-responsive">
        <table id="tablaProductosSeleccionados" class="table">
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Descripción</th>
                    <th>Precio Unitario</th>
                    <th>Cantidad</th>
                    <th>Subtotal</th>
                </tr>
            </thead>
            <tbody>
                <!-- Aquí se muestran los productos seleccionados -->
            </tbody>
        </table>
    </div>
    
    <div class="form-group">
        <label for="txtTotal">Total:</label>
        <input type="text" id="txtTotal" name="txtTotal" 
               value="" readonly class="form-control"/>
    </div>
    <div class="form-group">
        <label for="txtImpuestos">Impuestos (13%):</label>
        <input type="text" id="txtImpuestos" name="txtImpuestos" 
               value="" readonly class="form-control"/>
    </div>
    <div class="form-group">
        <label for="selDescuento">Descuento:</label>
        <select id="selDescuento" name="selDescuento" class="form-control">
            <option value="0">0%</option>
            <option value="1">1%</option>
            <option value="2">2%</option>
            <option value="3">3%</option>
            <option value="4">4%</option>
            <option value="5">5%</option>
        </select>
    </div>
    <div class="form-group">
        <label for="txtTotalFinal">Total Final:</label>
        <input type="text" id="txtTotalFinal" name="txtTotalFinal" 
               value="" readonly class="form-control"/>
    </div>
    <div class="form-group">

    </div>
        </form>
</div>


<script src="lib/jquery/dist/jquery.js" type="text/javascript"></script>
<script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
<script src="lib/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="lib/DataTables/datatables.min.js" type="text/javascript"></script>
<script src="lib/DataTables/DataTables-1.10.21/js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
<script  src=JS.js type="text/javascript"></script>
</body>
</html>