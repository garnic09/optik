/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
//Declaramos el array de los accesorios
let accesorios = [];

//Creamos funcion para inicializar el módulo con la tabla de accesorios cargada
export function inicializar() {
        //Obtenemos todos los accesorios con estatus activo
	getAll(1);
}

//Creamos funcion para insertar un nuevo accesorio
export function insertar(){
    //Validamos que los datos ingresados sean correctos
    if (validar() === false) {
        return;
    }
    //Recuperamos los valores ingresados en el formulario
    let nombre = document.getElementById("txtnombre").value;
    let marca = document.getElementById("txtmarca").value;
    let precioCompra = document.getElementById("txtpCompra").value;
    let precioVenta = document.getElementById("txtpVenta").value;
    let existencias = document.getElementById("txtexistencias").value;
    
    //Armamos el JSON
    let producto = {nombre:nombre,marca:marca,precioCompra:precioCompra,precioVenta:precioVenta,existencias:existencias};
    let acc = {producto:producto};
    
    let accesorio = {datosAccesorio:JSON.stringify(acc)};
    //Pasamos los parametros
    let parametros = new URLSearchParams(accesorio);
    //Consumimos el servicio
    fetch("../api/restoptik/insertAccesorio",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => function(data){
        if (data.error) {
            Swal.fire({position:'center',icon:'error', title:'Error al guardar accesorio',showConfirmButton:false,timer:1500});
            //alert("Error");
        }
    });
    Swal.fire({position:'center',icon:'success', title:'Accesorio guardado de forma correcta',showConfirmButton:false,timer:1500});
    //Recuperamos los accesorios con estatus activo
    setTimeout(() => {getAll(1);},2000);
    //Limpiamos el formulario
    limpiarForm();
}

//Creamos funcion para actualizar un registro de accesorio
export function actualizar(){
    //Validamos que los datos registrados sean correctos
    if (validar() === false) {
        return;
    }
    //Recuperamos los valores ingresados en el formulario
    let idAccesorio = document.getElementById("txtidAccesorio").value;
    let idProducto = document.getElementById("txtidProducto").value;
    let nombre = document.getElementById("txtnombre").value;
    let marca = document.getElementById("txtmarca").value;
    let precioCompra = document.getElementById("txtpCompra").value;
    let precioVenta = document.getElementById("txtpVenta").value;
    let existencias = document.getElementById("txtexistencias").value;
    
    //Armamos el JSON
    let producto = {idProducto:idProducto,nombre:nombre,marca:marca,precioCompra:precioCompra,precioVenta:precioVenta,existencias:existencias};
    let acc = {idAccesorio:idAccesorio,producto:producto};
    let accesorio = {datosAccesorio:JSON.stringify(acc)};
    //Pasamos los parametros
    let parametros = new URLSearchParams(accesorio);
    //Consumimos el servicio
    fetch("../api/restoptik/updateAccesorio",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => function(data){
        if (data.error) {
            Swal.fire({position:'center',icon:'error', title:'Error al actualizar accesorio',showConfirmButton:false,timer:1500});
        }
    });
    Swal.fire({position:'center',icon:'success', title:'Accesorio actualizado de forma correcta',showConfirmButton:false,timer:1500});
    //Recuperamos los accesorios
    setTimeout(() => {getAll(accesorios[idAccesorio].producto.estatus);},2000);
    //Limpiamos el formulario
    limpiarForm();
}

//Creamos funcion para eliminar un accesorio de forma lógica (cambiamos su estatus a inactivo)
export function eliminar(idAccesorio){
    //Armamos el JSON
    let acc = {idAccesorio:idAccesorio};
    let accesorio = {datosAccesorio: JSON.stringify(acc)};
    //Pasamos los parametros
    let parametros = new URLSearchParams(accesorio);
    //Consumimos el servicio
    fetch("../api/restoptik/deleteAccesorio",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => function(data){
        if (data.error) {
            Swal.fire({position:'center',icon:'error', title:'Error al eliminar accesorio',showConfirmButton:false,timer:1500});
        }
    });
    Swal.fire({position:'center',icon:'success', title:'Accesorio eliminado de forma correcta',showConfirmButton:false,timer:1500});
    //Recuperamos los accesorios con estatus activo
    setTimeout(() => {getAll(1);},2000);
}

