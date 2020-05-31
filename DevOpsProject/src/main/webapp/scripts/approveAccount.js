let approve = document.getElementById("approveSub");

approve.addEventListener("click", approveAccount);

function approveAccount() {
	let accountId = document.getElementById("pendingAccounts").value;
	
	fetch('http://3.16.139.46:8088/DevOpsProject/ApproveAccount', {
		method: 'POST',
		headers: {
            "Content-Type": "application/json; charset=UTF-8",
            'Accept': 'application/json',
        },
        body: JSON.stringify({
        	'accountId': accountId
        })
	})
	.then(response => response.text())
	.then(responseText => {
		alert(responseText);
		location.reload();
	})
}