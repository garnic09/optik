/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/* global fetch */
let armazones = [];
//var base64String = "";

export function inicializar() {
    getAll(1);
}

export function insertar() {
    console.log(localStorage.getItem("currentUser")); // Imprime en la consola el valor del campo "currentUser" almacenado en localStorage
    if (localStorage.getItem("currentUser") !== "" || localStorage.getItem("currentUser") !== null) { // Verifica si "currentUser" no es una cadena vacía ni nulo
        try {
            let arm = JSON.parse(localStorage.getItem("currentUser")); // Obtiene y convierte en objeto el valor del campo "currentUser" almacenado en localStorage
            let lToken = arm.usuario.lastToken; // Obtiene el último token de usuario del objeto almacenado en "currentUser"

            if (!validar()) { // Si la validación falla, detiene el proceso
                return;
            }

            // Obtiene los valores de los campos del formulario
            let nombre = document.getElementById("txtNombre").value;
            let marca = document.getElementById("txtMarca").value;
            let precioCompra = document.getElementById("txtPrecioCompra").value;
            let precioVenta = document.getElementById("txtPrecioVenta").value;
            let existencias = document.getElementById("txtExistencias").value;
            let modelo = document.getElementById("txtModelo").value;
            let color = document.getElementById("txtColor").value;
            let dimensiones = document.getElementById("txtDimensiones").value;
            let descripcion = document.getElementById("txtDescripcion").value;
            let fotografia = document.getElementById("txtDescripcion").value;//encodeImageFileAsURL();//necesitas ver como guardar la cadena que devuelve esta funcion encodeImageFileAsURL aqui

            // Crea un objeto "producto" con los valores obtenidos
            let producto = {nombre: nombre, marca: marca, precioCompra: precioCompra, precioVenta: precioVenta, existencias: existencias};
            // Crea un objeto "a" con los valores obtenidos y el objeto "producto"
            let a = {modelo: modelo, color: color, dimensiones: dimensiones, descripcion: descripcion, fotografia: fotografia, producto: producto};

            // Crea un objeto "armazon" con el objeto "a" en formato de cadena JSON y el último token de usuario obtenido
            let armazon = {datosArmazon: JSON.stringify(a), lastToken: lToken};

            // Crea una cadena con los parámetros a enviar en el cuerpo de la petición
            let parametros = new URLSearchParams(armazon);

            // Realiza una petición POST al servidor con la información del armazón a guardar
            fetch("http://localhost:8080/optik/api/restoptik/insertArmazon", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
                    .then(response => response.json())
                    .then(data => function (data) {
                            console.log(data);
                            if (data.error) {
                                Swal.fire({position: 'center', icon: 'error', title: 'Error al guardar armazón', showConfirmButton: false, timer: 1500});
                                //alert("Error");
                            }
                        });

            // Muestra un mensaje de éxito y llama a la función getAll() para obtener todos los armazones
            Swal.fire({position: 'center', icon: 'success', title: 'Armazón guardado de forma correcta', showConfirmButton: false, timer: 1500});
            setTimeout(() => {
                getAll(1);
            }, 2000);
            // Limpia el formulario
            limpiarForm();
        } catch (e) {
            Swal.fire({position: 'center', icon: 'error', title: 'Acceso denegado', showConfirmButton: false, timer: 1500});
            window.location.href = "http://localhost:8080/optik/";
        }
    } else {
        Swal.fire({position: 'center', icon: 'error', title: 'Acceso denegado', showConfirmButton: false, timer: 1500});
        window.location.href = "http://localhost:8080/optik/";
    }
}

// necesitas ver como hacer que la funcion no abra una nueva ventana con la cadena de texto
// Función que toma un objeto que contiene información sobre un archivo de imagen seleccionado por el usuario
export function encodeImageFileAsURL(element) {
    // Se obtiene el archivo de imagen seleccionado por el usuario del objeto 'element'
    let file = element.files[0];
    // Se crea un nuevo objeto 'FileReader'
    let reader = new FileReader();
    // Se asigna una función al evento 'onloadend' del objeto 'FileReader'
    reader.onloadend = function () {
        // Se muestra el contenido de la imagen en la página web
        document.write('', reader.result);
        // Se convierte el resultado de la lectura del archivo de imagen en una cadena base64 y se asigna a la variable 'base64String'
        base64String = reader.result.toString();
        // Se muestra una alerta que contiene la cadena base64
        alert(reader.result);
    };
    // Se inicia la lectura del archivo de imagen utilizando el objeto 'FileReader'
    reader.readAsDataURL(file);
}

export function actualizar() {
    console.log(localStorage.getItem("currentUser"));
    if (localStorage.getItem("currentUser") !== "" || localStorage.getItem("currentUser") !== null) {
        // Se inicia el bloque try-catch
        try {
// Se obtiene el token del usuario actual y se asigna a la variable lToken
            let arm = JSON.parse(localStorage.getItem("currentUser"));
            let lToken = arm.usuario.lastToken;

// Si la función validar() retorna falso, se sale del bloque try-catch
            if (!validar()) {
                return;
            }

// Se obtienen los valores de los elementos HTML y se asignan a variables
            let idProducto = document.getElementById("txtidP").value;
            let idArmazon = document.getElementById("txtidA").value;
            let nombre = document.getElementById("txtNombre").value;
            let marca = document.getElementById("txtMarca").value;
            let precioCompra = document.getElementById("txtPrecioCompra").value;
            let precioVenta = document.getElementById("txtPrecioVenta").value;
            let existencias = document.getElementById("txtExistencias").value;
            let modelo = document.getElementById("txtModelo").value;
            let color = document.getElementById("txtColor").value;
            let dimensiones = document.getElementById("txtDimensiones").value;
            let descripcion = document.getElementById("txtDescripcion").value;
            let fotografia = document.getElementById("txtDescripcion").value;

// Se crea un objeto producto con los valores obtenidos y se asigna a la variable producto
            let producto = {
                idProducto: idProducto,
                nombre: nombre,
                marca: marca,
                precioCompra: precioCompra,
                precioVenta: precioVenta,
                existencias: existencias
            };

// Se crea un objeto a con los valores obtenidos y el objeto producto, y se asigna a la variable a
            let a = {
                idArmazon: idArmazon,
                modelo: modelo,
                color: color,
                dimensiones: dimensiones,
                descripcion: descripcion,
                fotografia: fotografia,
                producto: producto
            };

// Se crea un objeto armazon con los valores obtenidos y el token, y se asigna a la variable armazon
            let armazon = {
                datosArmazon: JSON.stringify(a),
                lastToken: lToken
            };

// Se crea un objeto URLSearchParams con el objeto armazon y se asigna a la variable parametros
            let parametros = new URLSearchParams(armazon);

// Se hace una petición POST a la URL especificada con los parámetros y headers especificados
            fetch("http://localhost:8080/optik/api/restoptik/actualizarArmazon", {
                method: "POST",
                body: parametros,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
                }
            })
                    .then(response => response.json())
                    .then(data => function (data) {
// Si hay un error en la respuesta, se muestra una alerta de error
                            if (data.error) {
                                Swal.fire({position: 'center', icon: 'error', title: 'Error al actualizar armazón', showConfirmButton: false, timer: 1500});
                            }
                        });

// Se muestra una alerta de éxito y se llama a la función getAll después de 2 segundos
            Swal.fire({position: 'center', icon: 'success', title: 'Armazón actualizado de forma correcta', showConfirmButton: false, timer: 1500});
            setTimeout(() => {
                getAll(1);
            }, 2000);

// Se llama a la función limpiarForm()
            limpiarForm();
        } catch (e) {
            Swal.fire({position: 'center', icon: 'error', title: 'Acceso denegado', showConfirmButton: false, timer: 1500});
            window.location.href = "http://localhost:8080/optik/";
        }
    } else {
        Swal.fire({position: 'center', icon: 'error', title: 'Acceso denegado', showConfirmButton: false, timer: 1500});
        window.location.href = "http://localhost:8080/optik/";
    }
}

