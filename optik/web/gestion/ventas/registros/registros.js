export function inicializar() {}

export function llamarGetAll() {
    let opcion = parseInt(document.getElementById("slcTicket").value);
    console.log(opcion);
    if (opcion === 1) {
        getAllTicketP();
    } else if (opcion === 2) {
        getAllTicketL();
    } else if (opcion === 3) {
        getAllTicketLC();
    }
}
var boton = document.getElementById("boton");
var estado = "ascendente";

boton.classList.add("btn-success");
boton.innerHTML = "&#8593; Asc";
boton.value = "Asc";
boton.addEventListener("click", function () {
    if (estado === "ascendente") {
        console.log(document.getElementById("boton").value);
        boton.classList.remove("btn-success");
        boton.classList.add("btn-danger");
        boton.innerHTML = "&#8595; Desc";
        boton.value = "Desc";
        estado = "descendente";
    } else {
        console.log(document.getElementById("boton").value);
        boton.classList.remove("btn-danger");
        boton.classList.add("btn-success");
        boton.innerHTML = "&#8593; Asc";
        boton.value = "Asc";
        estado = "ascendente";
    }
    llamarGetAll();
});

export function getAllTicketP() {
    let filtro = document.getElementById("txtBarra").value;
    let orden = document.getElementById("boton").value;
    let parametros = new URLSearchParams({filtro: filtro, orden: orden});

    fetch("../api/restoptik/getAllTicketP", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    alert(data.error);
                } else {
                    console.log(data);
                    generarTarjetasP(data);
                }
            });
}

