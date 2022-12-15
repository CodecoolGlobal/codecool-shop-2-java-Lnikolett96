
function addProductToCart(addToCartBtns){
    addToCartBtns.forEach((button) => {
        button.addEventListener('click', async (e) => {
            const response = await fetch(`/cart-content?prodid=${e.currentTarget.dataset.prodId}`, {
                method:"POST"
            })
            if(response.status != 200){
                alert("NOPE")
            }
        })
    })
}

function increaseAmount(plusBtns, amount) {
    plusBtns.forEach((button) => {
        button.addEventListener('click', async (e) => {
            const response = await fetch(`/cart-content?prodid=${e.currentTarget.dataset.prodId}`, {
                method: "POST"
            })
        })
    })
}

function classSelector(className) {
    return document.querySelectorAll(className)
}

function initPage(){
    addProductToCart(classSelector(`.btn.btn-success`))
    increaseAmount(classSelector(`.btn.btn-primary`), classSelector(`.amount`))
}

initPage()