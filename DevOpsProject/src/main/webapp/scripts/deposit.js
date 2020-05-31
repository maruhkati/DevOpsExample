window.addEventListener("onload", console.log("deposit function running"));

let submit = document.getElementById("depSubmit");

submit.addEventListener("click", deposit);

function deposit() {
    let amount = document.getElementById("depositAmount").value;

    fetch("http://3.16.139.46:8088/DevOpsProject/Deposit", {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=UTF-8",
            'Accept': 'application/json',
        },
        body: JSON.stringify({
            amount: amount
        }),
    })
    .then(response => response.text())
    .then(responseText => {
        document.getElementById("depositAmount").value = '';
        alert(responseText);
            location.reload();
    })
}
