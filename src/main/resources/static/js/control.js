let stompClient = null;

function connect() {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, () => {
        console.log('WebSocket conectado (control)');
    });
}

function enviarVersiculo() {
    if (!stompClient) return;

    const payload = {
        book: 'S.Juan',
        chapter: '3',
        from: 16,
        to: 16
    };

    stompClient.send(
        '/app/verse',
        {},
        JSON.stringify(payload)
    );
}

document.addEventListener('DOMContentLoaded', () => {
    connect();
    document
        .getElementById('btnEnviar')
        .addEventListener('click', enviarVersiculo);
});