//Creamos funcion para activar un accesorio (cambiamos su estatus a activo)
export function activar(idAccesorio){
    //Armamos el JSON
    let acc = {idAccesorio:idAccesorio};
    let accesorio = {datosAccesorio: JSON.stringify(acc)};
    //Pasamos los parametros
    let parametros = new URLSearchParams(accesorio);
    //Consumimos el servicio
    fetch("../api/restoptik/activateAccesorio",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => function(data){
        if (data.error) {
            Swal.fire({position:'center',icon:'error', title:'Error al activar accesorio',showConfirmButton:false,timer:1500});
        }
    });
    Swal.fire({position:'center',icon:'success', title:'Accesorio activado de forma correcta',showConfirmButton:false,timer:1500});
    //Recuperamos los accesorios con estatus inactivo
    setTimeout(() => {getAll(0);},2000);
}

//Creamos funcion para realizar la búsqueda de un accesorio por cualquiera de sus campos
export function buscar(){
    //Recuperamos el valor que se desea buscar
    const busqueda = document.getElementById('txtSearch').value;
    //Creamos un array donde se agregaran los accesorios que coincidan con el valor dado
    const coincidencias = [];
    //Recorremos el arreglo con todos los accesorios
    for (let i = 0; i < accesorios.length; i++) {
        const accesorio = accesorios[i];
        //Se evalua si hay alguna coincidencia entre el valor y los registros del accesorio
        if(
            accesorio.producto.nombre.toLowerCase().includes(busqueda.toLowerCase())||
            accesorio.producto.marca.toLowerCase().includes(busqueda.toLowerCase())||
            accesorio.producto.precioCompra.toString().toLowerCase().includes(busqueda.toLowerCase())||
            accesorio.producto.precioVenta.toString().toLowerCase().includes(busqueda.toLowerCase())||
            accesorio.producto.existencias.toString().toLowerCase().includes(busqueda.toLowerCase())||
            accesorio.producto.codigoBarras.toLowerCase().includes(busqueda.toLowerCase())){
        
            coincidencias.push(accesorio);
        }
        //Cargamos la tabla con las coincidencias encontradas
        cargarTablaAccesorio(coincidencias,null);
    }
}
//Creamos funcion para obtener todos los accesorios ya sean con estatus activo o inactivo
export function getAll(estatus){
    //Armamos el JSON
    let datos = {estatus:estatus};
    //Pasamos los parametros
    let parametros = new URLSearchParams(datos);
    //Consumimos el servicio
    fetch("../api/restoptik/getAllAccesorio",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => { 
        if (data.error) {
            Swal.fire({position:'center',icon:'error', title:'Error al obtener accesorios',showConfirmButton:false,timer:1500});
        } else {
            //Cargamos la tabla con los registros obtenidos
            cargarTablaAccesorio(null,data);
        }
        JSON.stringify(data);
    });
}

//export function getAllInactivos(){
//    let datos = {estatus:0};
//    let parametros = new URLSearchParams(datos);
//    fetch("../api/restoptik/getAllAccesorio",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
//            .then(response => response.json())
//            .then(data => {
//        if (data.error) {
//            //alert(data.error);
//        } else {
//            cargarTablaAccesorio(null,data);
//        }
//        JSON.stringify(data);
//    });
//}

