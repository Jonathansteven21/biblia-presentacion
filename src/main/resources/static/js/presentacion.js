let stompClient = null;

const videos = [
    '/video/bg-1.mp4',
    '/video/bg-2.mp4',
    '/video/bg-3.mp4',
    '/video/bg-4.mp4',
    '/video/bg-5.mp4',
    '/video/bg-6.mp4',
    '/video/bg-7.mp4'
];

function seleccionarVideoAleatorio() {
    const video = document.getElementById('bg-video');
    const index = Math.floor(Math.random() * videos.length);
    video.src = videos[index];
}

function connect() {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, () => {
        console.log('WebSocket conectado (presentación)');

        stompClient.subscribe('/topic/verse', (message) => {
            const data = JSON.parse(message.body);

            const content = document.getElementById('content');

            content.innerHTML = `
                <div class="reference">${data.reference}</div>
                <div class="text">${data.text}</div>
            `;
        });
    });
}

document.addEventListener('DOMContentLoaded', () => {
    seleccionarVideoAleatorio();
    connect();
});
