<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <link rel="stylesheet" href="css/tasks.css">
    <title>To-do List</title>
</head>
    <div>
        <div class="container">
            <header class="header">
                <a href="/">Все задачи</a>
                <a href="#">Выполненные</a>
                <a href="#">Новые</a>
                <a href="#">Статистика</a>
            </header>

            <div class="task-detail">
                <h1>Задача ${data.id}</h1>
                <div class="task">
                    <div class="task-left">
                        <p><strong>Исполнитель задачи: </strong>${data.executor}</p>
                        <p><strong>Дата создания: </strong>${data.creationDate}</p>
                        <p><strong>Дата выполнения: </strong>
                            <#if data.completionDate??>
                                ${data.creationDate}
                            <#else>
                            -
                            </#if>
                        </p>
                    </div>
                </div>

                <div class="task-actions">
                   <form action="/tasks/change_state/${data.id}" method="post">
                        <button type="submit">Выполнить</button>
                   </form>

                   <form action="/tasks/delete/${data.id}" method="post">
                        <button type="submit">Удалить</button>
                   </form>
                </div>

                <div class="task-description">
                    <h2>Описание</h2>
                    <p>${data.description}</p>
                </div>
            </div>
        </div>
    </div>
</html>
