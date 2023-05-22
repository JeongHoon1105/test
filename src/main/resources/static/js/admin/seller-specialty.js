// [yoon] 전문분야 리스트
!async function() {
    let url = window.location.href;
    let id = url.split('/')[5];
    let response = await fetch("/api/seller/speciality/" + id + "/all");
    let data = await response.json();
    let monetaryUnit = "원";

    if (response.ok && data.length > 0) {
        let specialtyList = document.getElementById('specialtyList');
        let specialtyInfo = document.getElementById('specialtyInfo');

        for (let i = 0; i < data.length; i++) {

            let div = specialtyInfo.cloneNode(true);
            div.style.display = 'block';
             
            let specialtyCate = div.querySelector('.specialty-category');
            let specialtyDesc = div.querySelector('.specialty-description');
            let specialtyPrice = div.querySelector('.specialty-minPrice');
            let specialtyDetail = div.querySelector('.specialty-detail');
            let specialtyId = div.querySelector('.specialty-id');

            if (data[i].categoryName) specialtyCate.textContent = data[i].categoryName;
            if (data[i].description) specialtyDesc.textContent = data[i].description;
            if (data[i].minPrice) specialtyPrice.textContent = data[i].minPrice + monetaryUnit;
            if (data[i].detail) specialtyDetail.textContent = data[i].detail;
            if (data[i].sepcId) specialtyId.textContent = data[i].sepcId;
           
            specialtyList.appendChild(div);
            specialtyList.style.display = 'block';
            
            if (i < data.length - 1) {
                let hr = document.createElement('hr');
                specialtyList.appendChild(hr);
            }
        }
    } else {
        console.log("Error:", response.status);
    }
}();
// [yoon] 전문분야 편집/취소
function edit_specialty(obj) {
    
    let specialtyId = obj.querySelector(".specialty-id").textContent;
    let specialtyEdit = document.getElementById('specialtyEdit');
    let div = specialtyEdit.cloneNode(true);
    div.style.display = 'block';
  
    fetch("/admin/seller_spec/" + specialtyId)
    .then(response => response.json())
    .then(data => {

        const editCategory = div.querySelector('.specialty-category-edit');
        const editDescription = div.querySelector('.specialty-description-edit');
        const editMinPrice = div.querySelector('.specialty-minPrice-edit');
        const editDetail = div.querySelector('.specialty-detail-edit');

        if (data.categoryName) setTextSelected(editCategory.options, data.categoryName);
        if (data.description) editDescription.value = data.description;
        if (data.minPrice) setTextSelected(editMinPrice.options, data.minPrice);
        if (data.detail) editDetail.value = data.detail;
       
        obj.closest('.card-body').parentNode.style.display = 'none'; 
        obj.closest('.card-body').parentNode.insertAdjacentElement('afterend', div); 

        const specialtyCancel = div.querySelector('.specialtyCancel');
        specialtyCancel.addEventListener("click", function() {
            obj.closest('.card-body').parentNode.style.display = 'block';
            div.remove();
        });
    })
    .catch(error => {
        console.error(error);
    });
}
// [yoon] 전문분야 추가버튼
function add_specialty(){
    var div = document.createElement('div');
    div.innerHTML = document.getElementById('specialtyAdd').innerHTML;
    document.getElementById('specialtyParent').appendChild(div);
    var btnSpecialtyAdd = document.getElementById('btn-specialtyAdd');
    btnSpecialtyAdd.style.display = "none";
}
// [yoon] 전문분야 추가취소버튼
function remove_specialty(obj){
   document.getElementById('specialtyParent').removeChild(obj.parentNode.parentNode.parentNode.parentNode);
   var btnSpecialtyAdd = document.getElementById('btn-specialtyAdd');
   btnSpecialtyAdd.style.display = "block";
}
// [yoon] select 함수
function setTextSelected(options, value) {
    for (let i = 0; i < options.length; i++) {
        if (options[i].textContent === value) {
            options[i].selected = true;
            break;
        }
    }
}