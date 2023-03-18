/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
let materiales = [];

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
    
    let mt = {nombre:nombre,precioCompra:precioCompra,precioVenta:precioVenta};
    
    let material = {datosMaterial:JSON.stringify(mt)};
    
    let parametros = new URLSearchParams(material);
    
    fetch("../api/restoptik/insertMaterial",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => function(data){
                console.log(data);
        if (data.error) {
            Swal.fire({position:'center',icon:'error', title:'Error al guardar material',showConfirmButton:false,timer:1500});
            //alert("Error");
        }
    });
    Swal.fire({position:'center',icon:'success', title:'Material guardado de forma correcta',showConfirmButton:false,timer:1500});
    setTimeout(() => {getAll(1);},2000);
    limpiarForm();
}

export function actualizar(){
    if (validar() == false) {
        return;
    }
    let idMaterial = document.getElementById("txtidMaterial").value;
    let nombre = document.getElementById("txtnombre").value;
    let precioCompra = document.getElementById("txtpCompra").value;
    let precioVenta = document.getElementById("txtpVenta").value;
    
    let mt = {idMaterial:idMaterial,nombre:nombre,precioCompra:precioCompra,precioVenta:precioVenta};
    
    let material = {datosMaterial:JSON.stringify(mt)};
    
    let parametros = new URLSearchParams(material);
    
    fetch("../api/restoptik/updateMaterial",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => function(data){
        if (data.error) {
            Swal.fire({position:'center',icon:'error', title:'Error al actualizar material',showConfirmButton:false,timer:1500});
        }
    });
    Swal.fire({position:'center',icon:'success', title:'Material actualizado de forma correcta',showConfirmButton:false,timer:1500});
    setTimeout(() => {getAll(materiales[idMaterial].estatus);},2000);
    limpiarForm();
}

export function eliminar(idMaterial){
    
    let mt = {idTratamiento:idMaterial};
    let material = {datosMaterial: JSON.stringify(mt)};
    let parametros = new URLSearchParams(material);
    
    fetch("../api/restoptik/deleteMaterial",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => function(data){
        if (data.error) {
            Swal.fire({position:'center',icon:'error', title:'Error al eliminar material',showConfirmButton:false,timer:1500});
        }
    });
    Swal.fire({position:'center',icon:'success', title:'Material eliminado de forma correcta',showConfirmButton:false,timer:1500});
    setTimeout(() => {getAll(1);},2000);
}

export function activar(idMaterial){
    
    let mt = {idMaterial:idMaterial};
    let material = {datosMaterial: JSON.stringify(mt)};
    let parametros = new URLSearchParams(material);
    
    fetch("../api/restoptik/activateMaterial",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => function(data){
                console.log(data);
        if (data.error) {
            Swal.fire({position:'center',icon:'error', title:'Error al activar material',showConfirmButton:false,timer:1500});
        }
    });
    Swal.fire({position:'center',icon:'success', title:'Material activado de forma correcta',showConfirmButton:false,timer:1500});
    setTimeout(() => {getAll(0);},2000);
}

export function buscar(){
    const busqueda = document.getElementById('txtSearch').value;
    const coincidencias = [];
    for (let i = 0; i < materiales.length; i++) {
        const material = materiales[i];
        if(
            material.nombre.toLowerCase().includes(busqueda.toLowerCase())||
            material.precioCompra.toString().toLowerCase().includes(busqueda.toLowerCase())||
            material.precioVenta.toString().toLowerCase().includes(busqueda.toLowerCase())){
        
            coincidencias.push(material);
        }
        cargarTablaMaterial(coincidencias,null);
    }
}

export function getAll(estatus){
    let datos = {estatus:estatus};
    let parametros = new URLSearchParams(datos);
    fetch("../api/restoptik/getAllMaterial",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => { 
        if (data.error) {
            Swal.fire({position:'center',icon:'error', title:'Error al obtener materiales',showConfirmButton:false,timer:1500});
        } else {
            cargarTablaMaterial(null,data);
        }
        JSON.stringify(data);
    });
}

//export function getAllInactivos(){
//    let datos = {estatus:0};
//    let parametros = new URLSearchParams(datos);
//    fetch("../api/restoptik/getAllMaterial",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
//            .then(response => response.json())
//            .then(data => { 
//                //console.log(data);
//        if (data.error) {
//            //alert(data.error);
//        } else {
//            cargarTablaMaterial(null,data);
//        }
//    });
//}

export function cargarTablaMaterial(coincidencias,data){
    if (coincidencias) {
        data = coincidencias;
    } else
    materiales = data;
    let contenido = "";
    
//    for (let i = 0; i < empleados.length; i++) {
    data.forEach((material, index) => {
        const {producto} = material;
        
        contenido += "<tr>";
        contenido += "<td>" + material.idMaterial + "</td>";
        contenido += "<td>" + material.nombre + "</td>";
        contenido += "<td>" + material.precioCompra + "</td>";
        contenido += "<td>" + material.precioVenta + "</td>";
        contenido += "<td><button type='button' class='btn btn-primary btn-m m-2' onClick='ma.cargarForm(" + index + ")'>Ver</button></td>";
        if (material.estatus === 1) {
            contenido += "<td><button type='button' class='btn btn-danger btn-m m-2' onclick='ma.eliminar(" + material.idMaterial + ")'>Eliminar</button></td>";
        }else {
            contenido += "<td><button type='button' class='btn btn-success btn-m m-2' onclick='ma.activar(" + material.idMaterial + ")'>Activar</button></td>";
        }
        contenido += "</tr>";
    });
    document.getElementById("tbMaterial").innerHTML = contenido;
}


export function cargarForm(i){
    document.getElementById("txtidMaterial").value = materiales[i].idMaterial;
    document.getElementById("txtnombre").value = materiales[i].nombre;
    document.getElementById("txtpCompra").value = materiales[i].precioCompra;
    document.getElementById("txtpVenta").value = materiales[i].precioVenta;
}

export function limpiarForm(){
    document.querySelector('form').reset();
}

export function limpiarTablaMaterial(){
    document.getElementById("tbMaterial").innerHTML = "";
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
