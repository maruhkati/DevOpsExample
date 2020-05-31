let pendingAccounts = document.getElementById("pendingAccounts");

window.addEventListener("load", listPendingAccounts);

function listPendingAccounts() {
	event.preventDefault();
	fetch('http://3.16.139.46:8088/DevOpsProject/ViewPendingAccounts')
		.then(response => {
			return response.json();
		})
		.then (json => {
			for (let j in json) {
				let row = document.createElement("option");
				row.value = json[j].accountId;
				row.text = 'User: ' + json[j].userName + ', Account: ' + json[j].accountId + ", Balance: $" + json[j].balance.toFixed(2);
				
				pendingAccounts.appendChild(row);
			}
		})
		.catch(error => console.error(error));
}