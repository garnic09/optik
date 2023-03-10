let clientes = [];

export function inicializar() {
    getAll(1);
}

export function insertar() {
    let nombre = document.getElementById("txtnombre").value;
    let apePaterno = document.getElementById("txtapePaterno").value;
    let apeMaterno = document.getElementById("txtapeMaterno").value;
    let genero = document.getElementById("slcgenero").value;
    let fechaNacimiento = document.getElementById("txtfechaNacimiento").value;
    let calle = document.getElementById("txtcalle").value;
    let numero = document.getElementById("txtnumero").value;
    let colonia = document.getElementById("txtcolonia").value;
    let CP = document.getElementById("txtcp").value;
    let ciudad = document.getElementById("txtciudad").value;
    let estado = document.getElementById("txtestado").value;
    let telCasa = document.getElementById("txttelCasa").value;
    let telMovil = document.getElementById("txttelMovil").value;
    let email = document.getElementById("txtemail").value;

    let persona = {nombre: nombre, apellidoPaterno: apePaterno, apellidoMaterno: apeMaterno, genero: genero, fechaNacimiento: fechaNacimiento, calle: calle, numero: numero, colonia: colonia, cp: CP, ciudad: ciudad, estado: estado, telcasa: telCasa, telmovil: telMovil, email: email};
    let cl = {persona: persona};

    let cliente = {datosCliente: JSON.stringify(cl)};

    let parametros = new URLSearchParams(cliente);

    fetch("http://localhost:8080/optik/api/restoptik/insertCliente",
            {
                method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: parametros
            }).then(response => response.json())
            .then(data => {
                console.log(data);
                if (data.error) {
                    Swal.fire({position: 'center', icon: 'error', title: 'Error al guardar cliente', showConfirmButton: false, timer: 1500});
                    //alert("Error");
                }
            });
    Swal.fire({position: 'center', icon: 'success', title: 'Cliente guardado de forma correcta', showConfirmButton: false, timer: 1500});
    setTimeout(() => {
        getAll(1);
    }, 2000);
    limpiarForm();

}

export function limpiarForm() {
    document.getElementById("txtidCliente").value = "";
    document.getElementById("txtidPersona").value = "";
    document.getElementById("txtnombre").value = "";
    document.getElementById("txtapePaterno").value = "";
    document.getElementById("txtapeMaterno").value = "";
    document.getElementById("slcgenero").value = "D";

    document.getElementById("txtfechaNacimiento").value = "";
    document.getElementById("txtcalle").value = "";
    document.getElementById("txtnumero").value = "";
    document.getElementById("txtcolonia").value = "";
    document.getElementById("txtcp").value = "";
    document.getElementById("txtciudad").value = "";
    document.getElementById("txtestado").value = "";
    document.getElementById("txttelCasa").value = "";
    document.getElementById("txttelMovil").value = "";
    document.getElementById("txtemail").value = "";
}

export function actualizar() {
    let idCliente = document.getElementById("txtidCliente").value;
    let idPersona = document.getElementById("txtidPersona").value;
    let nombre = document.getElementById("txtnombre").value;
    let apePaterno = document.getElementById("txtapePaterno").value;
    let apeMaterno = document.getElementById("txtapeMaterno").value;
    let genero = document.getElementById("slcgenero").value;
    let fechaNacimiento = document.getElementById("txtfechaNacimiento").value;
    let calle = document.getElementById("txtcalle").value;
    let numero = document.getElementById("txtnumero").value;
    let colonia = document.getElementById("txtcolonia").value;
    let CP = document.getElementById("txtcp").value;
    let ciudad = document.getElementById("txtciudad").value;
    let estado = document.getElementById("txtestado").value;
    let telCasa = document.getElementById("txttelCasa").value;
    let telMovil = document.getElementById("txttelMovil").value;
    let email = document.getElementById("txtemail").value;
    let persona = {idPersona: idPersona, nombre: nombre, apellidoPaterno: apePaterno, apellidoMaterno: apeMaterno, genero: genero, fechaNacimiento: fechaNacimiento, calle: calle, numero: numero, colonia: colonia, cp: CP, ciudad: ciudad, estado: estado, telcasa: telCasa, telmovil: telMovil, email: email};
    let cl = {idCliente: idCliente, persona: persona};
    let cliente = {datosCliente: JSON.stringify(cl)};


    let parametros = new URLSearchParams(cliente);
    fetch("http://localhost:8080/optik/api/restoptik/updateCliente", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => function (data) {
                    if (data.error) {
                        Swal.fire({position: 'center', icon: 'error', title: 'Error al actualizar cliente', showConfirmButton: false, timer: 1500});
                    }
                });
    Swal.fire({position: 'center', icon: 'success', title: 'Accesorio actualizado de forma correcta', showConfirmButton: false, timer: 1500});
    setTimeout(() => {
        console.log(clientes[idCliente].estatus);
        getAll(clientes[idCliente].estatus);
    }, 2000);
    limpiarForm();
}

export function getAll(estatus) {
    let datos = {estatus: estatus};
    let parametros = new URLSearchParams(datos); // nuestro json lo convierte en un bloque de parametros, se usa para post

    fetch("http://localhost:8080/optik/api/restoptik/getAllCliente", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    Swal.fire({position: 'center', icon: 'error', title: 'Error al obtener accesorios', showConfirmButton: false, timer: 1500});
                } else {
                    cargarTablaCliente(null, data);
                }
                JSON.stringify(data);
            });
}

export function getAllInactivos() {
    let datos = {estatus: 0};
    let parametros = new URLSearchParams(datos); // nuestro json lo convierte en un bloque de parametros, se usa para post

    fetch("http://localhost:8080/optik/api/restoptik/getAllCliente", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if (data.error) {
                    alert(data.error);
                } else {
                    cargarTablaCliente(null, data);
                }
            });
}


