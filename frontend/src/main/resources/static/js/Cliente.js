function agregarCliente(cliente) {
    fetch('http://localhost:8081/clientes', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(cliente),
    })
    .then(response => response.json())
    .then(data => {
      // Aquí puedes manejar los datos devueltos (cliente agregado)
      console.log(data);
    })
    .catch(error => {
      // Aquí puedes manejar cualquier error ocurrido durante la solicitud
      console.error('Error:', error);
    });
  }
  