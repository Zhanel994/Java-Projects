<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Кот: ${name}</title>
    <link rel="stylesheet" href="/css/cat.css">
</head>
<body>
<div class="cat-container">
    <h2>Кот: ${name}</h2>

    <div class="stats">
        <p>Возраст: ${age}</p>
        <p>Сытость: ${satiety}</p>
        <p>Счастье: ${happiness}</p>
        <p>Состояние: ${state}</p>
    </div>

    <div class="cat-image">
        <img src="${avatar}" alt="кот" />
    </div>

    <form action="/cat" method="post">
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
