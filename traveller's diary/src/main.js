const countryInput = document.getElementById("country");
const dateInput = document.getElementById("date");
const messageInput = document.getElementById("message");
const addPostBtn = document.getElementById("addPostBtn");
const postsContainer = document.getElementById("postsContainer");

let posts = localStorage.getItem("posts") ? JSON.parse(localStorage.getItem("posts")) : [];

function generateId() {
    return Math.floor(Math.random() * 1000000);
}

function savePosts() {
    localStorage.setItem("posts", JSON.stringify(posts));
}

function addPost(country, date, message) {
    const newPost = {
        id: generateId(),
        country: country,
        date: date,
        message: message,
        isDeleted: false
    };

    posts.push(newPost);
    savePosts();
    showPosts();
}

function createPost(post) {
    const postDiv = document.createElement("div");
    postDiv.classList.add("post");
    postDiv.id = "post_" + post.id;

    const info = document.createElement("p");
    info.innerHTML = `<strong>${post.date}</strong> — ${post.country}`;

    const msg = document.createElement("p");
    msg.textContent = post.message;

    const deleteBtn = document.createElement("button");
    deleteBtn.textContent = "Х";
    deleteBtn.style.backgroundColor = "yellow";
    deleteBtn.onclick = function () {
        deletePost(post.id);
    };

    postDiv.appendChild(info);
    postDiv.appendChild(msg);
    postDiv.appendChild(deleteBtn);

    return postDiv;
}

function showPosts() {
    postsContainer.innerHTML = "";

    for (let i = 0; i < posts.length; i++) {
        const post = posts[i];
        if (post.isDeleted) {
            continue;
        }

        const postEl = createPost(post);
        postsContainer.appendChild(postEl);
    }
}

function deletePost(id) {
        for (let i = 0; i < posts.length; i++) {
            if (posts[i].id === id) {
                posts[i].isDeleted = true;
                break;
            }
        }
        savePosts();
        showPosts();
}

addPostBtn.onclick = function () {
    const country = countryInput.value;
    const date = dateInput.value;
    const message = messageInput.value;

    if (country && date && message) {
        addPost(country, date, message);
        countryInput.value = "";
        dateInput.value = "";
        messageInput.value = "";
    } else {
        alert("Please enter all values!");
    }
};

showPosts();
