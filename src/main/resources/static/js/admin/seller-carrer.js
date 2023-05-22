// [yoon] 경력 리스트
!async function() {
    let url = window.location.href;
    let id = url.split('/')[5];
    let response = await fetch("/api/seller/seller_c_q/" + id + "/all");
    let data = await response.json();

    if (response.ok && data.length > 0) {
        let carrerList = document.getElementById('carrerList');
        let carrerInfo = document.getElementById('carrerInfo');

        for (let i = 0; i < data.length; i++) {

            let div = carrerInfo.cloneNode(true);
            div.style.display = 'block';
             
            let carrerName = div.querySelector('.carrer-name');
            let carrerPosition = div.querySelector('.carrer-position');
            let carrerStartDate = div.querySelector('.carrer-startDate');
            let carrerFinishDate = div.querySelector('.carrer-finishDate');
            let carrerDetail = div.querySelector('.carrer-detail');
            let carrerId = div.querySelector('.carrer-id');

            if (data[i].name) carrerName.textContent = data[i].name;
            if (data[i].position) carrerPosition.textContent = data[i].position;
            if (data[i].startDate) carrerStartDate.textContent = new Date(data[i].startDate).toISOString().split('T')[0];
            if (data[i].finishDate) carrerFinishDate.textContent = new Date(data[i].finishDate).toISOString().split('T')[0];
            if (data[i].detail) carrerDetail.textContent = data[i].detail;
            if (data[i].comQualId) carrerId.textContent = data[i].comQualId;
           
            carrerList.appendChild(div);
            carrerList.style.display = 'block';
            
            if (i < data.length - 1) {
                let hr = document.createElement('hr');
                carrerList.appendChild(hr);
            }
        }
    } else {
        console.log("Error:", response.status);
    }
}();
// [yoon] 경력 편집/취소
function edit_carrer(obj) {
    
    let carrerId = obj.querySelector(".carrer-id").textContent;
    let carrerEdit = document.getElementById('carrerEdit');
    let div = carrerEdit.cloneNode(true);
    div.style.display = 'block';
  
    fetch("/admin/seller_c_q/" + carrerId)
    .then(response => response.json())
    .then(data => {

        const editName = div.querySelector('.carrer-name-edit');
        const editPosition = div.querySelector('.carrer-position-edit');
        const editStartDate = div.querySelector('.carrer-startDate-edit');
        const editFinishDate = div.querySelector('.carrer-finishDate-edit');
        const editDetail = div.querySelector('.carrer-detail-edit');

        if (data.name) editName.value = data.name;
        if (data.position) editPosition.value = data.position;
        if (data.startDate) editStartDate.value = new Date(data.startDate).toISOString().split('T')[0];
        if (data.finishDate) editFinishDate.value = new Date(data.finishDate).toISOString().split('T')[0];
        if (data.detail) editDetail.value = data.detail;
       
        obj.closest('.card-body').parentNode.style.display = 'none'; 
        obj.closest('.card-body').parentNode.insertAdjacentElement('afterend', div); 

        const carrerCancel = div.querySelector('.carrerCancel');
        carrerCancel.addEventListener("click", function() {
            obj.closest('.card-body').parentNode.style.display = 'block';
            div.remove();
        });
    })
    .catch(error => {
        console.error(error);
    });
}
// [yoon] 경력 추가버튼
function add_carrer(){
    var div = document.createElement('div');
    div.innerHTML = document.getElementById('careerAdd').innerHTML;
    document.getElementById('careerParent').appendChild(div);
    var btnCareerAdd = document.getElementById('btn-careerAdd');
    btnCareerAdd.style.display = "none";
}
// [yoon] 경력 추가취소버튼
function remove_career(obj){
   document.getElementById('careerParent').removeChild(obj.parentNode.parentNode.parentNode.parentNode);
   var btnCareerAdd = document.getElementById('btn-careerAdd');
   btnCareerAdd.style.display = "block";
}