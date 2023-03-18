 let ma = null; //modulo actual
//Cargar Modulo de Clientes
function cargarModuloCliente() {
    //Realizamos la petición al Servidor
    fetch('cliente/cliente.html')
            //Convertimos la respuesta en texto HTML
            .then(response => {
                return response.text();
            })
            //Procesamos el texto HTML
            .then(function (data) {
                //Cargamos el contenido dentro del contenedor principal
                document.getElementById('contenedorPrincipal').innerHTML = data;
                import('./cliente/cliente.js').then(obj => {
                    ma = obj;
                    ma.inicializar();
                });
            }); 
}

function cargarModuloAccesorios() {
    //Realizamos la petición al Servidor
    fetch('producto/accesorio/accesorio.html')
            //Convertimos la respuesta en texto HTML
            .then(response => {
                return response.text();
            })
            //Procesamos el texto HTML
            .then(function (data) {
                //Cargamos el contenido dentro del contenedor principal
                document.getElementById('contenedorPrincipal').innerHTML = data;
                import('./producto/accesorio/accesorio.js').then(obj => {
                    ma = obj;
                    ma.inicializar();
                });
                localStorage.setItem('vistaActual', 'accesorio');
            });
}

function cargarModuloEmpleado() {
    //Realizamos la petición al Servidor
    fetch('empleado/empleado.html')
            //Convertimos la respuesta en texto HTML
            .then(response => {
                return response.text();
            })
            //Procesamos el texto HTML
            .then(function (data) {
                //Cargamos el contenido dentro del contenedor principal
                document.getElementById('contenedorPrincipal').innerHTML = data;
                import('./empleado/empleado.js').then(obj => {
                    ma = obj;
                    ma.inicializar();
                    localStorage.setItem('vistaActual', 'empleado');
                });
            });
}

function cargarModuloSoluciones() {
    //Realizamos la petición al Servidor
    fetch('producto/solucion/solucion.html')
            //Convertimos la respuesta en texto HTML
            .then(response => {
                return response.text();
            })
            //Procesamos el texto HTML
            .then(function (data) {
                //Cargamos el contenido dentro del contenedor principal
                document.getElementById('contenedorPrincipal').innerHTML = data;
                import('./producto/solucion/solucion.js').then(obj => {
                    ma = obj;
                    ma.inicializar();
                    localStorage.setItem('vistaActual', 'solucion');
                });
            });
}

function cargarModuloTratamientos() {
    //Realizamos la petición al Servidor
    fetch('tratamiento/tratamiento.html')
            //Convertimos la respuesta en texto HTML
            .then(response => {
                return response.text();
            })
            //Procesamos el texto HTML
            .then(function (data) {
                //Cargamos el contenido dentro del contenedor principal
                document.getElementById('contenedorPrincipal').innerHTML = data;
                import('./tratamiento/tratamiento.js').then(obj => {
                    ma = obj;
                    ma.inicializar();
                    localStorage.setItem('vistaActual', 'tratamiento');
                });

            });
}

function cargarModuloArmazones() {
    //Realizamos la petición al Servidor
    fetch('producto/armazon/armazon.html')
            //Convertimos la respuesta en texto HTML
            .then(response => {
                return response.text();
            })
            //Procesamos el texto HTML
            .then(function (data) {
                //Cargamos el contenido dentro del contenedor principal
                document.getElementById('contenedorPrincipal').innerHTML = data;
                import('./producto/armazon/armazon.js').then(obj => {
                    ma = obj;
                    ma.inicializar();
                    localStorage.setItem('vistaActual', 'armazon');
                });
            });
}

