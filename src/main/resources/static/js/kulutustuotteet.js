const hamburgerMenu = document.querySelector(".navbar-ham");
const hamburgerAside = document.querySelector(".ham-aside");
const hamburgerCover = document.querySelector(".ham-cover");

hamburgerMenu.addEventListener("click", () => {
  hamburgerMenu.classList.toggle("active");
  hamburgerCover.classList.toggle("active");
  hamburgerAside.classList.toggle("open");
});

hamburgerCover.addEventListener("click", () => {
    hamburgerMenu.classList.toggle("active");
    hamburgerCover.classList.toggle("active");
    hamburgerAside.classList.toggle("open");
})