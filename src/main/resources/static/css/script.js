function isAuthenticated() {
    return sessionStorage.getItem("loggedIn") === "true";
}
function handleProtectedAction(actionUrl) {
    if (!isAuthenticated()) {
        showMessage("Login Required", "To use this feature, please log in first.");
        window.nextModalAction = function () {
            window.location.href = "/login";
        };
        return false;
    } else {
        window.location.href = actionUrl;
        return false;
    }
}

// After successful login, set "logged in" in sessionStorage
function setLoggedIn() {
    sessionStorage.setItem("loggedIn", "true");
    document.getElementById("loginModal").style.display = "none";
}

// Hook up handlers (run after DOM ready)
window.onload = function () {
    const postRideBtn = document.getElementById("postRideBtn");
    if (postRideBtn) postRideBtn.onclick = function() { handleProtectedAction("/post-ride"); };

    const findBuddyBtn = document.getElementById("findBuddyBtn");
    if (findBuddyBtn) findBuddyBtn.onclick = function() { handleProtectedAction("/find-buddy"); };

    const getStartedBtn = document.getElementById("getStartedBtn");
    if (getStartedBtn) getStartedBtn.onclick = function() { handleProtectedAction("/post-ride"); }; // Adjust if needed
};

function handleGetStarted() {
    if (!sessionStorage.getItem("loggedIn")) {
        showMessage("Login Required", "To use Get Started, please log in first.");
        window.nextModalAction = function () {
            window.location.href = "/login";
        };
        return false;
    } else {
        window.location.href = "/welcome";
         return false;
    }
}


// In handleLogin, after successful authentication from backend, call setLoggedIn():
function handleLogin(e) {
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value;
    if (!email || !password) {
        showMessage("Login Error", "Please enter both email and password.");
        e.preventDefault();
        return;
    }
   }


// Registration form validation (DOES NOT prevent backend storage)
function handleRegister(e) {
    const name = document.getElementById("regName").value.trim();
    const email = document.getElementById("regEmail").value.trim();
    const password = document.getElementById("regPassword").value;
    const confirm = document.getElementById("regConfirmPassword").value;

    if (!name || !email || !password || !confirm) {
        showMessage("Registration Error", "Please fill all required fields.");
        e.preventDefault();
        return;
    }
    if (password !== confirm) {
        showMessage("Password Mismatch", "Passwords do not match. Please try again.");
        e.preventDefault();
        return;
    }
}

// Login form validation (DOES NOT manage authentication)
function handleLogin(e) {
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value;
    if (!email || !password) {
        showMessage("Login Error", "Please enter both email and password.");
        e.preventDefault();
        return;
    }
}

function handleLinkClick(action) {

    var url = action === 'post' ? '/post_ride' : '/find_ride';
    if (!sessionStorage.getItem("loggedIn")) {
        showMessage("Login Required", "Please log in to use this feature.");
        window.nextModalAction = function () {
            window.location.href = "/login";
        };
        return false;
    } else {
        window.location.href = url;
        return false;
    }
}