function cargarVista() {
    const vista = localStorage.getItem('vistaActual');
    switch (vista) {
        case 'empleado':
            cargarModuloEmpleado();
            break;
        case 'accesorio':
            cargarModuloAccesorios();
            break;
        case 'tratamiento':
            cargarModuloTratamientos();
            break;
        case 'armazon':
            cargarModuloArmazones();
            break;
        case 'solucion':
            cargarModuloSoluciones();
            break;
        case 'home':
            cargarHome();
            break;
        case 'venta':
            cargarModuloVenta();
            break;
        case 'ventaLente':
            cargarModuloVentaLente();
            break;
        case 'ventaLenteC':
            cargarModuloVentaLenteC();
            break;
        case 'material':
            cargarModuloMaterial();
            break;
    }
}

cargarVista();

function cargarModuloMaterial() {
    //Realizamos la petición al Servidor
    fetch('material/material.html')
            //Convertimos la respuesta en texto HTML
            .then(response => {
                return response.text();
            })
            //Procesamos el texto HTML
            .then(function (data) {
                //Cargamos el contenido dentro del contenedor principal
                document.getElementById('contenedorPrincipal').innerHTML = data;
                import('./material/material.js').then(obj => {
                    ma = obj;
                    ma.inicializar();
                    localStorage.setItem('vistaActual', 'material');
                });
            });
}

function cargarHome() {
    fetch('home/home.html')
            //Convertimos la respuesta en texto HTML
            .then(response => {
                return response.text();
            })
            //Procesamos el texto HTML
            .then(function (data) {
                //Cargamos el contenido dentro del contenedor principal
                document.getElementById('contenedorPrincipal').innerHTML = data;
                import('./home/home.js').then(obj => {
                    ma = obj;
                    ma.inicializar();
                    localStorage.setItem('vistaActual', 'home');
                });
            });
}

function cargarModuloVenta() {
    fetch('ventas/venta/venta.html')
            //Convertimos la respuesta en texto HTML
            .then(response => {
                return response.text();
            })
            //Procesamos el texto HTML
            .then(function (data) {
                //Cargamos el contenido dentro del contenedor principal
                document.getElementById('contenedorPrincipal').innerHTML = data;
                import('./ventas/venta/venta.js').then(obj => {
                    ma = obj;
                    ma.inicializar();
                    localStorage.setItem('vistaActual', 'venta');
                });
            });
}

function cargarModuloVentaLente() {
    fetch('ventas/ventaLente/ventaLente.html')
            //Convertimos la respuesta en texto HTML
            .then(response => {
                return response.text();
            })
            //Procesamos el texto HTML
            .then(function (data) {
                //Cargamos el contenido dentro del contenedor principal
                document.getElementById('contenedorPrincipal').innerHTML = data;
                import('./ventas/ventaLente/ventaLente.js').then(obj => {
                    ma = obj;
                    ma.inicializar();
                    localStorage.setItem('vistaActual', 'ventaLente');
                });
            });
}

function cargarModuloVentaLenteC() {
    fetch('ventas/ventaLenteC/ventaLenteC.html')
            //Convertimos la respuesta en texto HTML
            .then(response => {
                return response.text();
            })
            //Procesamos el texto HTML
            .then(function (data) {
                //Cargamos el contenido dentro del contenedor principal
                document.getElementById('contenedorPrincipal').innerHTML = data;
                import('./ventas/ventaLenteC/ventaLenteC.js').then(obj => {
                    ma = obj;
                    ma.inicializar();
                    localStorage.setItem('vistaActual', 'ventaLenteC');
                });
            });
}

function logOut() {
    let token = localStorage.getItem("currentUser");
    localStorage.setItem('currentUser',"");
    let usuario = {empleado: token};

    const url = new URLSearchParams(usuario);
    fetch('http://localhost:8080/optik/api/restoptik/logout',
            {
                method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: url
            })
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    alert(data.error);
                } else if (data.idEmpleado) {
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Sesion cerrada',
                        showConfirmButton: false,
                        timer: 1500
                    });
                    setTimeout(() => {
                        console.log("2 segundos esperado");
                    }, 2000);
                } else {
                    Swal.fire({
                        position: 'center',
                        icon: 'error',
                        title: 'uyyyy...',
                        text: 'a ocurrido un error'
                    });
                }
                JSON.stringify(data);
            });
}