//Creamos funcion para cargar la tabla con los accesorios
export function cargarTablaAccesorio(coincidencias,data){
    //Verificamos que haya coincidenncias
    if (coincidencias) {
        data = coincidencias;
    } else
    accesorios = data;
    let contenido = "";
    
//    for (let i = 0; i < empleados.length; i++) {
    //Recorremos el arreglo de accesorios
    data.forEach((accesorio, index) => {
        const {producto} = accesorio;
        //Inyectamos codigo agregando los elementos a la tabla
        contenido += "<tr>";
        contenido += "<td style='display:none;'>" + accesorio.idAccesorio + "</td>";
        contenido += "<td>" + accesorio.producto.codigoBarras + "</td>";
        contenido += "<td>" + accesorio.producto.nombre + "</td>";
        contenido += "<td>" + accesorio.producto.marca + "</td>";
        contenido += "<td>" + accesorio.producto.precioCompra + "</td>";
        contenido += "<td>" + accesorio.producto.precioVenta + "</td>";
        contenido += "<td>" + accesorio.producto.existencias + "</td>";
        contenido += "<td><button type='button' class='btn btn-primary btn-m m-2' onClick='ma.cargarForm(" + index + ")'>Ver</button></td>";
        if (accesorio.producto.estatus === 1) {
            contenido += "<td><button type='button' class='btn btn-danger btn-m m-2' onclick='ma.eliminar(" + accesorio.idAccesorio + ")'>Eliminar</button></td>";
        }else {
            contenido += "<td><button type='button' class='btn btn-success btn-m m-2 ' onclick='ma.activar(" + accesorio.idAccesorio + ")'>Activar</button></td>";
        }
        contenido += "</tr>";
    });
    document.getElementById("tbAccesorio").innerHTML = contenido;
}

//Creamos funcion para cargar los datos del accesorio seleccionado
export function cargarForm(i){
    document.getElementById("txtidAccesorio").value = accesorios[i].idAccesorio;
    document.getElementById("txtidProducto").value = accesorios[i].producto.idProducto;
    document.getElementById("txtnombre").value = accesorios[i].producto.nombre;
    document.getElementById("txtmarca").value = accesorios[i].producto.marca;
    document.getElementById("txtcodigoBarras").value = accesorios[i].producto.codigoBarras;
    document.getElementById("txtpCompra").value = accesorios[i].producto.precioCompra;
    document.getElementById("txtpVenta").value = accesorios[i].producto.precioVenta;
    document.getElementById("txtexistencias").value = accesorios[i].producto.existencias;
}

//Creamos funcion para dejar limpios los campos del formulario
export function limpiarForm(){
    document.querySelector('form').reset();
}

//Creamos funcion para limpiar la tabla
export function limpiarTablaAccesorio(){
    document.getElementById("tbAccesorio").innerHTML = "";
}

const regexValidar = {
//validar letras, espacios y acentos
    letras: /^[a-zA-ZÁ-ÿ\s]{1,40}$/,
    //validar numeros y puntos
    numeros: /^[a-zA-Z0-9-]+$/,
    //validar numeros enteros
    numerosEnteros: /^[0-9]+$/,
    //validar letras, espacios, acentos, numeros, puntos, comas, guiones y máximo 240 caracteres
    letrasNumerosSimbolos: /^[a-zA-ZÁ-ÿ0-9\s.,-]{1,240}$/,
    //validar codigo postal
    cp: /^[0-9]{5}$/,
    //validar email
    email: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
    //validar contrasenia
    contrasenia: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,20}$/,
    //validar telefono
    telefono: /^[0-9]{10}$/
};

//Creamos funcion para validar que sean correctos los elementos registrados
function validar() {
    let nombre = document.getElementById("txtnombre").value;
    let marca = document.getElementById("txtmarca").value;
    let precioCompra = document.getElementById("txtpCompra").value;
    let precioVenta = document.getElementById("txtpVenta").value;
    let existencias = document.getElementById("txtexistencias").value;
//    if (nombre == "" || precioCompra == "" || precioVenta == "" || marca == "" || existencias == "") {
//        Swal.fire({position: 'center', icon: 'warning', title: 'No se permiten campos vacíos.', showConfirmButton: false, timer: 1500});
//        return false;
//    }

    //Hacemos validaciones
    if (!regexValidar.letras.test(nombre)) {
        Swal.fire({position: 'center', icon: 'warning', title: 'El campo Nombre solo puede contener letras.', showConfirmButton: false, timer: 1500});
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
    return true;
}
