const newTaskAdd = document.getElementById("newTaskAdd");
const addTaskBtn = document.getElementById("addTaskBtn");
const toDoTask  = document.getElementById("toDoTask");
const inProgressTask = document.getElementById("inProgressTask");
const doneTask = document.getElementById("doneTask");
const clearStorageBtn = document.getElementById("clearStorageBtn");

let tasks = localStorage.getItem("tasks") ? JSON.parse(localStorage.getItem("tasks")) : [];

function generateId() {
    return Math.floor(Math.random() * 100);
}

function saveTask() {
    localStorage.setItem("tasks", JSON.stringify(tasks));
}

function addTask(text) {
    const newTask = {
        id: generateId(),
        text: text,
        status: "New",
        isDeleted: false
    };
    tasks.push(newTask);
    saveTask();
    showTasks();
}

function createTask(task) {
    const taskDiv = document.createElement("div");
    taskDiv.classList.add("task");
    taskDiv.id = "task_" + task.id;

    const taskText = document.createElement("p");
    taskText.textContent = task.text;

    const nextBtn = document.createElement("button");

    const deleteBtn = document.createElement("button");
    deleteBtn.textContent = "X";
    deleteBtn.style.backgroundColor = "yellow";
    deleteBtn.onclick = function () {
        deleteTask(task.id);
    };

    if(task.status === "New") {
        nextBtn.textContent = "In Progress";
        nextBtn.onclick = function () {
            moveTask(task.id, "In Progress");
        };
    } else if(task.status === "In Progress") {
        nextBtn.textContent = "Done";
        nextBtn.onclick = function () {
            moveTask(task.id, "Done");
        };
    } else {
        nextBtn.style.display = "none";
    }

    taskDiv.appendChild(taskText);
    taskDiv.appendChild(nextBtn);
    taskDiv.appendChild(deleteBtn);

    return taskDiv;

}

function showTasks() {
    toDoTask.innerHTML = "";
    inProgressTask.innerHTML = "";
    doneTask.innerHTML = "";

    for(let i = 0; i < tasks.length; i++) {
        const task = tasks[i];
        if(task.isDeleted) {
            continue;
        }

        const taskEl = createTask(task);

        if(task.status === "New") {
            toDoTask.appendChild(taskEl);
        } else if(task.status === "In Progress") {
            inProgressTask.appendChild(taskEl);
        } else if(task.status === "Done") {
            doneTask.appendChild(taskEl);
        }
    }
}

function moveTask(id, newStatus) {
    for(let i = 0; i < tasks.length; i++) {
        if(tasks[i].id === id) {
            tasks[i].status = newStatus;
            break;
        }
    }
    saveTask();
    showTasks();
}

function deleteTask(id) {
    for(let i = 0; i < tasks.length; i++) {
        if(tasks[i].id === id) {
            tasks[i].isDeleted = true;
            break;
        }
    }
    saveTask();
    showTasks();
}

addTaskBtn.onclick = function () {
    const text = newTaskAdd.value;
    if(text) {
        addTask(text);
        newTaskAdd.value = "";
    }
}

clearStorageBtn.onclick = function() {
    localStorage.clear();
    tasks = [];
    showTasks();
};

showTasks();