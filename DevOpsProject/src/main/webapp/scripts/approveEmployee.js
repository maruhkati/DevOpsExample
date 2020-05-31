let empSubmit = document.getElementById("userSub");

empSubmit.addEventListener("click", approveEmployee);

function approveEmployee() {
	let user = document.getElementById("username").value;
	console.log(user);
	
	fetch("http://3.16.139.46:8088/DevOpsProject/ApproveEmployee", {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=UTF-8",
            'Accept': 'application/json',
        },
        body: JSON.stringify({
            'username': user
        }),
    })
    .then(response => response.text())
    .then(responseText => handleResponse(responseText))
}

function handleResponse(responseText) {
    document.getElementById("username").value = '';
    alert(responseText);
    location.reload();
}