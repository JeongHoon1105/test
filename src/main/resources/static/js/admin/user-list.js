const userList = document.getElementById('user-table');
fetch("/admin/user/all")
.then(response => response.json())
.then(data => {
    data.forEach(user => {
        const tr = document.createElement("tr");
        const checkIcon = document.createElement("i");
        const ROLES = {
            ROLE_USER: "일반",
            ROLE_SELLER: "프리랜서",
            ROLE_ADMIN: "관리자",
        };
        checkIcon.classList.add("bi", "bi-check-lg");
        
        tr.innerHTML = document.getElementById('hidden-table').innerHTML;
        tr.querySelector(".name").textContent = user.name;
        tr.querySelector(".email").textContent = user.email;
        tr.querySelector(".role").nextSibling.textContent = ROLES[user.roles] || "";
        tr.querySelector(".userinfo-edit").addEventListener('click', () => {
            window.location.href = `/admin/userinfo-edit/${user.id}`;
        });
        tr.querySelectorAll('td')[1].addEventListener('click', () => {
            window.location.href = `/admin/userinfo/${user.id}`;
        });
        tr.querySelectorAll('td')[2].addEventListener('click', () => {
            window.location.href = `/admin/userinfo/${user.id}`;
        });
        if (user.emailConfim === true) {
            tr.querySelectorAll('td')[3].appendChild(checkIcon);
        }
        userList.appendChild(tr);
    });
})
.catch(error => {
    console.error(error);
});