document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("registrationForm");

    // Form submission
    form.addEventListener("submit", async function (e) {
        e.preventDefault();

        if (!validateForm()) {
            return;
        }

        try {
            const formData = new FormData(form);
            const response = await fetch("/api/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(Object.fromEntries(formData)),
            });

            if (response.ok) {
                window.location.href = "/login?registered=true";
            } else {
                const error = await response.json();
                showError(error.message);
            }
        } catch (error) {
            showError("Registration failed. Please try again.");
        }
    });
});


function validateForm() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    if (!username || !password) {
        showError("All fields are required");
        return false;
    }

    if (password.length < 8) {
        showError("Password must be at least 8 characters long");
        return false;
    }

    return true;
}

function showError(message) {
    const errorDiv = document.createElement("div");
    errorDiv.className = "alert alert-danger mt-3";
    errorDiv.textContent = message;

    const form = document.getElementById("registrationForm");
    form.insertAdjacentElement("beforebegin", errorDiv);

    setTimeout(() => errorDiv.remove(), 5000);
}
