let empleados = [];
export function inicializar() {
    getAll();
}

export function insertar() {
    if (validar() == false) {
        return;
    }
    let nombre = document.getElementById("txtnombre").value;
    let apePaterno = document.getElementById("txtapePaterno").value;
    let apeMaterno = document.getElementById("txtapeMaterno").value;
    let genero = document.getElementById("slcgenero").value;
    let fechaNacimiento = document.getElementById("txtfechaNacimiento").value;
    let calle = document.getElementById("txtcalle").value;
    let numero = document.getElementById("txtnumero").value;
    let colonia = document.getElementById("txtcolonia").value;
    let cp = document.getElementById("txtcp").value;
    let ciudad = document.getElementById("txtciudad").value;
    let estado = document.getElementById("txtestado").value;
    let telCasa = document.getElementById("txttelCasa").value;
    let telMovil = document.getElementById("txttelMovil").value;
    let email = document.getElementById("txtemail").value;
    let nombreUsuario = document.getElementById("txtnombreUsuario").value;
    let contrasenia = document.getElementById("txtcontrasenia").value;
    let rol = document.getElementById("txtrol").value;
    let usuario = {nombre: nombreUsuario, contrasenia: contrasenia, rol: rol};
    let persona = {nombre: nombre, apellidoPaterno: apePaterno, apellidoMaterno: apeMaterno, genero: genero, fechaNacimiento: fechaNacimiento, calle: calle, numero: numero, colonia: colonia, cp: cp, ciudad: ciudad, estado: estado, telcasa: telCasa, telmovil: telMovil, email: email};
    let em = {usuario: usuario, persona: persona};
    let empleado = {datosEmpleado: JSON.stringify(em)};
    alert(JSON.stringify(em));
    let parametros = new URLSearchParams(empleado);
    fetch("http://localhost:8080/optik/api/restoptik/insertEmpleado", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => function (data) {
                    console.log(data);
                    if (data.error) {
                        alert("Error");
                    } else if (data.idUsuario) {
                        alert("Empleado almacenado de forma correcta con id: " + data.idUsuario);
                    } else {
                        JSON.stringify(data);
                    }
                    JSON.stringify(data);
                });
}

export function actualizar() {
    let idEmpleado = document.getElementById("txtidEmpleado").value;
    let idPersona = document.getElementById("txtidPersona").value;
    let idUsuario = document.getElementById("txtidUsuario").value;
    let nombre = document.getElementById("txtnombre").value;
    let apePaterno = document.getElementById("txtapePaterno").value;
    let apeMaterno = document.getElementById("txtapeMaterno").value;
    let genero = document.getElementById("slcgenero").value;
    let fechaNacimiento = document.getElementById("txtfechaNacimiento").value;
    let calle = document.getElementById("txtcalle").value;
    let numero = document.getElementById("txtnumero").value;
    let colonia = document.getElementById("txtcolonia").value;
    let cp = document.getElementById("txtcp").value;
    let ciudad = document.getElementById("txtciudad").value;
    let estado = document.getElementById("txtestado").value;
    let telCasa = document.getElementById("txttelCasa").value;
    let telMovil = document.getElementById("txttelMovil").value;
    let email = document.getElementById("txtemail").value;
    let nombreUsuario = document.getElementById("txtnombreUsuario").value;
    let contrasenia = document.getElementById("txtcontrasenia").value;
    let rol = document.getElementById("txtrol").value;
    let usuario = {idUsuario: idUsuario, nombre: nombreUsuario, contrasenia: contrasenia, rol: rol};
    let persona = {idPersona: idPersona, nombre: nombre, apellidoPaterno: apePaterno, apellidoMaterno: apeMaterno, genero: genero, fechaNacimiento: fechaNacimiento, calle: calle, numero: numero, colonia: colonia, cp: cp, ciudad: ciudad, estado: estado, telcasa: telCasa, telmovil: telMovil, email: email};
    let em = {idEmpleado: idEmpleado, usuario: usuario, persona: persona};
    let empleado = {datosEmpleado: JSON.stringify(em)};
    alert(JSON.stringify(em));
    let parametros = new URLSearchParams(empleado);
    fetch("http://localhost:8080/optik/api/restoptik/updateEmpleado", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => function (data) {
                    console.log(data);
                    if (data.error) {
                        alert(data.error);
                    } else if (data.idUsuario) {
                        alert("Empleado actualizado de forma correcta con id: " + data.idUsuario);
                    } else {
                        JSON.stringify(data);
                    }

                });
    setTimeout(() => {
        getAll();
    }, 2000);
    getAll();
    limpiarForm();
}

