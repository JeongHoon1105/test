// [yoon] 수상경력 리스트
!async function() {
    let url = window.location.href;
    let id = url.split('/')[5];
    let response = await fetch("/api/seller/seller_a_q/" + id + "/all");
    let data = await response.json();

    if (response.ok && data.length > 0) {
        let awardList = document.getElementById('awardList');
        let awardInfo = document.getElementById('awardInfo');
        const AWARDS_CATEGORY = {
            AWARDS: "수상",
            WRITING: "쓰기",
            LECTURE: "강연",
        };

        for (let i = 0; i < data.length; i++) {

            let div = awardInfo.cloneNode(true);
            div.style.display = 'block';
             
            let awardCategory = div.querySelector('.award-category');
            let awardTitle = div.querySelector('.award-title');
            let awardDate = div.querySelector('.award-date');
            let awardId = div.querySelector('.award-id');

            if (data[i].awardsCategory) awardCategory.textContent = AWARDS_CATEGORY[data[i].awardsCategory] || "";
            if (data[i].title) awardTitle.textContent = data[i].title;
            if (data[i].date) awardDate.textContent = new Date(data[i].date).toISOString().split('T')[0];
            if (data[i].awardsQualId) awardId.textContent = data[i].awardsQualId;
           
            awardList.appendChild(div);
            awardList.style.display = 'block';
            
            if (i < data.length - 1) {
                let hr = document.createElement('hr');
                awardList.appendChild(hr);
            }
        }
    } else {
        console.log("Error:", response.status);
    }
}();
// [yoon] 수상경력 편집/취소
function edit_award(obj) {
    
    let awardId = obj.querySelector(".award-id").textContent;
    let awardEdit = document.getElementById('awardEdit');
    let div = awardEdit.cloneNode(true);
    div.style.display = 'block';

    fetch("/admin/seller_a_q/" + awardId)
    .then(response => response.json())
    .then(data => {

        let editCategory = div.querySelector('.award-category-edit');
        let editTitle = div.querySelector('.award-title-edit');
        let editDate = div.querySelector('.award-date-edit');

        if (data.awardsCategory) setValueSelected(editCategory.options, data.awardsCategory);
        if (data.title) editTitle.value = data.title;
        if (data.date) editDate.value = new Date(data.date).toISOString().split('T')[0];
       
        obj.closest('.card-body').parentNode.style.display = 'none'; 
        obj.closest('.card-body').parentNode.insertAdjacentElement('afterend', div); 

        const awardCancel = div.querySelector('.awardCancel');
        awardCancel.addEventListener("click", function() {
            obj.closest('.card-body').parentNode.style.display = 'block';
            div.remove();
        });
    })
    .catch(error => {
        console.error(error);
    });
}
// [yoon] 수상경력 추가버튼
function add_award(){
    var div = document.createElement('div');
    div.innerHTML = document.getElementById('awardAdd').innerHTML;
    document.getElementById('awardParent').appendChild(div);
    var btnAwardAdd = document.getElementById('btn-awardAdd');
    btnAwardAdd.style.display = "none";
}
// [yoon] 수상경력 추가취소버튼
function remove_award(obj){
   document.getElementById('awardParent').removeChild(obj.parentNode.parentNode.parentNode.parentNode);
   var btnAwardAdd = document.getElementById('btn-awardAdd');
   btnAwardAdd.style.display = "block";
}
// [yoon] select 함수
function setValueSelected(options, value) {
    for (let i = 0; i < options.length; i++) {
        if (options[i].value === value) {
            options[i].selected = true;
            break;
        }
    }
}