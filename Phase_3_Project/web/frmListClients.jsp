<%@ page import="entityLayer.Client" %>
<%@ page import="businessLogicLayer.BLClients" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lista de Clientes</title>
    <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
    <link href="CSS/Styles.css" rel="stylesheet" type="text/css">
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
                            <a class="nav-link" href="frmListClients.jsp">Clientes</a>
                        </li>
                            <li class="nav-item">
                            <a class="nav-link" href="frmListPets.jsp">Mascotas</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Citas</a>
                        </li>
                           <li class="nav-item">
                            <a class="nav-link" href="frmPurchases.jsp">facturación</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
<div class="container mt-5">
    <div class="card">
        <div class="card-header">
            <h1 class="text-center">Listado de Clientes</h1>
        </div>
       <div class="card-body">
    <form action="frmListClients.jsp" method="post" class="mb-4">
        <div class="form-row">
            <div class="col-md-8">
                <input type="text" id="txtName" name="txtName" class="form-control"
                       placeholder="Buscar por nombre o apellido">
            </div>
            <div class="col-md-4">
                <input type="submit" id="btnBuscar" name="btnBuscar" value="Buscar"
                       class="btn btn-primary btn-block">
            </div>
        </div>
    </form>
           
   <!-- Modal de Confirmación -->
<div class="modal fade" id="confirmacionModal" tabindex="-1" role="dialog" aria-labelledby="confirmacionModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmacionModalLabel">Confirmación</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                ¿Estás seguro de que deseas eliminar este cliente?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <a href="#" id="eliminarCliente" class="btn btn-danger">Eliminar</a>
            </div>
        </div>
    </div>
</div>    
   
    <div class="table-responsive"> 
        <table class="table table-bordered">
            <thead class="thead-light">
            <tr>
                <th>Cédula</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Teléfono</th>
                <th>Email</th>
                <th>Dirección</th>
                <th>Notas</th>
                <th>Opciones</th>
            </tr>
            </thead>
            <tbody>
             <%
                    String name = "";
                    String condicion = "";

                    if (request.getParameter("txtName") != null || request.getParameter("txtName") != null) {
                        name = request.getParameter("txtName");
                        condicion = "FIRSTNAME LIKE '%" + name + "%'";
                    }
                    BLClients logica = new BLClients();
                    List<Client> datos;
                    datos = logica.listClients(condicion, "");

                    for (Client registro : datos) {
                %>
                <tr>
                    <% String idClient = registro.getId_client(); %>
                    <td> <%= registro.getId_client()  %> </td>
                    <td> <%= registro.getFirstName() %> </td>
                    <td> <%= registro.getLastName() %> </td>
                    <td> <%= registro.getPhone() %> </td>
                    <td> <%= registro.getEmail() %> </td>
                    <td> <%= registro.getAddress() %> </td>
                    <td> <%= registro.getNotes() %> </td>
                    <td>
                        <a href="frmClients.jsp?idCreateUpdate=<%= idClient %>" class="btn btn-warning btn-sm"><i
                                class="fas fa-user-edit"></i></a>
                        <a href="#" class="btn btn-danger btn-sm eliminar-cliente" data-id="<%= idClient %>"><i class="fas fa-trash-alt"></i></a></td>
                </tr>
            <% } %>
            </tbody>
        </table>
    </div>
            <%
               if (request.getParameter("createUpdateMessage") != null) {
                out.print("<p class='text-danger'>" + new String(request.getParameter("createUpdateMessage").getBytes("ISO-8859-1"), "UTF-8") + "</p>");
                }
                if (request.getParameter("mensajeEliminarCliente") != null) {
                    out.print("<p class='text-danger'>" + new String(request.getParameter("mensajeEliminarCliente").getBytes("ISO-8859-1"), "UTF-8") + "</p>");
                } 
            %>
            <a href="frmClients.jsp" class="btn btn-success">Agregar Cliente</a>
            <a href="frmListClients.jsp" class="btn btn-info">Actualizar</a>
            <a href="index.html" class="btn btn-secondary">Regresar</a>
        </div>
    </div>
</div>            
<script src="lib/jquery/dist/jquery.min.js"></script>
<script src="lib/bootstrap/dist/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function () {
        console.log("Document ready function executed");
        // Maneja el clic en el enlace de eliminación
        $('.eliminar-cliente').click(function () {
            var idCliente = $(this).data('id');
            // Establece el enlace del modal de confirmación para que apunte a la acción de eliminación
            $('#eliminarCliente').attr('href', 'DeleteClient?idClient=' + idCliente);
            // Abre el modal de confirmación
            $('#confirmacionModal').modal('show');
        });
    });
</script>
</body>
</html>