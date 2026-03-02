<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <link rel="stylesheet" href="css/tasks.css">
    <title>To-do List</title>
</head>
    <body>
        <div class="container">
            <header class="header">
                <a href="/">Все задачи</a>
                <a href="#">Выполненные</a>
                <a href="#">Новые</a>
                <a href="#">Статистика</a
            </header>

            <div class="main-content">
                <div class="task-list">
                    <h3>Список задач</h3>
                    <table>
                        <tr class="table-header">
                            <th>ID</th>
                            <th>Заголовок</th>
                            <th>Имя исполнителя</th>
                            <th>Дата создания</th>
                            <th>Статус</th>
                            <th>Действия</th>
                        </tr>
                        <#list data as data>
                            <tr class="table-body">
                                <td>${data.id}</td>
                                <td><a href="/tasks/${data.id}">${data.title}</a></td>
                                <td>${data.executor}</td>
                                <td>${data.creationDate}</td>
                                <td>${data.status}</td>
                                <td>
                                    <form action="/tasks/change_state/${data.id}" method="post">
                                        <button type="submit">Выполнить</button>
                                    </form>
                                    <form action="/tasks/delete/${data.id}" method="post">
                                        <button type="submit">Удалить</button>
                                    </form>
                                </td>
                            </tr>
                        </#list>
                    </table>
                </div>
                <div class="task-add">
                    <h2>Добавить новую задачу</h2>
                    <form action="/tasks" method="post">
                        <label>
                            Заголовок: <br>
                            <input name="title" type="text" required>
                        </label><br><br>

                        <label>
                            Имя исполнителя: <br>
                            <input name="executor" type="text" required>
                        </label><br><br>

                        <label>
                            Описание задачи: <br>
                            <input name="description" type="text" required>
                        </label><br><br>

                        <button type="submit">Добавить</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>