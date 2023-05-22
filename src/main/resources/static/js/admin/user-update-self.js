async function userUpdateSelf() {
	let formData = new FormData();
	let f = new FormData(document.getElementById('userUpdateSelfInfo'));
	let url = window.location.href;
	let id = url.split('/')[5];
	const data = Object.fromEntries(f.entries());
	const targetData = makeSelfInfoData(data);

	formData.append("file", document.getElementById('user-upload-avatar').files[0]);
	formData.append("request", new Blob([JSON.stringify(targetData)], { type: "application/json" }));

	let response = await fetch('/admin/user/' + id + '/self', {
		method: 'PUT',
		body: formData
	});
	let result = await response.json();
	console.log(result);
	if (result.status == 201) {
		alert("사용자가 업데이트되었습니다");
	}
}

function makeSelfInfoData(data) {
	let job = null;
	if (data.jobStatus != null) {
		job = data.jobStatus.toUpperCase()
	}
	const makeData = {
		"username": data.username,
		"selfIntro": data.selfIntro,
		"shedule": data.shedule,
		"cPhrase": data.cPhrase,
		"jobStatus": job
	};
	return makeData;
}