export function eliminar(idEmpleado) {

//let idEmpleado = document.getElementById("txtidEmpleado").value;
    let em = {idEmpleado: idEmpleado};
    let empleado = {datosEmpleado: JSON.stringify(em)};
    let parametros = new URLSearchParams(empleado);
    fetch("http://localhost:8080/optik/api/restoptik/deleteEmpleado", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => function (data) {
                    console.log(data);
                    if (data.error) {
                        alert(data.error);
                    } else if (data.idUsuario) {
                        alert("Empleado eliminado de forma correcta con id: " + data.idUsuario);
                    } else {
                        JSON.stringify(data);
                    }
                });
}

export function activar(idEmpleado) {
//let idEmpleado = document.getElementById("txtidEmpleado").value;
    let em = {idEmpleado: idEmpleado};
    let empleado = {datosEmpleado: JSON.stringify(em)};
    let parametros = new URLSearchParams(empleado);
    fetch("http://localhost:8080/optik/api/restoptik/activateEmpleado", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => function (data) {
                    console.log(data);
                    if (data.error) {
                        alert(data.error);
                    } else if (data.idUsuario) {
                        alert("Empleado activado de forma correcta con id: " + data.idUsuario);
                    } else {
                        JSON.stringify(data);
                    }
                });
}

export function buscar() {
    const busqueda = document.getElementById('txtSearch').value;
    const coincidencias = [];
    for (let i = 0; i < empleados.length; i++) {
        const empleado = empleados[i];
        if (
                empleado.persona.nombre.toLowerCase().includes(busqueda.toLowerCase()) ||
                empleado.persona.apellidoPaterno.toLowerCase().includes(busqueda.toLowerCase()) ||
                empleado.persona.apellidoMaterno.toLowerCase().includes(busqueda.toLowerCase()) ||
                empleado.persona.telcasa.toLowerCase().includes(busqueda.toLowerCase()) ||
                empleado.persona.telmovil.toLowerCase().includes(busqueda.toLowerCase()) ||
                empleado.persona.calle.toLowerCase().includes(busqueda.toLowerCase()) ||
                empleado.persona.numero.toLowerCase().includes(busqueda.toLowerCase()) ||
                empleado.persona.colonia.toLowerCase().includes(busqueda.toLowerCase()) ||
                empleado.persona.cp.toLowerCase().includes(busqueda.toLowerCase()) ||
                empleado.persona.ciudad.toLowerCase().includes(busqueda.toLowerCase()) ||
                empleado.persona.email.toLowerCase().includes(busqueda.toLowerCase()) ||
                empleado.usuario.nombre.toLowerCase().includes(busqueda.toLowerCase()) ||
                empleado.persona.genero.toLowerCase().includes(busqueda.toLowerCase()) ||
                empleado.persona.fechaNacimiento.toLowerCase().includes(busqueda.toLowerCase()) ||
                empleado.usuario.rol.toLowerCase().includes(busqueda.toLowerCase())) {

            coincidencias.push(empleado);
        }
        console.log(coincidencias);
        cargarTablaEmpleado(coincidencias, null);
    }
}

export function getAll() {
    let datos = {estatus: 1};
    let parametros = new URLSearchParams(datos);
    fetch("http://localhost:8080/optik/api/restoptik/getAllEmpleado", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if (data.error) {
                    alert(data.error);
                } else {
                    cargarTablaEmpleado(null, data);
                }
                JSON.stringify(data);
            });
}

export function getAllInactivos() {
    let datos = {estatus: 0};
    let parametros = new URLSearchParams(datos);
    fetch("http://localhost:8080/optik/api/restoptik/getAllEmpleado", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if (data.error) {
                    alert(data.error);
                } else {
                    cargarTablaEmpleado(null, data);
                }
                JSON.stringify(data);
            });
}

