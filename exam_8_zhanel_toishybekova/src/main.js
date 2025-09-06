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
}

clearStorageBtn.onclick = function() {
    localStorage.clear();
    tasks = [];
};