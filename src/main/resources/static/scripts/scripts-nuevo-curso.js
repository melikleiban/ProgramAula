
document.addEventListener("DOMContentLoaded", function () {
			document.getElementById("formulario").addEventListener('submit', validarFormulario);
			});

function validarFormulario(evento) {
			evento.preventDefault();

			var titulo = document.getElementById('titulo').value;
			if (titulo.length < 10) {
				alert('El titulo debe ser mayor a 10 caracteres');
				return;
			}
			var descripcion = document.getElementById('descripcion').value;
			if(!descripcion.length > 0 && descripcion.length > 500){
				alert('La descripci�n no debe estar vacia ni tener m�s de 500 caracteres');
				return;
			}
			var precio = document.getElementById('precio').value;
			if (precio == 0) {
				alert('El precio no puede ser 0')
				return;
			}

			this.submit();
		}
