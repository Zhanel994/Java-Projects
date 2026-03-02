<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Кот: ${data.name}</title>
    <link rel="stylesheet" href="/css/cat.css">
</head>
<body>
<div class="cat-container">
    <h2>Кот: ${data.name}</h2>

    <div class="stats">
        <p>Возраст: ${data.age}</p>
        <p>Сытость: ${data.satiety}</p>
        <p>Счастье: ${data.happiness}</p>
        <p>Состояние: ${data.state}</p>
    </div>

    <div class="cat-image">
        <img src="${data.avatar}" alt="кот" />
    </div>

    <form action="/cat/action" method="post">
        <label for="action">Выберите действие:</label>
        <select name="action" id="action">
            <option value="feed">Покормить</option>
            <option value="play">Поиграть</option>
            <option value="sleep">Уложить спать</option>
        </select>
        <button type="submit">Выполнить</button>
    </form>
</div>
</body>
</html>
