!async function() {
	let url = window.location.href;
	let id = url.split('/')[5];
	const response = await fetch("/admin/user/"+id);
	const data = await response.json();
	console.log(data)
	document.getElementById("user-role").value = data.roles;
	document.getElementById("username").innerText = data.name;
	document.getElementById("user-email").value = data.email;
	document.getElementById("user-avatar").value = data.imageThumb;
	document.getElementById("user-profile-edit").href = '/admin/userprofile-edit/' + data.id;
	const ROLES = {
		ROLE_USER: "일반",
		ROLE_SELLER: "프리랜서",
		ROLE_ADMIN: "관리자",
	};
	const JOB_STATUS = {
		POSSIBLE: "가능",
		REVIEW: "내용에 따라",
		BUSYNESS: "바쁨",
	};
	document.getElementById("user-info-edit").href = '/admin/userinfo-edit/' + data.id;
	document.getElementById("user-intro-edit").href = '/admin/userprofile-edit/' + data.id;
	// document.getElementById("user-role").textContent = data.roles;
	document.getElementById("user-role").textContent = ROLES[data.roles] || "";
	document.getElementById("user-email").textContent = data.email;
	if (data.emailConfim === true) document.getElementById("user-emailcon").checked = true;
	document.getElementById("user-birth").textContent = new Date(data.birth).toISOString().split('T')[0];
	if (data.gender === 'MALE') document.getElementById("user-male").checked = true;
	if (data.gender === 'FEMALE') document.getElementById("user-female").checked = true;
	document.getElementById("user-address").textContent = data.address;
	document.getElementById("user-status").textContent = JOB_STATUS[data.jobStatus] || "";
	document.getElementById("user-schedule").textContent = data.schedule;
	document.getElementById("user-phrase").textContent = data.cphrase;
	document.getElementById("user-intro").textContent = data.selfIntro;
}();