function validarFormulario(evento) {
			evento.preventDefault();

			var titulo = document.getElementById('titulo').value;
			if (titulo.length < 10) {
				alert('El titulo debe ser mayor a 10 caracteres');
				return;
			}
			var descripcion = document.getElementById('descripcion').value;
			if(descripcion.length != 0 && descripcion.length <= 500){
				alert('La descripción no debe estar vacia ni tener más de 500 caracteres')
				return;
			}
			var precio = document.getElementById('precio').value;
			if (precio.length != 0) {
				alert('El precio no puede ser 0')
				return;
			}

			this.submit();
		}