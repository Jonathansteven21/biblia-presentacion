let stompClient = null;

function connectWebSocket() {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, () => {
        console.log('WebSocket conectado (control)');
    });
}

function cargarLibros() {
    fetch('/api/bible/books')
        .then(res => res.json())
        .then(books => {
            const select = document.getElementById('book');
            select.innerHTML = '<option value="">Seleccione un libro</option>';

            books.forEach(book => {
                const option = document.createElement('option');
                option.value = book;
                option.textContent = book;
                select.appendChild(option);
            });
        })
        .catch(err => {
            console.error('Error cargando libros', err);
        });
}

function enviarVersiculo() {
    if (!stompClient) return;

    const book = document.getElementById('book').value;
    const chapter = document.getElementById('chapter').value;
    const from = parseInt(document.getElementById('from').value, 10);
    const to = parseInt(document.getElementById('to').value, 10);

    if (!book) {
        alert('Seleccione un libro');
        return;
    }

    const payload = {
        book,
        chapter,
        from,
        to
    };

    if (!chapter) {
        alert('Seleccione un capítulo');
        return;
    }


    stompClient.send(
        '/app/verse',
        {},
        JSON.stringify(payload)
    );
}

document
    .getElementById('book')
    .addEventListener('change', (e) => {
        const book = e.target.value;
        if (book) {
            cargarCapitulos(book);
        }
    });

function cargarCapitulos(book) {
    const chapterSelect = document.getElementById('chapter');
    chapterSelect.innerHTML = '<option value="">Cargando…</option>';

    fetch(`/api/bible/chapters?book=${encodeURIComponent(book)}`)
        .then(res => res.json())
        .then(chapters => {
            chapterSelect.innerHTML = '<option value="">Seleccione capítulo</option>';

            chapters.forEach(ch => {
                const option = document.createElement('option');
                option.value = ch;
                option.textContent = ch;
                chapterSelect.appendChild(option);
            });
        })
        .catch(err => {
            console.error('Error cargando capítulos', err);
        });
}

document.addEventListener('DOMContentLoaded', () => {
    connectWebSocket();
    cargarLibros();
});


