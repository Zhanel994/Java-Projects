<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Добро пожаловать</title>
    <link rel="stylesheet" href="/css/welcome.css">
</head>
<body>
<div class="welcome-container">
    <h2>Вас привествует симулятор кота!</h2>
    <p>для начала введите имя кота:</p>

    <form action="/cat" method="post">
        <input type="text" name="name" placeholder="Имя кота" required />
        <button type="submit">Создать</button>
    </form>
</div>
</body>
</html>
