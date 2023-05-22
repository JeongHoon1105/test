async function userUpdate() {
	let url = window.location.href;
	let id = url.split('/')[5];
	let formData = new FormData(document.getElementById('updateUserBasicInfo'));
	const roles = [];

	let emailConfim = document.getElementById('emailConfim');
	if (emailConfim.checked == true) {
		formData.set("emailConfim", "true");
	}

	if (formData.get("gender_male") == 'on') {
		formData.set("gender", "MALE")
	}

	if (formData.get("gender_female") == 'on') {
		formData.set("gender", "FEMALE")
	}

	if (formData.get("role_user") == 'on') {
		roles.push('ROLE_USER')
	}

	if (formData.get("role_admin") == 'on') {
		roles.push('ROLE_ADMIN')
	}

	if (formData.get("role_seller") == 'on') {
		roles.push('ROLE_SELLER')
	}
	//let json = JSON.stringify(object);
	//const plainFormData = Object.fromEntries(formData.entries());
	//formDataJsonString = JSON.stringify(plainFormData);

	let response = await fetch('/admin/user/' + id + '/basic', {
		method: 'PUT',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			password: formData.get("password"),
			passwordCheck: formData.get("passwordCheck"),
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
	if (result.status == 200) {
		alert("사용자가 업데이트되었습니다");
	}
}