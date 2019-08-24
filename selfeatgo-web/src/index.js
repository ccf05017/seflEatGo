(async () => {
    // data 원장 URL
    const url = "http://localhost:8080/restaurants";

    // data 객체 (restaurants)
    const element = document.getElementById("app");
    const response = await fetch(url);
    const restaurants = await response.json();

    // rendering 될 내용
    const contents = `
        ${restaurants.map(restaurant => `
            <p>
                ${restaurant.id}
                ${restaurant.name}
                ${restaurant.address}
            </p>
        `
        ).join("")}`; // map의 기본 join이 ',' 이기 때문에 시안성을 위해 수정

    element.innerHTML = contents;
})();