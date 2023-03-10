
let soluciones = [];

export function inicializar() {
	getAll();
}

export function insertar(){
    let nombre = document.getElementById("txtnombre").value;
    let marca = document.getElementById("txtmarca").value;
    let precioCompra = document.getElementById("txtpCompra").value;
    let precioVenta = document.getElementById("txtpVenta").value;
    let existencias = document.getElementById("txtexistencias").value;
    
    let producto = {nombre:nombre,marca:marca,precioCompra:precioCompra,precioVenta:precioVenta,existencias:existencias};
    let solucion = {producto:producto};
    
    let solucion1 = {datosSolucion:JSON.stringify(solucion)};
    alert(JSON.stringify(solucion));
    
    let parametros = new URLSearchParams(solucion1);
    
    fetch("http://localhost:8080/optik/api/restoptik/insertarSoluciones",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => function(data){
                console.log(data);
        if (data.error) {
            alert("Error");
        } else if (data.idSolucion) {
            alert("Solucion agregada correctamente con id: " + data.idSolucion);
        } else {
            JSON.stringify(data);
        }
        JSON.stringify(data);
    });
}

export function actualizar(){
    let idSolucion = document.getElementById("txtidSolucion").value;
    let idProducto = document.getElementById("txtidProducto").value;
    let nombre = document.getElementById("txtnombre").value;
    let marca = document.getElementById("txtmarca").value;
    let precioCompra = document.getElementById("txtpCompra").value;
    let precioVenta = document.getElementById("txtpVenta").value;
    let existencias = document.getElementById("txtexistencias").value;
    
    let producto = {idProducto:idProducto,nombre:nombre,marca:marca,precioCompra:precioCompra,precioVenta:precioVenta,existencias:existencias};
    let solucion = {idSolucion:idSolucion,producto:producto};
    
    let solucion1 = {datosSolucion:JSON.stringify(solucion)};
    alert(JSON.stringify(solucion));
    
    let parametros = new URLSearchParams(solucion1);
    
    fetch("http://localhost:8080/optik/api/restoptik/actualizarSolucion",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => function(data){
                console.log(data);
        if (data.error) {
            alert(data.error);
        } else if (data.idSolucion) {
            alert("Solucion realizada de forma correcta con id: " + data.idSolucion);
        } else {
            JSON.stringify(data);
        }
        
    });
    setTimeout(() => {getAll();},2000);
    getAll();
    limpiarForm();
}

export function eliminar(idSolucion){
    
    //let idEmpleado = document.getElementById("txtidEmpleado").value;
    let solucion = {idSolucion:idSolucion};
    let solucion1 = {datosSolucion: JSON.stringify(solucion)};
    let parametros = new URLSearchParams(solucion1);
    
    fetch("http://localhost:8080/optik/api/restoptik/borrarSolucion",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => function(data){
                console.log(data);
        if (data.error) {
            alert(data.error);
        } else if (data.idSolucion) {
            alert("Solucion eliminada correctamente con id: " + data.idSolucion);
        } else {
            JSON.stringify(data);
        }
    });
}

export function activar(idSolucion){
    
    //let idEmpleado = document.getElementById("txtidEmpleado").value;
    let solucion = {idSolucion:idSolucion};
    let solucion1 = {datosSolucion: JSON.stringify(solucion)};
    let parametros = new URLSearchParams(solucion1);
    
    fetch("http://localhost:8080/optik/api/restoptik/activarSolucion",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => function(data){
                console.log(data);
        if (data.error) {
            alert(data.error);
        } else if (data.idAccesorio) {
            alert("Solucion activada correctamente con id: " + data.idSolucion);
        } else {
            JSON.stringify(data);
        }
    });
}

