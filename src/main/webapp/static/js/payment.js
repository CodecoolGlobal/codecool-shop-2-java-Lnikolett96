console.log("Hi!");

init();

function init() {
    add_event_handlers();
}

function add_event_handlers(){
    let credit_card_radio = document.querySelector("#cc_radio");
    let credit_card_inputs = document.querySelectorAll(".cc_data");
    let other_radio_buttons = document.querySelectorAll(".other_radio_button")

    credit_card_radio.addEventListener("change", (event) =>
        {
            credit_card_inputs.forEach( (element)=> element.removeAttribute("hidden"))
        });

    other_radio_buttons.forEach((radio)=> {
        radio.addEventListener("click", (event)=>
        {
            credit_card_inputs.forEach( (element)=> element.setAttribute("hidden", "true"))
        })
    });
}
