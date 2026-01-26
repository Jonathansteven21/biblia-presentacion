let stompClient = null;

function connect() {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, () => {
        console.log('WebSocket conectado (presentación)');

        stompClient.subscribe('/topic/verse', (message) => {
            const texto = message.body?.trim();

            document.getElementById('contenido').innerText =
                texto && texto.length > 0 ? texto : '—';
        });
    });
}

document.addEventListener('DOMContentLoaded', connect);
