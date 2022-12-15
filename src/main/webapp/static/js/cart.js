
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

function buttonSelector() {
    return document.querySelectorAll(`.btn.btn-success`)
}

function initPage(){
    addProductToCart(buttonSelector())
}

initPage()