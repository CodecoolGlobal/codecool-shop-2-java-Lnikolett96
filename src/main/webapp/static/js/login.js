function login(loginButton){
    loginButton.addEventListener('click', async () => {
        let email = document.getElementById('email').value
        let password = document.getElementById('password').value
        const response = await fetch(`/login`,{
                method : "POST",
                headers: {"Content-type" : "application/json"},
                body: JSON.stringify({email, password})
            })

        if (response.status != 200) {
            alert("Neigh")
        }
        return await response.json()
        })

}

function intiLogin() {
    login('login')
}

intiLogin();