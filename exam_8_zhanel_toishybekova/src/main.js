const newTaskAdd = document.getElementById("newTaskAdd");
const addTaskBtn = document.getElementById("addTaskBtn");
const toDoTask  = document.getElementById("toDoTask");
const inProgressTask = document.getElementById("inProgressTask");
const doneTask = document.getElementById("doneTask");
const clearStorage = document.getElementById("clearStorageBtn");

const tasks = localStorage.getItem("tasks") ? JSON.parse(localStorage.getItem("tasks")) : [];

function generateId() {
    return Math.floor(Math.random() * 100);
}