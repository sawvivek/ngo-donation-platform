import { useLocation, useNavigate } from "react-router-dom";
import { useState } from "react";
import api from "../api/axios";

function Donate() {
    const location = useLocation();
    const navigate = useNavigate();

    const need = location.state?.need;

    const [amount, setAmount] = useState("");

    if (!need) {
        return <h2>No Need Selected</h2>;
    }

    const handleDonate = async () => {
        try {
            const token = localStorage.getItem("token");

            // Change this if you store donorId differently
            const donorId = localStorage.getItem("donorId");

            const contribution = {
                type: "MONEY",
                amount: Number(amount),
                itemName: null,
                quantity: null,
                donor: {
                    id: donorId,
                },
                need: {
                    id: need.id,
                },
            };

            await api.post("/contributions", contribution, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });

            alert("Donation Successful!");
            navigate("/donor");
        } catch (err) {
            console.error(err);
            alert("Donation Failed");
        }
    };

    return (
        <div style={{ padding: "20px" }}>
            <h2>Donate</h2>

            <h3>{need.title}</h3>

            <p>{need.description}</p>

            <p>Target Amount: {need.targetAmount}</p>

            <p>Current Amount: {need.currentAmount}</p>

            <input
                type="number"
                placeholder="Enter Amount"
                value={amount}
                onChange={(e) => setAmount(e.target.value)}
            />

            <br />
            <br />

            <button onClick={handleDonate}>Donate</button>

            <button
                onClick={() => navigate("/donor")}
                style={{ marginLeft: "10px" }}
            >
                Cancel
            </button>
        </div>
    );
}

export default Donate;