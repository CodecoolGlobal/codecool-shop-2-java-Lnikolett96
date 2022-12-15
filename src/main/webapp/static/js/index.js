let categoryOptions = document.querySelector('#category-chooser');
let category;

categoryOptions.addEventListener('click', function(){
    category = this.value;
    hideOtherItems(category)
})

function hideOtherItems(category){
    let allproducts = document.querySelectorAll('.products');
    for (let i = 0; i < allproducts.length; i++) {
        if(category === "all"){
            allproducts[i].style.display = "";
            allproducts[i].classList.remove("hidden");
        } else {
            if (allproducts[i].id != category) {

                allproducts[i].style.display = "none";
                allproducts[i].classList.add("hidden");
            } else {
                allproducts[i].classList.remove("hidden");
                allproducts[i].style.display = "";
            }

        }

    }
}
