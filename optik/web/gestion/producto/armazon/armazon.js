/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/* global fetch */
let armazones = [];
let foto = "";
//var base64String = "";

export function inicializar() {
    getAll(1);
}

export function insertar() {
    console.log(localStorage.getItem("currentUser"));
    if (localStorage.getItem("currentUser") !== "" || localStorage.getItem("currentUser") !== null) {
        try {
            let arm = JSON.parse(localStorage.getItem("currentUser"));
            let lToken = arm.usuario.lastToken;
            if (!validar()) {
                return;
            }
            let nombre = document.getElementById("txtNombre").value;
            let marca = document.getElementById("txtMarca").value;
            let precioCompra = document.getElementById("txtPrecioCompra").value;
            let precioVenta = document.getElementById("txtPrecioVenta").value;
            let existencias = document.getElementById("txtExistencias").value;
            let modelo = document.getElementById("txtModelo").value;
            let color = document.getElementById("txtColor").value;
            let dimensiones = document.getElementById("txtDimensiones").value;
            let descripcion = document.getElementById("txtDescripcion").value;
            
            let producto = {nombre: nombre, marca: marca, precioCompra: precioCompra, precioVenta: precioVenta, existencias: existencias};
            let a = {modelo: modelo, color: color, dimensiones: dimensiones, descripcion: descripcion, fotografia: foto, producto: producto};

            let armazon = {datosArmazon: JSON.stringify(a), lastToken: lToken};
            //alert(JSON.stringify(a));

            let parametros = new URLSearchParams(armazon);

            fetch("http://localhost:8080/optik/api/restoptik/insertArmazon", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
                    .then(response => response.json())
                    .then(data => function (data) {
                            console.log(data);
                            if (data.error) {
                                Swal.fire({position: 'center', icon: 'error', title: 'Error al guardar armazón', showConfirmButton: false, timer: 1500});
                                //alert("Error");
                            }
                        });
            Swal.fire({position: 'center', icon: 'success', title: 'Armazón guardado de forma correcta', showConfirmButton: false, timer: 1500});
            setTimeout(() => {
                getAll(1);
            }, 2000);
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
export function encodeImageFileAsURL(element) {
    let file = element.files[0];
    let reader = new FileReader();
    reader.onloadend = function () {
        document.write('', reader.result);
        base64String = reader.result.toString();
        alert(reader.result);
    };
    reader.readAsDataURL(file);
}

export function actualizar() {
    console.log(localStorage.getItem("currentUser"));
    if (localStorage.getItem("currentUser") !== "" || localStorage.getItem("currentUser") !== null) {
        try {
            let arm = JSON.parse(localStorage.getItem("currentUser"));
            let lToken = arm.usuario.lastToken;
            if (!validar()) {
                return;
            }
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


            let producto = {idProducto: idProducto, nombre: nombre, marca: marca, precioCompra: precioCompra, precioVenta: precioVenta, existencias: existencias};
            let a = {idArmazon: idArmazon, modelo: modelo, color: color, dimensiones: dimensiones, descripcion: descripcion, fotografia: foto, producto: producto};

            let armazon = {datosArmazon: JSON.stringify(a), lastToken: lToken};
            //alert(JSON.stringify(a));

            let parametros = new URLSearchParams(armazon);

            fetch("http://localhost:8080/optik/api/restoptik/actualizarArmazon", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
                    .then(response => response.json())
                    .then(data => function (data) {
                            if (data.error) {
                                Swal.fire({position: 'center', icon: 'error', title: 'Error al actualizar armazón', showConfirmButton: false, timer: 1500});
                            }
                        });
            Swal.fire({position: 'center', icon: 'success', title: 'Armazón actualizado de forma correcta', showConfirmButton: false, timer: 1500});
            setTimeout(() => {
                getAll(1);
            }, 2000);
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
    console.log(localStorage.getItem("currentUser"));
    if (localStorage.getItem("currentUser") !== "" || localStorage.getItem("currentUser") !== null) {
        try {
            let arm = JSON.parse(localStorage.getItem("currentUser"));
            let lToken = arm.usuario.lastToken;
            let datos = {estatus: estatus, lastToken: lToken};
            let parametros = new URLSearchParams(datos);

            fetch("../api/restoptik/getAllArmazon", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
                    .then(response => response.json())
                    .then(data => {
                        if (data.error) {
                            Swal.fire({position: 'center', icon: 'error', title: 'Error al obtener armazones', showConfirmButton: false, timer: 1500});
                        } else {
                            cargarTablaArmazon(null, data);
                        }
                        JSON.stringify(data);
                    });
        } catch (e) {
            Swal.fire({position: 'center', icon: 'error', title: 'Acceso denegado', showConfirmButton: false, timer: 1500});
            window.location.href = "http://localhost:8080/optik/";
        }
    } else {
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
//        contenido += "<td>" + armazon.producto.precioCompra + "</td>";
        contenido += "<td>" + armazon.producto.precioVenta + "</td>";
//        contenido += "<td>" + armazon.producto.existencias + "</td>";
        contenido += "<td>" + armazon.dimensiones + "</td>";
        contenido += "<td>" + armazon.descripcion + "</td>";
        contenido += "<td><button class='btn btn-primary btn-m m-2'  type='button' onclick='ma.loadFoto(" + index + ");'>Foto</button> </td>";
        contenido += "<td><button class='btn btn-primary btn-m m-2'  type='button' onclick='ma.cargarForm(" + index + ");'>Ver</button> </td>";
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

// Converitr imagen a base64 para guardar en base de datos
const fileInput = document.getElementById("fileInput");

fileInput.addEventListener("change", e => {
    const file = fileInput.files[0];
    const reader = new FileReader();

    reader.addEventListener("load", () => {
        foto = reader.result;
        console.log(reader.result);
    });
    reader.readAsDataURL(file);
});

// intentar cargar la imagen

export function loadFoto(i) {
    var image = new Image();
    image.src = armazones[i].fotografia;

    var w = window.open("");
    w.document.write(image.outerHTML);
}

export function limpiarTablaArmazon(){
    document.getElementById("tbArmazon").innerHTML = "";
}
