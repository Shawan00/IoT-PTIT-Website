function updateLabel(checkbox, ledId) {
    const label = checkbox.nextElementSibling;
    const status = checkbox.checked;
    label.textContent = status ? "On" : "Off";
    const action = status ? "on" : "off";
    localStorage.setItem(ledId, action);

    const url = `/control?ledId=${ledId}&action=${action}`;
    console.log(url);
    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        }
    })
    .then(response => {
        if (response.ok) {
            console.log(`Successfully sent action for ${ledId}: ${status ? "on" : "off"}`);
        } else {
            console.error("Failed to send action");
        }
    })
    .catch(error => console.error("Error:", error));
}

function loadStates() {
    const devices = ['led1', 'led2', 'led3'];

    devices.forEach(ledId => {
        const state = localStorage.getItem(ledId);
        const checkbox = document.getElementById(`${ledId}SwitchCheckDefault`);

        if (state === "on") {
            checkbox.checked = true;
            checkbox.nextElementSibling.textContent = 'On';
        } else {
            checkbox.checked = false;
            checkbox.nextElementSibling.textContent = 'Off';
        }
    });
}

window.onload = loadStates;