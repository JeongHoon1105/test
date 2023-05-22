!async function() {
	let url = window.location.href;
	let id = url.split('/')[5];
	const response = await fetch("/admin/user/" + id);
	const data = await response.json();
	document.getElementById("birth").value = data.birth.substring(0, data.birth.indexOf('T'));
	if (data.phone === 'undefined') {
		document.getElementById("phone").value = "";
	}
	document.getElementById("username").innerText = data.name;
	if (data.imageThumb !== null) {
		document.getElementById("user-avatar").src = data.imageThumb;
	}
	document.getElementById("working-time").value = data.schedule;
	document.getElementById("user-self-info-link").href = '/admin/userprofile-edit/' + data.id;
	document.getElementById("register-email").value = data.email;
	document.getElementById("emailConfim").value = data.emailConfim;
	document.getElementById("address").value = data.address;
	if (data.gender === 'MALE') {
		document.getElementById("gender_male").checked = true;
	}
	if (data.gender === 'FEMALE') {
		document.getElementById("gender_female").checked = true;
	}
	data.roles.forEach((role) => {
		document.getElementById(role.toLowerCase()).checked = true;
	});
}();