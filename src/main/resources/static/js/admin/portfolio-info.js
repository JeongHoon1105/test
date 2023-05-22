!async function() {
    const navPortfolio = document.getElementById("nav-portfolio");
    let url = window.location.href;
    let id = url.split('/')[5];
    const response = await fetch("/api/seller/portfolio/" + id + "/all");
    const data = await response.json();
    console.log(data);
    const main_div = document.createElement("div");
    main_div.classList.add("card", "mb-4", "userinfo", "d-inline-block", "w-100");

    data.forEach(portfolio => {
        var div = document.createElement('div');
        div.innerHTML = document.getElementById('portfolioThumb').innerHTML;
        var url = "";
        if (portfolio.youtubeLink != null) {
            url = " https://img.youtube.com/vi/" + portfolio.youtubeLink.split('=')[1] + "/mqdefault.jpg";

        } else {
            url = portfolio.imageUrl;
        }
        div.querySelector(".thumb").src = url;
        div.querySelector(".portfolio_id").textContent = portfolio.portId;
        div.style.display = "block";
        main_div.appendChild(div);

    });
    document.getElementById('portfolioMain').appendChild(main_div);

    //[Dongchan] 카테고리 전체 조회
    const portSelect = navPortfolio.querySelector(".portCategory");

    fetch("/api/category/all")
    .then(response => response.json())
    .then(data => {
        data.forEach(category => {
            let option = document.createElement("option");
            option.value = category.code;
            option.text = category.name;
            portSelect.options.add(option);
        });
    })
    .catch(error => {
        console.error(error);
    });
}();
