let categoryOptions = document.querySelector('#category-chooser');
let category;
let products = document.querySelector("#products-by-category");

categoryOptions.addEventListener('click', function(){
    category = this.value;
    let contentBycategory = document.querySelectorAll('#' + category);
    hideOtherItems(category)
})

function productsBuilder(contents){
    for (let i = 0; i < contents.length; i++) {
        products.appendChild(contents[i]);
    }
}

function hideOtherItems(category){
    let allproducts = document.querySelectorAll('.products');
    for (let i = 0; i < allproducts.length; i++) {
        if (allproducts[i].id != category) {

            allproducts[i].style.display = "none";
            allproducts[i].classList.add("hidden");
        } else {
            allproducts[i].classList.remove("hidden");
            allproducts[i].style.display = "";
        }
    }
}
