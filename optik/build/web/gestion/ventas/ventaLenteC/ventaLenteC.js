/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

let clientes = [];
let cliente;
let lentesC;
let lentesCT = [];
let examenes;
let indexTV = 0;
var suma = 0;
let pos = 0;

export function inicializar() {
    getAll(1);
    getAllLente();
}

export function getAll(estatus) {
    let datos = {estatus: estatus};
    let parametros = new URLSearchParams(datos);
    fetch("../api/restoptik/getAllCliente", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    Swal.fire({position: 'center', icon: 'error', title: 'Error al obtener productos', showConfirmButton: false, timer: 1500});
                } else {
                    cargarTablaCliente(null, data);
                }
                JSON.stringify(data);
            });
}

export function search() {
    let txtSearch = document.getElementById('txtSearch').value;
    let datos = {search: txtSearch, estatus: 1};
    let parametros = new URLSearchParams(datos);
    fetch("../api/restoptik/searchCliente", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    Swal.fire({position: 'center', icon: 'error', title: 'Error al obtener clientes', showConfirmButton: false, timer: 1500});
                } else {
                    cargarTablaCliente(null, data);
                }
                JSON.stringify(data);
                //cargarTablaProducto(null,data);
            });
}

export function cargarTablaCliente(coincidencias, data) {
    if (coincidencias) {
        data = coincidencias;
    } else
        clientes = data;
    let contenido = "";

//    for (let i = 0; i < empleados.length; i++) {
    data.forEach((cliente, index) => {
        let nc = cliente.persona.nombre + " " + cliente.persona.apellidoPaterno + " " + cliente.persona.apellidoMaterno;
        let dc = cliente.persona.calle + " " + cliente.persona.numero + " " + cliente.persona.colonia + " " + cliente.persona.cp + " " + cliente.persona.ciudad + " " + cliente.persona.estado;

        contenido += "<tr>";
        contenido += "<td>" + nc + "</td>";
        contenido += "<td>" + dc + "</td>";
        contenido += "<td>" + cliente.persona.email + "</td>";
        contenido += "<td><button type='button' class='btn btn-outline-success btn-m m-2' onClick='ma.seleccionarCliente(" + index + ")'>+</button></td>";
        contenido += "</tr>";
    });
    document.getElementById("tbCliente").innerHTML = contenido;
}

let contenido = "";
export function seleccionarCliente(indice) {
    cliente = clientes[indice];
    document.getElementById('txtCliente').value = cliente.persona.nombre + " " + cliente.persona.apellidoPaterno + " " + cliente.persona.apellidoMaterno;
    getExamen(cliente.idCliente);
}

export function getExamen(id) {
    let datos = {id: id};
    let parametros = new URLSearchParams(datos);
    fetch("../api/restoptik/getAllExamenVista", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    Swal.fire({position: 'center', icon: 'error', title: 'Error al obtener productos', showConfirmButton: false, timer: 1500});
                } else {
                    console.log(JSON.stringify(data));
                    examenes = data;
                    const selectExamen = document.getElementById("slcExamenVista");
                    examenes.forEach(examenes => {
                        const option = document.createElement("option");
                        option.value = JSON.stringify(examenes);
                        option.textContent = examenes.fecha;
                        selectExamen.appendChild(option);
                    });
                }
                JSON.stringify(data);
            });
}

export function getAllLente() {
    let datos = {estatus: 1};
    let parametros = new URLSearchParams(datos);
    fetch("../api/restoptik/getAllLenteC", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    Swal.fire({position: 'center', icon: 'error', title: 'Error al obtener productos', showConfirmButton: false, timer: 1500});
                } else {
                    
                    lentesC = data;
                    const selectLenteC = document.getElementById("slcLenteContacto");
                    lentesC.forEach(lentesC => {
                        const option = document.createElement("option");
                        option.value = pos;
                        option.textContent = lentesC.producto.nombre;
                        option.id = "opc"+pos;
                        selectLenteC.appendChild(option);
                        pos ++;
                    });
                }
                JSON.stringify(data);
            });
}

