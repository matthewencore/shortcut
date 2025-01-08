document.addEventListener("DOMContentLoaded", () => {
    // Подключаем календарь к полю ввода даты
    flatpickr("#expiration-date", {
        enableTime: false,
        dateFormat: "Y-m-d", // Формат даты
        minDate: "today", // Ограничение: нельзя выбирать прошлые даты
    });
});