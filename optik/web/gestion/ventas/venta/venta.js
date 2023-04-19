let productos = [];
let productosT = [];
let indexTV = 0;
var suma = 0;

export function inicializar() {
    getAll(1);
}

export function buscar() {

    const busqueda = document.getElementById('txtSearch').value;
    const coincidencias = [];
    for (let i = 0; i < productos.length; i++) {
        const producto = productos[i];
        if (
                producto.nombre.toLowerCase().includes(busqueda.toLowerCase()) ||
                producto.codigoBarras.toLowerCase().includes(busqueda.toLowerCase())) {

            coincidencias.push(producto);
        }
        cargarTablaProducto(coincidencias, null);
    }
}

export function getAll(estatus) {
    let datos = {estatus: estatus};
    let parametros = new URLSearchParams(datos);
    fetch("../api/restoptik/getAllProducto", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    Swal.fire({position: 'center', icon: 'error', title: 'Error al obtener productos', showConfirmButton: false, timer: 1500});
                } else {
                    cargarTablaProducto(null, data);
                }
                JSON.stringify(data);
            });
}

export function search() {
    let txtSearch = document.getElementById('txtSearch').value;
    let datos = {search: txtSearch, estatus: 1};
    let parametros = new URLSearchParams(datos);
    fetch("../api/restoptik/searchProducto", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    Swal.fire({position: 'center', icon: 'error', title: 'Error al obtener productos', showConfirmButton: false, timer: 1500});
                } else {
                    cargarTablaProducto(null, data);
                }
                JSON.stringify(data);
                //cargarTablaProducto(null,data);
            });
}

export function cargarTablaProducto(coincidencias, data) {
    if (coincidencias) {
        data = coincidencias;
    } else
        productos = data;
    let contenido = "";

//    for (let i = 0; i < empleados.length; i++) {
    data.forEach((producto, index) => {

        contenido += "<tr>";
        contenido += "<td>" + producto.codigoBarras + "</td>";
        contenido += "<td>" + producto.nombre + "</td>";
        contenido += "<td>" + producto.precioVenta + "</td>";
        contenido += "<td><button type='button' class='btn btn-primary btn-m m-2' onClick='ma.agregarProducto(" + index + ")'>Agregar</button></td>";
        contenido += "</tr>";
    });
    document.getElementById("tbProducto").innerHTML = contenido;
}

let contenido = "";
export function agregarProducto(indice) {
    productosT.push(productos[indice]);
    contenido += "<tr>" + "<td>" + productos[indice].codigoBarras + "</td>"
            + "<td>" + productos[indice].nombre + "</td>"
            + "<td>" + productos[indice].precioVenta + "</td>"
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
        const precioVenta = Number(tr.children[2].innerHTML);
        const cantidad = Number(document.getElementById(`txtCantidad${indexTV}`).value);
        const descuento = Number(document.querySelector(`#txtDescuento${indexTV}`).value);

        if (
                !validarNumero(cantidad, 'Cantidad no valida') ||
                !validarCantidad(cantidad, productos[index].existencias) ||
                !validarNumero(descuento, 'Descuento no valido')
                ) {
            total = totalAnterior;
            return;
        }

        const preDescuento = descuento / 100;
        total += precioVenta * cantidad * (1 - preDescuento);
    });

    document.getElementById('total').value = total;
    console.log(productosT);
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

let detalleVenta = {
    venta:{clave:0, empleado:null},
    listaVP:{}
};
export function realizarVenta(){
    detalleVenta.venta.clave = 'OQV-' + Math.floor(Math.random() * 1000000000000);
    detalleVenta.venta.empleado = JSON.parse(localStorage.getItem('currentUser'));
    
    const productos2 = Array.from(document.querySelectorAll('#tbProductoA tr'));
    let listaVentaP =[];
    productos2.forEach((tr, indexTV) => {
        
        listaVentaP.push({producto:productosT[indexTV], 
                        cantidad:Number(document.getElementById(`txtCantidad${indexTV}`).value),
                        precioUnitario:Number(tr.children[2].innerHTML),
                        descuento:Number(document.getElementById(`txtDescuento${indexTV}`).value)
                                });
    });
    detalleVenta.listaVP = listaVentaP;
    const detalleVentaP = {detalleVP: JSON.stringify(detalleVenta)};
    
    const url = new URLSearchParams(detalleVentaP);
    fetch('../api/restoptik/dvp',
            {
                method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: url
            })
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    alert(data.error);
                } else if(data.result){
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