export function buscar(){
    const busqueda = document.getElementById('txtSearch').value;
    const coincidencias = [];
    for (let i = 0; i < soluciones.length; i++) {
        const solucion = soluciones[i];
        if(
            solucion.producto.nombre.toLowerCase().includes(busqueda.toLowerCase())||
            solucion.producto.marca.toLowerCase().includes(busqueda.toLowerCase())||
            solucion.producto.precioCompra.toString().toLowerCase().includes(busqueda.toLowerCase())||
            solucion.producto.precioVenta.toString().toLowerCase().includes(busqueda.toLowerCase())||
            solucion.producto.existencias.toString().toLowerCase().includes(busqueda.toLowerCase())||
            solucion.producto.codigoBarras.toLowerCase().includes(busqueda.toLowerCase())){
        
            coincidencias.push(solucion);
        }
        console.log(coincidencias);
        cargarTablaSoluciones(coincidencias,null);
    }
}

export function getAll(){
    let datos = {estatus:1};
    let parametros = new URLSearchParams(datos);
    fetch("http://localhost:8080/optik/api/restoptik/getAllSolucion",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => { 
                console.log(data);
        if (data.error) {
            alert(data.error);
        } else {
            cargarTablaSoluciones(null,data);
        }
        JSON.stringify(data);
    });
}

export function getAllInactivos(){
    let datos = {estatus:0};
    let parametros = new URLSearchParams(datos);
    fetch("http://localhost:8080/optik/api/restoptik/getAllSolucion",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => { 
                console.log(data);
        if (data.error) {
            alert(data.error);
        } else {
            cargarTablaSoluciones(null,data);
        }
        JSON.stringify(data);
    });
}

export function cargarTablaSoluciones(coincidencias,data){
    if (coincidencias) {
        data = coincidencias;
    } else
    soluciones = data;
    let contenido = "";
    
//    for (let i = 0; i < empleados.length; i++) {
    data.forEach((solucion, index) => {
        const {producto} = solucion;
        
        contenido += "<tr>";
        contenido += "<td>" + solucion.idSolucion + "</td>";
        contenido += "<td>" + solucion.producto.codigoBarras + "</td>";
        contenido += "<td>" + solucion.producto.nombre + "</td>";
        contenido += "<td>" + solucion.producto.marca + "</td>";
        contenido += "<td>" + solucion.producto.precioCompra + "</td>";
        contenido += "<td>" + solucion.producto.precioVenta + "</td>";
        contenido += "<td>" + solucion.producto.existencias + "</td>";
        contenido += "<td><button type='button' class='btn btn-group-lg btn-primary m-3' onClick='ma.cargarForm(" + index + ")'>Ver</button></td>";
        if (solucion.producto.estatus === 1) {
            contenido += "<td><button type='button' class='btn btn-group-lg btn-primary m-3' onclick='ma.eliminar(" + solucion.idSolucion + ")'>Eliminar</button></td>";
        }else {
            contenido += "<td><button type='button' class='btn btn-group-lg btn-primary m-3' onclick='ma.activar(" + solucion.idSolucion + ")'>Activar</button></td>";
        }
        contenido += "</tr>";
    });
    document.getElementById("tbSoluciones").innerHTML = contenido;
}


export function cargarForm(i){
    document.getElementById("txtidSolucion").value = soluciones[i].idSolucion;
    document.getElementById("txtidProducto").value = soluciones[i].producto.idProducto;
    document.getElementById("txtnombre").value = soluciones[i].producto.nombre;
    document.getElementById("txtmarca").value = soluciones[i].producto.marca;
    document.getElementById("txtcodigoBarras").value = soluciones[i].producto.codigoBarras;
    document.getElementById("txtpCompra").value = soluciones[i].producto.precioCompra;
    document.getElementById("txtpVenta").value = soluciones[i].producto.precioVenta;
    document.getElementById("txtexistencias").value = soluciones[i].producto.existencias;
}

export function limpiarForm(){
    document.getElementById("txtidSolucion").value = "";
    document.getElementById("txtidProducto").value = "";
    document.getElementById("txtcodigoBarras").value = "";
    document.getElementById("txtnombre").value = "";
    document.getElementById("txtmarca").value = "";
    document.getElementById("txtpCompra").value = "";
    document.getElementById("txtpVenta").value = "";
    document.getElementById("txtexistencias").value = "";
}

export function limpiarTablaSoluciones(){
    document.getElementById("tbSoluciones").innerHTML = "";
}

