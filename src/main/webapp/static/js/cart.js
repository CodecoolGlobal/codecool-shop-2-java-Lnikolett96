function addProductToCart(addToCartBtns) {
    addToCartBtns.forEach((button) => {
        button.addEventListener('click', async (e) => {
            const response = await fetch(`/cart-content?prodid=${e.currentTarget.dataset.prodId}&method=add`, {
                method: "POST"
            })
            if (response.status != 200) {
                alert("NOPE")
            }
        })
    })
}

function increaseAmount(plusBtns) {
    plusBtns.forEach((button) => {
        button.addEventListener('click', async (e) => {
            const response = await fetch(`/cart-content?prodid=${e.currentTarget.dataset.prodId}&method=add`, {
                method: "POST"
            })
            const amount = document.querySelector(`.amount[data-prod-id="${button.dataset.prodId}"]`)
            amount.innerText = parseInt(amount.innerText) + 1;
            const price =document.querySelector(`.price[data-prod-id="${button.dataset.prodId}"]`)
            price.innerText = (parseFloat(price.innerText) + parseFloat(price.dataset.price)).toFixed(1) + " USD";
        })
    })
}

function decreaseAmount(minusBtn) {
    minusBtn.forEach((button) => {
        button.addEventListener('click', async (e) => {
            const response = await fetch(`/cart-content?prodid=${e.currentTarget.dataset.prodId}&method=remove`, {
                method:"POST"
                })
            const amount = document.querySelector(`.amount[data-prod-id="${button.dataset.prodId}"]`)
            amount.innerText = parseInt(amount.innerText) - 1;

            // erre kérdezz rá !!!!!!
            // if (amount.innerText === 0) {
            //     const tableRow = document.querySelector(`.table-tab[data-prod-id="${button.dataset.prodId}"]`)
            //     tableRow.replaceChildren();
            // }
            const price =document.querySelector(`.price[data-prod-id="${button.dataset.prodId}"]`)
            price.innerText = (parseFloat(price.innerText) - parseFloat(price.dataset.price)).toFixed(1) + " USD";
        })
    })

}

function buttonSelector(className) {
    return document.querySelectorAll(className)
}

function initPage() {
    addProductToCart(buttonSelector(`.btn.btn-success`))
    increaseAmount(buttonSelector(`.btn.btn-primary`))
    decreaseAmount(buttonSelector(`.btn.btn-danger`))
}

initPage()