export function cargarTablaEmpleado(coincidencias, data) {
    if (coincidencias) {
        data = coincidencias;
    } else
        empleados = data;
    let contenido = "";
//    for (let i = 0; i < empleados.length; i++) {
    data.forEach((empleado, index) => {
        const {persona, usuario} = empleado;
        let nc = empleado.persona.nombre + " " + empleado.persona.apellidoPaterno + " " + empleado.persona.apellidoMaterno;
        let dc = empleado.persona.calle + " " + empleado.persona.numero + ", " + empleado.persona.colonia + ". " + empleado.persona.cp + ". " + empleado.persona.ciudad + ", " + empleado.persona.estado;
        contenido += "<tr>";
        contenido += "<td>" + empleado.idEmpleado + "</td>";
        contenido += "<td>" + nc + "</td>";
        contenido += "<td>" + dc + "</td>";
        contenido += "<td>" + empleado.usuario.nombre + "</td>";
        contenido += "<td>" + empleado.persona.email + "</td>";
        contenido += "<td><button type='button' class='btn btn-primary btn-m m-2' onClick='ma.cargarForm(" + index + ")'>Ver</button></td>";
        if (empleado.estatus === 1) {
            contenido += "<td><button type='button' class='btn btn-danger btn-m m-2' onclick='ma.eliminar(" + empleado.idEmpleado + ")'>Eliminar</button></td>";
        } else {
            contenido += "<td><button type='button' class='btn btn-sucess btn-m m-2' onclick='ma.activar(" + empleado.idEmpleado + ")'>Activar</button></td>";
        }
        contenido += "</tr>";
    });
    document.getElementById("tbEmpleado").innerHTML = contenido;
}


export function cargarForm(i) {
    document.getElementById("txtidEmpleado").value = empleados[i].idEmpleado;
    document.getElementById("txtidPersona").value = empleados[i].persona.idPersona;
    document.getElementById("txtidUsuario").value = empleados[i].usuario.idUsuario;
    document.getElementById("txtnombre").value = empleados[i].persona.nombre;
    document.getElementById("txtapePaterno").value = empleados[i].persona.apellidoPaterno;
    document.getElementById("txtapeMaterno").value = empleados[i].persona.apellidoMaterno;
    if (empleados[i].persona.genero === "F")
        document.querySelector('#slcgenero').value = 'F';
    else if (empleados[i].persona.genero === "M")
        document.querySelector('#slcgenero').value = 'M';
    else
        document.querySelector('#slcgenero').value = 'O';
    document.getElementById("txtfechaNacimiento").value = empleados[i].persona.fechaNacimiento;
    document.getElementById("txtcalle").value = empleados[i].persona.calle;
    document.getElementById("txtnumero").value = empleados[i].persona.numero;
    document.getElementById("txtcolonia").value = empleados[i].persona.colonia;
    document.getElementById("txtcp").value = empleados[i].persona.cp;
    document.getElementById("txtciudad").value = empleados[i].persona.ciudad;
    if (empleados[i].persona.estado === "Aguascalientes")
        document.querySelector('#txtestado').value = 'Aguascalientes';
    else if (empleados[i].persona.estado === "Baja California")
        document.querySelector('#txtestado').value = 'Baja California';
    else if (empleados[i].persona.estado === "Baja California Sur")
        document.querySelector('#txtestado').value = 'Baja California Sur';
    else if (empleados[i].persona.estado === "Campeche")
        document.querySelector('#txtestado').value = 'Campeche';
    else if (empleados[i].persona.estado === "Chiapas")
        document.querySelector('#txtestado').value = 'Chiapas';
    else if (empleados[i].persona.estado === "Chihuahua")
        document.querySelector('#txtestado').value = 'Chihuahua';
    else if (empleados[i].persona.estado === "CDMX")
        document.querySelector('#txtestado').value = 'CDMX';
    else if (empleados[i].persona.estado === "Coahuila")
        document.querySelector('#txtestado').value = 'Coahuila';
    else if (empleados[i].persona.estado === "Colima")
        document.querySelector('#txtestado').value = 'Colima';
    else if (empleados[i].persona.estado === "Durango")
        document.querySelector('#txtestado').value = 'Durango';
    else if (empleados[i].persona.estado === "Estado de México")
        document.querySelector('#txtestado').value = 'Estado de México';
    else if (empleados[i].persona.estado === "Guanajuato")
        document.querySelector('#txtestado').value = 'Guanajuato';
    else if (empleados[i].persona.estado === "Guerrero")
        document.querySelector('#txtestado').value = 'Guerrero';
    else if (empleados[i].persona.estado === "Hidalgo")
        document.querySelector('#txtestado').value = 'Hidalgo';
    else if (empleados[i].persona.estado === "Jalisco")
        document.querySelector('#txtestado').value = 'Jalisco';
    else if (empleados[i].persona.estado === "Michoacán")
        document.querySelector('#txtestado').value = 'Michoacán';
    else if (empleados[i].persona.estado === "Morelos")
        document.querySelector('#txtestado').value = 'Morelos';
    else if (empleados[i].persona.estado === "Nayarit")
        document.querySelector('#txtestado').value = 'Nayarit';
    else if (empleados[i].persona.estado === "Nuevo León")
        document.querySelector('#txtestado').value = 'Nuevo León';
    else if (empleados[i].persona.estado === "Oaxaca")
        document.querySelector('#txtestado').value = 'Oaxaca';
    else if (empleados[i].persona.estado === "Puebla")
        document.querySelector('#txtestado').value = 'Puebla';
    else if (empleados[i].persona.estado === "Querétaro")
        document.querySelector('#txtestado').value = 'Querétaro';
    else if (empleados[i].persona.estado === "Quintana Roo")
        document.querySelector('#txtestado').value = 'Quintana Roo';
    else if (empleados[i].persona.estado === "San Luis Potosí")
        document.querySelector('#txtestado').value = 'San Luis Potosí';
    else if (empleados[i].persona.estado === "Sinaloa")
        document.querySelector('#txtestado').value = 'Sinaloa';
    else if (empleados[i].persona.estado === "Sonora")
        document.querySelector('#txtestado').value = 'Sonora';
    else if (empleados[i].persona.estado === "Tabasco")
        document.querySelector('#txtestado').value = 'Tabasco';
    else if (empleados[i].persona.estado === "Tamaulipas")
        document.querySelector('#txtestado').value = 'Tamaulipas';
    else if (empleados[i].persona.estado === "Tlaxcala")
        document.querySelector('#txtestado').value = 'Tlaxcala';
    else if (empleados[i].persona.estado === "Veracruz")
        document.querySelector('#txtestado').value = 'Veracruz';
    else if (empleados[i].persona.estado === "Yucatán")
        document.querySelector('#txtestado').value = 'Yucatán';
    else if (empleados[i].persona.estado === "Zacatecas")
        document.querySelector('#txtestado').value = 'Zacatecas';
    document.getElementById("txttelCasa").value = empleados[i].persona.telcasa;
    document.getElementById("txttelMovil").value = empleados[i].persona.telmovil;
    document.getElementById("txtemail").value = empleados[i].persona.email;
    document.getElementById("txtnombreUsuario").value = empleados[i].usuario.nombre;
    document.getElementById("txtcontrasenia").value = empleados[i].usuario.contrasenia;
    if (empleados[i].usuario.rol === "Empleado")
        document.querySelector('#txtrol').value = 'Empleado';
    else if (empleados[i].usuario.rol === "Administrador")
        document.querySelector('#txtrol').value = 'Administrador';
}

