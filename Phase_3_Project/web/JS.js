    var selectedProductId = "";
    var selectedProductDescription = "";
    var selectedProductPrice = 0;
    var selectedProductExistence = 0;
             // Función para habilitar/deshabilitar el botón de búsqueda de productos
 function actualizarEstadoBoton() {
    var nombreCliente = $("#txtNombreCliente").val().trim();
    var nombreEmpleado = $("#txtNombreEmpleado").val().trim();
    var habilitarBoton = nombreCliente !== "" && nombreEmpleado !== "";
    var idEmpleadoSeleccionado = "";
    var idClienteSeleccionado = "";
    $("#btnBuscarProducto").prop("disabled", !habilitarBoton);
}
    // Declaración de la lista para almacenar productos seleccionados
    var productosSeleccionados = [];
    
    $(document).ready(function () {
        // Mostrar calendario
        $('.datepicker').datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true,
            language: 'es'
        });
        
        $('#tablaClientes').dataTable({
            // Configuración de la tabla de clientes
        });

        $('#tablaProductos').dataTable({
            // Configuración de la tabla de productos
        });

    // Verificar y actualizar el estado del botón al cargar la página
    actualizarEstadoBoton();
    });

    function SeleccionarCliente(idCliente, nombre) {
        $("#txtIdCliente").val(idCliente); 
        $("#txtNombreCliente").val(nombre);
        idClienteSeleccionado = idCliente; // Guardar el idCliente seleccionado
        actualizarEstadoBoton();
    }

    function LimpiarCliente() {
        $("#txtNombreCliente").val("");
    }

    // Función para limpiar los campos de producto
    function LimpiarProducto() {
        $("#txtIdProducto").val("");
        $("#txtDescripcion").val("");
        $("#txtCantidad").val("");
        $("#txtPrecio").val("");
        $("#txtExistencia").val("");
    }

    // Función para abrir el modal de búsqueda de productos
function AbrirModalProductos() {
    var nombreCliente = $("#txtNombreCliente").val();
    var nombreEmpleado = $("#txtNombreEmpleado").val();
    
    // Verificar que se haya seleccionado un cliente y un empleado antes de abrir el modal
    if (nombreCliente.trim() === "" || nombreEmpleado.trim() === "") {
        alert("Por favor, seleccione un cliente y un empleado antes de buscar productos.");
    } else {
        // Abrir el modal
        $("#buscarProducto").modal("show");
    }
}
function SeleccionarEmpleado(idEmpleado, nombre) {
    $("#txtIdEmpleado").val(idEmpleado);
    $("#txtNombreEmpleado").val(nombre);
    idEmpleadoSeleccionado = idEmpleado; // Guardar el idEmpleado seleccionado
    actualizarEstadoBoton();
}

function LimpiarEmpleado() {
    $("#txtNombreEmpleado").val("");
}
    function SeleccionarProducto(idProducto, description,price, existence) 
    {
        selectedProductId = idProducto;
        selectedProductDescription =description;
        selectedProductPrice = price;
        selectedProductExistence = existence;
        // Llenar los campos del producto en el formulario
        $("#txtIdProducto").val(selectedProductId);
        $("#txtDescripcion").val(selectedProductDescription);
        $("#txtPrecio").val(selectedProductPrice);
        $("#txtExistencia").val(selectedProductExistence);
        // Cerrar el modal
        $("#buscarProducto").modal("hide");
    }

    // Función para agregar un producto a la tabla de productos seleccionados
$("#btnAgregarProducto").click(function () {
    var idProducto = $("#txtIdProducto").val();
    var descripcion = $("#txtDescripcion").val();
    var precioUnitario = parseFloat($("#txtPrecio").val());
    var cantidad = parseInt($("#txtCantidad").val()) || 0;
    var existencia = parseInt($("#txtExistencia").val()) || 0;
    // Validar que se haya seleccionado un producto
    if (idProducto === "") {
        alert("Por favor, seleccione un producto antes de agregarlo.");
        return;
    }

    // Validar que la cantidad sea mayor a 0
    if (cantidad <= 0) {
        alert("La cantidad debe ser mayor a 0.");
        return;
    }
    
      // Validar que la cantidad no supere la existencia disponible
    if (cantidad > existencia) {
        alert("La cantidad seleccionada supera la existencia disponible.");
        return;
    }

    // Calcular el subtotal del producto
    var subtotal = precioUnitario * cantidad;

    // Crear una nueva fila en la tabla de productos seleccionados
    var newRow = "<tr>";
    newRow += "<td>" + idProducto + "</td>";
    newRow += "<td>" + descripcion + "</td>";
    newRow += "<td>" + precioUnitario.toFixed(2) + "</td>";
    newRow += "<td>" + cantidad + "</td>";
    newRow += "<td>" + subtotal.toFixed(2) + "</td>";
    newRow += "</tr>";

    // Agregar la fila a la tabla
    $("#tablaProductosSeleccionados tbody").append(newRow);
    // Crear el objeto producto con la cantidad y agregarlo a productosSeleccionados
    var producto = {
        codigo: idProducto,
        descripcion: descripcion,
        precio: precioUnitario,
        cantidad: cantidad, // Asignar la cantidad aquí
        subtotal: subtotal.toFixed(2) // Puedes agregar el subtotal si lo necesitas
    };

    productosSeleccionados.push(producto);
    // Limpiar los campos de producto
    LimpiarProducto();

    // Calcular el total y actualizarlo
    CalcularTotal();

});
     $("#selDescuento").change(function () {
            // Calcular el total y actualizarlo
            CalcularTotal();
      
        });
    // Función para calcular el total
    function CalcularTotal() {
        var total = 0;
        var impuestos = 0;
        var descuento = parseFloat($("#selDescuento").val()) || 0;

        // Recorrer las filas de la tabla de productos seleccionados
        $("#tablaProductosSeleccionados tbody tr").each(function () {
            var subtotal = parseFloat($(this).find("td:last").text());
            total += subtotal;
        });

        // Calcular impuestos (13%)
        impuestos = total * 0.13;

        // Aplicar descuento
        total -= (total * (descuento / 100));

        // Actualizar los campos de total e impuestos
        $("#txtTotal").val(total.toFixed(2));
        $("#txtImpuestos").val(impuestos.toFixed(2));

        // Calcular total final
        var totalFinal = total + impuestos;
        $("#txtTotalFinal").val(totalFinal.toFixed(2));
    }
    
    // Asigna un controlador de eventos al botón
    $("#btnGuardar").click(function () {
        // Llama a la función para enviar los productos al servlet
        EnviarProductosSeleccionados();
    });
    
function EnviarProductosSeleccionados() {
    // Verificar si hay productos seleccionados
    if (productosSeleccionados.length === 0) {
        alert("No hay productos seleccionados para enviar.");
        return;
    }

    // Obtener el valor del descuento
    var discount = $("#selDescuento").val();
    try {
     $.post("SalesServlet", {
        idCliente: idClienteSeleccionado,
        idEmpleado: idEmpleadoSeleccionado,
        discount: discount,
        productosSeleccionados: JSON.stringify(productosSeleccionados)
    }, function (data) {
        // Manejar la respuesta del Servlet si es necesario
        alert("Factura ingresada con exito.");
    }).fail(function () {
        alert("Error al enviar los datos al servidor.");
    });       
    } catch (e) {
        throw e;
    }

    // Realizar una solicitud POST al Servlet con los datos de productos y los IDs

}
    