let stompClient = null;

function connect() {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, () => {
        console.log('WebSocket conectado (control)');
    });
}

function enviarMensaje() {
    if (stompClient) {
        stompClient.send('/app/verse', {}, 'Juan 3:16');
    }
}

document.addEventListener('DOMContentLoaded', () => {
    connect();

    document.getElementById('btnEnviar')
        .addEventListener('click', enviarMensaje);
});
