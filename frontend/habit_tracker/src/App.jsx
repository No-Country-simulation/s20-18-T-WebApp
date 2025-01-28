import React from "react";
import { Routes, Route } from "react-router-dom";
import { UserProvider } from "./contexts/UsersContext";
import Layout from "./components/Layout";
//import GuestRoutes from "./routes/GuestRoutes";
import UserRoutes from "./routes/UserRoutes";

function App() {
  return (
    <UserProvider>      
        <Layout>
          <Routes>
            {/* Rutas de invitados 
            <Route path="/*" element={<GuestRoutes />} />*/}
            {/* Rutas para usuarios logueados */}
            <Route path="/*" element={<UserRoutes />} />
          </Routes>
        </Layout>      
    </UserProvider>
  );
}

export default App;
