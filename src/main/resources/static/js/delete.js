function deleteLink(linkId, btnElement) {
    if (confirm('Вы уверены, что хотите удалить эту ссылку?')) {
    // Отправка AJAX-запроса на сервер для удаления
    fetch(`/api/delete-link/${linkId}`, {
    method: 'POST',
    headers: {
    'Content-Type': 'application/json'
}
})
    .then(response => {
    if (response.ok) {
    // Удаляем карточку с интерфейса
    const cardElement = btnElement.closest('.card');
    cardElement.remove();
} else {
    alert('Произошла ошибка при удалении ссылки');
}
})
    .catch(error => {
    console.error('Ошибка:', error);
    alert('Ошибка при удалении ссылки');
});
}
}

