const hamBurger = document.querySelector(".toggle-btn");

hamBurger.addEventListener("click", function () {
  const sidebar = document.querySelector("#sidebar");
  sidebar.classList.toggle("expand");

  if (sidebar.classList.contains("expand")) {
    localStorage.setItem("sidebarState", "expanded");
  } else {
    localStorage.setItem("sidebarState", "collapsed");
  }
});

document.addEventListener("DOMContentLoaded", function () {
  const sidebar = document.querySelector("#sidebar");

  const sidebarState = localStorage.getItem("sidebarState");

  if (sidebarState === "expanded") {
    sidebar.classList.add("expand");
  } else {
    sidebar.classList.remove("expand");
  }
});