export function generarTarjetasP(tickets) {
    const tarjetasContainer = document.getElementById("tarjetas-container");
    tarjetasContainer.innerHTML = "";

    let colorFondo = "#FFFFFF";

    //Crear objeto para agrupar tickets por clave
    const ticketsPorClave = {};

    //Iterar a través de los tickets y agregarlos al objeto según su clave
    tickets.forEach((ticket) => {
        if (ticket.clave in ticketsPorClave) {
            ticketsPorClave[ticket.clave].push(ticket);
        } else {
            ticketsPorClave[ticket.clave] = [ticket];
        }
    });

    //Iterar sobre el objeto y crear una tarjeta por cada array de tickets que tenga
    for (const clave in ticketsPorClave) {
        let total = 0;

        //Crear la estructura HTML para la tarjeta 
        const tarjeta = document.createElement("div");
        tarjeta.className = "tarjeta";
        tarjeta.style.backgroundColor = colorFondo;
        tarjeta.style.padding = "20px";
        tarjeta.style.marginBottom = "20px";
        tarjeta.style.width = "460px";

        const imagen = document.createElement("img");
        imagen.setAttribute("src", "https://drive.google.com/uc?id=1VixhgP-KKrY3aXakLs59axh5rdzuQTap");
        imagen.style.with = "60px";
        imagen.style.height = "60px";
        imagen.style.display = "block";
        imagen.style.margin = "auto";
        tarjeta.appendChild(imagen);

        const title = document.createElement("h2");
        title.textContent = "New View";
        title.style.color = "black";
        title.style.textAlign = "center";

        const c = document.createElement("p");
        c.textContent = clave;
        c.style.color = "#fe303a";
        c.style.textAlign = "center";


        const empleado = document.createElement("p");
        empleado.textContent = "Empleado: " + ticketsPorClave[clave][0].empleado + " " + ticketsPorClave[clave][0].apellidoPaterno + " " + ticketsPorClave[clave][0].apellidoMaterno;
        empleado.style.color = "black";

        tarjeta.appendChild(empleado);

        //crear la tabla y encabezados
        const tabla = document.createElement("table");
        const encabezados = document.createElement("tr");

        const nombreEncabezado = document.createElement("th");
        nombreEncabezado.textContent = "Nombre";
        nombreEncabezado.style.color = "black";
        encabezados.appendChild(nombreEncabezado);

        const marcaEncabezado = document.createElement("th");
        marcaEncabezado.textContent = "Marca";
        marcaEncabezado.style.color = "black";
        encabezados.appendChild(marcaEncabezado);

        const cantidadEncabezado = document.createElement("th");
        cantidadEncabezado.textContent = "Cantidad";
        cantidadEncabezado.style.color = "black";
        encabezados.appendChild(cantidadEncabezado);

        const precioUnitarioEncabezado = document.createElement("th");
        precioUnitarioEncabezado.textContent = "Precio";
        precioUnitarioEncabezado.style.color = "black";
        encabezados.appendChild(precioUnitarioEncabezado);

        const descuentoEncabezado = document.createElement("th");
        descuentoEncabezado.textContent = "Descuento";
        descuentoEncabezado.style.color = "black";
        encabezados.appendChild(descuentoEncabezado);

        tabla.appendChild(encabezados);


        ticketsPorClave[clave].forEach((ticket) => {
            const subtotal = ticket.cantidad * ticket.precioUnitario;
            const des = subtotal * (ticket.descuento / 100);
            const totalTicket = subtotal - des;
            total += totalTicket;
            const fila = document.createElement("tr");

            const nombre = document.createElement("td");
            nombre.textContent = ticket.nombre;
            nombre.style.color = "black";
            fila.appendChild(nombre);

            const marca = document.createElement("td");
            marca.textContent = ticket.marca;
            marca.style.color = "black";
            fila.appendChild(marca);

            const cantidad = document.createElement("td");
            cantidad.textContent = ticket.cantidad;
            cantidad.style.color = "black";
            fila.appendChild(cantidad);

            const precioUnitario = document.createElement("td");
            precioUnitario.textContent = "$" + ticket.precioUnitario.toFixed(2);
            precioUnitario.style.color = "black";
            fila.appendChild(precioUnitario);

            const descuento = document.createElement("td");
            descuento.textContent = ticket.descuento + "%";
            descuento.style.color = "black";
            fila.appendChild(descuento);

            tabla.appendChild(fila);
        });
        const t = document.createElement("p");
        t.textContent = "Total: $" + total;
        t.style.color = "#fe303a";
        t.style.textAlign = "center";

        const imagenC = document.createElement("img");
        imagenC.setAttribute("src", "https://drive.google.com/uc?id=1WXmWdwNNTIvM5i0CGwVevmsqzjrV0YgA");
        imagenC.style.display = "block";
        imagenC.style.width = "200px"; // Establece el ancho al 50%
        imagenC.style.height = "100px";
        imagenC.style.margin = "auto";

        tarjeta.insertBefore(title, tarjeta.firstChild);
        tarjeta.appendChild(tabla);
        tarjeta.appendChild(t);
        tarjeta.appendChild(imagenC);
        tarjeta.appendChild(c);

        //Agregar la tarjeta al contenedor de tarjetas
        tarjetasContainer.appendChild(tarjeta);
    }
}

export function getAllTicketL() {
    let filtro = document.getElementById("txtBarra").value;
    let orden = document.getElementById("boton").value;
    let parametros = new URLSearchParams({filtro: filtro, orden: orden});

    fetch("../api/restoptik/getAllTicketL", {
        method: "POST",
        body: parametros,
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    })
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    alert(data.error);
                } else {
                    console.log(data);
                    generarTarjetasL(data);
                }
            });
}


