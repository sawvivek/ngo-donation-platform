import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/axios";

function DonorDashboard() {
    const [needs, setNeeds] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetchNeeds();
    }, []);

    const fetchNeeds = async () => {
        try {
            const token = localStorage.getItem("token");

            const response = await api.get("/needs", {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });

            setNeeds(response.data);
        } catch (error) {
            console.error(error);
            alert("Failed to load needs");
        }
    };

    const logout = () => {
        localStorage.clear();
        navigate("/");
    };

    return (
        <div style={{ padding: "20px" }}>
            <h1>Donor Dashboard</h1>

            <button onClick={logout}>Logout</button>

            <hr />

            {needs.length === 0 ? (
                <p>No needs available.</p>
            ) : (
                needs.map((need) => (
                    <div
                        key={need.id}
                        style={{
                            border: "1px solid black",
                            marginBottom: "15px",
                            padding: "10px",
                        }}
                    >
                        <h3>{need.title}</h3>

                        <p>Description: {need.description}</p>

                        <p>Target: {need.targetAmount}</p>

                        <p>Current: {need.currentAmount}</p>

                        <button
                            onClick={() =>
                                navigate("/donate", {
                                    state: {
                                        need,
                                    },
                                })
                            }
                        >
                            Donate
                        </button>
                    </div>
                ))
            )}
        </div>
    );
}

export default DonorDashboard;