export function getOptionGenero() {
    selectElement = document.querySelector("#slcgenero");
    output = selectElement.options[selectElement.selectedIndex].value;
    document.querySelector('.output').textContent = output;
}

export function getOptionEstado() {
    selectElement = document.querySelector("#txtestado");
    output = selectElement.options[selectElement.selectedIndex].value;
    document.querySelector('.output').textContent = output;
}

export function getOptionRol() {
    selectElement = document.querySelector("#txtrol");
    output = selectElement.options[selectElement.selectedIndex].value;
    document.querySelector('.output').textContent = output;
}

export function limpiarForm() {
    document.getElementById("txtidEmpleado").value = "";
    document.getElementById("txtidPersona").value = "";
    document.getElementById("txtidUsuario").value = "";
    document.getElementById("txtnombre").value = "";
    document.getElementById("txtapePaterno").value = "";
    document.getElementById("txtapeMaterno").value = "";
    document.querySelector('#slcgenero').value = 'D';
    document.getElementById("txtfechaNacimiento").value = "";
    document.getElementById("txtcalle").value = "";
    document.getElementById("txtnumero").value = "";
    document.getElementById("txtcolonia").value = "";
    document.getElementById("txtcp").value = "";
    document.getElementById("txtciudad").value = "";
    document.getElementById("txtestado").value = "";
    ;
    document.getElementById("txttelCasa").value = "";
    document.getElementById("txttelMovil").value = "";
    document.getElementById("txtemail").value = "";
    document.getElementById("txtnombreUsuario").value = "";
    document.getElementById("txtcontrasenia").value = "";
    document.getElementById("txtrol").value = "";
}

