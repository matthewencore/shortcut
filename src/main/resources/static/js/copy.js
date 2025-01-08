// Копирование в буфер обмена
function copyToClipboard() {
    const shortUrlElement = document.getElementById("short-url");
    if (shortUrlElement) {
        const url = shortUrlElement.innerText;

        navigator.clipboard.writeText(url)
            .then(() => {
                const tooltip = document.querySelector(".copy-tooltip");
                tooltip.innerText = "Скопировано!";
                setTimeout(() => {
                    tooltip.innerText = "Скопировать";
                }, 2000);
            })
            .catch(err => console.error("Ошибка копирования: ", err));
    } else {
        console.error("Элемент короткой ссылки не найден!");
    }
}