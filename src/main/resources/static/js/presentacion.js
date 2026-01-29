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

            const referenceEl = document.getElementById('reference');
            const verseTextEl = document.getElementById('verse-text');

            console.log('PARSED:', data);

            referenceEl.textContent = data.reference ?? '';
            verseTextEl.textContent = data.text ?? '—';
        });
    });
}

document.addEventListener('DOMContentLoaded', () => {
    seleccionarVideoAleatorio();
    connect();
});
