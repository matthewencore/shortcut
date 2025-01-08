function openModalFromButton(button) {
    var id = button.getAttribute('data-id');
    var token = button.getAttribute('data-token');
    var url = button.getAttribute('data-url');
    var deadLineDate = button.getAttribute('data-deadline');
    var count = button.getAttribute('data-count');

    openModal(id, token, url, deadLineDate, count);
}



function openModal(linkId, token, url, deadline, views) {
    const modal = document.getElementById('editModal');
    modal.style.display = 'flex';

    // Заполняем поля модального окна
    document.getElementById('shortenedUrl').value = token;
    document.getElementById('fullUrl').value = url;
    document.getElementById('deadline').value = deadline;
    document.getElementById('views').value = views;

    // Сохраняем ID ссылки для дальнейших операций
    document.getElementById('editModal').dataset.linkId = linkId;
}


function closeModal() {
    document.getElementById('editModal').style.display = 'none';
}

document.getElementById('editModal').addEventListener('click', function (event) {
    const modalContent = document.querySelector('.modal-content');  // Предположим, что это контейнер с содержимым модального окна

    // Проверяем, был ли клик на фон модального окна, а не на его содержимое
    if (!modalContent.contains(event.target)) {
        closeModal();  // Закрываем модальное окно только если клик был не по содержимому
    }
});

function saveChanges() {
    const token = document.getElementById('shortenedUrl').value;
    const url = document.getElementById('fullUrl').value;
    const deadline = document.getElementById('deadline').value;
    const views = document.getElementById('views').value;
    const linkId = document.getElementById('editModal').dataset.linkId;

    // Отправляем данные на сервер через fetch
    fetch(`/api/update-link/${linkId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            token: token,
            url: url,
            deadLineDate: deadline,
            limit: views,
        })
    })
        .then(response => {
            console.log('Ответ от сервера:', response);
            return response.json(); // Преобразуем ответ в JSON
        })
        .then(data => {
            console.log('Ответ в формате JSON:', data); // Выведем ответ на консоль для отладки

            if (data.success) {
                closeModal();
                location.reload();
            } else {
                alert('Ошибка при обновлении ссылки');
            }
        })
        .catch(error => {
            console.error('Ошибка:', error);
            alert('Произошла ошибка при сохранении!');
        });
}
