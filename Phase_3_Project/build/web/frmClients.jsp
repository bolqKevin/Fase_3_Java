<%@page import="businessLogicLayer.BLClients"%>
<%@page import="entityLayer.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
    <link href="CSS/Styles.css" rel="stylesheet" type="text/css">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Datos dek cliente</title>
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
<div class="container">
    <div class="row">
        <div class="col-md-6 mx-auto">
            <div class="card-header">
                <h1>Datos del cliente</h1>
            </div><br>
            <%
                String id = request.getParameter("idCreateUpdate");
                int codigo = 1;
                if (id != null && !id.isEmpty()) {
                    try {
                        codigo = Integer.parseInt(id);
                    } catch (NumberFormatException e) {
                    throw e;
                    }
                }
                Client client;
                BLClients logic = new BLClients();
                if (codigo > 0) {
                    client = logic.GetClient("CLIENTID = " + codigo);
                } else {
                    client = new Client();
                }
            %>
            <form action="CreateUpdateClient" method="post" id="form_CreateUpdateClient">
                <div>
                    <label for="txtIdClient" class="control-label">Cédula</label>
                    <input type="text" id="txtIdClient" name="txtIdClient" value="<%= client.getId_client()%>" readonly class="form-control">
                </div>
                <div>
                    <label for="txtName" class="control-label">Nombre</label>
                    <input type="text" id="txtName" name="txtName" value="<%= client.getFirstName()%>" class="form-control">
                </div>
                <div>
                    <label for="txtLastName" class="control-label">Apellido</label>
                    <input type="text" id="txtLastName" name="txtLastName" value="<%= client.getLastName()%>" class="form-control">
                </div>
                <div>
                    <label for="txtPhone" class="control-label">Teléfono</label>
                    <input type="text" id="txtPhone" name="txtPhone" value="<%= client.getPhone()%>" class="form-control">
                </div>
                <div>
                    <label for="txtEmail" class="control-label">Email</label>
                    <input type="text" id="txtEmail" name="txtEmail" value="<%= client.getEmail()%>" class="form-control">
                </div>
                <div>
                    <label for="txtAddress" class="control-label">Dirección</label>
                    <input type="text" id="txtAddress" name="txtAddress" value="<%= client.getAddress()%>" class="form-control">
                </div>
                <div>
                    <label for="txtNotes" class="control-label">Notas</label>
                    <input type="text" id="txtNotes" name="txtNotes" value="<%= client.getNotes()%>" class="form-control">
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <input type="submit" id="btnSave" value="Guardar" class="btn btn-primary m-2"/>
                        <input type="button" id="btnReturn" value="Regresar" onclick="location.href='frmListClients.jsp'" class="btn btn-secondary m-2"/>
                    </div>
                </div>
            </form>
        </div>
    </div><!-- row -->
</div><!-- container -->
<script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
<script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
<script src="lib/jquery-validation/dist/jquery.validate.js" type="text/javascript"></script>
<script>
    $(document).ready(function(){
        $("#form_CreateUpdateClient").validate({
           rules:{
             txtIdClient: {required:true, maxlength:9, minlength:9, digits: true},  
             txtName: {required:true, maxlength:50},
             txtLastName: {required:true, maxlength:50},
             txtPhone: {required:true, minlength:8, maxlength:11, digits: true},
             txtEmail: {required:true, email:true},
             txtAddress: {required:true, maxlength:80},
             txtNotes: {maxlength:255},
           },
           messages:{
               txtIdClient: "Ingrese una Cédula valida",
               txtName: "El campo nombre es obligatorio, máximo 50 caracteres",
               txtLastName: "El campo apellido es obligatorio, máximo 50 caracteres",
               txtPhone: "Ingrese una telefono valido",
               txtEmail: "Ingrese un Correo valido",
               txtAddress: "El campo dirección es obligatorio, máximo 80 caracteres",
               txtNotes: "El campo notas no debe exceder los 255 caracteres",
           },
           errorElement: 'span'
        });
    });
</script>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        var txtIdClient = document.getElementById("txtIdClient");

        // Verifica si el campo txtIdClient está vacío
        if (txtIdClient.value === "") {
            txtIdClient.removeAttribute("readonly");
        } else {
            txtIdClient.setAttribute("readonly", "readonly");
        }
    });
</script>
</body>
</html>
