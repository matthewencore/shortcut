document.querySelector('form').addEventListener('submit', async function(event) {
    event.preventDefault();

    const urlInput = document.getElementById('url-input').value;
    const dateInput = document.getElementById('expiration-date').value;

    if (urlInput) {
        try {
            const response = await fetch('/api/shorten', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',  // Указываем, что данные будут в формате JSON
                },
                body: JSON.stringify({
                    url: urlInput,              // Ссылка для сокращения
                    expDate: dateInput || null  // Дата окончания, если не указана, передаем null
                }),
            });

            if (!response.ok) {
                throw new Error('Ошибка при сокращении ссылки');
            }

            const data = await response.json(); // Дожидаемся JSON-ответа

            // Выводим сгенерированную ссылку
            document.getElementById('result').innerHTML = `
                <span id="short-url">${data.url}</span>
                <span class="copy-tooltip">Скопировать</span>
            `;
            console.log(`Info | ${data.message}`);

        } catch (error) {
            alert('Произошла ошибка: ' + error.message);
            console.log(error.message);
        }
    } else {
        alert('Пожалуйста, введите URL.');
    }
});
