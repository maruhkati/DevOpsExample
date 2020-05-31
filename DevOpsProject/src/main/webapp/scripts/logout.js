function logoutUser(event) {
    fetch('http://3.16.139.46:8088/DevOpsProject/app/Logout')
        .then(response => {
        	console.log(response)
        	window.location.href = response.url;
        })
        .catch(error => console.error(error));
}