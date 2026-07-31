const role = response.data.role.toUpperCase();

localStorage.setItem("token", response.data.token);
localStorage.setItem("role", role);

if (role === "NGO") {
    navigate("/ngo");
} else {
    navigate("/donor");
}