export function cargarTablaCliente(coincidencias, data) {
    if (coincidencias) {
        data = coincidencias;
    } else
        clientes = data;
    let contenido = "";
    data.forEach((cliente, index) => {
        let nc = cliente.persona.nombre + " " + cliente.persona.apellidoPaterno + " " + cliente.persona.apellidoMaterno;
        let dc = cliente.persona.calle + " " + cliente.persona.numero + " " + cliente.persona.colonia + " " + cliente.persona.cp + " " + cliente.persona.ciudad + " " + cliente.persona.estado;


        contenido += "<tr>";
        contenido += "<td>" + cliente.idCliente + "</td>";
        contenido += "<td>" + nc + "</td>";
        contenido += "<td>" + dc + "</td>";
        contenido += "<td>" + cliente.persona.email + "</td>";
        contenido += "<td><button type='button' class ='btn btn-group-lg btn-primary m-3 ' onclick='ma.cargarForm(" + index + ")'>Ver</button></td>";
        if (cliente.estatus === 1) {
            contenido += "<td><button type='button' class='btn btn-group-lg btn-danger m-3' onclick='ma.eliminar(" + cliente.idCliente + ")'>Eliminar</button></td>";
        } else {
            contenido += "<td><button type='button' class='btn btn-group-lg btn-success m-3' onclick='ma.activar(" + cliente.idCliente + ")'>Activar</button></td>";
        }
        contenido += "</tr>";
    });
    document.getElementById("tbCliente").innerHTML = contenido;
}

export function cargarForm(i) {
    document.getElementById("txtnombre").value = clientes[i].persona.nombre;
    document.getElementById("txtapePaterno").value = clientes[i].persona.apellidoPaterno;
    document.getElementById("txtapeMaterno").value = clientes[i].persona.apellidoMaterno;
    if (clientes[i].persona.genero === 'F') {
        document.querySelector('#slcgenero').value = 'F';
    } else if (clientes[i].persona.genero === 'M') {
        document.querySelector('#slcgenero').value = 'M';
    } else {
        document.querySelector('#slcgenero').value = 'O';
    }
    document.getElementById("txtidCliente").value = clientes[i].idCliente;
    document.getElementById("txtidPersona").value = clientes[i].persona.idPersona;
    document.getElementById("txtfechaNacimiento").value = clientes[i].persona.fechaNacimiento;
    document.getElementById("txtcalle").value = clientes[i].persona.calle;
    document.getElementById("txtnumero").value = clientes[i].persona.numero;
    document.getElementById("txtcolonia").value = clientes[i].persona.colonia;
    document.getElementById("txtcp").value = clientes[i].persona.cp;
    document.getElementById("txtciudad").value = clientes[i].persona.ciudad;
    document.getElementById("txtestado").value = clientes[i].persona.estado;
    document.getElementById("txttelCasa").value = clientes[i].persona.telcasa;
    document.getElementById("txttelMovil").value = clientes[i].persona.telmovil;
    document.getElementById("txtemail").value = clientes[i].persona.email;
}
export function limpiarTablaCliente() {
    document.querySelector('form').reset();
}

export function Rbusqueda() {
    const busqueda = document.getElementById("myInput").value;
    const coincidencias = [];
    for (let i = 0; i < clientes.length; i++) {
        const cliente = clientes[i];

        if (cliente.persona.nombre.toLowerCase().includes(busqueda.toLowerCase()) ||
                cliente.persona.apellidoPaterno.toLowerCase().includes(busqueda.toLowerCase()) ||
                cliente.persona.apellidoMaterno.toLowerCase().includes(busqueda.toLowerCase()) ||
                cliente.persona.telCasa.includes(busqueda) ||
                cliente.persona.telMovil.includes(busqueda) ||
                cliente.persona.calle.toLowerCase().includes(busqueda.toLowerCase()) ||
                cliente.persona.colonia.toLowerCase().includes(busqueda.toLowerCase()) ||
                cliente.persona.ciudad.toLowerCase().includes(busqueda.toLowerCase()) ||
                cliente.persona.email.toLowerCase().includes(busqueda.toLowerCase()) ||
                cliente.persona.genero.toLowerCase().includes(busqueda.toLowerCase())
                ) {
            coincidencias.push(cliente);
        }
    }
    cargarTablaCliente(coincidencias, null);
}

export function eliminar(idCliente) {
    let c = {idCliente: idCliente};

    let cliente = {datosCliente: JSON.stringify(c)};
    let parametros = new URLSearchParams(cliente);
    fetch("http://localhost:8080/optik/api/restoptik/eliminarCliente", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => function (data) {
        if (data.error) {
            Swal.fire({position:'center',icon:'error', title:'Error al eliminar cliente',showConfirmButton:false,timer:1500});
        }
    });
    Swal.fire({position:'center',icon:'success', title:'Cliente eliminado de forma correcta',showConfirmButton:false,timer:1500});
    setTimeout(() => {getAll(1);},2000);
}

export function activar(idCliente) {
    let c = {idCliente: idCliente};

    let cliente = {datosCliente: JSON.stringify(c)};
    let parametros = new URLSearchParams(cliente);
    fetch("http://localhost:8080/optik/api/restoptik/activarCliente", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => function (data) {
if (data.error) {
            Swal.fire({position:'center',icon:'error', title:'Error al activar cliente',showConfirmButton:false,timer:1500});
        }
    });
    Swal.fire({position:'center',icon:'success', title:'Cliente activado de forma correcta',showConfirmButton:false,timer:1500});
    setTimeout(() => {getAll(0);},2000);
}