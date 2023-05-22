// [yoon] 보유기술 리스트
!async function() {
    let url = window.location.href;
    let id = url.split('/')[5];
    let response = await fetch("/api/seller/skill/" + id + "/all");
    let data = await response.json();
    let year = "년";

    if (response.ok && data.length > 0) {
        let skillList = document.getElementById('skillList');
        let skillInfo = document.getElementById('skillInfo');

        for (let i = 0; i < data.length; i++) {

            let div = skillInfo.cloneNode(true);
            div.style.display = 'block';
             
            let skillTitle = div.querySelector('.skill-title');
            let skillExp = div.querySelector('.skill-experience');
            let skillLevel = div.querySelector('.skill-level');
            let skillDetail = div.querySelector('.skill-detail');
            let skillId = div.querySelector('.skill-id');

            if (data[i].title) skillTitle.textContent = data[i].title;
            if (data[i].experience) skillExp.textContent = data[i].experience + year;
            if (data[i].skillLevel) {
                switch (data[i].skillLevel) {
                    
                    case "BEGINNER":
                        skillLevel.classList.add('bg-info');
                        skillLevel.style.width = '0%';
                        skillLevel.setAttribute('aria-valuenow', '0');
                        skillLevel.textContent = data[i].skillLevel;
                        break;
                    case "SEMI_BEGINNER":
                        skillLevel.classList.add('bg-success');
                        skillLevel.style.width = '25%';
                        skillLevel.setAttribute('aria-valuenow', '25');
                        skillLevel.textContent = data[i].skillLevel;
                        break;
                    case "EXPERIENCE":
                        skillLevel.style.width = '50%';
                        skillLevel.setAttribute('aria-valuenow', '50');
                        skillLevel.textContent = data[i].skillLevel;
                        skillLevel.value = data[i].skillLevel;
                        break;
                    case "SEMI_EXPERT":
                        skillLevel.classList.add('bg-warning');
                        skillLevel.style.width = '75%';
                        skillLevel.setAttribute('aria-valuenow', '75');
                        skillLevel.textContent = data[i].skillLevel;
                        break;
                    case "EXPERT":
                        skillLevel.classList.add('bg-danger');
                        skillLevel.style.width = '100%';
                        skillLevel.setAttribute('aria-valuenow', '100');
                        skillLevel.textContent = data[i].skillLevel;
                        break;
                    default:
                        break;
                }
            }
            if (data[i].detail) skillDetail.textContent = data[i].detail;
            if (data[i].skillId) skillId.textContent = data[i].skillId;
           
            skillList.appendChild(div);
            skillList.style.display = 'block';
            
            if (i < data.length - 1) {
                let hr = document.createElement('hr');
                skillList.appendChild(hr);
            }
        }
    } else {
        console.log("Error:", response.status);
    }
}();
// [yoon] 보유기술 편집/취소
function edit_skill(obj) {
    
    let skillId = obj.querySelector(".skill-id").textContent;
    let skillEdit = document.getElementById('skillEdit');
    let div = skillEdit.cloneNode(true);
    div.style.display = 'block';
    
    fetch("/admin/seller_skill/" + skillId)
    .then(response => response.json())
    .then(data => {
 
        const editTitle = div.querySelector('.skill-title-edit');
        const editExp = div.querySelector('.skill-experience-edit');
        const editLevel = div.querySelector('.skill-level-edit');
        const editDetail = div.querySelector('.skill-detail-edit');

        if (data.title) editTitle.value = data.title;
        if (data.experience) setTextSelected(editExp.options, data.experience);
        if (data.skillLevel) {
            editLevel.value = {
                "BEGINNER": 1,
                "SEMI_BEGINNER": 2,
                "EXPERIENCE": 3,
                "SEMI_EXPERT": 4,
                "EXPERT": 5
            }[data.skillLevel];
        }
        if (data.detail) editDetail.textContent = data.detail;
        
        obj.closest('.card-body').parentNode.style.display = 'none'; 
        obj.closest('.card-body').parentNode.insertAdjacentElement('afterend', div); 
        
        const skillCancel = div.querySelector('.skillCancel');
        skillCancel.addEventListener("click", function() {
            obj.closest('.card-body').parentNode.style.display = 'block';
            div.remove();
        });
    })
    .catch(error => {
        console.error(error);
    });
 }
// [yoon] 보유기술 추가버튼
function add_skill(){
    var div = document.createElement('div');
    div.innerHTML = document.getElementById('skillAdd').innerHTML;
    document.getElementById('skillParent').appendChild(div);
    var btnSkillAdd = document.getElementById('btn-skillAdd');
    btnSkillAdd.style.display = "none";
}
// [yoon] 보유기술 추가취소버튼
function remove_skill(obj){
    document.getElementById('skillParent').removeChild(obj.parentNode.parentNode.parentNode.parentNode);
    var skillAdd = document.getElementById('btn-skillAdd');
    skillAdd.style.display = "block";
}