export function generarTarjetasL(tickets) {
    const tarjetasContainer = document.getElementById("tarjetas-container");
    tarjetasContainer.innerHTML = "";

    let colorFondo = "#FFFFFF";

    //Crear objeto para agrupar tickets por clave
    const ticketsPorClave = {};

    //Iterar a través de los tickets y agregarlos al objeto según su clave
    tickets.forEach((ticket) => {
        if (ticket.claveV in ticketsPorClave) {
            ticketsPorClave[ticket.claveV].push(ticket);
        } else {
            ticketsPorClave[ticket.claveV] = [ticket];
        }
    });

    //Iterar sobre el objeto y crear una tarjeta por cada array de tickets que tenga
    for (const clave in ticketsPorClave) {
        let total = 0;

        //Crear la estructura HTML para la tarjeta 
        const tarjeta = document.createElement("div");
        tarjeta.className = "tarjeta";
        tarjeta.style.backgroundColor = colorFondo;
        tarjeta.style.padding = "20px";
        tarjeta.style.marginBottom = "20px";
        tarjeta.style.width = "550px";

        const imagen = document.createElement("img");
        imagen.setAttribute("src", "https://drive.google.com/uc?id=1VixhgP-KKrY3aXakLs59axh5rdzuQTap");
        imagen.style.display = "block";
        imagen.style.margin = "auto";
        tarjeta.appendChild(imagen);

        const title = document.createElement("h2");
        title.textContent = "New View";
        title.style.color = "black";

        const c = document.createElement("p");
        c.textContent = clave;
        c.style.color = "#fe303a";


        const empleado = document.createElement("p");
        empleado.textContent = "Empleado: " + ticketsPorClave[clave][0].nombreE + " " + ticketsPorClave[clave][0].apellidoPaternoE + " " + ticketsPorClave[clave][0].apellidoMaternoE;
        empleado.style.color = "black";

        const cliente = document.createElement("p");
        cliente.textContent = "Cliente: " + ticketsPorClave[clave][0].nombreC + " " + ticketsPorClave[clave][0].apellidoPaternoC + " " + ticketsPorClave[clave][0].apellidoMaternoC;
        cliente.style.color = "black";

        const fechaExamen = document.createElement("p");
        fechaExamen.textContent = "Fecha examen: " + ticketsPorClave[clave][0].fecha;
        fechaExamen.style.color = "black";

        tarjeta.appendChild(empleado);
        tarjeta.appendChild(cliente);
        tarjeta.appendChild(fechaExamen);

        //crear la tabla y encabezados
        const tabla = document.createElement("table");
        const encabezados = document.createElement("tr");

        const ArmazonEncabezado = document.createElement("th");
        ArmazonEncabezado.textContent = "Armazon";
        ArmazonEncabezado.style.color = "black";
        encabezados.appendChild(ArmazonEncabezado);

        const materialEncabezado = document.createElement("th");
        materialEncabezado.textContent = "Material";
        materialEncabezado.style.color = "black";
        encabezados.appendChild(materialEncabezado);

        const tipoMicaEncabezado = document.createElement("th");
        tipoMicaEncabezado.textContent = "Mica";
        tipoMicaEncabezado.style.color = "black";
        encabezados.appendChild(tipoMicaEncabezado);

        const cantidadEncabezado = document.createElement("th");
        cantidadEncabezado.textContent = "Cantidad";
        cantidadEncabezado.style.color = "black";
        encabezados.appendChild(cantidadEncabezado);

        const precioUnitarioEncabezado = document.createElement("th");
        precioUnitarioEncabezado.textContent = "Precio";
        precioUnitarioEncabezado.style.color = "black";
        encabezados.appendChild(precioUnitarioEncabezado);

        const descuentoEncabezado = document.createElement("th");
        descuentoEncabezado.textContent = "Descuento";
        descuentoEncabezado.style.color = "black";
        encabezados.appendChild(descuentoEncabezado);

        tabla.appendChild(encabezados);


        ticketsPorClave[clave].forEach((ticket) => {
            const subtotal = ticket.cantidad * ticket.precioUnitario;
            const des = subtotal * (ticket.descuento / 100);
            const totalTicket = subtotal - des;
            total += totalTicket;
            const fila = document.createElement("tr");

            const armazon = document.createElement("td");
            armazon.textContent = ticket.marca + " " + ticket.nombreA;
            armazon.style.color = "black";
            fila.appendChild(armazon);

            const material = document.createElement("td");
            material.textContent = ticket.nombreM;
            material.style.color = "black";
            fila.appendChild(material);

            const tipoMica = document.createElement("td");
            tipoMica.textContent = ticket.nombreTP;
            tipoMica.style.color = "black";
            fila.appendChild(tipoMica);

            const cantidad = document.createElement("td");
            cantidad.textContent = ticket.cantidad;
            cantidad.style.color = "black";
            fila.appendChild(cantidad);

            const precioUnitario = document.createElement("td");
            precioUnitario.textContent = "$" + ticket.precioUnitario.toFixed(2);
            precioUnitario.style.color = "black";
            fila.appendChild(precioUnitario);

            const descuento = document.createElement("td");
            descuento.textContent = ticket.descuento + "%";
            descuento.style.color = "black";
            fila.appendChild(descuento);

            tabla.appendChild(fila);
        });
        const t = document.createElement("p");
        t.textContent = "Total: $" + total;
        t.style.color = "#fe303a";

        const imagenC = document.createElement("img");
        imagenC.setAttribute("src", "https://drive.google.com/uc?id=1WXmWdwNNTIvM5i0CGwVevmsqzjrV0YgA");
        imagenC.style.display = "block";
        imagenC.style.width = "200px"; // Establece el ancho al 50%
        imagenC.style.height = "100px";
        imagenC.style.margin = "auto";

        tarjeta.insertBefore(title, tarjeta.firstChild);
        tarjeta.appendChild(tabla);
        tarjeta.appendChild(t);
        tarjeta.appendChild(imagenC);
        tarjeta.appendChild(c);

        //Agregar la tarjeta al contenedor de tarjetas
        tarjetasContainer.appendChild(tarjeta);
    }
}

