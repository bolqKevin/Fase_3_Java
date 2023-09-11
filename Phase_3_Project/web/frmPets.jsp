<%@page import="businessLogicLayer.BLPets"%>
<%@page import="entityLayer.Pet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
    <link href="CSS/Styles.css" rel="stylesheet" type="text/css">
    <link href="lib/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css" rel="stylesheet" type="text/css"/>
    <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
    <script src="lib/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Datos de la Mascota</title>
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
                <h1>Datos de la Mascota</h1>
            </div><br>
 <%
    String id = request.getParameter("idCreateUpdate");
    int codigo = 0; // Cambia esto a 0 para indicar que es una nueva mascota por defecto
    if (id != null && !id.isEmpty()) {
        try {
            codigo = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw e;
        }
    }
    Pet pet;
    BLPets logic = new BLPets();
    if (codigo > 0) {
        pet = logic.getPet("PETID = " + codigo);
    } else {
        pet = new Pet();
    }
%>
            <form action="CreateUpdatePet" method="post" id="form_CreateUpdatePet">
                <div>
                    <label for="txtPetId" class="control-label">ID de Mascota</label>
                    <input type="text" id="txtPetId" name="txtPetId" value="<%= pet.getPetId()%>" readonly class="form-control">
                </div>
                <div>
                    <label for="txtPetName" class="control-label">Nombre</label>
                    <input type="text" id="txtPetName" name="txtPetName" value="<%= pet.getPetName()%>" class="form-control">
                </div>
                <div>
                    <label for="txtSpecies" class="control-label">Especie</label>
                    <input type="text" id="txtSpecies" name="txtSpecies" value="<%= pet.getSpecies()%>" class="form-control">
                </div>
                <div>
                    <label for="txtBreed" class="control-label">Raza</label>
                    <input type="text" id="txtBreed" name="txtBreed" value="<%= pet.getBreed()%>" class="form-control">
                </div>
                <div>
                    <label for="txtBirthDate" class="control-label">Fecha de Nacimiento</label>
                    <input type="text" id="txtBirthDate" name="txtBirthDate" value="<%= pet.getBirthDate()%>" class="form-control">
                </div>
                <div>
                    <label for="txtGender" class="control-label">Género</label>
                    <input type="text" id="txtGender" name="txtGender" value="<%= pet.getGender()%>" class="form-control">
                </div>
                <div>
                    <label for="txtClientId" class="control-label">ID del Cliente</label>
                    <input type="text" id="txtClientId" name="txtClientId" value="<%= pet.getClientId()%>" class="form-control">
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <input type="submit" id="btnSave" value="Guardar" class="btn btn-primary m-2"/>
                        <input type="button" id="btnReturn" value="Regresar" onclick="location.href='frmListPets.jsp'" class="btn btn-secondary m-2"/>
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
        $("#form_CreateUpdatePet").validate({
           rules:{
             txtPetName: {required:true, maxlength:50},
             txtSpecies: {required:true, maxlength:50},
             txtBreed: {maxlength:50},
             txtBirthDate: {
                required: true,
                dateISO: true, // Validar el formato ISO de la fecha (YYYY-MM-DD)
                customDateValidation: true // Validación personalizada para el año no posterior a 1990
             },
             txtGender: {required:true, maxlength:10},
             txtClientId: {required:true, maxlength:9, minlength:9, digits: true},
           },
           messages:{
               txtPetName: "El campo nombre es obligatorio, máximo 50 caracteres",
               txtSpecies: "El campo especie es obligatorio, máximo 50 caracteres",
               txtBreed: "El campo raza no debe exceder los 50 caracteres",
               txtBirthDate: {
                   required: "Seleccione una fecha de nacimiento",
                   dateISO: "El formato de fecha debe ser YYYY-MM-DD",
                   customDateValidation: "El año de nacimiento no puede ser posterior a 1990"
               },
               txtGender: "El campo género es obligatorio, máximo 10 caracteres",
               txtClientId: "Ingrese un ID de cliente válido",
           },
           errorElement: 'span'
        });

        // Agregar regla de validación personalizada para el año
        $.validator.addMethod("customDateValidation", function(value, element) {
            var selectedYear = new Date(value).getFullYear();
            return selectedYear >= 1990;
        }, "El año de nacimiento no puede ser posterior a 1990");

        // Inicializar el selector de fechas con formato ISO
        $(".datepicker").datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true
        });
    });
</script>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        var txtPetId = document.getElementById("txtPetId");

        // Verifica si el campo txtPetId está vacío
        if (txtPetId.value === "") {
            txtPetId.removeAttribute("readonly");
        } else {
            txtPetId.setAttribute("readonly", "readonly");
        }
    });
</script>
</body>
</html>