export function agregarLenteC() {
    let indice = document.getElementById("slcLenteContacto").value;
    document.getElementById("opc"+indice).disabled = true;
    lentesCT.push(lentesC[indice]);
    contenido += "<tr>"
            + "<td>" + lentesC[indice].producto.precioVenta + "</td>"
            + `<td> <input type='text' value='1' id='txtCantidad${indexTV}' onchange="ma.calcularTotal(${indice})"> </td>`
            + `<td> <input type='text' value='0' id='txtDescuento${indexTV}' onchange="ma.calcularTotal(${indice})"/> </td>`
            + "</tr>",
            document.getElementById('tbProductoA').innerHTML = contenido;
    indexTV++;
    calcularTotal(indice);
}

export const calcularTotal = index => {
    const productos2 = Array.from(document.querySelectorAll('#tbProductoA tr'));
    const totalAnterior = Number(document.querySelector('#total').innerHTML);

    let total = 0;

    productos2.forEach((tr, indexTV) => {
        const precioVenta = Number(tr.children[0].innerHTML);
        const cantidad = Number(document.getElementById(`txtCantidad${indexTV}`).value);
        const descuento = Number(document.querySelector(`#txtDescuento${indexTV}`).value);

        if (
                !validarNumero(cantidad, 'Cantidad no valida') ||
                !validarCantidad(cantidad, lentesC[index].producto.existencias) ||
                !validarNumero(descuento, 'Descuento no valido')
                ) {
            total = totalAnterior;
            return;
        }

        const preDescuento = descuento / 100;
        total += precioVenta * cantidad * (1 - preDescuento);
    });
    document.getElementById('total').value = total;
    console.log(lentesCT);
};

const validarNumero = (valor, mensaje) => {
    if (isNaN(valor) || valor < 0) {
        mostrarAlerta('error', mensaje);
        return false;
    }
    return true;
};

function mostrarAlerta(icon, mensaje) {
    const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: true,
        timer: 3000,
        timerProgressBar: true
    });

    Toast.fire({
        icon: icon,
        title: mensaje
    });
}

// Función para validar la cantidad de productos
const validarCantidad = (cantidad, existencias) => {
    if (cantidad > existencias) {
        mostrarAlerta('error', 'Cantidad no valida en inventario');
        return false;
    }
    return true;
};

//let detalleVenta = {
//    venta: {clave: 0, empleado: null},
//    listaVPLC: {}
//};
export function realizarVenta() {
    let listaVLC = [];
//    
//    detalleVenta.venta.clave = 'OQV-' + Math.floor(Math.random() * 1000000000000);
//    detalleVenta.venta.empleado = JSON.parse(localStorage.getItem('currentUser'));
    let venta = {clave: 'OQV-' + Math.floor(Math.random() * 1000000000000), empleado: JSON.parse(localStorage.getItem('currentUser'))};
    
    let presupuesto = {examenVista:JSON.parse(document.getElementById('slcExamenVista').value), clave:'OQP-' + Math.floor(Math.random() * 1000000000000)};
//    let presupuestoVLC = {lenteContacto:lentesC, clave:'OQVP-' + Math.floor(Math.random() * 1000000000000), presupuesto: presupuesto};
    
    const productos2 = Array.from(document.querySelectorAll('#tbProductoA tr'));
    productos2.forEach((tr, indexTV) => {

        listaVLC.push({
            presupuestoVentaLC: {lenteContacto:lentesCT[indexTV], 
                                clave:'OQVP-' + Math.floor(Math.random() * 1000000000000), 
                                presupuesto: presupuesto},
            cantidad: Number(document.getElementById(`txtCantidad${indexTV}`).value),
            precioUnitario: Number(tr.children[0].innerHTML),
            descuento: Number(document.getElementById(`txtDescuento${indexTV}`).value)
        });
    });
    //console.log(listaVLC);
    const detalleVentaP = {detalleVLC: JSON.stringify({venta:venta, listVentaPresupuestoLC:listaVLC})};
    console.log(detalleVentaP);
    
    const url = new URLSearchParams(detalleVentaP);
    fetch('../api/restoptik/dvlc',
            {
                method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: url
            })
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    alert(data.error);
                } else if (data.result) {
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Venta exitosa',
                        showConfirmButton: false,
                        timer: 1500
                    });
                } else {
                    JSON.stringify(data);
                }
            });
}