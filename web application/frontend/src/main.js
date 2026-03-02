async function getPosts() {
  try {
    const response = await fetch("http://localhost:8080/api/v1/posts", { method: "GET" });
    return await response.json();
  } catch (error) {
    const container = document.getElementById("posts");
    container.innerHTML = "<p style='color: red;'>Не удалось загрузить данные</p>";
  }
}
async function getComments(postId) {
  try {
    const response = await fetch(`http://localhost:8080/api/v1/posts/${postId}/comments`, { method: "GET" });
    return await response.json();
  } catch (error) {
    alert("Не удалось загрузить комментарии");
  }
}

async function renderPosts() {
  const container = document.getElementById("posts");
  const posts = await getPosts();
  container.innerHTML = "";

  for (const post of posts) {
    const postCard = document.createElement("div");
    postCard.classList.add("post-card");

    const title = document.createElement("h3");
    title.textContent = post.topic;

    const text = document.createElement("p");
    text.textContent = post.post;

    const button = document.createElement("button");
    button.textContent = "Комментарии";
    button.classList.add("show-comments");

    const commentsContainer = document.createElement("div");
    commentsContainer.classList.add("comments");

    button.onclick = async function() {
      if (commentsContainer.innerHTML !== "") {
        commentsContainer.innerHTML = "";
      } else {
        if (commentsContainer.innerHTML === "") {
          const comments = await getComments(post.postId);
          for (let i = 0; i < comments.length; i++) {
            const text = document.createElement("p");
            text.textContent = comments[i].comment;
            commentsContainer.appendChild(text);

            if (i < comments.length - 1) {
              const divider = document.createElement("hr");
              divider.style.border = "1px solid grey";
              commentsContainer.appendChild(divider);
            }
          }
        }
      }
    };

    postCard.appendChild(title);
    postCard.appendChild(text);
    postCard.appendChild(button);
    postCard.appendChild(commentsContainer);

    container.appendChild(postCard);
  }
}

renderPosts();
