async function userRegister() {
	let formData = new FormData(document.getElementById('adminUserRegister'));
	const roles = [];

	let emailConfim = document.getElementById('flexCheckEmailChecked');
	if (emailConfim.checked == true) {
		formData.set("emailConfim", "true");
	}

    let gender = document.getElementsByName("gender");
    if (formData.get("gender") == 'on') {
        if (gender[0].checked == true) {
            formData.set("gender", "MALE")
        } else if (gender[1].checked == true) {
            formData.set("gender", "FEMALE")
        }
	}

	if (formData.get("user") == 'on') {
		roles.push('ROLE_USER')
	}

	if (formData.get("admin") == 'on') {
		roles.push('ROLE_ADMIN')
	}

	if (formData.get("seller") == 'on') {
		roles.push('ROLE_SELLER')
	}
	//let json = JSON.stringify(object);
	//const plainFormData = Object.fromEntries(formData.entries());
	//formDataJsonString = JSON.stringify(plainFormData);

	let response = await fetch('/admin/user/register', {
		method: 'POST',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			password: formData.get("password"),
			passwordCheck: formData.get("passwordCheck"),
			name: formData.get("name"),
			email: formData.get("email"),
			address: formData.get("address"),
			gender: formData.get("gender"),
			birth: formData.get("birth"),
			emailConfim: formData.get("emailConfim"),
			roles: roles
		})
	});
	let result = await response.json();
	console.log(result);
    const test = document.getElementsByName("errorMessage");
    var i = 0;
    while (i < 7) {
        test[i].textContent = "";
        i++;
    }

    if (response.status == 201) {
        alert("사용자가 생성되었습니다");
        location.href = "/admin/users";
    }
    else {
        result.forEach(error => {
            const errMessage = document.getElementById(error.field + "Checked");
            if (error.field == "password") {
                if (error.code == "Pattern") {
                    const pass = document.getElementsByName("password");
                    if (pass[0].value != "") {
                        errMessage.textContent = error.defaultMessage;
                    }
                } else {
                    errMessage.textContent = error.defaultMessage;
                }
            }
            else if (error.field != null) {
                errMessage.textContent = error.defaultMessage;
            } else {
                const passwordCheck = document.getElementsByName("passwordCheck");
                if (passwordCheck[0].value != "") {
                    const errMessage = document.getElementById("passwordCheckChecked");
                    errMessage.textContent = error.defaultMessage;
                }
            }
        });
	}
}