export function limpiarTablaEmpleado() {
    document.getElementById("tbEmpleado").innerHTML = "";
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
    let apePaterno = document.getElementById("txtapePaterno").value;
    let apeMaterno = document.getElementById("txtapeMaterno").value;
    let genero = document.getElementById("slcgenero").value;
    let fechaNacimiento = document.getElementById("txtfechaNacimiento").value;
    let calle = document.getElementById("txtcalle").value;
    let numero = document.getElementById("txtnumero").value;
    let colonia = document.getElementById("txtcolonia").value;
    let cp = document.getElementById("txtcp").value;
    let ciudad = document.getElementById("txtciudad").value;
    let estado = document.getElementById("txtestado").value;
    let telCasa = document.getElementById("txttelCasa").value;
    let telMovil = document.getElementById("txttelMovil").value;
    let email = document.getElementById("txtemail").value;
    let nombreUsuario = document.getElementById("txtnombreUsuario").value;
    let contrasenia = document.getElementById("txtcontrasenia").value;
    let rol = document.getElementById("txtrol").value;
    if (nombre == "" || apePaterno == "" || apeMaterno == "" || calle == "" || colonia == "" || numero == "" || cp == "" || ciudad == "" || telCasa == "" || telMovil == "" || email == "" || nombreUsuario == "" || contrasenia == "") {
        Swal.fire({position: 'center', icon: 'warning', title: 'No se permiten campos vacíos.', showConfirmButton: false, timer: 1500});
        return false;
    }
    if (!regexValidar.letras.test(nombre)) {
        Swal.fire({position: 'center', icon: 'warning', title: 'El campo nombre solo puede contener letras.', showConfirmButton: false, timer: 1500});
        return false;
    }
    if (!regexValidar.letras.test(apePaterno)) {
        Swal.fire({position: 'center', icon: 'warning', title: 'El campo apellido paterno solo puede contener letras.', showConfirmButton: false, timer: 1500});
        return false;
    }
    if (!regexValidar.letras.test(apeMaterno)) {
        Swal.fire({position: 'center', icon: 'warning', title: 'El campo apellido materno solo puede contener letras.', showConfirmButton: false, timer: 1500});
        return false;
    }
    if (!regexValidar.letras.test(calle)) {
        Swal.fire({position: 'center', icon: 'warning', title: 'El campo calle solo puede contener letras.', showConfirmButton: false, timer: 1500});
        return false;
    }
    if (!regexValidar.letras.test(colonia)) {
        Swal.fire({position: 'center', icon: 'warning', title: 'El campo colonia solo puede contener letras.', showConfirmButton: false, timer: 1500});
        return false;
    }
    if (!regexValidar.letras.test(ciudad)) {
        Swal.fire({position: 'center', icon: 'warning', title: 'El campo ciudad solo puede contener letras.', showConfirmButton: false, timer: 1500});
        return false;
    }
    if (!regexValidar.numeros.test(numero)) {
        Swal.fire({position: 'center', icon: 'warning', title: 'El campo número solo puede contener números y letras.', showConfirmButton: false, timer: 1500});
        return false;
    }
    if (!regexValidar.cp.test(cp)) {
        
        Swal.fire({position: 'center', icon: 'warning', title: 'El campo C.P. solo puede contener 5 números.', showConfirmButton: false, timer: 1500});
        return false;
    }
    if (!regexValidar.telefono.test(telCasa)) {
        Swal.fire({position: 'center', icon: 'warning', title: 'El campo tel. Casa solo puede contener 10 números.', showConfirmButton: false, timer: 1500});
        return false;
    }
    if (!regexValidar.telefono.test(telMovil)) {
        Swal.fire({position: 'center', icon: 'warning', title: 'El campo tel. Movil solo puede contener 10 números.', showConfirmButton: false, timer: 1500});
        return false;
    }
    if (!regexValidar.email.test(email)) {
        Swal.fire({position: 'center', icon: 'warning', title: 'El campo email solo puede contener letras, números y caracteres (.,-+).', showConfirmButton: false, timer: 1500});
        return false;
    }
    if (!regexValidar.letrasNumerosSimbolos.test(nombreUsuario)) {
        Swal.fire({position: 'center', icon: 'warning', title: 'El campo usuario solo puede contener letras, números y simbolos.', showConfirmButton: false, timer: 1500});
        return false;
    }
    if (!regexValidar.contrasenia.test(contrasenia)) {
        Swal.fire({position: 'center', icon: 'warning', title: 'El campo contraseña debe contener de 8 a 20 letras.', showConfirmButton: false, timer: 1500});
        return false;
    }
    return true;
}