export function getAllTicketLC() {
    let filtro = document.getElementById("txtBarra").value;
    let orden = document.getElementById("boton").value;
    let parametros = new URLSearchParams({filtro: filtro, orden: orden});


    fetch("../api/restoptik/getAllTicketLC", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    alert(data.error);
                } else {
                    console.log(data);
                    generarTarjetasLC(data);
                }
            });
}

export function generarTarjetasLC(tickets) {
    const tarjetasContainer = document.getElementById("tarjetas-container");
    tarjetasContainer.innerHTML = "";

    let colorFondo = "#FFFFFF";

    //Crear objeto para agrupar tickets por clave
    const ticketsPorClave = {};

    //Iterar a través de los tickets y agregarlos al objeto según su clave
    tickets.forEach((ticket) => {
        if (ticket.claveV in ticketsPorClave) {
            ticketsPorClave[ticket.claveV].push(ticket);
        } else {
            ticketsPorClave[ticket.claveV] = [ticket];
        }
    });

    //Iterar sobre el objeto y crear una tarjeta por cada array de tickets que tenga
    for (const clave in ticketsPorClave) {
        let total = 0;

        //Crear la estructura HTML para la tarjeta 
        const tarjeta = document.createElement("div");
        tarjeta.className = "tarjeta";
        tarjeta.style.backgroundColor = colorFondo;
        tarjeta.style.padding = "20px";
        tarjeta.style.marginBottom = "20px";

        const imagen = document.createElement("img");
        imagen.setAttribute("src", "https://drive.google.com/uc?id=1VixhgP-KKrY3aXakLs59axh5rdzuQTap");
        imagen.style.display = "block";
        imagen.style.margin = "auto";
        tarjeta.appendChild(imagen);

        const title = document.createElement("h2");
        title.textContent = "New View";
        title.style.color = "black";

        const c = document.createElement("p");
        c.textContent = clave;
        c.style.color = "#fe303a";


        const empleado = document.createElement("p");
        empleado.textContent = "Empleado: " + ticketsPorClave[clave][0].nombreE + " " + ticketsPorClave[clave][0].apellidoPaternoE + " " + ticketsPorClave[clave][0].apellidoMaternoE;
        empleado.style.color = "black";

        const cliente = document.createElement("p");
        cliente.textContent = "Cliente: " + ticketsPorClave[clave][0].nombreC + " " + ticketsPorClave[clave][0].apellidoPaternoC + " " + ticketsPorClave[clave][0].apellidoMaternoC;
        cliente.style.color = "black";

        const fechaExamen = document.createElement("p");
        fechaExamen.textContent = "Fecha examen: " + ticketsPorClave[clave][0].fecha;
        fechaExamen.style.color = "black";

        tarjeta.appendChild(empleado);
//        tarjeta.appendChild(numeroUnicoE);
        tarjeta.appendChild(cliente);
        tarjeta.appendChild(fechaExamen);
//        tarjeta.appendChild(numeroUnicoC);

        //crear la tabla y encabezados
        const tabla = document.createElement("table");
        const encabezados = document.createElement("tr");

        const lenteContactoEncabezado = document.createElement("th");
        lenteContactoEncabezado.textContent = "Lente Contacto";
        lenteContactoEncabezado.style.color = "black";
        encabezados.appendChild(lenteContactoEncabezado);

        const keratometriaEncabezado = document.createElement("th");
        keratometriaEncabezado.textContent = "Keratometria";
        keratometriaEncabezado.style.color = "black";
        encabezados.appendChild(keratometriaEncabezado);

        const cantidadEncabezado = document.createElement("th");
        cantidadEncabezado.textContent = "Cantidad";
        cantidadEncabezado.style.color = "black";
        encabezados.appendChild(cantidadEncabezado);

        const precioUnitarioEncabezado = document.createElement("th");
        precioUnitarioEncabezado.textContent = "Precio";
        precioUnitarioEncabezado.style.color = "black";
        encabezados.appendChild(precioUnitarioEncabezado);

        const descuentoEncabezado = document.createElement("th");
        descuentoEncabezado.textContent = "Descuento";
        descuentoEncabezado.style.color = "black";
        encabezados.appendChild(descuentoEncabezado);

        tabla.appendChild(encabezados);


        ticketsPorClave[clave].forEach((ticket) => {
            const subtotal = ticket.cantidad * ticket.precioUnitario;
            const des = subtotal * (ticket.descuento / 100);
            const totalTicket = subtotal - des;
            total += totalTicket;
            const fila = document.createElement("tr");

            const lenteContacto = document.createElement("td");
            lenteContacto.textContent = ticket.marca + " " + ticket.nombreP;
            lenteContacto.style.color = "black";
            fila.appendChild(lenteContacto);

            const keratometria = document.createElement("td");
            keratometria.textContent = ticket.keratometria;
            keratometria.style.color = "black";
            fila.appendChild(keratometria);

            const cantidad = document.createElement("td");
            cantidad.textContent = ticket.cantidad;
            cantidad.style.color = "black";
            fila.appendChild(cantidad);

            const precioUnitario = document.createElement("td");
            precioUnitario.textContent = "$" + ticket.precioUnitario.toFixed(2);
            precioUnitario.style.color = "black";
            fila.appendChild(precioUnitario);

            const descuento = document.createElement("td");
            descuento.textContent = ticket.descuento + "%";
            descuento.style.color = "black";
            fila.appendChild(descuento);

            tabla.appendChild(fila);
        });
        const t = document.createElement("p");
        t.textContent = "Total: $" + total;
        t.style.color = "#fe303a";

        const imagenC = document.createElement("img");
        imagenC.setAttribute("src", "https://drive.google.com/uc?id=1WXmWdwNNTIvM5i0CGwVevmsqzjrV0YgA");
        imagenC.style.display = "block";
        imagenC.style.width = "200px"; // Establece el ancho al 50%
        imagenC.style.height = "100px";
        imagenC.style.margin = "auto";

        tarjeta.insertBefore(title, tarjeta.firstChild);
        tarjeta.appendChild(tabla);
        tarjeta.appendChild(t);
        tarjeta.appendChild(imagenC);
        tarjeta.appendChild(c);

        //Agregar la tarjeta al contenedor de tarjetas
        tarjetasContainer.appendChild(tarjeta);
    }
}