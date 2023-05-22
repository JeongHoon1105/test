// [yoon] 포트폴리오 추가/삭제
function add_portfolio(){
    var portfolioAdd = document.getElementById('portfolioAdd');
    var portfolioMain = document.getElementById("portfolioMain");
    var sellerPortfolio = document.getElementById("sellerPortfolio");
    sellerPortfolio.querySelector(".portPostBtn").textContent = "등록";
    portfolioAdd.style.display = "none";
    portfolioMain.style.display = "none";
    sellerPortfolio.style.display = "block";
}
function detail_portfolio(obj){
    let div = document.getElementById('sellerPortfolio');
    let portfolio_id = obj.parentNode.parentNode.querySelector(".portfolio_id").textContent;
    let categoryList = div.querySelector(".portCategory");
    fetch("/admin/seller_portfolio/" + portfolio_id)
    .then(response => response.json())
    .then(data => {
        for (i=0;i<categoryList.options.length;i++) {
            if (categoryList.options[i].value == data.categoryCode) {
                categoryList.options[i].selected = true;
            }
        }
        div.querySelector(".portTitle").value = data.title;
        div.querySelector(".portDetail").value = data.detail;
        div.querySelector(".portUpdate").value = data.upDate.substring(0, data.upDate.indexOf('T'));;
        div.querySelector(".portPostBtn").textContent = "수정";
    })
    .catch(error => {
        console.error(error);
    });

    var portfolioAdd = document.getElementById('portfolioAdd');
    var portfolioMain = document.getElementById("portfolioMain");
    var sellerPortfolio = document.getElementById("sellerPortfolio");
    portfolioAdd.style.display = "none";
    portfolioMain.style.display = "none";
    sellerPortfolio.style.display = "block";
}
function remove_portfolio(obj){
    var portfolioAdd = document.getElementById('portfolioAdd');
    var portfolioMain = document.getElementById("portfolioMain");
    var sellerPortfolio = document.getElementById("sellerPortfolio");

    if (sellerPortfolio.querySelector(".container-img")) {
        sellerPortfolio.querySelector(".portPreview").removeChild(sellerPortfolio.querySelector(".container-img"));
    }
    sellerPortfolio.querySelector(".portTitle").value = "";
    sellerPortfolio.querySelector(".portDetail").value = "";
    sellerPortfolio.querySelector(".portUpdate").value = "";
    sellerPortfolio.querySelector(".portPostBtn").textContent = "수정";

    portfolioAdd.style.display = "block";
    portfolioMain.style.display = "block";
    sellerPortfolio.style.display = "none";
}
//function searchCategoryList(obj) {
//    fetch("/api/category/all")
//    .then(response => response.json())
//    .then(data => {
//        data.forEach(category => {
//            let option = document.createElement("option");
//            option.value = category.code;
//            option.text = category.name;
//            obj.options.add(option);
//        });
//    })
//    .catch(error => {
//        console.error(error);
//    });
//}