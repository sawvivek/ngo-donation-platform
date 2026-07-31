import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import NgoDashboard from "./pages/NgoDashboard";
import DonorDashboard from "./pages/DonorDashboard";
import Needs from "./pages/Needs";
import Donate from "./pages/Donate";

function App() {
  return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/ngo" element={<NgoDashboard />} />
          <Route path="/donor" element={<DonorDashboard />} />
          <Route path="/needs" element={<Needs />} />
          <Route path="/donate" element={<Donate />} />
        </Routes>
      </BrowserRouter>
  );
}

export default App;