/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
let tratamientos = [];

export function inicializar() {
	getAll(1);
}

export function insertar(){
    if (validar() == false) {
        return;
    }
    let nombre = document.getElementById("txtnombre").value; 
    let precioCompra = document.getElementById("txtpCompra").value;
    let precioVenta = document.getElementById("txtpVenta").value;
    
    let tr = {nombre:nombre,precioCompra:precioCompra,precioVenta:precioVenta};
    
    let tratamiento = {datosTratamiento:JSON.stringify(tr)};
    
    let parametros = new URLSearchParams(tratamiento);
    
    fetch("../api/restoptik/insertTratamiento",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => function(data){
                console.log(data);
        if (data.error) {
            Swal.fire({position:'center',icon:'error', title:'Error al guardar tratamiento',showConfirmButton:false,timer:1500});
            //alert("Error");
        }
    });
    Swal.fire({position:'center',icon:'success', title:'Tratamiento guardado de forma correcta',showConfirmButton:false,timer:1500});
    setTimeout(() => {getAll(1);},2000);
    limpiarForm();
}

export function actualizar(){
    if (validar() == false) {
        return;
    }
    let idTratamiento = document.getElementById("txtidTratamiento").value;
    let nombre = document.getElementById("txtnombre").value;
    let precioCompra = document.getElementById("txtpCompra").value;
    let precioVenta = document.getElementById("txtpVenta").value;
    
    let tr = {idTratamiento:idTratamiento,nombre:nombre,precioCompra:precioCompra,precioVenta:precioVenta};
    
    let tratamiento = {datosTratamiento:JSON.stringify(tr)};
    
    let parametros = new URLSearchParams(tratamiento);
    
    fetch("../api/restoptik/updateTratamiento",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => function(data){
        if (data.error) {
            Swal.fire({position:'center',icon:'error', title:'Error al actualizar tratamiento',showConfirmButton:false,timer:1500});
        }
    });
    Swal.fire({position:'center',icon:'success', title:'Tratamiento actualizado de forma correcta',showConfirmButton:false,timer:1500});
    setTimeout(() => {getAll(tratamientos[idTratamiento].estatus);},2000);
    limpiarForm();
}

export function eliminar(idTratamiento){
    
    let tr = {idTratamiento:idTratamiento};
    let tratamiento = {datosTratamiento: JSON.stringify(tr)};
    let parametros = new URLSearchParams(tratamiento);
    
    fetch("../api/restoptik/deleteTratamiento",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => function(data){
        if (data.error) {
            Swal.fire({position:'center',icon:'error', title:'Error al eliminar tratamiento',showConfirmButton:false,timer:1500});
        }
    });
    Swal.fire({position:'center',icon:'success', title:'Tratamiento eliminado de forma correcta',showConfirmButton:false,timer:1500});
    setTimeout(() => {getAll(1);},2000);
}

export function activar(idTratamiento){
    
    let tr = {idTratamiento:idTratamiento};
    let tratamiento = {datosTratamiento: JSON.stringify(tr)};
    let parametros = new URLSearchParams(tratamiento);
    
    fetch("../api/restoptik/activateTratamiento",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => function(data){
                console.log(data);
        if (data.error) {
            Swal.fire({position:'center',icon:'error', title:'Error al activar tratamiento',showConfirmButton:false,timer:1500});
        }
    });
    Swal.fire({position:'center',icon:'success', title:'Tratamiento activado de forma correcta',showConfirmButton:false,timer:1500});
    setTimeout(() => {getAll(0);},2000);
}

export function buscar(){
    const busqueda = document.getElementById('txtSearch').value;
    const coincidencias = [];
    for (let i = 0; i < tratamientos.length; i++) {
        const tratamiento = tratamientos[i];
        if(
            tratamiento.nombre.toLowerCase().includes(busqueda.toLowerCase())||
            tratamiento.precioCompra.toString().toLowerCase().includes(busqueda.toLowerCase())||
            tratamiento.precioVenta.toString().toLowerCase().includes(busqueda.toLowerCase())){
        
            coincidencias.push(tratamiento);
        }
        cargarTablaTratamiento(coincidencias,null);
    }
}

export function getAll(estatus){
    let datos = {estatus:estatus};
    let parametros = new URLSearchParams(datos);
    fetch("../api/restoptik/getAllTratamiento",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => { 
        if (data.error) {
            Swal.fire({position:'center',icon:'error', title:'Error al obtener tratamientos',showConfirmButton:false,timer:1500});
        } else {
            cargarTablaTratamiento(null,data);
        }
        JSON.stringify(data);
    });
}

export function getAllInactivos(){
    let datos = {estatus:0};
    let parametros = new URLSearchParams(datos);
    fetch("../api/restoptik/getAllTratamiento",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => { 
                //console.log(data);
        if (data.error) {
            //alert(data.error);
        } else {
            cargarTablaTratamiento(null,data);
        }
    });
}

export function cargarTablaTratamiento(coincidencias,data){
    if (coincidencias) {
        data = coincidencias;
    } else
    tratamientos = data;
    let contenido = "";
    
//    for (let i = 0; i < empleados.length; i++) {
    data.forEach((tratamiento, index) => {
        const {producto} = tratamiento;
        
        contenido += "<tr>";
        contenido += "<td style='display:none;'>" + tratamiento.idTratamiento + "</td>";
        contenido += "<td>" + tratamiento.nombre + "</td>";
        contenido += "<td>" + tratamiento.precioCompra + "</td>";
        contenido += "<td>" + tratamiento.precioVenta + "</td>";
        contenido += "<td><button type='button' class='btn btn-primary btn-m m-2' onClick='ma.cargarForm(" + index + ")'>Ver</button></td>";
        if (tratamiento.estatus === 1) {
            contenido += "<td><button type='button' class='btn btn-danger btn-m m-2' onclick='ma.eliminar(" + tratamiento.idTratamiento + ")'>Eliminar</button></td>";
        }else {
            contenido += "<td><button type='button' class='btn btn-success btn-m m-2' onclick='ma.activar(" + tratamiento.idTratamiento + ")'>Activar</button></td>";
        }
        contenido += "</tr>";
    });
    document.getElementById("tbTratamiento").innerHTML = contenido;
}


export function cargarForm(i){
    document.getElementById("txtidTratamiento").value = tratamientos[i].idTratamiento;
    document.getElementById("txtnombre").value = tratamientos[i].nombre;
    document.getElementById("txtpCompra").value = tratamientos[i].precioCompra;
    document.getElementById("txtpVenta").value = tratamientos[i].precioVenta;
}

export function limpiarForm(){
    document.querySelector('form').reset();
}

export function limpiarTablaTratamiento(){
    document.getElementById("tbTratamiento").innerHTML = "";
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
function validar() {
    let nombre = document.getElementById("txtnombre").value; 
    let precioCompra = document.getElementById("txtpCompra").value;
    let precioVenta = document.getElementById("txtpVenta").value;
    if (nombre == "" || precioCompra == "" || precioVenta == "") {
        Swal.fire({position: 'center', icon: 'warning', title: 'No se permiten campos vacíos.', showConfirmButton: false, timer: 1500});
        return false;
    }
    if (!regexValidar.letras.test(nombre)) {
        Swal.fire({position: 'center', icon: 'warning', title: 'El campo Nombre solo puede contener letras.', showConfirmButton: false, timer: 1500});
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
    return true;
}
