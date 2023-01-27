let form = document.getElementById('form')
form.addEventListener('submit', subbmitLogin)

async function subbmitLogin(e) {
    e.preventDefault()
    let email = document.getElementById('email').value
    let password = document.getElementById('password').value
    let userInfo = {email: email, password: password}
    const response = await fetch(`/login`,{
        method : "POST",
        headers: {"Content-type" : "application/json"},
        body: JSON.stringify(userInfo)
    })

    if (response.status != 200) {
        alert("Neigh")
    }
    window.location.href = '/'
}