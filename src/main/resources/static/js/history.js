const element = document.querySelector(".pagination ul");
const tableBody = document.getElementById("table-body");
let totalPages = 1;
let currentPage = 1;
let pageSize = 10;

function createPagination(totalPages, page) {
    let liTag = '';
    let active;
    let beforePage = page - 1;
    let afterPage = page + 1;

    if (page > 1) {
        liTag += `<li class="btn prev" onclick="fetchData(${page - 1})"><span><i class="fas fa-angle-left"></i> Prev</span></li>`;
    }

    if (totalPages <= 5) {
        for (let plength = 1; plength <= totalPages; plength++) {
            active = page == plength ? "active" : "";
            liTag += `<li class="numb ${active}" onclick="fetchData(${plength})"><span>${plength}</span></li>`;
        }
    } else {
        if (page > 2) {
            liTag += `<li class="first numb" onclick="fetchData(1)"><span>1</span></li>`;
            if (page > 3) {
                liTag += `<li class="dots"><span>...</span></li>`;
            }
        }

        if (page == totalPages) {
            beforePage -= 2;
        } else if (page == totalPages - 1) {
            beforePage -= 1;
        }

        if (page == 1) {
            afterPage += 2;
        } else if (page == 2) {
            afterPage += 1;
        }

        for (let plength = beforePage; plength <= afterPage; plength++) {
            if (plength > totalPages || plength < 1) continue;
            active = page == plength ? "active" : "";
            liTag += `<li class="numb ${active}" onclick="fetchData(${plength})"><span>${plength}</span></li>`;
        }

        if (page < totalPages - 1) {
            if (page < totalPages - 2) {
                liTag += `<li class="dots"><span>...</span></li>`;
            }
            liTag += `<li class="last numb" onclick="fetchData(${totalPages})"><span>${totalPages}</span></li>`;
        }
    }

    if (page < totalPages) {
        liTag += `<li class="btn next" onclick="fetchData(${page + 1})"><span>Next <i class="fas fa-angle-right"></i></span></li>`;
    }

    element.innerHTML = liTag;
}


function fetchData(page) {
    let startTime = document.getElementById("startTime").value;
    let endTime = document.getElementById("endTime").value;
    const device = document.getElementById("searchDevice").value;

    if (startTime != "") startTime = convertToISO(startTime);
    if (endTime != "") endTime = convertToISO(endTime);
    const url = `/action-history?page=${page - 1}&size=${pageSize}&device=${device}&start=${startTime}&end=${endTime}`;

    console.log(url);
    fetch(url)
        .then(response => response.json())
        .then(data => {
            totalPages = data.totalPages;
            currentPage = page;
            updateTable(data.content);
            createPagination(totalPages, currentPage);
        })
        .catch(error => console.error('Error fetching data:', error));
}

function updateTable(data) {
    tableBody.innerHTML = "";
    data.forEach(item => {
        const row = `<tr>
            <td>${item.id}</td>
            <td>${formatDateTime(item.dateTime)}</td>
            <td>${item.device}</td>
            <td>${item.action}</td>
        </tr>`;
        tableBody.innerHTML += row;
    });
}

fetchData(currentPage);

setInterval(() => {
    fetchData(currentPage);
}, 2000);

document.getElementById("pageSize").addEventListener("change", (event) => {
    pageSize = event.target.value;
    fetchData(currentPage);
});

function clearSearch() {
    document.getElementById('startTime').value = '';
    document.getElementById('endTime').value = '';
    document.getElementById('searchDevice').value = '';
}

function convertToISO(dateString) {
    const dateTimeRegex = /^(\d{2})\/(\d{2})\/(\d{4})(?: (\d{2}):(\d{2}):(\d{2}))?$/;
    const match = dateString.match(dateTimeRegex);

    if (!match) {
        throw new Error("Invalid string format");
    }

    const [, day, month, year, hour = "00", minute = "00", second = "00"] = match;
    console.log(`${year}-${month}-${day}T${hour}:${minute}:${second}`);
    return `${year}-${month}-${day}T${hour}:${minute}:${second}`;
}

function formatDateTime(dateTimeString) {
    const date = new Date(dateTimeString);

    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const year = date.getFullYear();

    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');

    return `${day}/${month}/${year} ${hours}:${minutes}:${seconds}`;
}