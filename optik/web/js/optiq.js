//function login() {
//	//Leemos Usuario y Contraseña
//	let usuario = document.getElementById('txtUsuario').value;
//	let password = document.getElementById('txtPassword').value;
//
//	//Validamos que los datos sean correctos;
//	if (usuario === 'Admin' && password === '123456') {
//		window.location.replace('gestion/');
//	} else {
//		//Si el usuario o contraseña no son correctos, mandamos un mensaje
//		Swal.fire('', 'Datos de usuario incorrectos,', 'warning');
//	}
//}

async function encriptar(texto) {
    const encoder = new TextEncoder(); //Invocamos la clase q convierte un String en bytes
    const data = encoder.encode(texto);//Hace la conversión
    console.log('-------');
    console.log(data);
    const hash = await crypto.subtle.digest('SHA-256', data); //crypto toma los bytes y los encripta, devuelve un buffer
    console.log(hash);
    const hashArray = Array.from(new Uint8Array(hash)); // convierte el buffer en un arreglo de bytes
    const hashHex = hashArray.map((b) => b.toString(16).padStart(2, '0')).join(''); // convierte los bytes en string
    return hashHex;
}

function login()
{
    let user = document.getElementById("txtUsuario").value;
    let contrasenia = document.getElementById("txtPassword").value;


    encriptar(contrasenia).then((textoEncriptado) => {
        alert(textoEncriptado.toString());
        let usuario = {datosUsuario: JSON.stringify({nombre: user, contrasenia: textoEncriptado.toString()})};

        const url = new URLSearchParams(usuario);
        fetch('api/restoptik/login',
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
                        localStorage.setItem('currentUser', JSON.stringify(data));
                        Swal.fire({
                            position: 'center',
                            icon: 'success',
                            title: 'Acceso Concedido' + data.persona.nombre,
                            showConfirmButton: false,
                            timer: 1500
                        });
                        setTimeout(() => {
                            console.log("2 segundos esperado");
                        }, 2000);

                        window.location.replace('gestion/');

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
    });
}

function inicializar() {
    var counter = 1;
    setInterval(function () {
        document.getElementById('radio' + counter).checked = true;
        counter++;
        if (counter > 5) {
            counter = 1;
        }
    }, 5000);
}