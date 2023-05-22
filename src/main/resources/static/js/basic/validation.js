async function checkEmailDuplicate() {
	let email = document.getElementById("register-email");

	if (!email.value) {
        alert("이메일을 입력해주세요.");
        return false;
    } else {
        fetch("/api/user/email/" + email.value + "/check")
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            console.log(data);
            if (!data) {
                alert("사용 가능한 이메일입니다.");
            } else {
                alert("이미 사용중인 이메일입니다.");
            }
        })
    }
}

async function checkNameDuplicate() {
	let name = document.getElementById("register-name");

	if (name.value != "") {
        fetch("/api/user/name/" + name.value + "/check")
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            console.log(data);
            if (!data) {
                alert("사용 가능한 이름입니다.");
            } else {
                alert("이미 사용중인 이름입니다.");
            }
        })
    } else {
        alert("이름을 입력해주세요.");
        return false;
    }
}
