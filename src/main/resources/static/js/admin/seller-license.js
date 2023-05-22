// [yoon] 자격증 리스트
!async function() {
    let url = window.location.href;
    let id = url.split('/')[5];
    let response = await fetch("/api/seller/qualification/" + id + "/all");
    let data = await response.json();

    if (response.ok && data.length > 0) {
        let licenseList = document.getElementById('licenseList');
        let licenseInfo = document.getElementById('licenseInfo');

        for (let i = 0; i < data.length; i++) {

            let div = licenseInfo.cloneNode(true);
            div.style.display = 'block';
             
            let licenseName = div.querySelector('.license-name');
            let licenseDate = div.querySelector('.license-date');
            let licenseId = div.querySelector('.license-id');

            if (data[i].name) licenseName.textContent = data[i].name;
            if (data[i].date) licenseDate.textContent = new Date(data[i].date).toISOString().split('T')[0];
            if (data[i].qualId) licenseId.textContent = data[i].qualId;
           
            licenseList.appendChild(div);
            licenseList.style.display = 'block';
            
            if (i < data.length - 1) {
                let hr = document.createElement('hr');
                licenseList.appendChild(hr);
            }
        }
    } else {
        console.log("Error:", response.status);
    }
}();
// [yoon] 자격증 편집/취소
function edit_license(obj) {
    
    let licenseId = obj.querySelector(".license-id").textContent;
    let licenseEdit = document.getElementById('licenseEdit');
    let div = licenseEdit.cloneNode(true);
    div.style.display = 'block';

    fetch("/admin/seller_qualification/" + licenseId)
    .then(response => response.json())
    .then(data => {

        let editName = div.querySelector('.license-name-edit');
        let editDate = div.querySelector('.license-date-edit');

        if (data.name) editName.value = data.name;
        if (data.date) editDate.value = new Date(data.date).toISOString().split('T')[0];
       
        obj.closest('.card-body').parentNode.style.display = 'none'; 
        obj.closest('.card-body').parentNode.insertAdjacentElement('afterend', div); 

        const licenseCancel = div.querySelector('.licenseCancel');
        licenseCancel.addEventListener("click", function() {
            obj.closest('.card-body').parentNode.style.display = 'block';
            div.remove();
        });
    })
    .catch(error => {
        console.error(error);
    });
}
// [yoon] 자격증 추가버튼
function add_license(){
    var div = document.createElement('div');
    div.innerHTML = document.getElementById('licenseAdd').innerHTML;
    document.getElementById('licenseParent').appendChild(div);
    var btnLicenseAdd = document.getElementById('btn-licenseAdd');
    btnLicenseAdd.style.display = "none";
}
// [yoon] 자격증 추가취소버튼
function remove_license(obj){
   document.getElementById('licenseParent').removeChild(obj.parentNode.parentNode.parentNode.parentNode);
   var btnLicenseAdd = document.getElementById('btn-licenseAdd');
   btnLicenseAdd.style.display = "block";
}