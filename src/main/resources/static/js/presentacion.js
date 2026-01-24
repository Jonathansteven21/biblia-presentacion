let stompClient = null;

function connect() {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, () => {
        console.log('WebSocket conectado (presentación)');

        stompClient.subscribe('/topic/verse', (message) => {
            document.getElementById('contenido').innerText = message.body;
        });
    });
}

document.addEventListener('DOMContentLoaded', connect);
