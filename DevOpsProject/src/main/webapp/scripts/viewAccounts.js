let userAccounts = document.getElementById("userAccounts");

window.addEventListener("load", listAllAccounts);

function listAllAccounts() {
	event.preventDefault();
	fetch('http://3.16.139.46:8088/DevOpsProject/ViewAccounts')
		.then(response => {
			return response.json();
		})
		.then (json => {
			for (let j in json) {
				let row = document.createElement("option");
				row.value = json[j].accountId;
				row.text = 'Account: ' + json[j].accountId + ", Balance: $" + json[j].balance.toFixed(2);
				
				userAccounts.appendChild(row);
			}
		})
		.catch(error => console.error(error));
}