export function getAll(estatus) {
    console.log(localStorage.getItem("currentUser")); // Se imprime en consola el valor que se encuentra en el objeto currentUser almacenado en localStorage.
    if (localStorage.getItem("currentUser") !== "" || localStorage.getItem("currentUser") !== null) { // Si el objeto no está vacío y no es nulo.
        try {
            let arm = JSON.parse(localStorage.getItem("currentUser")); // Se almacena en la variable arm el objeto JSON parseado que se encuentra en currentUser.
            let lToken = arm.usuario.lastToken; // Se almacena en la variable lToken el último token de sesión del usuario que se encuentra en currentUser.
            let datos = {estatus: estatus, lastToken: lToken}; // Se crea un objeto llamado datos que contiene el estatus y el último token de sesión del usuario.
            let parametros = new URLSearchParams(datos); // Se crea un objeto de URLSearchParams con los datos a enviar en la petición.

            // Se envía una petición POST al servidor con los datos de la petición y se especifica que se enviará información en formato x-www-form-urlencoded.
            fetch("http://localhost:8080/optik/api/restoptik/getAllArmazon", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
                    .then(response => response.json()) // Se obtiene la respuesta del servidor y se convierte a JSON.
                    .then(data => {
                        if (data.error) { // Si la respuesta del servidor contiene un error, se muestra una alerta con el mensaje de error.
                            Swal.fire({position: 'center', icon: 'error', title: 'Error al obtener armazones', showConfirmButton: false, timer: 1500});
                        } else {
                            cargarTablaArmazon(null, data); // Si la respuesta es exitosa, se carga la tabla con los datos obtenidos.
                        }
                        JSON.stringify(data); // Se convierte la respuesta en JSON y se retorna.
                    });
        } catch (e) { // Si ocurre una excepción al intentar realizar la petición, se muestra una alerta de acceso denegado y se redirige al usuario a la página de inicio.
            Swal.fire({position: 'center', icon: 'error', title: 'Acceso denegado', showConfirmButton: false, timer: 1500});
            window.location.href = "http://localhost:8080/optik/";
        }
    } else { // Si el objeto currentUser está vacío o es nulo, se muestra una alerta de acceso denegado y se redirige al usuario a la página de inicio.
        Swal.fire({position: 'center', icon: 'error', title: 'Acceso denegado', showConfirmButton: false, timer: 1500});
        window.location.href = "http://localhost:8080/optik/";
    }
}

export function cargarTablaArmazon(coincidencias, data) {
    if (coincidencias) {
        data = coincidencias;
    } else {
        armazones = data;
    }

    let contenido = "";
    //for(let i=0;i<empleados.length;i++){ 
    data.forEach((armazon, index) => {
        const {producto} = armazon;

        contenido += "<tr>";
        contenido += "<td>" + armazon.producto.nombre + "</td>";
        contenido += "<td>" + armazon.producto.marca + "</td>";
        contenido += "<td>" + armazon.modelo + "</td>";
        contenido += "<td>" + armazon.color + "</td>";
        contenido += "<td>" + armazon.producto.precioCompra + "</td>";
        contenido += "<td>" + armazon.producto.precioVenta + "</td>";
        contenido += "<td>" + armazon.producto.existencias + "</td>";
        contenido += "<td>" + armazon.dimensiones + "</td>";
        contenido += "<td>" + armazon.descripcion + "</td>";
        contenido += "<td><button class='btn btn-primary btn-m m-2'  type='button' onclick='ma.cargarForm(" + index + ");'>ver</button> </td>";
        if (armazon.estatus === 1) {
            contenido += "<td><button class='btn btn-danger btn-m m-2'  type='button' onclick='ma.estatusInactivo(" + armazon.idArmazon + ");'>Eliminar</button></td>";
        } else {
            contenido += "<td><button class='btn btn-success btn-m m-2'  type='button' onclick='ma.estatusActivo(" + armazon.idArmazon + ");'>Activar</button> </td>";
        }
        contenido += "</tr>";

    });
//       alert(contenido);
    document.getElementById("tbArmazon").innerHTML = contenido;
    //String = "Quiero comerme tu pancreas"
}

export function cargarForm(i) {
    document.getElementById("txtidP").value = armazones[i].producto.idProducto;
    document.getElementById("txtidA").value = armazones[i].idArmazon;
    document.getElementById("txtNombre").value = armazones[i].producto.nombre;
    document.getElementById("txtMarca").value = armazones[i].producto.marca;
    document.getElementById("txtPrecioCompra").value = armazones[i].producto.precioCompra;
    document.getElementById("txtPrecioVenta").value = armazones[i].producto.precioVenta;
    document.getElementById("txtExistencias").value = armazones[i].producto.existencias;
    document.getElementById("txtModelo").value = armazones[i].modelo;
    document.getElementById("txtColor").value = armazones[i].color;
    document.getElementById("txtDimensiones").value = armazones[i].dimensiones;
    document.getElementById("txtDescripcion").value = armazones[i].descripcion;
}

//Obtiene todos los armazones en la base de datos que tienen como estatus inactivo
export function getAllInactivos() {
    //alert("ttt");
    let datos = {estatus: 0};
    let parametros = new URLSearchParams(datos);

    fetch("http://localhost:8080/optik/api/restoptik/getAllArmazon", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => {
                console.log(data);
                //alert(JSON.stringify(data));
                if (data.error) {
                    //alert(data.error);
                } else {
                    cargarTablaArmazon(null, data);
                }
            });

}

//Manda a llamar al servicio rest que coloca como inactivo a un armazon
export function estatusInactivo(idArmazon) {
    console.log(localStorage.getItem("currentUser"));
    if (localStorage.getItem("currentUser") !== "" || localStorage.getItem("currentUser") !== null) {
        try {
            let arm = JSON.parse(localStorage.getItem("currentUser"));
            let lToken = arm.usuario.lastToken;
                let pr = {idArmazon: idArmazon};
                let armazon = {datosArmazon: JSON.stringify(pr), lastToken: lToken};
                //alert(JSON.stringify(em));
                let parametros = new URLSearchParams(armazon);
                fetch("http://localhost:8080/optik/api/restoptik/estatusInactivoArmazon", {method: "POST",
                body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
                                .then(response => response.json())
                                .then(data => function (data) {
                                          if (data.error) {
                                Swal.fire({position: 'center', icon: 'error', title: 'Error al eliminar armazón', showConfirmButton: false, timer: 1500});
                            }
                        });
            Swal.fire({position: 'center', icon: 'success', title: 'Armazón eliminado de forma correcta', showConfirmButton: false, timer: 1500});
            setTimeout(() => {
                getAll(1);
            }, 2000);
        } catch (e) {
            Swal.fire({position: 'center', icon: 'error', title: 'Acceso denegado', showConfirmButton: false, timer: 1500});
            window.location.href = "http://localhost:8080/optik/";
        }
    } else {
        Swal.fire({position: 'center', icon: 'error', title: 'Acceso denegado', showConfirmButton: false, timer: 1500});
        window.location.href = "http://localhost:8080/optik/";
    }
}

//Manda a llamar al servicio rest que coloca como activo a un armazon
export function estatusActivo(idArmazon) {
    console.log(localStorage.getItem("currentUser"));
    if (localStorage.getItem("currentUser") !== "" || localStorage.getItem("currentUser") !== null) {
        try {
            let arm = JSON.parse(localStorage.getItem("currentUser"));
            let lToken = arm.usuario.lastToken;
            let pr = {idArmazon: idArmazon};
                let armazon = {datosArmazon: JSON.stringify(pr), lastToken: lToken};
                //alert(JSON.stringify(em));
                let parametros = new URLSearchParams(armazon);
                fetch("http://localhost:8080/optik/api/restoptik/estatusActivoArmazon", {method: "POST",
                body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
                                .then(response => response.json())
                                .then(data => function (data) {
                                            console.log(data);
                                    if (data.error) {
                                Swal.fire({position: 'center', icon: 'error', title: 'Error al activar armazón', showConfirmButton: false, timer: 1500});
                            }
                        });
            Swal.fire({position: 'center', icon: 'success', title: 'Armazón activado de forma correcta', showConfirmButton: false, timer: 1500});
            setTimeout(() => {
                getAll(0);
            }, 2000);
        } catch (e) {
            Swal.fire({position: 'center', icon: 'error', title: 'Acceso denegado', showConfirmButton: false, timer: 1500});
            window.location.href = "http://localhost:8080/optik/";
        }
    } else {
        Swal.fire({position: 'center', icon: 'error', title: 'Acceso denegado', showConfirmButton: false, timer: 1500});
        window.location.href = "http://localhost:8080/optik/";
    }
}


export function limpiarForm() {
    document.querySelector('form').reset();
}

//Busca y filtra los armazones por nombre y otros campos, posteriormente carga las coincidencias en la tabla
export function buscar() {
    const busqueda = document.getElementById("txtSearch").value;
    const coincidencias = [];
    for (let i = 0; i < armazones.length; i++) {
        const x = armazones[i];
        if (
                x.producto.nombre.toLowerCase().includes(busqueda.toLowerCase()) ||
                x.producto.marca.toLowerCase().includes(busqueda.toLowerCase()) ||
                x.producto.precioCompra.toString().toLowerCase().includes(busqueda.toLowerCase()) ||
                x.producto.precioVenta.toString().toLowerCase().includes(busqueda.toLowerCase()) ||
                x.armazon.modelo.toLowerCase().includes(busqueda.toLowerCase()) ||
                x.armazon.color.toLowerCase().includes(busqueda.toLowerCase()) ||
                x.armazon.descripcion.toLowerCase().includes(busqueda.toLowerCase())
                ) {

            coincidencias.push(x);
        }
        console.log(coincidencias);
        cargarTablaArmazon(coincidencias, null);
    }
}

const regexValidar = {
//validar letras, espacios y acentos
    letras: /^[a-zA-ZÁ-ÿ\s]{1,40}$/,
    //validar numeros y puntos
    numeros: /^[a-zA-Z0-9-.]+$/,
    //validar numeros enteros
    numerosEnteros: /^[0-9]+$/,
    //validar letras, espacios, acentos, numeros, puntos, comas, guiones y máximo 240 caracteres
    letrasNumerosSimbolos: /^[a-zA-ZÁ-ÿ0-9\s.,-]{1,240}$/,
    //validar codigo postal
    decimales: /^[0-9.]+$/,
    //validar email
    email: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
    //validar contrasenia
    contrasenia: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,20}$/,
    //validar telefono
    telefono: /^[0-9]+$/
};

//Con ayuda de la funcion regexValidar, valida que todos los campos esten completos y con valores validos
function validar() {
    let nombre = document.getElementById("txtNombre").value;
    let marca = document.getElementById("txtMarca").value;
    let precioCompra = document.getElementById("txtPrecioCompra").value;
    let precioVenta = document.getElementById("txtPrecioVenta").value;
    let existencias = document.getElementById("txtExistencias").value;
    let modelo = document.getElementById("txtModelo").value;
    let color = document.getElementById("txtColor").value;
    let dimensiones = document.getElementById("txtDimensiones").value;
    let descripcion = document.getElementById("txtDescripcion").value;
//    if (nombre === "" || precioCompra === "" || precioVenta === "" || marca === "" || existencias === "" || modelo === "" || color === "" || dimensiones === "" || descripcion === "") {
//        Swal.fire({position: 'center', icon: 'warning', title: 'No se permiten campos vacíos.', showConfirmButton: false, timer: 1500});
//        return false;
//    }
    if (!regexValidar.letras.test(nombre)) {
        Swal.fire({position: 'center', icon: 'warning', title: 'El campo Nombre solo puede contener letras.', showConfirmButton: false, timer: 1500});
        return false;
    }
    if (!regexValidar.numeros.test(modelo)) {
        Swal.fire({position: 'center', icon: 'warning', title: 'El campo Modelo solo puede contener letras.', showConfirmButton: false, timer: 1500});
        return false;
    }
    if (!regexValidar.letras.test(color)) {
        Swal.fire({position: 'center', icon: 'warning', title: 'El campo Color solo puede contener letras.', showConfirmButton: false, timer: 1500});
        return false;
    }
    if (!regexValidar.letras.test(marca)) {
        Swal.fire({position: 'center', icon: 'warning', title: 'El campo Marca solo puede contener letras.', showConfirmButton: false, timer: 1500});
        return false;
    }
    if (!regexValidar.numeros.test(precioCompra)) {
        Swal.fire({position: 'center', icon: 'warning', title: 'El campo Precio Compra solo puede contener números.', showConfirmButton: false, timer: 1500});
        return false;
    }
    if (!regexValidar.numeros.test(precioVenta)) {
        Swal.fire({position: 'center', icon: 'warning', title: 'El campo Precio Venta solo puede contener números.', showConfirmButton: false, timer: 1500});
        return false;
    }
    if (!regexValidar.numerosEnteros.test(existencias)) {
        Swal.fire({position: 'center', icon: 'warning', title: 'El campo Existencias solo puede contener números enteros.', showConfirmButton: false, timer: 1500});
        return false;
    }
    if (!regexValidar.letrasNumerosSimbolos.test(dimensiones)) {
        Swal.fire({position: 'center', icon: 'warning', title: 'Verifique los datos en el campo Dimensiones.', showConfirmButton: false, timer: 1500});
        return false;
    }
    if (!regexValidar.letrasNumerosSimbolos.test(descripcion)) {
        Swal.fire({position: 'center', icon: 'warning', title: 'Verifique los datos en el campo Descripción.', showConfirmButton: false, timer: 1500});
        return false;
    }
    return true;
}
