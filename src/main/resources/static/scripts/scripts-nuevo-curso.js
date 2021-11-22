<<<<<<< HEAD
=======

>>>>>>> cf906d43c5b07353ecc6ab7aa445a59c479c1d50
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
<<<<<<< HEAD
			if(descripcion.length != 0 && descripcion.length <= 500){
				alert('La descripción no debe estar vacia ni tener más de 500 caracteres');
				return;
			}
			var precio = document.getElementById('precio').value;
			if (precio.length != 0) {
=======
			if(!descripcion.length > 0 && descripcion.length > 500){
				alert('La descripciï¿½n no debe estar vacia ni tener mï¿½s de 500 caracteres');
				return;
			}
			var precio = document.getElementById('precio').value;
			if (precio == 0) {
>>>>>>> cf906d43c5b07353ecc6ab7aa445a59c479c1d50
				alert('El precio no puede ser 0')
				return;
			}

			this.submit();
<<<<<<< HEAD
		}
=======
		}
>>>>>>> cf906d43c5b07353ecc6ab7aa445a59c479c1d50
