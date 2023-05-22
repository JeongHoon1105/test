!async function() {
	let url = window.location.href;
	let id = url.split('/')[5];
	const response = await fetch("/admin/user/" + id);
	const data = await response.json();
	;
	if (data.imageThumb != null) {
		document.getElementById("user-profile-avatar").src = data.imageThumb;
		document.getElementById("user-avatar").src = data.imageThumb;
	}
	document.getElementById("register-name").value = data.name;
	document.getElementById("username").value = data.name;
	if (data.roles.includes("ROLE_SELLER")) {
		document.getElementById("cPhrase").value = data.cphrase;
		document.getElementById("selfIntro").value = data.selfIntro;
		document.getElementById("username").innerText = data.name;
		if (data.shedule != null) {
			document.getElementByClassName("work-time").forEach(element => {
				element.value = data.shedule;
			});
		}
		if (data.jobStatus != null) {
			Array.from(document.getElementsByClassName("job-status")).forEach(element => {
				element.value = data.jobStatus.toLowerCase();
			});
		}
	}
}();